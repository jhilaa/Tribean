chrome.runtime.onMessage.addListener(function (request, sender, sendResponse) {
    if (request.type === "submitData") {
        console.log("submitData ================")
    }

    if (request.type === "submitCredentials") {
        console.log("submitData ================")
    }
})
