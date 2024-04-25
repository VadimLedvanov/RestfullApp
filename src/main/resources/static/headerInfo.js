function fillHeader() {
    const url = "http://localhost:8080/api/authUser"
    let navbar = document.getElementById("navbar-toggle-button")
    let output = `<div class="container-fluid">`

    fetch(url)
        .then(res => res.json())
        .then(data => {
            let roles = ''
            output += `
                <span style = "color: white">${data.username}</span>
                <span style="color: white" class="text-start">with roles: </span>
            `
            output += `<span style="color: white">`
            data.roles.forEach(role => {
                roles += role.name.replace("ROLE_", '') + ' ';
            })
            output += roles
            output += `</span>`
            output += `<a href="/logout">Logout</a>`
            navbar.innerHTML = output
        })
}

fillHeader()

