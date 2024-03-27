<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*, jspBoard.dto.MDto, jspBoard.dao.MembersDao" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- 자바Bean파일을 쓰겠다. -->
<jsp:useBean id="mDto" class="jspBoard.dto.MDto" scope="page" /> <!-- 빈 객체 생성 / jspBoard bDto = new jspBoard(); 와 같은 뜻 -->
<jsp:setProperty name="mDto" property="*" /> <!--useBean을 사용해서 bDto 클래스에 있는모든 필드값을 setting하겠다 -->
<%

   Connection conn = db.getConnection();
   MembersDao dao = new MembersDao(conn);
   int rs = dao.updateMem(mDto);
   db.closeConnection();
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<Script>
   alert("회원정보를 수정하였습니다.");
   location.href= "./index.jsp";
   //  ./ 는 현재페이지를 뜻한다.
</Script>
</head>
<body>
  <h1>데이터가 성공적으로 등록되었습니다.</h1>
</body>
</html>