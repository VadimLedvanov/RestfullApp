
fillTable()
function fillTable() {
    const url = "http://localhost:8080/api/users"
    let tbody = document.getElementById("users-table");
    let output = ''

    // Заполнение таблицы пользователей
    fetch(url)
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let roles = ''

                output += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.age}</td>
                `

                user.roles.forEach(role => {
                    roles += role.name.replace("ROLE_", '') + ' ';
                })

                output += `
                <td colspan="2">${roles}</td>
            `
                // edit form
                output += `
                
                <td>
                    <button type="button" style="color: white" class="btn btn-info" data-bs-toggle="modal" data-bs-target="${'#Modal_Edit_' + user.id}">
                        Edit
                    </button>
                    <div class="modal fade" id="${'Modal_Edit_' + user.id}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">-->
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editModalLabel">Edit user</h5>
                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="formEdit_${user.id}">
                                        <label for="edit-id" class="form-label" style="display: block; text-align: center;"><strong>ID</strong></label>
                                        <input type="text" class="form-control" style="width: 300px; margin: 0 auto" value="${user.id}" name="id" id="edit-id${user.id}" readonly>
                                        <br>
                                        <label for="edit-name" class="form-label" style="display: block; text-align: center;"><strong>Name</strong></label>
                                        <input type="text" class="form-control" style="width: 300px; margin: 0 auto" value="${user.username}" name="name" id="edit-name${user.id}" required>
                                        <br>
                                        <label for="edit-age" class="form-label" style="display: block; text-align: center;"><strong>Age</strong></label>
                                        <input type="text" class="form-control" style="width: 300px; margin: 0 auto" value="${user.age}" name="age" id="edit-age${user.id}" required>
                                        <br>
                                        <label for="edit-password" class="form-label" style="display: block; text-align: center;"><strong>Password</strong></label>
                                        <input type="password" class="form-control" style="width: 300px; margin: 0 auto" value="${user.password}" name="password" id="edit-password${user.id}" required>
                                        <br>
                                        <label class="form-label" style="display: block; text-align: center;"><strong>Role</strong></label>
                                        <select name="selectedRoles" id="edit-roles${user.id}" class="form-select" style="width: 300px; margin: 0 auto" aria-label="Default select" size="2">
                                            <option value="1">ADMIN</option>
                                            <option value="2">USER</option>
                                        </select>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <input type="submit" class="btn btn-primary" id="buttonEdit" value="Edit">
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
                `

                // delete form
                output += `
            
                <td>
                    <button type="button" style="color: white" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="${'#Modal_Delete_' + user.id}">
                        Delete</button>
                    <div class="modal fade" id="${'Modal_Delete_' + user.id}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">-->
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Delete user</h5>
                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form id="formDelete_${user.id}">
                                        <label for="delete-id" class="form-label" style="display: block; text-align: center;"><strong>ID</strong></label>
                                        <input type="text" class="form-control" style="width: 300px; margin: 0 auto" value="${user.id}" name="id" id="delete-id${user.id}" readonly>
                                        <br>
                                        <label for="delete-name" class="form-label" style="display: block; text-align: center;"><strong>Name</strong></label>
                                        <input type="text" class="form-control" style="width: 300px; margin: 0 auto" value="${user.username}" name="name" id="delete-name${user.id}" readonly>
                                        <br>
                                        <label for="delete-age" class="form-label" style="display: block; text-align: center;"><strong>Age</strong></label>
                                        <input type="text" class="form-control" style="width: 300px; margin: 0 auto" value="${user.age}" name="age" id="delete-age${user.id}" readonly>
                                        <br>
                                        <label for="delete-password" class="form-label" style="display: block; text-align: center;"><strong>Password</strong></label>
                                        <input type="password" class="form-control" style="width: 300px; margin: 0 auto" value="${user.password}" name="password" id="delete-password${user.id}" readonly>
                                        <br>
                                        <label class="form-label" style="display: block; text-align: center;"><strong>Role</strong></label>
                                        <select disabled name="selectedRoles" class="form-select" style="width: 300px; margin: 0 auto" aria-label="Default select" size="2">
                                            <option>${roles}</option>
                                        </select>
                                        <br>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <input type="submit" class="btn btn-danger" id="buttonDelete" value="Delete">
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            `

                output += `</tr>`
            })
            tbody.innerHTML = output
        })
}
