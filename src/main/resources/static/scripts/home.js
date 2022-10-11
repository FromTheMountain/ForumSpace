const forumLists = document.querySelectorAll('.forum-list')

forumLists.forEach(forumListDiv => {
    let forumDivs = forumListDiv.children;

    for (let i = 0; i < forumDivs.length; i++) {
        if (i % 2 == 0) {
            forumDivs[i].classList.add('even-index-forum')
        } else {
            forumDivs[i].classList.add('odd-index-forum')
        }
    }    
})
