import {sendAjax, sleep} from "/javascript/utility.js";
import {showDisplay, hideDisplay, highlightChosen} from "/javascript/visuals.js";

function loadQuestionWithAnswers() {
    let questionElement = document.querySelector(".question");
    let answerElements = document.querySelectorAll("div[class^=answer]");

    sendAjax("http://localhost:8762/game", "GET", "", () => {
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

function returnToGame() {
    sleep(1000).then(() => {
        loadQuestionWithAnswers();
    });

    sleep(10000).then(() => {
        let gameDisplay = document.querySelector('#game-display');
        showDisplay(gameDisplay);

        let source = document.querySelector("source");
        hideDisplay(source.parentNode);
        source.parentNode.pause();
        source.parentNode.removeChild(source);

        handleAnswerChoosing();
    })
}

function addButtonInteractions() {
    let answerElements = document.querySelectorAll("div[class^=answer]");
    let questionElement = document.querySelector(".question");
    for (let i=0; i<4; i++) {
        answerElements[i].removeEventListener("click", addButtonInteractions);
    }

    highlightChosen();
    sendAjax("http://localhost:8762/game",
        "POST",
        `{"selectedAnswer": "${event.target.textContent}", "question": "${questionElement.dataset.text}"}`,
        () => { //service works
            let json = JSON.parse(event.target.response);
            if (json.correctAnswer) {
                refreshContent("reward");
                waitForSelectReward(json.surprises).then(resultSrc => showReward(resultSrc));
            }
            else {
                let punishment = json.surprises[0];
                showAdvertisement(punishment);
                returnToGame();
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

function handleChoose(event) {
    const selectedImg = event.target;
    selectedImg.classList.toggle("selected");
}

function addRewardEventHandlers() {
    document.querySelector("#choose-cat").addEventListener("click", handleChoose);
    document.querySelector("#choose-funnyImg").addEventListener("click", handleChoose);
}

function showReward(rewardSrc) {
    const selectedImg = document.querySelector("img.selected");
    const origSrc = selectedImg.getAttribute("src");
    const otherImg = Array.from(document.querySelectorAll("img")).filter(img => !img.classList.contains("selected"))[0];
    hideDisplay(otherImg);
    sleep(1000).then(() => {
        selectedImg.classList.add("reveal");
        selectedImg.setAttribute("src", rewardSrc);
        selectedImg.setAttribute("title", "Click to continue with next question");
        selectedImg.addEventListener("click", function listenerForLoadNextQuestion() {
            loadNextQuestion(selectedImg, otherImg, origSrc, listenerForLoadNextQuestion);
        })
    });
}

function loadNextQuestion(selectedImg, otherImg, origSrc, eventListener) {
    let gameDisplay = document.querySelector('#game-display');
    let rewardDisplay = document.querySelector('#reward-display');
    initGame();
    refreshContent("game");
    sleep(1000).then(() => {
        resetRewardDisplay(selectedImg, otherImg, origSrc, eventListener)
    });
}

function resetRewardDisplay(selectedImg, otherImg, origSrc, eventListener){
    selectedImg.classList.remove("reveal", "selected");
    selectedImg.removeAttribute("title");
    selectedImg.setAttribute("src", origSrc);
    selectedImg.removeEventListener("click", eventListener);
    otherImg.style.display = "initial";
    otherImg.classList.remove("fade-out");
}


function waitForSelectReward(rewards) {
    return new Promise(resolve => {
        let resolved = false;
        setTimeout(() => {
            let selectors = document.querySelectorAll("img");
            for (const selector of selectors) {
                if(selector.classList.contains("selected")) {
                    const type = selector.id.split("-")[1];
                    const src = rewards.filter(reward => reward.surpriseType === type).map(reward => reward.src)[0];
                    resolve(src);
                    resolved = true;
                }
            }
            if(!resolved) waitForSelectReward(rewards).then(result => resolve(result));
        }, 500);
    })
}

function initGame() {
    document.body.dataset.status = "game";
    addRewardEventHandlers();
    loadQuestionWithAnswers();
    handleAnswerChoosing();
}

initGame();

