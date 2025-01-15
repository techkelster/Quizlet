let button = document.getElementById("submit");
button.addEventListener("click", signup);

async function signup(event) {
  event.preventDefault(); // Prevent default form submission

  let roleButtons = document.getElementsByName("role");
  let role = "STUDENT";
  for (let i = 0; i < roleButtons.length; i++) {
    if (roleButtons[i].checked) {
      role = roleButtons[i].value;
    }
  }
  let username = document.getElementById("username").value;
  let email = document.getElementById("email").value;
  let password = document.getElementById("password").value;

  try {
    const endPoint = "http://localhost:8080/api/users/signup";
    const response = await fetch(endPoint, {
      method: "POST",
      body: JSON.stringify({
        email,
        password,
        role,
        username,
      }),
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
    });

    const data = await response.json();

    if (response.status === 201) {
      localStorage.setItem("token", data.token);
      window.location.href = "../index.html";
    }
  } catch (error) {
    if (
      error instanceof TypeError &&
      error.message.includes("Network request failed")
    ) {
      console.log("Network error. Please check your internet connection.");
    } else {
      const errorMessage = error.message || "An unknown error occurred.";
      console.log(`Error: ${errorMessage}`);
    }
    window.location.href = "../signup.html";
  }
}
