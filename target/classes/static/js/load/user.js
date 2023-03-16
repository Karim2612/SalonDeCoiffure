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
    document.getElementById('userID').value = decodeURI(data.id);
    document.getElementById('firstName').value = decodeURI(data.firstName);
    document.getElementById('lastName').value = decodeURI(data.lastName);
    document.getElementById('mobile').value = decodeURI(data.mobile);
    document.getElementById('password').value = decodeURI(data.password);
    document.getElementById('email').value = decodeURI(data.email);
    document.getElementById('enabled').value = decodeURI(data.enabled);
    document.getElementById('locked').value = decodeURI(data.locked);
    document.getElementById('role').value = decodeURI(data.role);
    document.getElementById('updatedAt').value = decodeURI(data.updatedAt);
    document.getElementById('createdAt').value = decodeURI(data.createdAt);
    
}