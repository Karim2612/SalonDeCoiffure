$(document).ready(function(){
    let serviseId = 0;

    $(document).on("click", "#div_servise_table table button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        serviseId = btn_id.split("_")[2];

        $("div.delete-form").text("Servise N°" + serviseId + " will be deleted, do you want to continue?");
        $("#model-delete-btn").css({"display": "inline"});
    });

    $(document).on("click", "#model-delete-btn", function() {
        $.ajax({
            url: '/api/servise/deletebyid/' + serviseId,
            type: 'DELETE',
            success: function(response) {
                $("div.delete-form").text("Servise N°" + serviseId + " has been successfully deleted" + "!");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the servise row on html page
                let row_id = "tr_" + serviseId;
                $("#" + row_id).remove();
                $("#div_servise_updating").css({"display": "none"});
            },
            error: function(error){
                console.log(error);
                $("#div_servise_updating").css({"display": "none"});
                alert("Error -> " + error);
            }
        });
    });
});