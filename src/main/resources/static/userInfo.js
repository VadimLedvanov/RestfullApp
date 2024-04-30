function authUser() {
    const url = "http://localhost:8080/api/authUser"
    let tbody = document.getElementById("user-tbody")
    let output = ""

    fetch(url)
        .then(res => res.json())
        .then(data => {
            let roles = ''
            output += `
                <tr>
                    <td>${data.id}</td>
                    <td>${data.username}</td>
                    <td>${data.age}</td>
                    <td>     
                `

            data.roles.forEach(role => {
                output += role.name.replace("ROLE_", '') + ' ';
            })
            output += "</td>"
            output += "</tr>"
            tbody.innerHTML = output
        })
}
authUser()