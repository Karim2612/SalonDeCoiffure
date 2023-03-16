$(document).ready(function () {
    $("#add_new_servise").submit(function (evt) {
        evt.preventDefault();

       // let id = 1;


        // PREPARE FORM DATA
        let formData = {
            nom_servise: $("#nom_servise").val(),
            prix_servise: $("#prix_servise").val(),
            description_servise: $("#description_servise").val(),
            date_creation_servise: $("#date_creation_servise").val(),
        }

        $.ajax({
            //url: '/api/servise/create/' + id,
            url: '/api/servise/create/' ,
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(formData),
            dataType: 'json',
            async: false,
            cache: false,
            success: function (response) {
                window.location = "/service-admin.html";
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                    '<strong> There was an error adding this service, please make sure the ID is correct </strong>' +
                    '</div>';

                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
            },
           // id++;
        });
    });
});
