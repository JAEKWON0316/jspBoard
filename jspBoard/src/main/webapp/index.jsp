<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%> <!-- -->
<%@ page import="java.sql.Connection , java.util.ArrayList, java.sql.Timestamp, jspBoard.dao.*, jspBoard.dto.BDto, java.text.SimpleDateFormat"  %>  <!--  -->
<jsp:include page="inc/header.jsp" flush="true" />  
<%@ include file="inc/aside.jsp" %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- Bean으로 class를 불러오는 방법!! 이게 아니면 import후 new객체 생성 해줘야함-->


<%        
          
          String st = request.getParameter("searchname");
          String txt = request.getParameter("txt");
       
          Connection conn = db.conn; //DBConnect의 conn을 불러오는것 -> 성공했으면 이것만 해도 접속이 완료된다.

          //System.out.println(conn); //출력이 잘 되는지 확인.
          JBoardDao dao = new JBoardDao(conn);
          ArrayList<BDto> list = null;
          
          if(st == null || st.trim().isEmpty()){ //st에 값이 없으면
        	  list = dao.selectDB(); //selectDB() 기본 메소드 실행
          }
          else{
        	  list = dao.selectDB(st, txt);
          }
          
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd"); //날짜형식을 포맷 해주는 SimpleDateFormat 클래스
  
%>


    <section>
                <!--listbox-->
                <div class="listbox">
                    <h1 class="text-center mb-5">게시판</h1>
                    <div class="d-flex justify-content-between py-4 btn-box">
                        <div>
                            <label>총 게시글</label> : 000 / 000page
                        </div>
                        <div>
                            <a href="./" class="btn btn-dark">목록</a>
                            <a href="write.jsp" class="btn btn-white border-dark">글쓰기</a>
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
                               int num = list.size();
                            
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
                            %>
                            <tr>
                                <td class="text-center"><%=num %></td>
                                <td><a href="contents.jsp?id=<%=id%>">
                                      <%=styleDepth%><%=title %>
                                    </a><span></span>
                                    <!-- 
                                    <i class="ri-file-image-fill"></i>
                                    <i class="ri-file-hwp-fill"></i>
                                    <i class="ri-file-music-fill"></i>
                                     -->
                                </td>

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
                                <a href="#" class="btn btn-dark">목록</a>
                                <a href="write.jsp" class="btn btn-white border-dark">글쓰기</a>
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