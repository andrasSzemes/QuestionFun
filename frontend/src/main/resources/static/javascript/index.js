import {sendAjax, sleep} from "/javascript/utility.js";

function loadQuestionWithAnswers() {
    let questionElement = document.querySelector(".question");
    let answerElements = document.querySelectorAll("div[class^=answer]");

    sendAjax("/game", "GET", "", () => {
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
function showContent(status) {
    let gameDisplay = "initial";
    let rewardDisplay = "none";
    if (status == "reward") {
        gameDisplay = "none";
        rewardDisplay = "initial";
    }
    sleep(1500).then(() => {
        let gameElements = document.querySelectorAll('[data-type="game"]');
        for (let i=0; i<gameElements.length; i++) {
            gameElements[i].style.display=gameDisplay;
        }

        let rewardElements = document.querySelectorAll('[data-type="reward"]');
        for (let i=0; i<rewardElements.length; i++) {
            rewardElements[i].style.display=rewardDisplay;
        }
    })
}

function addButtonInteractions() {
    let answerElements = document.querySelectorAll("div[class^=answer]");
    for (let i=0; i<4; i++) {
        answerElements[i].removeEventListener("click", addButtonInteractions);
    }

    highlightChosen();
    document.body.dataset.status = (document.body.dataset.status == "game") ? "reward" : "game";
    sendAjax("/game",
        "POST",
        '{"selectedAnswer": "'+event.target.textContent+'"}',
        () => { //service works
            let json = JSON.parse(event.target.response);
            if (json.correctAnswer) {
                showContent(document.body.dataset.status);
            }
            else {
            }
        },
        () => { //service does not work

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