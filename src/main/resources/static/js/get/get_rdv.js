$(document).ready(function(){
    (function(){



-//get fk of user and servise
        // var results = [];
        // for (var i=0; i<user.length; i++) {
        //     for (var j=0; j<servise.length; j++) {
        //         results.push({
        //             employee_name: employee[i].name, 
        //             rdv_id: user[i].servise_id,
        //             servise_id: servise[j].servise_id,
        //             department_name: department[j].name
        //         });
        //     }
        // }




        $.ajax({
            type : "GET",
            url : "/api/rdv/retrieveinfos",
            success: function(response){
                $.each(response.rdv, (i, rdv) => {

                    let deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + rdv.id + '\"'+
                        ' type="button" class="btn btn-danger btn_delete" data-bs-toggle="modal" data-bs-target="#delete-modal">' +
                        'Delete</button>';

                    let get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + rdv.id + '\"' +
                        ' type="button" class="btn btn-warning btn_id">' +
                        'Edit' +
                        '</button>';

                    let tr_id = 'tr_' + rdv.id;
                    let rdvRow = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + rdv.id + '</td>' +
                        '<td class=\"td_date_heur_rdv\">' + getDate(rdv.date_heur_rdv) + '</td>' +
                        '<td class=\"td_description_rdv\">' + rdv.description_rdv + '</td>' +
                        '<td class=\"td_date_creation_rdv\">' + getDate(rdv.date_creation_rdv) + '</td>' +
                        // '<td class=\"td_servise_id\">' + rdv.servise_id + '</td>' +
                        // '<td class=\"td_user_id\">' + rdv.user_id + '</td>' +
                        '<td>' +  `${get_More_Info_Btn} <span class="separator">|</span> ${deleteButton}` + '</td>' +
                        '</tr>';
                    $('#rdvTable tbody').append(rdvRow);
                });










                

            },
            error : function(e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    })();
});