$(document).ready(function(){
    let  rdvId = 0;

    $(document).on("click", "#div_rdv_table table button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        rdvId = btn_id.split("_")[2];

        $("div.delete-form").text("All datas registered in rdv N°" + rdvId + " will be deleted, do you want to continue?");
        $("#model-delete-btn").css({"display": "inline"});
    });

    $(document).on("click", "#model-delete-btn", function() {
        $.ajax({
            url: '/api/rdv/deletebyid/' + rdvId,
            type: 'DELETE',
            success: function(response) {
                $("div.delete-form").text("All datas in rdv N°" + rdvId + " has been successfully deleted" + "!");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the rdv row on html page
                let row_id = "tr_" + rdvId;
                $("#" + row_id).remove();
                $("#div_rdv_updating").css({"display": "none"});
            },
            error: function(error){
                console.log(error);
                $("#div_rdv_updating").css({"display": "none"});
                alert("Error -> " + error);
            }
        });
    });
});