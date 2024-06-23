    const seeker_join =  document.querySelector('.seeker_join');
    const offer_join = document.querySelector('.offer_join');

    const seekerJoin = document.seekerJoin;
    const offerJoin = document.offerJoin;

    seeker_join.addEventListener('click',function(){
       console.log('clicked..');
       offerJoin.classList.add('hidden');
       seekerJoin.classList.remove('hidden');
    })

    offer_join.addEventListener('click',function(){
       console.log('clicked..');
       seekerJoin.classList.add('hidden');
       offerJoin.classList.remove('hidden');
    })