async function follow(pageUserId) {
    let response = await fetch('/follow/' + pageUserId, {
        method: "post"
    });
    let result = await response.text();
    if(result === 'ok'){
        location.reload();
    }
}
async function unFollow(pageUserId) {
    let response = await fetch('/follow/' + pageUserId, {
        method: "delete"
    });
    let result = await response.text();
    if(result === 'ok'){
        location.reload();
    }
}