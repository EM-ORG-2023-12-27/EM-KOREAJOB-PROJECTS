const apply_seeeker_btn = document.querySelectorAll('.apply_seeeker_btn');

apply_seeeker_btn.forEach(btn=>{
    btn.addEventListener('click',function(){

        const recruit_id =  btn.getAttribute('data-recruitid');

        axios.get(`/apply/offer/list?recruit_id=${recruit_id}`)
        .then(resp=>{

            console.log(resp);

            const modalBtn = document.querySelector('.apply_user_modal_btn');
            modalBtn.click();

         })
        .catch(err=>{console.log(err);})



    })
})