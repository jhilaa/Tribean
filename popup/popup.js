console.log ("start");
if (localStorage.getItem(AUTH_TOKEN_KEY)) {
    window.location = "./resource.html";
} else {
    window.location = "./login.html";
}





