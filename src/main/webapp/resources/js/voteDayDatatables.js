const restUrl = "rest/vote-days/";

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

