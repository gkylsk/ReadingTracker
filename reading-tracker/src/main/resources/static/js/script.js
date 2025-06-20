document.addEventListener("DOMContentLoaded", function () {
    // LOGIN FORM
    const loginForm = document.getElementById("login");
    if (loginForm) {
        loginForm.addEventListener("submit", async function (e) {
            e.preventDefault();
            const username = loginForm.querySelector("input[name='username']").value;
            const password = loginForm.querySelector("input[name='password']").value;

            try {
                const response = await fetch("/api/login", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ username, password })
                });

                if (response.ok) {
                    const data = await response.json();
                    localStorage.setItem("token", data.token); // save JWT token
                    window.location.href = "/api/home"; // redirect after login
                } else {
                    alert("Login failed. Check your credentials.");
                }
            } catch (err) {
                console.error("Login error:", err);
                alert("Something went wrong.");
            }
        });
    }

    // REGISTER FORM
    const registerForm = document.getElementById("register");
    if (registerForm) {
        registerForm.addEventListener("submit", async function (e) {
            e.preventDefault();
            const username = registerForm.querySelector("input[name='username']").value;
            const password = registerForm.querySelector("input[name='password']").value;

            try {
                const response = await fetch("/api/register", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ username, password })
                });

                if (response.ok) {
                    alert("Registration successful! Please login.");
                    window.location.href = "/api/login";
                } else {
                    alert("Registration failed.");
                }
            } catch (err) {
                console.error("Register error:", err);
                alert("Something went wrong.");
            }
        });
    }
});
