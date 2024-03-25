<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*, jspBoard.dto.BDto, jspBoard.dao.JBoardDao" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- 자바Bean파일을 쓰겠다. -->
<jsp:useBean id="bDto" class="jspBoard.dto.BDto" scope="page" /> <!-- 빈 객체 생성 / jspBoard bDto = new jspBoard(); 와 같은 뜻 -->
<jsp:setProperty name="bDto" property="*" /> <!--useBean을 사용해서 bDto 클래스에 있는모든 필드값을 setting하겠다 -->
<%
    
   String cpg = request.getParameter("cpg");
   Connection conn = db.getConnection();
   JBoardDao dao = new JBoardDao(conn);
   if(bDto.getDepth() > 0 ){
	   bDto.setRefid(bDto.getRefid()); //순서를 맞춰주기 위해 답글의 답글을 달 때는 id값이 아니라 refid값을 넘겨준다.    
   }
   else{
	   bDto.setRefid(bDto.getId());   
   }  
   bDto.setDepth(bDto.getDepth()+1);
   bDto.setRenum(bDto.getRenum());
   int rs = dao.insertDB(bDto);
   
   db.closeConnection();

   //response.sendRedirect("index.jsp"); //페이지로 넘어가는것을 안에 있는페이지로 리다이렉트 해준다.
   
 //이런식으로 객체화하고 가져와서 쓰는 write.jsp는 실행메소드 같은 느낌이다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
  alert("글을 등록했습니다.");
  location.href="./index.jsp?cpg=<%=cpg %>";
</script>

</head>
<body>
<h1>데이터가 성공적으로 등록 되었습니다.</h1>
</body>
</html>