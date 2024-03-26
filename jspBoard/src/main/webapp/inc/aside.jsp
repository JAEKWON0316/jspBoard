<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
   //세션
   //쿠키
   HttpSession sess1 = request.getSession(true);
   Cookie[] cooks1 = request.getCookies();  //웹브라우저에 저장된 모든 쿠키를 받는다.
   String rememberId = "";
   
   if((cooks1 != null) && (cooks1.length > 0)){
	     for(int i=0; i<cooks1.length; i++){ //쿠키의 개수만큼 for문을 돌린다
	    	 if(cooks1[i].getName().equals("uid")){ //만약 cookies 배열의 이름이 uid와 같다면 
	    		 rememberId = cooks1[i].getValue(); //rememberId는 그 값이 된다.
	    	 }
	     }
   }
   
%>
        <!-- ASIDE -->
         <aside class="mt-3 p-4 bg-white">
<%
    if(sess1.getAttribute("mid") == null){
%>
            <form name="loginForm" action="login" class="loginform" id="loginform" method="post">
               <input type="text" class="form-control userid mb-4" id="uid"
                      placeholder="아이디" name="uid" value="<%=rememberId %>" />
               <input type="password" class="form-control userpass mb-3" id="upass"
                      placeholder="비밀번호" name="upass" />      
               <div class="text-right mb-3">
                 <label> 아이디 기억 <input type="checkbox" name="rid" value="rid" id="rid"></label>
               </div>         
               <button type="submit" class="btn btn-dark btn-block">로그인</button>                     
               <a href="join.jsp">회원가입</a>
            </form>
<%
    }else{
        int mid = (int) sess1.getAttribute("mid");
    	String userid = (String) sess1.getAttribute("userid");  //타입이 세션이니까 String으로 강제타입변환 해줘야함.
    	String username = (String) sess1.getAttribute("username");
    	String usereamil = (String) sess1.getAttribute("useremail");
    	String role = (String) sess1.getAttribute("role");
    
%>    
      <div class="userbox">
      <ul>
         <li><p>환엽합니다. <%=username %>님</p></li>
         <li><a href="memedit.jsp?mid=<%=mid%>">회원정보 수정</a></li>
         <li><a href="logout">로그아웃</a></li>
         <% if(role.equals("ADMIN")){ %>
         <li><a href="membersList.jsp">회원관리</a></li>
         <% } %>
      </ul>
      </div>
<% } %>

         </aside>
         <!--/ ASIDE-->
