var stompClient = null;
var takenlijst = null;
var ArrayTakenlijst = [];

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}
function stopWeek(){
    stompClient.send("/app/EindeWeek", {} );
}

function connect() {
    var socket = new SockJS('http://localhost:3733/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/ServerMessages', function (message) {

            console.log(JSON.parse(message.body).thisweek);
            ShowWeeks(JSON.parse(message.body).thisweek);
        });
    });
}



function sendName() {
    stompClient.send("/app/StartGame", {} );
}

function ShowWeeks(message) {
    //console.log(message.body);
    var i = 0;
    takenlijst = message.taskList;
    console.log(takenlijst);
    document.getElementById("greetings").innerHTML = "";
    $.each(message.taskList , function(key, taak){

        $("#greetings").append("<button id='button' name='"+i+"' type='button'>"+taak.hour+"</button>");
        i++;
        console.log(taak.hour);
    });


}
$(function(){
    $(document).on('click', 'button[id=button]', function(event){
        var ButtonName = $(event.target).attr('name');
        console.log(ButtonName);
        addTaskToArray(ButtonName);
    })
});

function addTaskToArray(index){

    ArrayTakenlijst.push(takenlijst[index]);

    console.log(ArrayTakenlijst);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#EindeWeek" ).click(function() { stopWeek(); });
    $( "#send" ).click(function() { sendName(); });
});

