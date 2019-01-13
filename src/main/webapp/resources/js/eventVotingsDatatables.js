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
                "data": "event.name",
                "render": function (data, type, row) {
                    if (type === "display") {
                        // return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";

                        return "<a href=event-votings/" + row.id + " title='Детали'>" + data + "</a>";
                    }
                    return data;
                }
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

