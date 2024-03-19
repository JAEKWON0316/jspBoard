$(document).ready(function(){
    //경고창을 팝업으로
    $.validator.setDefaults({
       onkeyup: false,
       onclick: false,
       onfocusout: false,
       showErrors: function(errorMap, errorList){
          if(this.numberOfInvalids()){  //만약 에러가 있으면
            alert(errorList[0].message);  //alert으로 출력
          }
       }
    });
    
    $("#registerform").validate({
        rules: {
          username: {
             required: true
          },
          fuserid: {
             required: true,
             rangelength: [3, 10],
             remote: { type: "post", url: "inc/check.jsp?mode=id" }
          },
          fuserpass: {
             required: true,
          },
          freuserpass: {
             required: true,
             equalTo: "#fuserpass"
          },
          useremail: {
             required: true,
             remote: { type: "post", url: "inc/check.jsp?mode=email" }
          }
        },
        messages : {
          username: {
             required: "이름을 입력 하세요."
          },
          fuserid: {
             required: "아이디를 입력하세요.",
             rangelength: "아이디는 {0}자에서 {1}자까지 사용이 가능합니다.",
             remote: "이미 등록되어 있는 아이디 입니다."
          },
          fuserpass: {
             required: "비밀번호를 입력하세요."
          },
          freuserpass: {
             required: "비밀번호를 확인하세요.",
             equalTo: "비밀번호가 같지 않습니다. 다시 확인하세요."
          },
          useremail:{
             required: "이메일을 입력하세요.",
             remote: "이미 등록되어 있는 이메일입니다."
          }
          
       }
    });


   
});