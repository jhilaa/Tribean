const AUTH_TOKEN_KEY = 'jhi-authenticationToken';

document.addEventListener("DOMContentLoaded", function () {
    const submitButton = document.getElementById("submitButton");
    submitButton.addEventListener("click", function () {
        const title = document.getElementById("titleInput").value;
        const description = document.getElementById("descriptionInput").value;
        console.log("1. title -----------");
        console.log(title);
        console.log("localStorage.getItem(AUTH_TOKEN_KEY)");
        console.log(localStorage.getItem(AUTH_TOKEN_KEY));
        chrome.runtime.sendMessage({type: "submitData", title: title, description: description});
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const submitCredentials = document.getElementById("submitCredentials");
    submitCredentials.addEventListener("click", function () {
        const email = document.getElementById("emailInput").value;
        const password = document.getElementById("passwordInput").value;
        console.log("submitCredentials -----------");
        console.log(email);
        //chrome.runtime.sendMessage({type: "submitCredentials", email: email, password: password});
        axios.post("http://localhost:8080/authenticate", {
            email: email,
            password: password
        }).then(response => {
            const bearerToken = response?.headers?.authorization;
            if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
                const jwt = bearerToken.slice(7, bearerToken.length);
                //sessionStorage.setItem(AUTH_TOKEN_KEY, jwt)
                localStorage.setItem(AUTH_TOKEN_KEY, jwt) //persistant
                // redirection
            }
        }).catch(() => {
            //To catch
            console.log("-- catch -----------")
        })
    });

    //-------------------------------------
    var submit
    const submitButton = document.getElementById("submitButton");
    submitButton.addEventListener("click", function () {
        const title = document.getElementById("titleInput").value;
        const description = document.getElementById("descriptionInput").value;
        console.log("submitButton -----------");
        console.log(title);
        //chrome.runtime.sendMessage({type: "submitCredentials", email: email, password: password});
        axios.post("http://localhost:8080/resources", {
                title: title,
                description: description
            },
            {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem(AUTH_TOKEN_KEY)}`
                }
            })
            .then(response => {
                console.log(" nouvelle ressource créée");
            }).catch(() => {
            //To catch
            console.log("-- catch -----------")
        })
    });
})


