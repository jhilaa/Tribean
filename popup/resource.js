let AUTH_TOKEN_KEY = 'jhi-authenticationToken';
let description = "";

document.addEventListener("DOMContentLoaded", function () {
        const resourceContainer = document.getElementById("resourceContainer");
        const titleInput = document.getElementById("titleInput");
        const descriptionInput = document.getElementById("descriptionInput");
        //
        const submitResourceButton = document.getElementById("submitResourceButton");
        const cancelResourceButton = document.getElementById("cancelResourceButton");
        const logoutButton = document.getElementById("logoutButton");

        //-- logout
        logoutButton.addEventListener("click", function () {
            localStorage.removeItem(AUTH_TOKEN_KEY);
            window.close();
        })

        //-- envoi des données saisies
        submitResourceButton.addEventListener("click", function () {
            const title = titleInput.value;
            const description = descriptionInput.value;
            postData("http://localhost:8080/resources",
                {title: title, description: description},
                {
                    "Content-Type": "application/json",
                    'Authorization': `Bearer ${localStorage.getItem(AUTH_TOKEN_KEY)}`
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
    cancelResourceButton.addEventListener("click", function () {
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



