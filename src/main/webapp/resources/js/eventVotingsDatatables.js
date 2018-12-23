const restUrl = "rest/event-votings/";
let datatableApi;

$(document).ready(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": restUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDetailBtn
            },
            {
                "data": "event.name"
            },
            {
                "data": "event.description"
            },
            {
                "data": "description"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
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

function saveEventVoting() {
    let eventVoting = {
        event: {
            name: $("#event_name").val(),
            description: $("#event_description").val()
        },
        description: $("#description").val()
    };

    $.ajax({
        type: "POST",
        url: restUrl,
        contentType : "application/json",
        data: JSON.stringify(eventVoting)
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    });
}

