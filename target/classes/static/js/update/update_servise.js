$(document).ready(function(){
    $("#update_servise_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let serviseId = $("#serviseID").val();

            // PREPARE FORM DATA
            let formData = {
                nom_servise :  $("#nom_servise").val(),
                prix_servise: $("#prix_servise").val(),
                description_servise: $("#description_servise").val(),
                date_creation_servise: $("#date_creation_servise").val(),
            }

            $.ajax({
                url: '/api/servise/updatebyid/' + serviseId + "/",
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                        '<strong> Servise N°' + serviseId + ' has been successfully updated! Redirecting to all Servise page... </strong>' +
                    '</div>'

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});

                    setTimeout( () => {
                        window.location = "/service-admin.html";
                    }, 2500);
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                        '<strong> There was an error updating this Servise, please try again </strong>' +
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
        let serviseId = id_of_button.split("_")[2];

        $.ajax({
            url: '/api/servise/findone/' + serviseId,
            type: 'GET',
            success: function(response) {
                let servise = response.servise[0];
                $("#nom_servise").val(servise.nom_servise);
                $("#prix_servise").val(servise.prix_servise);
                $("#description_servise").val(servise.description_servise);
                $("#date_creation_servise").val(servise.date_creation_servise);   

                let url = "/updateservise.html?id=" + servise.id +
                    "&nomservise=" + servise.nom_servise +
                    "&prixservise=" + servise.prix_servise +
                    "&descriptionservise=" + servise.description_servise +
                    "&datecreationservise=" + servise.date_creation_servise

                window.location.href = url;

            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });
    });
});