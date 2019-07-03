import {sendAjax, sleep} from "/javascript/utility.js";
import {showDisplay, hideDisplay, highlightChosen} from "/javascript/visuals.js";

function loadQuestionWithAnswers() {
    let questionElement = document.querySelector(".question");
    let answerElements = document.querySelectorAll("div[class^=answer]");

    sendAjax("http://localhost:60050/game", "GET", "", () => {
        let json = JSON.parse(event.target.response);
        questionElement.innerHTML = json.question;
        questionElement.dataset.text=json.question;
        for (let i=0; i<4; i++) {
            answerElements[i].innerHTML = json.answers[i];
        }
    },
    () => {
        questionElement.textContent = "Here should have been loaded a question..";
        for (let i=0; i<4; i++) {
            answerElements[i].textContent = "This should be an answer";
        }
    });
}

/**
 * Responsible for showing the game interface or the reward interface.
 */
function refreshContent(status) {
    let gameDisplay = document.querySelector('#game-display');
    let rewardDisplay = document.querySelector('#reward-display');

    if (status === "reward") {
        hideDisplay(gameDisplay);
        showDisplay(rewardDisplay);
    } else {
        hideDisplay(rewardDisplay);
        showDisplay(gameDisplay);
    }
}

function showAdvertisement(punishment) {
    let video = document.querySelector("video");
    let source = document.createElement('source');

    showDisplay(video);
    source.setAttribute('src', punishment.src);
    video.appendChild(source);
    video.play();

    let gameDisplay = document.querySelector('#game-display');
    hideDisplay(gameDisplay);
}

function addButtonInteractions() {
    let answerElements = document.querySelectorAll("div[class^=answer]");
    let questionElement = document.querySelector(".question");
    for (let i=0; i<4; i++) {
        answerElements[i].removeEventListener("click", addButtonInteractions);
    }

    highlightChosen();
    document.body.dataset.status = (document.body.dataset.status == "game") ? "reward" : "game";
    sendAjax("http://localhost:60050/game",
        "POST",
        `{"selectedAnswer": "${event.target.textContent}", "question": "${questionElement.dataset.text}"}`,
        () => { //service works
            let json = JSON.parse(event.target.response);
            if (json.correctAnswer) {
                refreshContent(document.body.dataset.status);
            }
            else {
                let punishment = json.surprises[0];
                showAdvertisement(punishment);
                sleep(1000).then(() => {loadQuestionWithAnswers();});

                sleep(2000).then(() => {
                    let gameDisplay = document.querySelector('#game-display');
                    showDisplay(gameDisplay);

                    let source = document.querySelector("source");
                    hideDisplay(source.parentNode);
                    source.parentNode.removeChild(source);

                    handleAnswerChoosing();
                })
            }
        },
        () => { //service does not work
            alert("The game server is down.. I'm really sorry.")
        });
}

function handleAnswerChoosing() {
    let answerElements = document.querySelectorAll("div[class^=answer]");

    for (let i=0; i<4; i++) {
        answerElements[i].addEventListener("click", addButtonInteractions);
        answerElements[i].classList.remove("clicked");
        answerElements[i].classList.remove("not-clicked");
    }
}

loadQuestionWithAnswers();
handleAnswerChoosing();