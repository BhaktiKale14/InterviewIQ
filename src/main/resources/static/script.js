
const API_BASE = "/api/v1/interview";

const questionList = document.getElementById("question-list");
const questionCount = document.getElementById("question-count");
const selectedQuestion = document.getElementById("selected-question");
const selectedLevel = document.getElementById("selected-level");
const answerText = document.getElementById("answer-text");
const evaluateButton = document.getElementById("evaluate-btn");
const statusMessage = document.getElementById("status-message");
const charCount = document.getElementById("char-count");
const results = document.getElementById("results");

let questions = [];
let currentQuestion = null;

async function loadQuestions() {
    try {
        const response = await fetch(`${API_BASE}/questions`);

        if (!response.ok) {
            throw new Error("Could not load interview questions.");
        }

        questions = await response.json();
        questionCount.textContent = `${questions.length} questions`;

        questionList.replaceChildren();

        questions.forEach((question, index) => {
            const button = document.createElement("button");
            button.type = "button";
            button.className = "question-item";

            const number = document.createElement("span");
            number.className = "question-number";
            number.textContent = `QUESTION ${index + 1}`;

            const title = document.createElement("span");
            title.className = "question-title";
            title.textContent = question.questionText;

           const meta = document.createElement("span");
            meta.className = "question-meta";
            meta.textContent =
             `${question.jobRole || "Interview"} · ${question.difficulty || "General"}`;

            button.append(number, title, meta);
            button.addEventListener("click", () => selectQuestion(question, button));

            questionList.appendChild(button);
        });

        if (questions.length > 0) {
            selectQuestion(
                questions[0],
                questionList.querySelector(".question-item")
            );
        } else {
            questionList.textContent = "No questions are available yet.";
        }
    } catch (error) {
        questionList.textContent =
            "Unable to load questions. Check that your Spring Boot server is running.";
        questionCount.textContent = "Unavailable";
    }
}

function selectQuestion(question, button) {
    currentQuestion = question;

    document.querySelectorAll(".question-item").forEach(item => {
        item.classList.remove("active");
    });

    button.classList.add("active");
    selectedQuestion.textContent = question.questionText;
    selectedLevel.textContent =
    `${question.jobRole || "Interview"} · ${question.difficulty || "General"}`;
    evaluateButton.disabled = false;
    statusMessage.textContent = "";
    results.classList.add("hidden");
}

answerText.addEventListener("input", () => {
    charCount.textContent = `${answerText.value.length} characters`;
});

evaluateButton.addEventListener("click", evaluateAnswer);

async function evaluateAnswer() {
    if (!currentQuestion) {
        statusMessage.textContent = "Please select a question first.";
        return;
    }

    const answer = answerText.value.trim();

    if (!answer) {
        statusMessage.textContent = "Please write your answer first.";
        answerText.focus();
        return;
    }

    evaluateButton.disabled = true;
    evaluateButton.textContent = "Evaluating...";
    statusMessage.textContent = "AI is reviewing your answer...";
    results.classList.add("hidden");

    try {
        const response = await fetch(`${API_BASE}/evaluate`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                questionId: currentQuestion.id,
                answerText: answer
            })
        });

        if (!response.ok) {
            const errorBody = await response.json().catch(() => ({}));
            throw new Error(
                errorBody.message || "Evaluation failed. Please try again."
            );
        }

        const evaluation = await response.json();
        displayResults(evaluation);

        statusMessage.textContent = "Evaluation completed successfully!";
        results.classList.remove("hidden");
        results.scrollIntoView({ behavior: "smooth", block: "start" });
    } catch (error) {
        statusMessage.textContent = error.message ||
            "Something went wrong. Please try again.";
    } finally {
        evaluateButton.disabled = false;
        evaluateButton.textContent = "Evaluate Answer →";
    }
}

function displayResults(data) {
    document.getElementById("technical-score").textContent =
        data.technicalScore ?? 0;

    document.getElementById("communication-score").textContent =
        data.communicationScore ?? 0;

    document.getElementById("completeness-score").textContent =
        data.completenessScore ?? 0;

    document.getElementById("overall-score").textContent =
        data.overallScore ?? 0;

    displayList("strengths", data.strengths);
    displayList("weaknesses", data.weaknesses);
    displayList("suggestions", data.suggestions);
}

function displayList(elementId, items) {
    const list = document.getElementById(elementId);
    list.replaceChildren();

    const safeItems = Array.isArray(items) ? items : [];

    safeItems.forEach(item => {
        const li = document.createElement("li");
        li.textContent = item;
        list.appendChild(li);
    });

    if (safeItems.length === 0) {
        const li = document.createElement("li");
        li.textContent = "No feedback available.";
        list.appendChild(li);
    }
}

loadQuestions();
