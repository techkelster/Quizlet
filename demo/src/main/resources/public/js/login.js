function getTokenInfo(token) {
  try {
    const tokenPayload = JSON.parse(atob(token.split(".")[1]));
    if (tokenPayload.exp && tokenPayload.role) {
      const expirationDate = new Date(tokenPayload.exp * 1000);
      const role = tokenPayload.role;
      return [expirationDate, role];
    }
  } catch (error) {
    console.log("Error parsing token:", error);
  }
  return [null, null];
}

const token = localStorage.getItem("token");
if (token) {
  const [expirationDate, role] = getTokenInfo(token);
  if (expirationDate && expirationDate > new Date()) {
    if (role === "STUDENT") {
      window.location.href = "../student.html";
    } else {
      window.location.href = "../instructor.html";
    }
  }
}

const button = document.getElementById("submit");
button.addEventListener("click", login);

// Login function
async function login(event) {
  event.preventDefault();

  const userId = document.getElementById("userId").value;
  const password = document.getElementById("password").value;
  const role = document.querySelector('input[name="role"]:checked').value;

  try {
    const endPoint = "http://localhost:8080/api/auth/login";
    const response = await fetch(endPoint, {
      method: "POST",
      body: JSON.stringify({
        userId,
        password,
        role,
      }),
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
    });

    const data = await response.json();
    console.log(response.status);
    if (response.status === 200) {
      localStorage.setItem("token", data.token);

      if (role === "STUDENT") {
        window.location.href = "../student.html";
      } else {
        window.location.href = "../instructor.html";
      }
    }
  } catch (error) {
    if (
      error instanceof TypeError &&
      error.message.includes("Network request failed")
    ) {
      console.log("Network error. Please check your internet connection.");
    } else {
      const errorMessage = error.message || "An unknown error occurred.";
      console.log(`Please carefully enter your credentials.`);
    }
    window.location.href = "../index.html";
  }
}
