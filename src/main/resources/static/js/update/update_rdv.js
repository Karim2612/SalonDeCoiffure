$(document).ready(function(){
    $("#update_rdv_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let rdvId = $("#rdv_id").val();

            // PREPARE FORM DATA
            let formData = {
                date_heur_rdv :  $("#date_heur_rdv").val(),
                description_rdv: $("#description_rdv").val(),
                date_creation_rdv: $("#date_creation_rdv").val(),
            }

            // If the dates are all the same
            if(Date.parse($("#date_heur_rdv").val()) == Date.parse($("#date_creation_rdv").val())){
                let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                    '<strong>' + 'Error: Invalid dates' + '</strong>' +
                    '</div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});
                return;
            }

            $.ajax({
                url: '/api/rdv/updatebyid/' + rdvId + "/",
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                        '<strong> Rdv N°' + rdvId + ' has been successfully updated! Redirecting to all rdv page... </strong>' +
                    '</div>'

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});

                    setTimeout( () => {
                        window.location = "/rdv-admin.html";
                    }, 2500);
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                        '<strong> There was an error updating this client, please try again </strong>' +
                    '</div>';

                    $("#response").empty();
                    $("#response").append(errorAlert);
                    $("#response").css({"display": "block"});
                }
            });
        } catch(error){
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function(){
        // split the ID from the component button and class .btn_id
        let id_of_button = (event.srcElement.id);
        let rdvId = id_of_button.split("_")[2];

        $.ajax({
            url: '/api/rdv/findone/' + rdvId,
            type: 'GET',
            success: function(response) {
                let rdv = response.rdv[0];

                console.log(response);

                $("#userID").val(rdv.id);
                $("#serviseID").val(rdv.id);
                $("#date_heur_rdv").val(rdv.date_heur_rdv);
                $("#description_rdv").val(rdv.description_rdv);
                $("#date_creation_rdv").val(rdv.date_creation_rdv);

                let url = "/rdv/updaterdv.html?id=" + rdv.id +
                    "&dateheurrdv=" + rdv.date_heur_rdv +
                    "&datecreationrdv=" + rdv.date_creation_rdv +
                    "&descriptionrdv=" + rdv.description_rdv;

                window.location.href = url;

            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });
    });
});