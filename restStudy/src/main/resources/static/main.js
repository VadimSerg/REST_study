$(async function () {
    await getAuthUser();
    await getAllUsers();
    await newUser();
    removeUser();
    updateUser();

});

async function getAuthUser() {
    fetch("http://localhost:8060/user/auth")
        .then(response => response.json())
        .then(fieldUser => {


            let authorities=[];
            for (let i = 0; i < fieldUser.roles.length; i++ ) {
               authorities.push(fieldUser.roles[i].roleName.substring(0));
            }
            let listAuthorities = authorities.join("; ");


           $('#userName').append(fieldUser.username);
         //  $('#userRole').append(fieldUser.roles[0].roleName.substring(0)+ "; "+ fieldUser.roles[1].roleName.substring(0));


            // const arr =Array.of(fieldUser.roles.roleName.substring(0));
            $('#userRole').append(listAuthorities);




            let user = `$(
            <tr class="fs-5">
                <td>${fieldUser.id}</td>
                <td>${fieldUser.username}</td>
                <td>${fieldUser.surname}</td>
                <td>${fieldUser.age}</td>
                <td>${fieldUser.city}</td>
                <td>${listAuthorities}</td>)`;
            $('#userTable').append(user);
        })
        .catch(error => console.log(error))
}

async function getAllUsers() {
    const table = $('#usersTable');
    table.empty();
    fetch("http://localhost:8060/admin/users")
        .then(response => response.json())
        .then(dataUsers => {
            dataUsers.forEach(user => {

                let rolesArr = [];
                for (let i = 0; i < user.roles.length; i++) {
                    rolesArr.push(user.roles[i].roleName.substring(0));
                }
                let rolesList = rolesArr.join("; ");

                let usersTable = `$(
                <tr class="fs-5">
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.city}</td>
                   
                   <td>
                                <div>
                                    <span class="text-uppercase">${rolesList}</span>
                                </div>
                            </td>
                    <td>
                        
                        <button class="btn btn-info text-white" type="button" data-userid="${user.id}" data-action="Edit"
                                data-bs-toggle="modal" data-bs-target="#editModal">Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" type="button" data-userid="${user.id}" data-action="Delete"
                                data-bs-toggle="modal" data-bs-target="#delModal">Delete</button>
                    </td>
                </tr>)`;
                table.append(usersTable);
            })
        })
        .catch(error => console.log(error))
}

async function getUser(id) {
    let url = "http://localhost:8060/admin/users/" + id;
    let response = await fetch(url);
    return await response.json();
}

async function getRolesOption() {
    await fetch("http://localhost:8060/admin/roles")
        .then(response => response.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.value = role.id;
                el.text = role.roleName.substring(0);
                $('#selectNewRoles')[0].appendChild(el);
            })
        })
}

async function newUser() {
    await getRolesOption();

    const form = document.forms["formNewUser"];

    form.addEventListener('submit', addNewUser)

    async function addNewUser(e) {
        e.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) newUserRoles.push({
                id: form.roles.options[i].value,
                roleName: form.roles.options[i].text
            })
        }

        fetch("http://localhost:8060/admin/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: form.username.value,
                surname: form.surname.value,
                age: form.age.value,
                city: form.city.value,
                password: form.password.value,
                roles: newUserRoles
            })
        }).then(() => {
                form.reset();
                getAllUsers();
                $(`.nav-tabs a[href="#nav-home"]`).tab("show");
            })
    }
}

function removeUser(){
    const deleteForm = document.forms["formDeleteUser"];
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8060/admin/delete/users/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
                getAllUsers();
                $('#buttonDelFormClose').click();

            })
    })
}

$('#delModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('userid');
    showDeleteModal(id);
})

async function showDeleteModal(id) {
    let user = await getUser(id);

    let form = document.forms["formDeleteUser"];
    form.id.value = user.id;
    form.username.value = user.username;
    form.lastName.value = user.surname;
    form.age.value = user.age;
    form.city.value = user.city;
    form.password.value = user.password;


    $('#selectDelRoles').empty();

    user.roles.forEach(role => {
        let el = document.createElement("option");
        el.text = role.roleName.substring(0);
        el.value = role.id;
        $('#selectDelRoles')[0].appendChild(el);
    });
}

function updateUser() {
    const editForm = document.forms["formEditUser"];
    editForm.addEventListener("submit", async ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editForm.rolesEdit.options.length; i++) {
            if (editForm.rolesEdit.options[i].selected) editUserRoles.push({
                id: editForm.rolesEdit.options[i].value,
                roleName: editForm.rolesEdit.options[i].text
            })
        }



        fetch("http://localhost:8060/admin/update/users/" + editForm.editId.value, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                //id: editForm.editId.value,
                username: editForm.nameEdit.value,
                surname:  editForm.lastNameEdit.value,
                age:      editForm.ageEdit.value,
                city:     editForm.cityEdit.value,
                password: editForm.passwordEdit.value,
                roles:     editUserRoles
            })
        }).then(() => {
            getAllUsers();
            $('#buttonEditFormClose').click();
        })
    })
}

$('#editModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('userid');
    showEditModal(id);
})

async function showEditModal(id) {
    let user = await getUser(id);

    let form = document.forms["formEditUser"];
    form.editId.value = user.id;
    form.nameEdit.value = user.username;
    form.lastNameEdit.value = user.surname;
    form.ageEdit.value = user.age;
    form.cityEdit.value = user.city;
    form.passwordEdit.value = user.password;

   $('#selectUpdateRoles').empty();

    await fetch("http://localhost:8060/admin/roles")
        .then(response => response.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.value = role.id;
                el.text = role.roleName.substring(0);
                $('#selectUpdateRoles')[0].appendChild(el);
            })
        })
}