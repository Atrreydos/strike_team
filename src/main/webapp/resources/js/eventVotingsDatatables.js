const restUrl = "rest/event-votings/";
let datatableApi;
let editVotingForm = $('#detailsForm');

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
                        return "<a href=event-votings/" + row.id + " title='Детали'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "status"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditVotingBtn,
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
    let currentStatus = $("#status").val() === "" ? null : $("#status").val();

    let eventVoting = {
        id: $("#id").val(),
        description: $("#description").val(),
        status: currentStatus,
        event: {
            id: $("#event_id").val(),
            name: $("#event_name").val(),
            description: $("#event_description").val()
        }
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

function renderEditVotingBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateVotingRow(" + row.id + ");' title='Редактировать'><span class='fa fa-edit'></span></a>";
    }
}

function updateVotingRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);

    $.get(restUrl + id, function (data) {
        editVotingForm.find("input[name='id']").val(data.id);
        editVotingForm.find("input[name='description']").val(data.description);
        editVotingForm.find("input[name='status']").val(data.status);
        editVotingForm.find("input[name='event.id']").val(data.event.id);
        editVotingForm.find("input[name='event.name']").val(data.event.name);
        editVotingForm.find("input[name='event.description']").val(data.event.description);
        $('#editRow').modal();
    });
}

