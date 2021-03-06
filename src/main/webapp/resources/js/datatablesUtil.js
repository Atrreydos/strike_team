let form = $('#detailsForm');

let columnVisible = false;
if ($('#roleAdmin').val() === 'true'){
    columnVisible = true;
}

function makeEditable() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function deleteRow(id) {
    $.ajax({
        url: restUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
        successNoty("Deleted");
    });
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function addWithInput() {
    $("#modalTitle").html(i18n["addTitle"]);
    $("#editRow").modal();
}

function save() {
    $.ajax({
        type: "POST",
        url: restUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
        successNoty("Saved");
    });
}

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(restUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function updateTable() {
    $.get(restUrl, updateTableByData);
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status + (jqXHR.responseText ? "<br>" + jqXHR.responseText : ""),
        type: "error",
        layout: "bottomRight"
    }).show();
}

closeNoty();
// https://stackoverflow.com/questions/48229776
const errorInfo = JSON.parse(jqXHR.responseText);
failedNote = new Noty({
    text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + ": " + jqXHR.status + "<br>" + errorInfo.type + "<br>" + errorInfo.details.join("<br>"),
    type: "error",
    layout: "bottomRight"
}).show();

function renderDetailBtn(data, type, row) {
    if (type === "display") {
        return "<a href=event-votings/" + row.id + " title='Детали'><span class='fa fa-eye'></span></a>";
    }
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");' title='Редактировать'><span class='fa fa-edit'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");' title='Удалить'><span class='fa fa-remove'></span></a>";
    }
}