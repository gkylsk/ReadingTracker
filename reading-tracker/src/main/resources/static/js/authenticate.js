document.addEventListener("DOMContentLoaded", function () {
	      const token = localStorage.getItem("token");

	      if (!token) {
	        alert("No token found. Please login.");
	        window.location.href = "/api/login";
	        return;
	      }

	      fetch("/api/home", {
	        headers: { Authorization: "Bearer " + token }
	      })
	      .then(res => {
	        if (!res.ok) throw new Error("Unauthorized");
	        return res.text();
	      })
	      .then(data => {
	        const dataDiv = document.getElementById("data");
	        if (dataDiv) {
	          dataDiv.innerText = data;
	        }
	      })
	      .catch(() => {
	        alert("Access denied. Login again.");
	        localStorage.removeItem("token");
	        window.location.href = "/api/login";
	      });
	    });