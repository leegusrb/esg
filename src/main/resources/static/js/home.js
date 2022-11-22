var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.6.0.min.js';
document.getElementsByTagName('head')[0].appendChild(script);

var sockJs = document.createElement('script');
sockJs.src = "https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js";
sockJs.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(sockJs);

// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

let sock;

window.onload = connectSocket;

function connectSocket() {
    sock = new SockJS("/ws");

    sock.onopen = onOpen;

    sock.onerror = onError;

    sock.onclose = onClose;
}

function onOpen() {
    console.log('open');
    alert('연결에 성공하였습니다.');
    // sock.onmessage = (data => {
    //     $("<p>" + data.data + "</p>").prependTo('#chatBox');
    // });
    // $('#chatConnect').hide();
    // $('#chat').show();
    sock.onmessage = appendMessage;
}

function onError(e) {
    console.log('error');
    // alert('연결에 실패하였습니다.' + e);
    // $('#chatConnect').show();
    // $('#chat').hide();
}

function onClose() {
    console.log('close');
    // alert('연결을 종료합니다.');
    // $('#chatConnect').show();
    // $('#chat').hide();
}

function sendMessage() {
    var msg = "123";
    sock.send(msg);
    // sock.send($("#message").val());
    // $('#message').val("");
}

function appendMessage(msg) {
    $("#text").append(msg+"<br>");
}

// $("#message").keyup(e => {
//     if (e.keyCode == 13) {
//         sendMessage();
//     }
// });

$("#request").click(() => {
    sendMessage();
});