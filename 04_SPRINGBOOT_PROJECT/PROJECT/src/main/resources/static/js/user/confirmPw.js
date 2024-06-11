document.addEventListener('DOMContentLoaded', function() {
    const confirmPw_btn = document.querySelector('.confirmPw_btn');

    confirmPw_btn.addEventListener('click', function(event) {
        event.preventDefault();
        
        const check_pw_form = document.checkPwform;
        const username = check_pw_form.username.value;
        const email = check_pw_form.email.value;

        const formData = new URLSearchParams();
        formData.append('username', username);
        formData.append('email', email);

        axios.post('/user/confirmPw', formData, { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
            .then(resp => {
                console.log(resp);
                alert(resp.data);
            })
            .catch(err => {
                console.log(err);
                alert(err.response.data);
            });

        console.log("confirmPw_btn clicked..");
    });
});
