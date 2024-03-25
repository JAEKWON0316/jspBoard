<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jspBoard.dao.JBoardDao, jspBoard.dto.BDto" %>    
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" />
<%
   String id = request.getParameter("id");
   String cpass = request.getParameter("cpass");
   String mode = request.getParameter("mode");
   Connection conn = db.getConnection();
   JBoardDao dao = new JBoardDao(conn);
   int result = dao.findPass(id, cpass);
   db.closeConnection();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%   
   if(result == 1){
    if(mode.equals("edit")){  
    BDto dto = new BDto();
    BDto rs = dao.viewDB(id);
    String title = rs.getTitle();
    String pass = rs.getPass();
    String content = rs.getContent();
    String writer = rs.getWriter();
%>
   <%@ include file="edit.jsp" %>
<%
    }else{
     int rs = dao.deleteDB(id, cpass); 
       if(rs == 0){
%>
       <script>
         alert("내용을 삭제하는데 문제가 발생했습니다.");
         history.go(-1);
       </script>       
<%        
      }else{
%>
       <script>
         alert("삭제가 완료 되었습니다.");
         location.href="/jspBoard";
       </script>  
<%       
       }
    }  
   }else{
%>
   <script>
    alert("비밀번호를 다시 확인하세요.");
    history.go(-1);
   </script>
<%      
   }
%>    


</body>
</html>