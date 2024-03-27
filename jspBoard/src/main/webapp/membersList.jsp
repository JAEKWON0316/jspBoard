<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%> <!-- -->
<%@ page import="java.sql.Connection , java.util.ArrayList, java.sql.Timestamp, jspBoard.dao.*, jspBoard.dto.MDto, java.text.SimpleDateFormat, java.text.NumberFormat"  %>  <!--  -->
<jsp:include page="inc/header.jsp" flush="true" />  
<%@ include file="inc/aside.jsp" %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- Bean으로 class를 불러오는 방법!! 이게 아니면 import후 new객체 생성 해줘야함-->
<!-- useBean으로 생성자 만듦 -->

<%        
          
          String st = request.getParameter("searchname"); //검색엔진
          String txt = request.getParameter("txt"); //검색엔진
         
         
    
       	                       
          Connection conn = db.getConnection(); //DBConnect의 conn을 불러오는것 -> 성공했으면 이것만 해도 접속이 완료된다.

          //System.out.println(conn); //출력이 잘 되는지 확인.
          MembersDao dao = new MembersDao(conn);
          ArrayList<MDto> list = null;
          
          list = dao.MselectDB();
          db.closeConnection();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); //날짜형식을 포맷 해주는 SimpleDateFormat 클래스
          NumberFormat formatter = NumberFormat.getInstance(); //getInstance()란 메소드로 객체화 NumberForamt을 ==> 세자리수 콤마가 생김!!
%>


    <section>
                <!--listbox-->
                <div class="listbox">
                    <h1 class="text-center mb-5">회원정보 관리</h1>
                    <div class="d-flex justify-content-between py-4 btn-box">
                        <div>
                            <label>총 회원수</label> : 000명 /  <label></label> 
                        </div>
                        <div>
                           <a href="/jspBoard" class="btn btn-dark">목록</a>
                                <% if(sess1.getAttribute("mid") != null){ %>
                                <a href="write.jsp" class="btn btn-white border-dark">글쓰기</a>
                                <% } %>
                        </div>
                    </div>
                    <table class="table table-hover">
                        <colgroup> <!-- 테이블 크기를 정해줄 때 해주는 방법!! colgroup + col(범위지정)-->
                            <col width="8%">
                            <col width="8%">
                            <col width="8%">
                            <col width="8%">
                            <col width="8%">
                            <col width="8%" />
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="8%"/>
                            <col width="8%"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>아이디</th>
                                <th>비밀번호</th>
                                <th>이름</th>
                                <th>이메일</th>
                                <th>전화번호</th>
                                <th>우편번호</th>
                                <th>주소1</th>
                                <th>주소2</th>
                                <th>링크</th>
                                <th>권한</th>
                                <th>가입날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- loop -->
                            <% 
                               int num = list.size(); //한 페이지에 보이는 번호를 10씩 빼누는 법
                            
                               for(int i = 0; i < list.size(); i++){ //List는 size가 크기임으로 이거로 for문을 돌리면서 값을 추출할 수 있다.
                            	   MDto dto = list.get(i);
                            	   int id = dto.getId();
                            	   String userid = dto.getUserid();
                            	   String userpass = dto.getUserpass();
                            	   String username = dto.getUsername();
                            	   String useremail = dto.getUseremail();
                            	   String usertel = dto.getUsertel();
                            	   int zipcode = dto.getZipcode();
                            	   String addr1 = dto.getAddr1();
                            	   String addr2 = dto.getAddr2();
                            	   String userlink = dto.getUserlink();
                            	   String role = dto.getRole();
                            	   Timestamp dates = dto.getWdate();
                            	   String wdate = sdf.format(dates); //위에서 정한 방식으로 sdf.format(값); 해준다.                           	
                            %>
                            <tr>
                                <td class="text-center"><%=num %></td>
                                <% if(sess1.getAttribute("mid") != null){ %>      
                                <td><a href="memedit.jsp?id=<%=id%>"> <!--  contents로 id와 cpg가 같이 가도록 -->
                                      <%=userid %>
                                    </a><span></span></td>
                                    <td class="text-center"><%=userpass %></td>
                                    <td class="text-center"><%=username %></td>
                                    <td class="text-center"><%=useremail %></td>
                                    <td class="text-center"><%=usertel %></td>
                                    <td class="text-center"><%=zipcode %></td>
                                    <td class="text-center"><%=addr1 %></td>
                                    <td class="text-center"><%=addr2 %></td>
                                    <td class="text-center"><%=userlink %></td>
                                   
                                    <!-- 
                                    <i class="ri-file-image-fill"></i>
                                    <i class="ri-file-hwp-fill"></i>
                                    <i class="ri-file-music-fill"></i>
                                     -->
                                                     
                                    <!-- 
                               <i class="ri-file-image-fill"></i>
                               <i class="ri-file-hwp-fill"></i>
                               <i class="ri-file-music-fill"></i>
                               -->   
                                                 
                                <% } %>                               
                                <td class="text-center"><%=role %></td>
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
                            <a href="/jspboard" class="btn btn-dark">최신글순</a>
                            <a href="/?sort=hit" class="btn btn-white border-dark">인기글순</a>
                        </div>
                        <ul class="paging">
                            <li>
                                <a href="#"><i class="ri-arrow-left-double-line"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="ri-arrow-left-s-line"></i></a>
                            </li>
                            <li><a href="#" class="active">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li>
                                <a href="#"><i class="ri-arrow-right-s-line"></i></a>
                            </li>
                            <li>
                                <a href="#"><i class="ri-arrow-right-double-line"></i></a>
                            </li>
                        </ul>
                            <div>
                                 <a href="/jspBoard" class="btn btn-dark">목록</a>
                                <% if(sess1.getAttribute("mid") != null){ %>
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