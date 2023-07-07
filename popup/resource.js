let AUTH_TOKEN_KEY = 'jhi-authenticationToken';
let description = "";




document.addEventListener("DOMContentLoaded", function () {
        const loginContainer = document.getElementById("loginContainer");
        const loginButton = document.getElementById("loginButton");
        const resourceContainer = document.getElementById("resourceContainer");
        const submitResourceButton = document.getElementById("submitResourceButton");
        const logoutButton = document.getElementById("logoutButton");
        //
        const titleInput = document.getElementById("titleInput");
        const descriptionInput = document.getElementById("descriptionInput");


        // gestion des cas où l'utilisateur est connecté ou non
        if (localStorage.getItem(AUTH_TOKEN_KEY)) {
            show(resourceContainer);
            hide(loginContainer)
            show(logoutButton)
        } else {
            hide(resourceContainer);
            show(loginContainer);
            hide(logoutButton);
        }

        //-- logout
        logoutButton.addEventListener("click", function () {
            localStorage.removeItem(AUTH_TOKEN_KEY);
            window.close();
        })

        //-- login
        loginButton.addEventListener("click", function () {
            const email = document.getElementById("emailInput").value;
            const password = document.getElementById("passwordInput").value;
            postData("http://localhost:8080/authenticate",
                {email: email, password: password},
                {"Content-Type": "application/json"}
            ).then(response => {
                const bearerToken = response?.headers?.authorization;
                if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
                    const jwt = bearerToken.slice(7, bearerToken.length);
                    //sessionStorage.setItem(AUTH_TOKEN_KEY, jwt)
                    localStorage.setItem(AUTH_TOKEN_KEY, jwt) //persistant
                    hide(loginContainer);
                    show(resourceContainer);
                    show(logout);
                }
            })
        });

//-- envoi des données saisies
        submitResourceButton.addEventListener("click", function () {
            const title = document.getElementById("titleInput").value;
            const description = document.getElementById("descriptionInput").value;
            postData("http://localhost:8080/resources",
                {title: title, description: description},
                {
                    "Content-Type": "application/json",
                    //'Authorization': `Bearer ${localStorage.getItem(AUTH_TOKEN_KEY)}`
                    // 'Content-Type': 'application/x-www-form-urlencoded',
                }).then(response => {
                console.log("response")
            })
                .catch(response => {
                        console.log("catch")
                    }
                )
        })

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
            return response.json(); // parses JSON response into native JavaScript objects
        }


    }
)
;



