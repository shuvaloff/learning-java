function checkAuth() { 
// authorization logic; "prevents" unauthorized access to the chat.html
    var usr = getCurrentUser();
    if ((usr === null) || (usr === "")) {
        alert("You are not logged in.");
        location.href = 'index.html';
    } else {
        if (localStorage.getItem("newChatHistory") === null) {
            newSampleHistory();
            loadMessages(loadNewHistory());
        } else {
            loadMessages(loadNewHistory());
        }
        if (!isActive(usr)) {
            addToUserList(usr);
            joinedChat(usr);
        }
        loadUserListUI();
        loadMessages(loadNewHistory());
        setCharLimit(document.getElementById('inputline'));
    }
}

function getDateTime() { 
// returns date and time as a string in a YYYY-MM-DD HH-MM-SS format
    var date = new Date();
    function pad(n) {
        return n < 10 ? '0' + n : n;
    }
    return date.getFullYear() + '-'
            + pad(date.getMonth() + 1) + '-'
            + pad(date.getDate()) + " "
            + pad(date.getHours()) + ":"
            + pad(date.getMinutes()) + ":"
            + pad(date.getSeconds());
}

function loadUserListUI() {
// renders list of users into UI
    var UL = getUserList();
    document.getElementById('system').innerHTML = getCurrentUser();
    document.getElementById('active_users').innerHTML = "<li>Dummy</li><li>Yummy</li>";
    for (var i = UL.length - 1; i >= 0; i--) {
        if (isAdmin(UL[i])) {
            document.getElementById('active_users').innerHTML = document.getElementById('active_users').innerHTML
                    + "<li class='admin'>" + UL[i] + "</li>";
        } else {
            if (getCurrentUser() === UL[i]) {
                document.getElementById('active_users').innerHTML = document.getElementById('active_users').innerHTML
                        + "<li class='user'>" + UL[i] + "</li>";
            } else {
                document.getElementById('active_users').innerHTML = document.getElementById('active_users').innerHTML
                        + "<li class='ouser'>" + UL[i] + "</li>";
            }
        }
    }
}

function loadMessages(storage) {
// re-renders all messages from chat history and forces autoscroll
    document.getElementById('central_room').innerHTML = "";
    document.getElementById('central_room').innerHTML += storage;
    autoScroll();
}

function joinedChat(usr) {
// system message to keep track of logged in users
    addMessage(getDateTime(), "", usr + ' has joined conversation.');
    return false;
}

function logout() {
// logout logic
    if (confirm("Are you sure you want to quit?") === true) {
        addMessage(getDateTime(), '', getCurrentUser() + ' has been disconnected.');
        removeFromUserList(getCurrentUser());
        sessionStorage.clear();
        location.href = 'index.html';
    }
}

function sendMessage() {
// straightforward logic of sending message
    var usr = getCurrentUser();
    var message = document.chat_message.inputline.value;
    addMessage(getDateTime(), usr, message);
    document.chat_message.inputline.value = "";
    loadMessages(loadNewHistory());
    return false;
}

function autoScroll() {
// pretty dumb implementation of autoscroll
    document.getElementById('central_room').scrollTop = 1048576;
}



function setCharLimit(element) {
// sets char limit for the textarea
    if (!('maxLength' in element)) {
        var max = element.attributes.maxLength.value;
        element.onkeypress = function () {
            if (this.value.length >= max)
                return false;
        };
    }
}

function loadNewHistory() {
// returns an innerHTML of chat messages; it should be inserted into "central_room" DIV
    var newChatHistory = localStorage.getItem("newChatHistory");
    var chatHistory2 = JSON.parse(newChatHistory);
    var innerHTMLmsg = "";
    for (var i = 0; i < chatHistory2.length; i++) {
        switch (chatHistory2[i].user) {
            case "": //system
                innerHTMLmsg += '<div class="line system">'
                        + '<span class="timestamp">' + chatHistory2[i].timestamp + '</span>'
                        + '<span class="message">' + chatHistory2[i].message + '</span></div>';
                break;
            case getCurrentUser(): //green or red
                if (isAdmin(chatHistory2[i].user)) { // red over green
                    innerHTMLmsg += '<div class="line">'
                            + '<span class="timestamp">' + chatHistory2[i].timestamp + '</span>'
                            + '<span class="user">' + chatHistory2[i].user + '</span>'
                            + '<span class="admin_message">' + smileys(chatHistory2[i].message) + '</span></div>';
                } else {
                    innerHTMLmsg += '<div class="line">'
                            + '<span class="timestamp">' + chatHistory2[i].timestamp + '</span>'
                            + '<span class="user">' + chatHistory2[i].user + '</span>'
                            + '<span class="user_message">' + smileys(chatHistory2[i].message) + '</span></div>';
                }
                break;
            default:
                if (isAdmin(chatHistory2[i].user)) { // red over white
                    innerHTMLmsg += '<div class="line">'
                            + '<span class="timestamp">' + chatHistory2[i].timestamp + '</span>'
                            + '<span class="user">' + chatHistory2[i].user + '</span>'
                            + '<span class="admin_message">' + smileys(chatHistory2[i].message) + '</span></div>';
                } else {
                    innerHTMLmsg += '<div class="line">'
                            + '<span class="timestamp">' + chatHistory2[i].timestamp + '</span>'
                            + '<span class="user">' + chatHistory2[i].user + '</span>'
                            + '<span class="ouser_message">' + smileys(chatHistory2[i].message) + '</span></div>';
                }
                break;
            case null:
                console.log('SOMETHING WENT COMPLETELY WRONG!!!');
                break;
        }
    }
    return innerHTMLmsg;
}

function smileys(msg) {
// replaces smiles with an <IMG> tags inside of message 
    var smiles = {
        "smile": Array(":)", ":-)"),
        "wink": Array(";)", ";-)"),
        "sad": Array(":(", ":-(")
    };

    for (var smile in smiles) {
        for (var i = 0; i < smiles[smile].length; i++) {
            var re = new RegExp(escapeRegExp(smiles[smile][i]), "g");
            msg = msg.replace(re, '<img src="images/' + smile + '.png" height="15" width="15"/>');
        }
    }
    return msg;
}

function escapeRegExp(str) {
    return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
}

function setCookie(cname, cvalue, exdays) {
// TO BE DELETED
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
// TO BE DELETED
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ')
            c = c.substring(1);
        if (c.indexOf(name) === 0)
            return c.substring(name.length, c.length);
    }
    return "";
}