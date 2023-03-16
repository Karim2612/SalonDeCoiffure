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
    document.getElementById('serviseID').value = decodeURI(data.id);
    document.getElementById('description_servise').value = decodeURI(data.descriptionservise);
    document.getElementById('nom_servise').value = decodeURI(data.nomservise);
    document.getElementById('prix_servise').value = decodeURI(data.prixservise);
    document.getElementById('date_creation_servise').value = decodeURI(data.datecreationservise);
}