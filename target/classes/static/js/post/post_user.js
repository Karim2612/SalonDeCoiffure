$(document).ready(function () {
    $("#add_new_user").submit(function (evt) {
        evt.preventDefault();

        // PREPARE FORM DATA
        let formData = {
            first_name: $("#firstName").val(),
            last_name: $("#lastName").val(),
            mobile: $("#mobile").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            enabled: $("#enabled").val(),
            locked: $("#locked").val(),
            role: $("#role").val(),
            created_at: $("#createdAt").val(),
            updated_at: $("#updatedAt").val(),
        }


        $.ajax({
            
            url: '/api/user/create',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(formData),
            dataType: 'json',
            async: false,
            cache: false,
            success: function (response) {
                window.location = "/user-admin.html";
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                    '<strong> There was an error adding this user </strong>' +
                    '</div>';

                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
            }
        });
    });

    function todayDate() {
        var today = new Date(); // get the current date
        var dd = today.getDate(); // get the day from today.
        var mm = today.getMonth() + 1; // get the month from today +1 because january is 0!
        var yyyy = today.getFullYear(); // get the year from today

        // if day is below 10, add a zero
        if (dd < 10) {
            dd = '0' + dd
        }

        // if day is below 10, add a zero
        if (mm < 10) {
            mm = '0' + mm
        }

        // final result is dd/mm/yyyy
        return dd + '/' + mm + '/' + yyyy;
    }

    $(document).ready(function () {
        $('#date_creation_user').attr('min', todayDate());
        $('#date_naissance_user').attr('min', todayDate());
    });

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/user-admin.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});
