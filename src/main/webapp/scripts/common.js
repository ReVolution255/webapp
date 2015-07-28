var req;
var isIE;

function init() {

}

function deleteUser(){
    var elem = document.getElementById('idfield_deleteform');
    var id = elem.value;
    var url = "?action=delete&delete=" + id;
    req = createReq();
    req.open("GET", url, true);
    req.send();
    req.onreadystatechange = checkState;
    elem.value = "";
}

function createUser(){
    var elem = document.getElementById('namefield_createform');
    var name = elem.value;
    var url = "?action=create&name=" + name;
    req = createReq();
    req.open("GET", url, true);
    req.send();
    req.onreadystatechange = checkState;
    elem.value = "";
}

function editUser(){
    var elem1 = document.getElementById('idfield_editform');
    var id = elem1.value;
    var elem2 = document.getElementById('namefield_editform');
    var name = elem2.value;
    var url = "?action=edit&id=" + id + "&name=" + name;
    req = createReq();
    req.open("GET", url, true);
    req.send();
    req.onreadystatechange = checkState;
    elem1.value = "";
    elem2.value = "";
}

function createReq(){
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function checkState() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            updateTable();
        }
    }
}

function updateTable(){
    var users = $.parseJSON(req.responseText);
    var table = document.getElementById("users");
    table.innerHTML = "";
    $.each(users, function(number, user){
        //Links 'about' page
        var a_id = document.createElement('a');
        var a_name = document.createElement('a');
        a_id.setAttribute('href', 'about-user?id=' + user.id);
        a_name.setAttribute('href', 'about-user?id=' + user.id);
        //Table
        var tr = document.createElement('tr');
        var td_id = document.createElement('td');
        var td_name = document.createElement('td');

        var td_edit = document.createElement('td');
        var temp_form = document.createElement('form');
        temp_form.setAttribute('action', 'edit-user.jsp');
        var temp_button = document.createElement('button');
        temp_button.setAttribute('name', 'edit');
        temp_button.setAttribute('type', 'submit');
        temp_button.setAttribute('value', user.id);
        temp_button.innerHTML = "Edit";
        temp_form.appendChild(temp_button);
        td_edit.appendChild(temp_form);

        var td_delete = document.createElement('td');
        temp_form = document.createElement('form');
        temp_form.setAttribute('action', 'delete-user.jsp');
        temp_button = document.createElement('button');
        temp_button.setAttribute('name', 'delete');
        temp_button.setAttribute('type', 'submit');
        temp_button.setAttribute('value', user.id);
        temp_button.innerHTML = "Delete";
        temp_form.appendChild(temp_button);
        td_delete.appendChild(temp_form);

        a_id.appendChild(document.createTextNode(user.id));
        a_name.appendChild(document.createTextNode(user.name));
        td_id.appendChild(a_id);
        td_name.appendChild(a_name);
        tr.appendChild(td_id);
        tr.appendChild(td_name);
        tr.appendChild(td_edit);
        tr.appendChild(td_delete);
        table.appendChild(tr);
    });
}