const restUrl = "rest/vote-days/";
const voteRestUrl = "rest/votes/";
const eventVotingRestUrl = "rest/event-votings/";
let datatableApi;
let selectDayApi;
const eventVotingId = $("#eventVotingId").val();

$.datetimepicker.setLocale('ru');

$('#day').datetimepicker({
    timepicker: false,
    format: 'd.m.Y'
});

$(document).ready(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": restUrl + eventVotingId,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                data: "day"
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
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderClearBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteDayBtn,
                visible: columnVisible
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderProgress
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
            "url": restUrl + eventVotingId,
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
        eventVotingId: eventVotingId
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
        return "<a onclick='setAcceptVote(" + row.id + ");' title='Проголосовать за'><span class='fa fa-check'></span></a>";
    }
}

function renderRejectBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='setRejectVote(" + row.id + ");' title='Проголосовать против'><span class='fa fa-times'></span></a>";
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

function renderClearBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='clearVote(" + row.id + ");' title='Очистить голос'><span class='fa fa-times'></span></a>";
    }
}

function clearVote(id) {
    $.ajax({
        type: "DELETE",
        url: voteRestUrl + id
    }).done(function () {
        updateVoteDaysTable();
        successNoty("Cleared");
    });
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
        url: eventVotingRestUrl + eventVotingId + "/vote-day/" + voteDayId,
    }).done(function () {
        $("#selectDay").modal("hide");
        location.reload();
        successNoty("День для события установлен");
    });
}

function updateVoteDaysTable() {
    $.get(restUrl + eventVotingId, updateTableByData);
}

function selectVoteDay() {
    $("#selectDayModalTitle").html("Выбрать день");
    $("#selectDay").modal();
}

function renderDeleteDayBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRowDay(" + row.id + ");' title='Удалить'><span class='fa fa-remove'></span></a>";
    }
}

function deleteRowDay(id) {
    $.ajax({
        url: restUrl + id,
        type: "DELETE"
    }).done(function () {
        updateDaysTable();
        successNoty("Deleted");
    });
}

function renderProgress(data, type, row) {
    if (type === "display") {
        return "<div class='progress bg-white'>" +
            "<div class='progress-bar bg-success' role='progressbar' style='width: 25%' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100'></div>" +
            "</div>";
    }
}

function updateDaysTable() {
    $.get(restUrl + eventVotingId, updateTableByData);
}

