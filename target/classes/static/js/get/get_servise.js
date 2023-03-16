$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/api/servise/retrieveinfos",
            success: function(response){
                $.each(response.servise, (i, servise) => {

                    let deleteButton = '<button ' +
                        'id=' +
                        '\"' + 'btn_delete_' + servise.id + '\"'+
                        ' type="button" class="btn btn-danger btn_delete" data-bs-toggle="modal" data-bs-target="#delete-modal">' +
                        'Delete</button>';

                    let get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + servise.id + '\"' +
                        ' type="button" class="btn btn-warning btn_id">' +
                        'Edit' +
                        '</button>';

                    let tr_id = 'tr_' + servise.id;
                    let serviseRow = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + servise.id + '</td>' +
                        '<td class=\"td_nom_servise\">' + servise.nom_servise + '</td>' +
                        '<td class=\"td_prix_servise\">' + servise.prix_servise + '</td>' +
                        '<td class=\"td_description_servise\">' + servise.description_servise + '</td>' +
                        '<td class=\"td_date_creation_servise\">' + getDate(servise.date_creation_servise) + '</td>' +
                        '<td>' +  `${get_More_Info_Btn} <span class="separator">|</span> ${deleteButton}` + '</td>' +
                        '</tr>';
                    $('#serviseTable tbody').append(serviseRow);
                });

            },
            error : function(e) {
                alert("ERROR: ", e);
                console.log("ERROR: ", e);
            }
        });
    })();
});