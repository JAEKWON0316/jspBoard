<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%> <!-- -->
<%@ page import="java.util.ArrayList, java.sql.Timestamp, jspBoard.service.*, jspBoard.dto.BDto, java.text.SimpleDateFormat, java.text.NumberFormat"  %>  <!--  -->  
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt"%> <!-- 변환에 특화된 태그(날짜형식, 계산형식 등 -->
<!-- useBean으로 생성자 만듦 -->  
<jsp:include page="inc/header.jsp" flush="true" />  
<jsp:include page="inc/aside.jsp" />

<%       
          HttpSession sess = request.getSession(true);
          String st = request.getParameter("searchname"); //검색엔진 sname
          String txt = request.getParameter("txt"); //검색엔진 svalue
          ServletContext cont = getServletContext();
          
          try{
        	TrashFile trashfile = new TrashFile(cont);  
          }
          catch(Exception e){
        	  e.printStackTrace();
          }
                
          /* 페이징 변수들 */
          int pg; //받아올 현재 페이지 번호
          int cnt;    //1.전체 게시글 수  
          int listCount = 10;   //2.한 페이지에 보일 목록 수
          int pageCount = 10;  //3.한 페이지에 보일 페이지 수
          int limitPage; //4.쿼리문으로 보낼 시작페이지
          
          String cpg = request.getParameter("cpg"); //request로 받을것이기 때문에 String 타입으로 받은 후 integer로 변환해야함
          //pg = (cpg == null)? 1 : Integer.parseInt(cpg); //3항 연산으로 아래 if else문이랑 똑같이 만든것
          if(cpg == null){
             pg = 1;
          }
          else{
        	  pg = Integer.parseInt(cpg);
          }
                
          limitPage = (pg -1) * listCount; // (현재페이지 - 1) X 목록 수 0, 10, 20 .. 단위로 잘라지게 됨.
          //페이징 연산 끝
         
                	                       
         //Connection conn = db.getConnection(); //DBConnect의 conn을 불러오는것 -> 성공했으면 이것만 해도 접속이 완료된다.
         //System.out.println(conn); //출력이 잘 되는지 확인.
         ArrayList<BDto> list = null;
         DbWorks db = new DbWorks(limitPage, listCount, st, txt);
          
          
          //전체 게시글의 수를 가져옴*/
          cnt = db.getAllSelect(); 
          //담겨진 정보들을 가지고 현재페이지, 전체글 수, 페이지 수, 글목록 수로 paging 클래스 호출.
          Paging myPage = new Paging(pg, cnt, pageCount, listCount);
                          
          if(st == null || st.trim().isEmpty()){ //st에 값이 없으면
        	  list = db.getList(); //selectDB() 기본 메소드 실행
          }
          else{
        	  list = db.getSearchList();
          }
               
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); //날짜형식을 포맷 해주는 SimpleDateFormat 클래스
          NumberFormat formatter = NumberFormat.getInstance(); //getInstance()란 메소드로 객체화 NumberForamt을 ==> 세자리수 콤마가 생김!!
%>


    <section>
                <!--listbox-->
                <div class="listbox">
                    <h1 class="text-center mb-5">게시판</h1>
                    <div class="d-flex justify-content-between py-4 btn-box">
                        <div>
                            <label>총 게시글</label> : <%=formatter.format(cnt)%>개 /  <label>총 페이지</label> : <%=formatter.format(myPage.getTotalPages()) %>page
                        </div>
                        <div>
                           <a href="/jspBoard" class="btn btn-dark">목록</a>
                           <%-- 
                                <% if(sess.getAttribute("mid") != null){ %>
                                <a href="write.jsp" class="btn btn-white border-dark">글쓰기</a>
                                <% } %>
                            --%>
                                <c:if test="${not empty sess.mid }"> 
                                   <a href="write.jsp" class="btn btn-primary">글쓰기</a>
                                </c:if>
                        </div>
                    </div>
                    <table class="table table-hover">
                        <colgroup> <!-- 테이블 크기를 정해줄 때 해주는 방법!! colgroup + col(범위지정)-->
                            <col width="8%">
                            <col width="52%">
                            <col width="15%">
                            <col width="8%">
                            <col width="15%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>글쓴이</th>
                                <th>조회수</th>
                                <th>날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- loop -->
                            <% 
                               int num = cnt - (listCount * (pg -1)); //한 페이지에 보이는 번호를 10씩 빼누는 법
                            %>
                                 
                            <% 
                               for(int i = 0; i < list.size(); i++){ //List는 size가 크기임으로 이거로 for문을 돌리면서 값을 추출할 수 있다.
                            	   BDto dto = list.get(i);
                            	   int id = dto.getId();
                            	   int depth = dto.getDepth();
                            	   String title = dto.getTitle();
                            	   String writer = dto.getWriter();
                            	   int hit = dto.getHit();
                            	   int chit = dto.getChit();
                            	   Timestamp dates = dto.getWdate();
                            	   String wdate = sdf.format(dates); //위에서 정한 방식으로 sdf.format(값); 해준다.
                            	   String styleDepth = "";
                                   if(depth > 0){
                                	   String padding = (depth*10)+"px";
                                	   String reicon = "<i class=\"ri-corner-down-right-line\"></i>";
                                	   styleDepth = "<span style='display:inline-block;width:" + padding + "'></span>"+reicon+" ";
                                   }
                                   String commentHit = "";
                                   if(chit > 0){
                                	   commentHit = " ("+chit+")";
                                   }
                            %>
                            <tr>
                                <td class="text-center"><%=num %></td>
                                <% if(sess.getAttribute("mid") != null){ %>      
                                <td><a href="contents.jsp?id=<%=id%>&cpg=<%=pg%>"> <!--  contents로 id와 cpg가 같이 가도록 -->
                                      <%=styleDepth%><%=title %><%=commentHit %>
                                    </a><span></span>
                                    <!-- 
                                    <i class="ri-file-image-fill"></i>
                                    <i class="ri-file-hwp-fill"></i>
                                    <i class="ri-file-music-fill"></i>
                                     -->
                                </td>
                                <% }else{ %>                              
                                <td>
                               <a href="javascript:void(0)"><%=styleDepth%><%=title %><%=commentHit %>
                               </a><span></span>
                                    <!-- 
                               <i class="ri-file-image-fill"></i>
                               <i class="ri-file-hwp-fill"></i>
                               <i class="ri-file-music-fill"></i>
                               -->   
                               </td>                                
                                <% } %>
                                <td class="text-center"><%=writer %></td>
                                <td class="text-center"><%=hit %></td>
                                <td class="text-center"><%=wdate %></td>
                            </tr>                
                            <%
                                num--;
                               }
                               
                            %>
                            <!--/loop-->
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-between py-4 btn-box">
                        <div>
                           
                        </div>
                        <%
                          //검색일 때 처리
                          String query = "";
                          if(st != null){
                  	        query = "&searchname="+st+"&txt="+txt;
                          }
                        %>
                        <ul class="paging">
                            <li>
                                <a href="?cpg=1<%=query%>"><i class="ri-arrow-left-double-line"></i></a>
                            </li>
                            <li>
                            <%                            
                               if(myPage.getStartPage() - 1 == 0){
                            %>
                               <a href="?cpg=<%=myPage.getStartPage()%><%=query%>"><i class="ri-arrow-left-s-line"></i></a>
                            <% }else{ %>
                                <a href="?cpg=<%=myPage.getStartPage()-1%><%=query%>"><i class="ri-arrow-left-s-line"></i></a>
                            <% } %>
                            </li>
                            
                            <%
                            
                              for(int i = myPage.getStartPage(); i <= myPage.getEndPage(); i++){
                            	  if(pg == i) {
                            		  out.println("<li><a href=\"?cpg="+i+query+"\" class=\"active\">"+i+"</a></li>");
                            	  }else{
                            		  out.println("<li><a href=\"?cpg="+i+query+"\">"+i+"</a></li>");
                            	  }
                              }
                            
                            %>
                                              
                             <li>
                            <%                                                    
                               if(myPage.getEndPage() + 1 > myPage.getTotalPages()){
                            %>
                               <a href="?cpg=<%=myPage.getEndPage()%><%=query%>"><i class="ri-arrow-right-s-line"></i></a>
                            <% }else{ %>
                                <a href="?cpg=<%=myPage.getEndPage() + 1%><%=query%>"><i class="ri-arrow-right-s-line"></i></a>
                            <% } %>
                            </li>
                            <li>
                                <a href="?cpg=<%=myPage.getTotalPages()%><%=query%>"><i class="ri-arrow-right-double-line"></i></a>
                            </li>
                        </ul>
                            <div>
                                <a href="/jspBoard" class="btn btn-dark">목록</a>
                                <% if(sess.getAttribute("mid") != null){ %>
                                <a href="write.jsp" class="btn btn-white border-dark">글쓰기</a>
                                <% } %>
                            </div>
                        </div>
                        <form  name="searchform" id="searchform" class="searchform" method="get"> <!-- 여기 페이지에서 get하겠다. -->
                                <div class="input-group my-3">
                                    <div class="input-group-prepend">
                                        <button type="submit" 
                                        class="btn btn-outline-secondary dropdown-toggle" 
                                        data-toggle="dropdown"   
                                        name="search"                                    
                                        value="title">
                                            제목검색
                                          </button>
                                          <input type="hidden" name="searchname" id="searchname" value="title">                                      
                                          <div class="dropdown-menu">
                                            <a class="dropdown-item" href="writer">이름검색</a>
                                            <a class="dropdown-item"  href="title">제목검색</a>
                                            <a class="dropdown-item" href="content">내용검색</a>
                                          </div>
                                    </div>
                                                                                 
                                    <input type="search" class="form-control" placeholder="검색" name="txt">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-primary"><i class="ri-search-line"></i></button>
                                    </div>
                                </div>
                        </form>
                      </div>
                    <!--/listbox-->                        
               
            </section>
          
    <%@ include file="inc/footer.jsp" %>     