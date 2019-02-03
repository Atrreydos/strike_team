const restUrl = "rest/events/";
let datatableUpcomingApi;
let datatableInVotingApi;

//  http://xdsoft.net/jqplugins/datetimepicker/
$.datetimepicker.setLocale('ru');

$('#date').datetimepicker({
    timepicker: false,
    format: 'd.m.Y'
});

$(document).ready(function () {
    datatableUpcomingApi = $("#datatable_upcoming").DataTable({
        "ajax": {
            "url": restUrl + "upcoming",
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href=events/" + row.id + " title='Детали'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "date"
            },
            {
                "data": "status"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn,
                visible: columnVisible
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn,
                visible: columnVisible
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

    datatableInVotingApi = $("#datatable_in_voting").DataTable({
        "ajax": {
            "url": restUrl + "in-voting",
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href=events/" + row.id + " title='Детали'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "date"
            },
            {
                "data": "status"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn,
                visible: columnVisible
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn,
                visible: columnVisible
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

function saveEvent() {
    let currentStatus = $("#status").val() === "" ? null : $("#status").val();

    let newEvent = {
        id: $("#id").val(),
        name: $("#name").val(),
        description: $("#description").val(),
        date: $("#date").val(),
        status: currentStatus
    };

    $.ajax({
        type: "POST",
        url: restUrl,
        contentType: "application/json",
        data: JSON.stringify(newEvent)
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    });
}

function updateTable() {
    $.get(restUrl + "upcoming", updateUpcomingTableByData);
    $.get(restUrl + "in-voting", updateInVotingTableByData);
}

function updateUpcomingTableByData(data) {
    datatableUpcomingApi.clear().rows.add(data).draw();
}

function updateInVotingTableByData(data) {
    datatableInVotingApi.clear().rows.add(data).draw();
}