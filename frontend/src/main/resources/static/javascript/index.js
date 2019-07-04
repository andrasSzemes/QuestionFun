import {sendAjax, sleep} from "/javascript/utility.js";
import {showDisplay, hideDisplay, highlightChosen} from "/javascript/visuals.js";

function loadQuestionWithAnswers() {
    let questionElement = document.querySelector(".question");
    let answerElements = document.querySelectorAll("div[class^=answer]");

    sendAjax("http://localhost:60050/game", "GET", "", () => {
        let json = JSON.parse(event.target.response);
        questionElement.innerHTML = json.question;
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

function addButtonInteractions() {
    let answerElements = document.querySelectorAll("div[class^=answer]");
    let questionElement = document.querySelector(".question");
    for (let i=0; i<4; i++) {
        answerElements[i].removeEventListener("click", addButtonInteractions);
    }

    highlightChosen();
    document.body.dataset.status = (document.body.dataset.status == "game") ? "reward" : "game";
    refreshContent(document.body.dataset.status);
    waitForSelectReward([
        {
            type: "img",
            src: "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg",
            surpriseType: "reward"
        },
        {
            type: "img",
            src: "data:image/jpeg;base64,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",
            surpriseType: "cat"
        }
    ]).then(resultSrc => showReward(resultSrc));
    /*sendAjax("http://localhost:60050/game",
        "POST",
        `{"selectedAnswer": "${event.target.textContent}", "question": "${questionElement.textContent}"}`,
        () => { //service works
            let json = JSON.parse(event.target.response);
            if (json.correctAnswer) {
                refreshContent(document.body.dataset.status);
                waitForChoose(json.surprises)
            }
            else {
            }
        },
        () => { //service does not work
            alert("The game server is down.. I'm really sorry.")
        });*/
}

function handleAnswerChoosing() {
    let answerElements = document.querySelectorAll("div[class^=answer]");

    for (let i=0; i<4; i++) {
        answerElements[i].addEventListener("click", addButtonInteractions);
    }
}

function handleChoose(event) {
    const selectedImg = event.target;
    selectedImg.classList.toggle("selected");
}

function addRewardEventHandlers() {
    document.querySelector("#choose-cat").addEventListener("click", handleChoose);
    document.querySelector("#choose-reward").addEventListener("click", handleChoose);
}

function showReward(rewardSrc) {
    const selectedImg = document.querySelector("img.selected");
    const otherImg = Array.from(document.querySelectorAll("img")).filter(img => !img.classList.contains("selected"))[0];
    hideDisplay(otherImg);
    selectedImg.setAttribute("src", rewardSrc);
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

addRewardEventHandlers();
loadQuestionWithAnswers();
handleAnswerChoosing();