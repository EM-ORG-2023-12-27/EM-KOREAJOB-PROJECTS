const confirmIdBtn = document.querySelector('.confirmId_btn');
confirmIdBtn.addEventListener('click',function(){

    const form = document.confirmForm;

    const nickname = form.nickname.value;
    const phone = form.phone.value;
    const type = form.phone.type;

    let formData = new FormData();
    formData.append('nickname',nickname);
    formData.append('phone',phone);
    formData.append('type',type);

    axios.post("/user/confirmId",formData)
    .then(resp=>{
            console.log(resp);
            alert(resp.data);
        }
    )
    .catch(err=>{console.log(err);})

})