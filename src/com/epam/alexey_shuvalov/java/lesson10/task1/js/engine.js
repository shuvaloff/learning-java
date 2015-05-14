function getUserList() {
// returns list of users
    if (localStorage.getItem("userList") === null) {
        console.log('Tere is nothing here!');
    } else {
        return JSON.parse(localStorage.getItem("userList"));
    }
}

function addToUserList(user) {
// adds user to the list of users
    if (localStorage.getItem("userList") === null) {
        localStorage.setItem("userList", "[" + JSON.stringify(user) + "]");
    } else {
        var currentUL = JSON.parse(localStorage.getItem("userList"));
        currentUL.push(user);
        localStorage.setItem("userList", JSON.stringify(currentUL));
    }
}

function removeFromUserList(usr) {
// removes user from the list of users
    if (localStorage.getItem("userList") === null) {
        console.log('Tere is nothing here!');
    } else {
        var currentUL = JSON.parse(localStorage.getItem("userList"));
        var newCurrentUL = currentUL.filter(function (i) {
            return i !== usr;
        });
        localStorage.setItem("userList", JSON.stringify(newCurrentUL));
    }
}

function setCurrentUser(usr) {
// create a 'cookie' in sessionStorage
    sessionStorage.setItem("userName", JSON.stringify(usr));
}

function getCurrentUser() {
// returns a value of 'cookie' from sessionStorage
    if ((sessionStorage.getItem("userName") !== null) || (sessionStorage.getItem("userName") !== "")) {
        return JSON.parse(sessionStorage.getItem("userName"));
    }
}

function isActive(usr) {
// checks if specified user is already in the chat
    if (localStorage.getItem("userList") === null) {
        console.log('Tere is nothing here!');
    } else {
        var currentUL = JSON.parse(localStorage.getItem("userList"));
        for (var i = currentUL.length - 1; i >= 0; i--) {
            if (currentUL[i] === usr) {
                return true;
            }
        }
        return false;
    }
}

function isAdmin(username) {
// pretty bugged version of determining if specified user is an admin or not
    if (username.indexOf("@epam.com") !== -1) {
        return true;
    } else {
        return false;
    }
}

function addMessage(t, u, m) {
// adds a message into localStorage
    var msg = {timestamp:t, user:u, message:m};
    var currentChatHistory = JSON.parse(localStorage.getItem("newChatHistory"));
    currentChatHistory.push(msg);
    localStorage.setItem("newChatHistory", JSON.stringify(currentChatHistory));
}

function newSampleHistory() {
// test history
    var currentHistory = [
        {timestamp:"2015-05-10 20:50:21", user:"Dummy", message:"Hey, guys! :)"},
        {timestamp:"2015-05-10 20:50:22", user:"Yummy", message:"Hi there, Dummy! ;)"},
        {timestamp:"2015-05-10 20:50:23", user:"Bummy", message:"Shut up, everyone! :("},
        {timestamp:"2015-05-10 20:50:24", user:"", message:"Bunny has been disconnected."}
    ];
    localStorage.setItem("newChatHistory", JSON.stringify(currentHistory));
}

function debug(usr) {
// TO BE DELETED
    for (var i = usr.length - 1; i >= 0; i--) {
        console.log("[" + i + "] " + usr[i]);
    }
}