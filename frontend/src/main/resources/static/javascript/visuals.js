import {sleep} from "/javascript/utility.js";

export function showDisplay(display) {
    if (display.classList.contains("fade-out")) {
        display.classList.replace("fade-out", "fade-in")
    } else {
        display.classList.add("fade-in");
    }
    sleep(1000).then(() => display.style.display = "initial");
}

export function hideDisplay(display) {
    if (display.classList.contains("fade-in")) {
        display.classList.replace("fade-in", "fade-out")
    } else {
        display.classList.add("fade-out");
    }
    sleep(1000).then(() => display.style.display = "none");
}

/**
 * Highlight the chosen answer and removes the hover effect from other options.
 */
export function highlightChosen() {
    let answerElements = document.querySelectorAll("div[class^=answer]");

    for (let i=0; i<4; i++) {
        answerElements[i].classList.add("not-clicked");
    }
    event.target.classList.remove("not-clicked");
    event.target.classList.add("clicked");

}