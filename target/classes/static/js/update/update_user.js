$(document).ready(function () {
    $("#update_user_form").submit(function (evt) {
        evt.preventDefault();
        try {
            let userId = $("#userID").val();

            // PREPARE FORM DATA
            let formData = {
                createdAt: $("#createdAt").val(),
                email: $("#email").val(),
                enabled: $("#enabled").val(),
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                locked: $("#locked").val(),
                mobile: $("#mobile").val(),
                password: $("#password").val(),
                role: $("#role").val(),
                updatedAt: $("#updatedAt").val(),
            }


            $.ajax({
                url: '/api/user/updatebyid/' + userId + "/",
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(formData),
                dataType: 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let successAlert = '<div class="alert alert-success alert-dismissible fade show" role="alert">' +
                        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                        '<strong> User N°' + userId + ' has been successfully updated! Redirecting to all users page... </strong>'
                    '</div>'

                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({ "display": "block" });

                    setTimeout(() => {
                        window.location = "/user-admin.html";
                    }, 2500);
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
                        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                        '<strong> There was an error updating this user, please try again </strong>'
                    '</div>';

                    $("#response").empty();
                    $("#response").append(errorAlert);
                    $("#response").css({ "display": "block" });
                }
            });
        } catch (error) {
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function () {
        let id_of_button = (event.srcElement.id);
        let userId = id_of_button.split("_")[2];

        $.ajax({
            url: '/api/user/findone/' + userId,
            type: 'GET',
            success: function (response) {
                let user = response.users[0];
                $("#userID").val(user.id);
                $("#firstName").val(user.firstName);
                $("#lastName").val(user.lastName);
                $("#mobile").val(user.mobile);
                $("#password").val(user.password);
                $("#email").val(user.email);
                $("#enabled").val(user.enabled);
                $("#locked").val(user.locked);
                $("#role").val(user.role);
                $("#createdAt").val(user.createdAt);
                $("#updatedAt").val(user.updatedAt);



                let url = "/user/updateuser.html?id=" + user.id +
                    "&firstName=" + user.firstName +
                    "&lastName=" + user.lastName +
                    "&email=" + user.email +
                    "&mobile=" + user.mobile +
                    "&password=" + user.password +
                    "&enabled=" + user.enabled +
                    "&locked=" + user.locked +
                    "&role=" + user.role +
                    "&createdAt=" + user.createdAt +
                    "&updatedAt=" + user.updatedAt;

                window.location.href = url;

            },
            error: function (error) {
                console.log(error);
                alert("Error -> " + error);
            }
        });
    });
});