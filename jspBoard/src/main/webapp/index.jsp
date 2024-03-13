<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- -->
<%@ page import="java.sql.Connection"  %>  <!--  -->
<jsp:include page="inc/header.jsp" flush="true" />  
<%@ include file="inc/aside.jsp" %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page" /> <!-- Bean으로 class를 불러오는 방법!! 이게 아니면 import후 new객체 생성 해줘야함-->
<%
          Connection conn = db.conn;
          System.out.println(conn); //출력이 잘 되는지 확인.
%>


    <section>
                <!--listbox-->
                <div class="listbox">
                    <h1 class="text-center mb-5">게시판</h1>
                    <div class="d-flex justify-content-between py-4 btn-box">
                        <div>
                            <a href="#" class="btn btn-dark">최신글순</a>
                            <a href="#" class="btn btn-white border-dark">인기글순</a>
                        </div>
                        <div>
                            <a href="#" class="btn btn-dark">목록</a>
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
                            <tr>
                                <td class="text-center">1</td>
                                <td><a href="">제목입니다. 이곳에 제목
                                    제목입니다. 이곳에 제목
                                    제목입니다. 이곳에 제목
                                    제목입니다. 이곳에 제목
                                    제목입니다. 이곳에 제목
                                    </a><span>(2)</span>
                                    <i class="ri-file-image-fill"></i>
                                    <i class="ri-file-hwp-fill"></i>
                                    <i class="ri-file-music-fill"></i>
                                </td>

                                <td class="text-center">홍길동</td>
                                <td class="text-center">12</td>
                                <td class="text-center">2024.02.26</td>
                            </tr>
                            <tr>
                                <td class="text-center">1</td>
                                <td><a href="#">제목입니다. 이곳에 제목<i class="ri-file-hwp-fill"></i></a></td>
                                <td class="text-center">홍길동</td>
                                <td class="text-center">12</td>
                                <td class="text-center">2024.02.26</td>
                            </tr>
                            <tr>
                                <td class="text-center">1</td>
                                <td><a href="#">제목입니다. 이곳에 제목<i class="ri-file-pdf-2-fill"></i></a></td>
                                <td class="text-center">홍길동</td>
                                <td class="text-center">12</td>
                                <td class="text-center">2024.02.26</td>
                            </tr>
                            <tr>
                                <td class="text-center">1</td>
                                <td><span class="re"></span><i class="ri-corner-down-right-line"></i><a href="#">제목입니다. 이곳에 제목</a></td>
                                <td class="text-center">홍길동</td>
                                <td class="text-center">12</td>
                                <td class="text-center">2024.02.26</td>
                            </tr>
                            <tr>
                                <td class="text-center">1</td>
                                <td><span class="re"></span><span class="re"></span><i class="ri-corner-down-right-line"></i></span><a href="#">제목입니다. 이곳에 제목</a></td>
                                <td class="text-center">홍길동</td>
                                <td class="text-center">12</td>
                                <td class="text-center">2024.02.26</td>
                            </tr>
                            <!--/loop-->
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-between py-4 btn-box">
                        <div>
                            <a href="#" class="btn btn-dark">최신글순</a>
                            <a href="#" class="btn btn-white border-dark">인기글순</a>
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
                        <form name="searchform" id="searchform" class="searchform">
                                <div class="input-group my-3">
                                    <div class="input-group-prepend">
                                        <button type="button" 
                                        class="btn btn-outline-secondary dropdown-toggle" 
                                        data-toggle="dropdown"
                                        value="title">
                                            제목검색
                                          </button>
                                          <div class="dropdown-menu">
                                            <a class="dropdown-item" href="username">이름검색</a>
                                            <a class="dropdown-item" href="title">제목검색</a>
                                            <a class="dropdown-item" href="content">내용검색</a>
                                          </div>
                                    </div>
                                    <input type="search" class="form-control" placeholder="검색">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary"><i class="ri-search-line"></i></button>
                                    </div>
                                </div>
                        </form>
                    <!--/listbox-->                        
                </div>
            </section>
    <%@ include file="inc/footer.jsp" %>     