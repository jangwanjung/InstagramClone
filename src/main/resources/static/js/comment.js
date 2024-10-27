function commentSend(imageId){
    let content = $("#content-"+imageId).val();
    if(content == "" || content == "null"){
        alert("댓글 입력이 필요합니다.");
        return;
    }
    let data = $("#frm-"+imageId).serialize();
    console.log(data);
    alert(data)

    fetch("/comment", {
        method: "post",
        body: data,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
        }
    }).then(function (res){
        return res.text();
    }).then(function (res){
        alert("댓글 작성 완료");
        location.reload();
    });
}