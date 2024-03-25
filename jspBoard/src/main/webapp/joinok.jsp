<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*, jspBoard.dto.BDto, jspBoard.dao.MembersDao" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- 자바Bean파일을 쓰겠다. -->
<jsp:useBean id="mDto" class="jspBoard.dto.MDto" scope="page" /> <!-- 빈 객체 생성 / jspBoard bDto = new jspBoard(); 와 같은 뜻 -->
<jsp:setProperty name="mDto" property="*" /> <!--useBean을 사용해서 bDto 클래스에 있는모든 필드값을 setting하겠다 -->
<%
    
 
   Connection conn = db.getConnection();
   MembersDao dao = new MembersDao(conn);

   int rs = dao.insertDB(mDto);
   
   db.closeConnection();
   //response.sendRedirect("index.jsp"); //페이지로 넘어가는것을 안에 있는페이지로 리다이렉트 해준다.
   

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
    <script>
  alert("회원가입이 완료되었습니다.");
  location.href="./index.jsp";
</script>
    
</body>
</html>