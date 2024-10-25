function commentSend(){
    if($("#content").val() === ""){
        alert("댓글 입력이 필요합니다.")
        console.log($("#content").val());
        return;
    }
    let data = $("#frm").serialize();
    console.log(data);

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