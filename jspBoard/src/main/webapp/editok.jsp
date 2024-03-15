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
    //Bean을 안 썻을 시!! (위에 import에 jspBoard.dto.BDto를 해주고 난 후 ! )
    //getParameter로 name에 대한 value를 뽑아온후
    //String writer = request.getParameter("wrtier");
    //String pass = request.getParameter("pass");
    //String title = request.getParameter("title");
    //String content = request.getParameter("content");
    //setter메소드로 값을 세팅해준 후 아래에서 getter메소드로 값을 받아온다.
    //BDto bDto = new BDto();
    //bDto.setWriter(writer);
    //bDto.setTitle(title);
    //bDto.setPass(pass);
    //bDto.setContent(content);
    //이런 과정을 위에 useBean과 setProperty 두줄로 다 해줄 수 있다.
     
   //Bean을 이용하면 id값으로 new객체를 생성한것처럼 쓸 수 있다!!!!.
   //String sql = "insert into jboard (title, content, writer, pass)" 
   //		         + "values (?, ?, ?, ?) ";
   //PreparedStatement pstmt = conn.prepareStatement(sql);
   //pstmt.setString(1, bDto.getTitle());
   //pstmt.setString(2, bDto.getContent());
   //pstmt.setString(3, bDto.getWriter());
   //pstmt.setString(4, bDto.getPass());
   //pstmt.executeUpdate();
   //pstmt.close();
   //conn.close();
  

   //response.sendRedirect("index.jsp"); //페이지로 넘어가는것을 안에 있는페이지로 리다이렉트 해준다.
   
 //이런식으로 객체화하고 가져와서 쓰는 write.jsp는 실행메소드 같은 느낌이다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<Script>
   alert("글을 등록했습니다.");
   location.href= "./contents.jsp?id=<%=id%>";
   //  ./ 는 현재페이지를 뜻한다.
</Script>
</head>
<body>
  <h1>데이터가 성공적으로 등록되었습니다.</h1>
</body>
</html>