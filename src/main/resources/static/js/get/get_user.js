$(document).ready(function () {
    (function () {
        $.ajax({
            type: "GET",
            url: "/api/user/retrieveinfos",
            success: function (response) {
                $.each(response.users, (i, user) => {

                    let deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + user.id + '\"' +
                        ' type="button" class="btn btn-danger btn_delete" data-bs-toggle="modal" data-bs-target="#delete-modal">' +
                        'Delete</button>';

                    let get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + user.id + '\"' +
                        ' type="button" class="btn btn-warning btn_id">' +
                        'Edit' +
                        '</button>';

                    let active;
                    if (user.active == true) {
                        active = 'Yes';
                    } else if (user.active == false) {
                        active = 'No';
                    }

                    let tr_id = 'tr_' + user.id;
                    let userRow = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + user.id + '</td>' +
                        '<td class=\"td_firstName\">' + user.firstName + '</td>' +
                        '<td class=\"td_lastName\">' + user.lastName + '</td>' +
                        '<td class=\"td_mobile\">' + user.mobile + '</td>' +
                        '<td class=\"td_password\">' + user.password + '</td>' +
                        '<td class=\"td_email\">' + user.email + '</td>' +
                        '<td class=\"td_enabled\">' + user.enabled + '</td>' +
                        '<td class=\"td_locked\">' + user.locked + '</td>' +
                        '<td class=\"td_role\">' + user.role + '</td>' +
                        '<td class=\"td_createdAt\">' + getDate(user.createdAt) + '</td>' +
                        '<td class=\"td_updatedAt\">' + getDate(user.updatedAt) + '</td>' +
                        '<td>' + `${get_More_Info_Btn} <span class="separator">|</span> ${deleteButton}` + '</td>' +
                        '</tr>';
                    $('#userTable tbody').append(userRow);
                });
            },
            error: function (e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    })();

    (function () {
        let pathname = window.location.pathname;
        if (pathname == "/user-admin.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});