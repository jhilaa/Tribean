console.log ("start");
const AUTH_TOKEN_KEY = 'jhi-authenticationToken';
if (localStorage.getItem(AUTH_TOKEN_KEY)) {
    window.location = "./resource.html";
} else {
    window.location = "./login.html";
}





