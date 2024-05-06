function fillHeader() {
    const url = "http://localhost:8080/api/admin/authUser"
    let navbar = document.getElementById("navbar-toggle-button")
    let output = `<div class="container-fluid">`

    fetch(url)
        .then(res => res.json())
        .then(data => {
            output += `
                <span style = "color: white">
            `

            output += `
                <span>${data.username}</span>
                <span>with roles: </span>
            `
            output += `<span>`
            data.roles.forEach(role => {
                output += role.name.replace("ROLE_", '') + ' ';
            })

            output += `</span>`
            output += `</span>`
            output += `<a class="btn btn-link" style="text-decoration: none; color: gray" href="/logout">Logout</a>`
            navbar.innerHTML = output
        })
}
fillHeader()


