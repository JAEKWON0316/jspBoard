<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*, jspBoard.dto.BDto, jspBoard.dao.JBoardDao" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- 자바Bean파일을 쓰겠다. -->
<jsp:useBean id="bDto" class="jspBoard.dto.BDto" scope="page" /> <!-- 빈 객체 생성 / jspBoard bDto = new jspBoard(); 와 같은 뜻 -->
<jsp:setProperty name="bDto" property="*" /> <!--useBean을 사용해서 bDto 클래스에 있는모든 필드값을 setting하겠다 -->
<%

   String id = request.getParameter("id");
   Connection conn = db.conn;
   JBoardDao dao = new JBoardDao(conn);
   int rs = dao.updateDB(bDto);
 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<Script>
   alert("글을 등록했습니다.");
   location.href= "./contents.jsp?id=<%=id%>";
 
</Script>
</head>
<body>
  <h1>데이터가 성공적으로 등록되었습니다.</h1>
</body>
</html>