let submitButton = document.getElementById("submitQuestion");

async function pushQuestion(question) {
  try {
    const endpoint = "http://localhost:8080/api/questions";
    const token = localStorage.getItem("token");
    const response = await fetch(endpoint, {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(question),
    });

    if (!response.ok) {
      throw new Error("You must be a verified instructor.");
    }
  } catch (error) {
    console.log(error.message);
    return "error";
  }

  console.log("question added successfully");
  window.location.href = "../instructor.html";
}

submitButton.addEventListener("click", (event) => {
  event.preventDefault();
  let description = document.getElementById("description").value;
  let explanation = document.getElementById("explanation").value;
  let options = [];
  for (let i = 1; i <= 4; i++) {
    let option = document.getElementById(`option-${i}`).value;
    if (!option) {
      console.log(`option-${i} missing.`);
      return "error";
    }
    options.push(option);
  }
  let answerInput = document.getElementById("answer").value;

  if (!answerInput || !description || !explanation) {
    console.log("required fields missing.");
    return "error";
  }

  let answer = +answerInput;
  if (!answer) {
    console.log("answer should be  1-4");
    return "error";
  }

  const question = {
    description,
    options,
    answer,
    explanation,
  };
  console.log(question);
  pushQuestion(question);
});

let logOutButton = document.getElementById("logOut");
logOutButton.addEventListener("click", () => {
  window.localStorage.removeItem("token");
  window.location.href = "../index.html";
});
