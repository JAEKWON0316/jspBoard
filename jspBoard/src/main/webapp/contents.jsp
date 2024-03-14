<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jspBoard.dao.*, jspBoard.dto.BDto" %> <!-- *은 java.sql.에 있는 모든 클래스를 다 쓸 수 있다. -->
<%@ include file="inc/header.jsp" %>
<%@ include file="inc/aside.jsp" %> 
<% DBConnect db = new DBConnect(); //db접속
   Connection conn = db.conn;
   JBoardDao dao = new JBoardDao(conn); //viewDB()메소드를 쓰기위해 dao 객체 생성
   BDto dto = new BDto(); //viewDB 리턴 타입인 dto를 받기 위해 dto 객체 생성
   String id = request.getParameter("id");
   BDto rs = dao.viewDB(id); 
  
%>


   <section>
     <!--  listbox -->
     <div class="listbox">
        <h3 class="mt-5"><i class="ri-arrow-right-double-line"></i><%=rs.getTitle() %></h3>
          <div class="mt-2 pt-2 border-top text-right">
            <span class="mr-4"><label class="font-italic">hit:</label><%=rs.getHit() %></span>
            <span class="mr-4"><%=rs.getWriter() %></span>
            <span class="mr-2">2024.03.18</span>
          </div>
          <!-- 
          <div class="mt-2 pt-2 border-top file-box">
             <span>
                 <label class="font-italic">file :</label>
                 <a href="#">asdf.gif</a>  <a href="#">afdfd.asdf</a>
              </span>
          </div>
            -->
          <div class="content-box mt-3">
             <%=rs.getContent() %>
          </div>
         
          <div class="my-5 pt-5 text-right">
             <a href="#" class="btn btn-primary mr-3">목록</a>
             <a href="rewrite.jsp" class="btn btn-primary">답글쓰기</a>
          </div>
      </div>
   </section>
 
<%@ include file="inc/footer.jsp" %>
