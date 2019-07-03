import {sendAjax, sleep} from "/javascript/utility.js";

function loadQuestionWithAnswers() {
    let questionElement = document.querySelector(".question");
    let answerElements = document.querySelectorAll("div[class^=answer]");

    sendAjax("http://localhost:60050/game", "GET", "", () => {
        let json = JSON.parse(event.target.response);
        questionElement.textContent = json.question;
        for (let i=0; i<4; i++) {
            answerElements[i].textContent = json.answers[i];
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
 * Highlight the chosen answer and removes the hover effect from other options.
 */
function highlightChosen() {
    let answerElements = document.querySelectorAll("div[class^=answer]");

    for (let i=0; i<4; i++) {
        answerElements[i].classList.add("not-clicked");
    }
    event.target.classList.remove("not-clicked");
    event.target.classList.add("clicked");

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

function showDisplay(display) {
    if (display.classList.contains("fade-out")) {
        display.classList.replace("fade-out", "fade-in")
    } else {
        display.classList.add("fade-in");
    }
    sleep(1000).then(() => display.style.display = "initial");
}

function hideDisplay(display) {
    if (display.classList.contains("fade-in")) {
        display.classList.replace("fade-in", "fade-out")
    } else {
        display.classList.add("fade-out");
    }
    sleep(1000).then(() => display.style.display = "none");
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
        '{"selectedAnswer": "'+event.target.textContent+'", "question": "'+questionElement.textContent+'"}',
        () => { //service works
            let json = JSON.parse(event.target.response);
            if (json.correctAnswer) {
                refreshContent(document.body.dataset.status);
            }
            else {
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
    }
}

loadQuestionWithAnswers();
handleAnswerChoosing();