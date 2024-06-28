const recruit_btn = document.querySelector('.recruit_btn')
recruit_btn.addEventListner('click',function(){
    //이력서 가져오기
    axios.get('/seeker/resume/my')
})