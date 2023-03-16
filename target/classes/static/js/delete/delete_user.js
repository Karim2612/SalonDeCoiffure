$(document).ready(function () {
    let userId = 0;

    $(document).on("click", "#div_user_table table button.btn_delete", function () {
        let btn_id = (event.srcElement.id);
        userId = btn_id.split("_")[2];

        $("div.delete-form").text("All datas registered in user N°" + userId + " will be deleted, do you want to continue?");
        $("#model-delete-btn").css({ "display": "inline" });
    });

    $(document).on("click", "#model-delete-btn", function () {
        $.ajax({
            url: '/api/user/deletebyid/' + userId,
            type: 'DELETE',
            success: function (response) {
                $("div.delete-form").text("All datas in user N°" + userId + " has been successfully deleted" + "!");

                $("#model-delete-btn").css({ "display": "none" });
                $("button.btn.btn-secondary").text("Close");

                // delete the user row on html page
                let row_id = "tr_" + userId;
                $("#" + row_id).remove();
                $("#div_user_updating").css({ "display": "none" });
            },
            error: function (error) {
                console.log(error);
                $("#div_user_updating").css({ "display": "none" });
                alert("Error -> " + error);
            }
        });
    });
});