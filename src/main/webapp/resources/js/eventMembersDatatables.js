const eventMembersRestUrl = "rest/event-members/";
let datatableApi;
const eventId = $("#eventId").val();

$(document).ready(function () {
    datatableApi = $("#membersDatatable").DataTable({
        "ajax": {
            "url": eventMembersRestUrl + "event/" + eventId,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                data: "id"
            },
            {
                data: "userId"
            },
            {
                data: "decision"
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