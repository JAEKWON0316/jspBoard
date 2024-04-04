<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspBoard.service.*, jspBoard.dto.*,java.sql.*, java.text.*, java.util.ArrayList"%> 
<%
   
   ArrayList<CDto> lists = db.getCommentList(id); //contents 하위에 이 폴더가 있으니 contents에서 바로 id값을 받아올 수 있다.  
   for(CDto list : lists) {
  
%>   
   
    <li class="list-group-item flex-column p-5 align-items-start">
                  <div class="d-flex w-100 justify-content-between">
                     <div class="mb-1 col-9">[<%=list.getUsername() %>] <%=list.getComment() %></div>
                     <div class="mb-1 col-2 text-right">
                        <%=list.getWdate() %>
                     </div>
                     <div class="btn-box col-1 text-right">
                        <i class="ri-more-2-fill"></i>
                        <div class="edel">
                           <a href="edit">수정</a>
                           <a href="del">삭제</a>
                        </div>
                     </div>
                  </div>
     </li>

 <% } %>   