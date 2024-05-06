function createUser() {
    const url = "http://localhost:8080/api/admin/users"
    document.getElementById("profile").addEventListener('click', (e) => {
        e.preventDefault()
        let addButtonPressed = e.target.id === 'buttonAdd'
        if (addButtonPressed) {
            const name = document.getElementById('name')
            const age = document.getElementById('age')
            const password = document.getElementById('password')
            const rolesUser = document.getElementById('selectedRoles')

            console.log(rolesUser);

            let roles = []
            for (const option of rolesUser.selectedOptions) {
                if (option.value === '1') {
                    roles.push({
                        id: 1,
                        name: 'ROLE_ADMIN'
                    })
                }
                else if (option.value === '2') {
                    roles.push({
                        id: 2,
                        name: 'ROLE_USER'
                    })
                }
            }

            const d = {
                username: name.value,
                age: age.value,
                password: password.value,
                roles: roles
            }

            console.log(d);

            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(d)
            })
                .then(r => r.json())
                .then(() => location.reload())
        }
    })
}

createUser()