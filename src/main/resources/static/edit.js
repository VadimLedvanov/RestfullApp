
editUser();
function editUser(){
    const url = 'http://localhost:8080/api/users'
    let userId;
    document.getElementById('users-table')
        .addEventListener('click', ev => {
            ev.preventDefault();

            let editButtonPressed = ev.target.id === 'buttonEdit';

            userId = ev.target.parentElement.parentElement.id.value;

            if (editButtonPressed) {
                const nameEdit = document.getElementById(`edit-name${userId}`)
                const passwordEdit = document.getElementById(`edit-password${userId}`)
                const rolesEdit = document.getElementById(`edit-roles${userId}`)
                const ageEdit = document.getElementById(`edit-age${userId}`)


                let editRoles = [];
                for (const option of rolesEdit.selectedOptions) {
                    if (option.value === '1') {
                        editRoles.push({
                            id: 1,
                            name: 'ROLE_ADMIN'
                        })
                    }
                    else if (option.value === '2') {
                        editRoles.push({
                            id: 2,
                            name: 'ROLE_USER'
                        })
                    }
                }

                const u = {
                    id: userId,
                    username: nameEdit.value,
                    age: ageEdit.value,
                    password: passwordEdit.value,
                    roles: editRoles
                }

                fetch(url, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(u)
                })
                    .then(r => r.json())
                    .then(() => location.reload())
            }

        })
}