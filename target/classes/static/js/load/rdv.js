/* Get input field parameter in URL */
window.onload = function () {
    var url = document.location.href,
        params = url.split('?')[1].split('&'),
        data = {}, tmp;
    for (var i = 0, l = params.length; i < l; i++) {
        tmp = params[i].split('=');
        data[tmp[0]] = tmp[1];
    }

    /* Decode input field to avoid URL syntax */
    document.getElementById('rdv_id').value = decodeURI(data.id);
    document.getElementById('description_rdv').value = decodeURI(data.descriptionrdv);
    document.getElementById('date_heur_rdv').value = decodeURI(data.dateheurrdv);
    document.getElementById('date_creation_rdv').value = decodeURI(data.datecreationrdv);
}