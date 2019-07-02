import {sendAjax} from "/javascript/utility.js";

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
        answerElements[i].removeEventListener("click", highlightChosen)
    }
    event.target.classList.remove("not-clicked");
    event.target.classList.add("clicked");

}

function handleAnswerChoosing() {
    let answerElements = document.querySelectorAll("div[class^=answer]");

    for (let i=0; i<4; i++) {
        answerElements[i].addEventListener("click", highlightChosen)
    }
}

loadQuestionWithAnswers();
handleAnswerChoosing();