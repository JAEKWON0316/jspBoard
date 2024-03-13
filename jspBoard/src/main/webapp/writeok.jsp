<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- 자바Bean파일을 쓰겠다. -->
<jsp:useBean id="bDto" class="jspBoard.dto.BDto" scope="page" /> <!-- 빈 객체 생성 / jspBoard bDto = new jspBoard(); 와 같은 뜻 -->
<jsp:setProperty name="bDto" property="*" /> <!--useBean을 사용해서 bDto 클래스에 있는모든 필드값을 setting하겠다 -->
<%
   //Bean을 이용하면 id값으로 new객체를 생성한것처럼 쓸 수 있다!!!!.
   Connection conn = db.conn;
   String sql = "insert into jboard (title, content, writer, pass)" 
		         + "values (?, ?, ?, ?) ";
   PreparedStatement pstmt = conn.prepareStatement(sql);
   pstmt.setString(1, bDto.getTitle());
   pstmt.setString(2, bDto.getContent());
   pstmt.setString(3, bDto.getWriter());
   pstmt.setString(4, bDto.getPass());
   pstmt.executeUpdate();
   pstmt.close();
   conn.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>데이터가 성공적으로 등록되었습니다.</h1>
</body>
</html>