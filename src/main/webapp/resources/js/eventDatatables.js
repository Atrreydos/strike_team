const restUrl = "rest/events/";
let datatableApi;

//  http://xdsoft.net/jqplugins/datetimepicker/
$.datetimepicker.setLocale('ru');

$('#date').datetimepicker({
    timepicker: false,
    format: 'd.m.Y'
});

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
    let newEvent = {
        id: $("#id").val(),
        name: $("#name").val(),
        description: $("#description").val(),
        date: $("#date").val()
    };

    $.ajax({
        type: "POST",
        url: restUrl,
        contentType : "application/json",
        data: JSON.stringify(newEvent)
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    });
}