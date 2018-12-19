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
                "data": "eventName"
            },
            {
                "data": "eventDescription"
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

