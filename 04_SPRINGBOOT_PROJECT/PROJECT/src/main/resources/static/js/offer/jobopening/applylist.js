const apply_seeeker_btn = document.querySelectorAll('.apply_seeeker_btn');

apply_seeeker_btn.forEach(btn=>{
    btn.addEventListener('click',function(){

        const recruit_id =  btn.getAttribute('data-recruitid');

        axios.get(`/apply/offer/list?recruit_id=${recruit_id}`)
        .then(resp=>{

            console.log(resp);

            const modalBtn = document.querySelector('.apply_user_modal_btn');

            createModalHeaderEls(resp.data);

            modalBtn.click();

         })
        .catch(err=>{console.log(err);})



    })
})


function createModalHeaderEls(array){
    const modalHeader = document.querySelector('.modal-content .modal-header .user-btn-block');
    while (modalHeader.firstChild) {
            modalHeader.removeChild(modalHeader.firstChild);
    }

    const title = document.querySelector('.modal .table .title')
    array.forEach(el=>{
        const li = document.createElement('li');
        const a= document.createElement('a');
        a.innerHTML=el.resume.name;
        a.setAttribute('href',"javascript:void(0)");
        a.setAttribute('style',"display:block;");
        a.setAttribute('data-applyid',el.apply_id);
        a.classList.add('apply_user_resume_btn');
        a.addEventListener('click',function(){
                console.log("clicked...");

            title.innerHTML = el.resume.title;

        })
        li.append(a);
        modalHeader.append(li);
    })
}

