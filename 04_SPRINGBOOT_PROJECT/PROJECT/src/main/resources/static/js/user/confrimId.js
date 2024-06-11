const confirmId_btn = document.querySelector('.confirmId_btn');
confirmId_btn.addEventListener('click',function(){
    const check_id_form = document.checkIdform;
    console.log(check_id_form);
    const email = check_id_form.email.value;

    const formData = new FormData();
    formData.append('email', email);


    axios.post('/user/confirmId', formData ,  {headers: {'Content-Type': 'application/json'}})
    .then(resp=>{
        console.log(resp)
        alert("EMAIL : " + resp.data);
        }
    )
    .catch(err=>{
        console.log(err);
        alert(err.response.data);
    })

    console.log("confirmId_btn clicked..");


})