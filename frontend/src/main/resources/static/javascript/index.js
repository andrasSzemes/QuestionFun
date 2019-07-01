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

loadQuestionWithAnswers();
