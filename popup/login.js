const AUTH_TOKEN_KEY = 'jhi-authenticationToken';

document.addEventListener("DOMContentLoaded", function () {
        const loginButton = document.getElementById("loginButton");
        const logoutButton = document.getElementById("logoutButton");
        const cancelButton = document.getElementById("logoutButton");

        //-- logout
        logoutButton.addEventListener("click", function () {
            localStorage.removeItem(AUTH_TOKEN_KEY);
            window.close();
        })

        //-- login
        loginButton.addEventListener("click", function () {
            let bearerToken = "";
            const email = document.getElementById("emailInput").value;
            const password = document.getElementById("passwordInput").value;
            postData("http://localhost:8080/authenticate",
                {email: email, password: password},
                {"Content-Type": "application/json"}
            ).then(response => {4
                // recherche du jwt token dans les headers de la réponse
                for (var pair of response.headers.entries()) { // accessing the entries
                    if (pair[0] === 'authorization') { // key I'm looking for in this instance
                        bearerToken = pair[1] // saving that value where I can use it
                //const bearerToken = response?.headers?.authorization;
                if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
                    const jwt = bearerToken.slice(7, bearerToken.length);
                    //sessionStorage.setItem(AUTH_TOKEN_KEY, jwt)
                    localStorage.setItem(AUTH_TOKEN_KEY, jwt) //persistant
                    console.log (jwt);
                    window.location = "./resource.html";
                }
            }}})
                .catch(error => {console.log (error);})
        });

//-- fermeture de la popup
        cancelButton.addEventListener("click", function () {
            window.close();
        })

        async function postData(url = "", data = {}, headers = {}) {
            // Default options are marked with *
            const response = await fetch(url, {
                method: "POST", // *GET, POST, PUT, DELETE, etc.
                mode: "cors", // no-cors, *cors, same-origin
                cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
                credentials: "same-origin", // include, *same-origin, omit
                headers: headers,
                redirect: "follow", // manual, *follow, error
                referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
                body: JSON.stringify(data), // body data type must match "Content-Type" header
            });
            return response; // parses JSON response into native JavaScript objects
        }
    }
)
;



