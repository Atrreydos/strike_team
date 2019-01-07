const restUrl = "rest/vote-days/";
const voteRestUrl = "rest/votes/";
const eventVotingRestUrl = "rest/event-votings/";
let datatableApi;
let selectDayApi;

$(document).ready(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": restUrl + $("#eventVotingId").val(),
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "eventVotingId"
            },
            {
                "data": "day"
            },
            {
                "data": "myVote"
            },
            {
                "data": "votes"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderAcceptBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderRejectBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });

    selectDayApi = $("#selectDayDatatable").DataTable({
        "ajax": {
            "url": restUrl + $("#eventVotingId").val(),
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "day"
            },
            {
                "data": "votes"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderSelectDayBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});

function saveVoteDay() {
    let eventVoting = {
        day: $("#day").val(),
        eventVotingId: $("#eventVotingId").val()
    };

    $.ajax({
        type: "POST",
        url: restUrl,
        contentType : "application/json",
        data: JSON.stringify(eventVoting)
    }).done(function () {
        $("#editRow").modal("hide");
        successNoty("Saved");
        location.reload();
    });
}

function renderAcceptBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='setAcceptVote(" + row.id + ");'><span class='fa fa-check'></span></a>";
    }
}

function renderRejectBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='setRejectVote(" + row.id + ");'><span class='fa fa-times'></span></a>";
    }
}

function setAcceptVote(id) {
    let vote = {
        voteDayId: id,
        decisionType: "ACCEPT"
    };

    setVote(vote)
}

function setRejectVote(id) {
    let vote = {
        voteDayId: id,
        decisionType: "REJECT"
    };

    setVote(vote)
}

function setVote(vote) {
    $.ajax({
        type: "POST",
        url: voteRestUrl,
        contentType : "application/json",
        data: JSON.stringify(vote)
    }).done(function () {
        updateVoteDaysTable();
        successNoty("Saved");
    });
}

function renderSelectDayBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='setupDay(" + row.id + ");' title='Выбрать'><span class='fa fa-check'></span></a>";
    }
}

function setupDay(voteDayId) {
    $.ajax({
        type: "PUT",
        url: eventVotingRestUrl + $("#eventVotingId").val() + "/vote-day/" + voteDayId,
    }).done(function () {
        $("#selectDay").modal("hide");
        location.reload();
        successNoty("День для события установлен");
    });
}

function updateVoteDaysTable() {
    $.get(restUrl + $("#eventVotingId").val(), updateTableByData);
}

function selectVoteDay() {
    $("#selectDayModalTitle").html("Выбрать день");
    $("#selectDay").modal();
}

