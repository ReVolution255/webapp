var req;
var isIE;
var no_users_text;
var no_users_div;
var buttons_div;
var add_form;
var edit_form;
var url = 'users/';
var users_table;
var users_table_div;

function init() {
    no_users_text = document.createTextNode('No users in database');
    no_users_div = document.getElementById('no_users');
    buttons_div = document.getElementById('buttons');
    add_form = document.getElementById('add_form');
    edit_form = document.getElementById('edit_form');
    users_table = document.getElementById('users');
    users_table_div = document.getElementById('users_table');

    no_users_div.appendChild(no_users_text);

    req = createReq();
    req.open("GET", url, true);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send();
    req.onreadystatechange = checkState;
}

function showEditForm(id){
    hideAddForm();
    edit_form.style.display = 'block';
    document.getElementById('idfield_editform').setAttribute('value', id);
}

function showAddForm(){
    hideEditForm();
    buttons_div.style.display = 'none';
    add_form.style.display = 'block';
}

function hideEditForm(){
    edit_form.style.display = 'none';
    document.getElementById('namefield_editform').value = '';
}

function hideAddForm(){
    add_form.style.display = 'none';
    document.getElementById('namefield_createform').value = '';
    buttons_div.style.display = 'block';
}

function editUser(){
    var user = {};
    var elem1 = document.getElementById('idfield_editform');
    user.id = elem1.value;
    var elem2 = document.getElementById('namefield_editform');
    user.name = elem2.value;
    req = createReq();
    req.open("PUT", url, true);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(JSON.stringify(user));
    req.onreadystatechange = checkState;
    elem2.value = "";
}

function deleteUser(user_id){
    var user = {};
    var deleteConfirmation = confirm("Удалить пользователя с id: " + user_id + " ?");
    if (!deleteConfirmation) return;
    user.id = user_id;
    req = createReq();
    req.open("DELETE", url, true);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(JSON.stringify(user));
    req.onreadystatechange = checkState;
}

function createUser(){
    var user = {};
    var elem = document.getElementById('namefield_createform');
    user.name = elem.value;
    req = createReq();
    req.open("POST", url, true);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(JSON.stringify(user));
    req.onreadystatechange = checkState;
    elem.value = '';
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

function createRow(user){
    //Links 'about' page
    var a_id = document.createElement('a');
    var a_name = document.createElement('a');
    a_id.setAttribute('href', 'about-user?id=' + user.id);
    a_name.setAttribute('href', 'about-user?id=' + user.id);
    //Table
    var tr = document.createElement('tr');
    var td_id = document.createElement('td');
    var td_name = document.createElement('td');

    //Edit button
    var td_edit = document.createElement('td');
    var temp_button = document.createElement('input');
    temp_button.setAttribute('name', 'edit');
    temp_button.setAttribute('type', 'button');
    temp_button.setAttribute('value', 'Edit');
    temp_button.setAttribute('onclick', 'showEditForm(' + user.id + ')');
    td_edit.appendChild(temp_button);

    //Delete button
    var td_delete = document.createElement('td');
    temp_button = document.createElement('input');
    temp_button.setAttribute('name', 'delete');
    temp_button.setAttribute('type', 'button');
    temp_button.setAttribute('value', 'Delete');
    temp_button.setAttribute('onclick', 'deleteUser(' + user.id + ')');
    td_delete.appendChild(temp_button);

    a_id.appendChild(document.createTextNode(user.id));
    a_name.appendChild(document.createTextNode(user.name));
    td_id.appendChild(a_id);
    td_name.appendChild(a_name);
    tr.appendChild(td_id);
    tr.appendChild(td_name);
    tr.appendChild(td_edit);
    tr.appendChild(td_delete);
    return tr;
}

function updateTable(){
    var users = $.parseJSON(req.responseText);
    hideEditForm();
    hideAddForm();

    if (users.length == 0){
        users_table_div.style.display = 'none';
        no_users_div.style.display = 'block';
    } else {
        no_users_div.style.display = 'none';
        users_table_div.style.display = 'block';
        var table_body = document.getElementById('users_list');
        table_body.innerHTML = '';
        $.each(users, function (number, user) {
            table_body.appendChild(createRow(user));
        });
    }
}