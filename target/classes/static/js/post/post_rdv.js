$(document).ready(function () {
    $("#add_new_rdv").submit(function (evt) {
        evt.preventDefault();

        let userId = $("#userID").val();
        let serviseId = $("#serviseID").val();
        

        // PREPARE FORM DATA
        let formData = {
            userId: $("#userID").val(),
            serviseId: $("#serviseID").val(),
            description_rdv: $("#description_rdv").val(),
            date_heur_rdv: $("#date_heur_rdv").val(),
            date_creation_rdv: $("#date_creation_rdv").val(),
        }

        // If the dates are all the same
        if (Date.parse($("#date_heur_rdv").val()) == Date.parse($("#date_creation_rdv").val())) {
            let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                '<strong>' + 'Error: Invalid dates' + '</strong>' +
                '</div>'
            $("#response").append(errorAlert);
            $("#response").css({ "display": "block" });
            return;
        }

        $.ajax({
            url: '/api/rdv/create/' + userID + serviseID,
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(formData),
            dataType: 'json',
            async: false,
            cache: false,
            success: function (response) {
                window.location = "/rdv/rdv-admin.html";
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                    '<strong> There was an error adding this rdv, please make sure the ID is correct </strong>' +
                    '</div>';

                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
            }
        });
    });
});
