
deleteUser();
function deleteUser(){
    let userId;
    document.getElementById('users-table')
        .addEventListener('click', ev => {
        ev.preventDefault();

        let delButtonPressed = ev.target.id === `buttonDelete`;

        userId = ev.target.parentElement.parentElement.id.value;
        if (delButtonPressed) {
            fetch("http://localhost:8080/api/admin/users/" + userId, {
                method: 'DELETE',
            })
                .then(res => res.json())
                .then(() => location.reload())
        }

    })
}