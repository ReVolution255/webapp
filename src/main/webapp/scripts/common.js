var req;
var isIE;
var no_users_text;
var no_users_div;
var users_table_div;
var buttons_div;

function init() {
    no_users_text = document.createTextNode('No users in database');
    no_users_div = document.getElementById('no_users');
    users_table_div = document.getElementById('users_table');
    buttons_div = document.getElementById('buttons');
}

function deleteUser(user_id){
    var deleteConfirmation = confirm("Удалить пользователя с id: " + user_id + " ?");
    if (!deleteConfirmation) return;
    var url = "?action=delete&delete=" + user_id;
    req = createReq();
    req.open("GET", url, true);
    req.send();
    req.onreadystatechange = checkState;
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

function showEditForm(id){
    hideAddForm();
    var edit_form = document.getElementById('edit_form');
    var form = '<label for="idfield_editform">User id:</label>';
    form += '<input type="text" id="idfield_editform" name="id" placeholder="User id" value="' + id + '" readonly>';
    form += '<br>';
    form += '<label for="namefield_editform">User name:</label>';
    form += '<input type="text" id="namefield_editform" name="name" placeholder="New name">';
    form += '<br>';
    form += '<input onclick="editUser()" type="button" value="Accept">';
    edit_form.innerHTML = form;
}

function showAddForm(){
    hideEditForm();
    var add_form = document.getElementById('add_form');
    var form = '<label for="namefield_createform">User name:</label>';
    form += '<input type="text" id="namefield_createform" name="name" placeholder="User name">';
    form += '<input value="Create" onclick="createUser()" type="button">';
    add_form.innerHTML = form;
}

function hideEditForm(){
    document.getElementById('edit_form').innerHTML = '';
}

function hideAddForm(){
    document.getElementById('add_form').innerHTML = '';
    var button = '<input id="add_button" type="button" value="Add user" onclick="showAddForm()">';
    document.getElementById('add_form').innerHTML = button;
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
    document.getElementById('edit_form').innerHTML = '';
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

    var buttons = '';
    buttons += '<div id="add_form">';
    buttons += '<input type="button" value="Add user" onclick="showAddForm()">';
    buttons += '</div>';
    buttons += '<div id="edit_form">';
    buttons += '</div>';
    buttons_div.innerHTML = buttons;

    if (users.length == 0){
        users_table_div.innerHTML = '';
        no_users_div.appendChild(no_users_text);
    } else {
        no_users_div.innerHTML = '';

        var table;
        if (document.getElementById('users') == null)
            table = document.createElement('table');
        else {
            table = document.getElementById('users');
            table.innerHTML = '';
        }
        table.setAttribute('border', '1');
        table.setAttribute('id', 'users');
        users_table_div.appendChild(table);

        //Head
        var tr = document.createElement('tr');
        var th;
        th = document.createElement('th');
        th.appendChild(document.createTextNode('User ID'));
        tr.appendChild(th);
        th = document.createElement('th');
        th.appendChild(document.createTextNode('User Name'));
        tr.appendChild(th);
        th = document.createElement('th');
        th.appendChild(document.createTextNode('Edit User'));
        tr.appendChild(th);
        th = document.createElement('th');
        th.appendChild(document.createTextNode('Delete User'));
        tr.appendChild(th);
        table.appendChild(tr);

        $.each(users, function (number, user) {
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
            table.appendChild(tr);
        });
    }
}