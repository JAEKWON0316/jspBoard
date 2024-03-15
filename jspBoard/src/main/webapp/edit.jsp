<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jspBoard.dao.*, jspBoard.dto.BDto" %> 
<%@ include file="inc/header.jsp" %>
 <script src="js/summernote-bs4.js"></script>
    <script src="js/lang/summernote-ko-KR.min.js"></script>
    <script src="js/write.js"></script>  <!-- 스크립트는 헤더 안에 있어야한다!! -->
<%
   String id = request.getParameter("id");
   DBConnect db = new DBConnect(); //db접속
   Connection conn = db.conn;
   JBoardDao dao = new JBoardDao(conn); //viewDB()메소드를 쓰기위해 dao 객체 생성
   BDto dto = new BDto(); //viewDB 리턴 타입인 dto를 받기 위해 dto 객체 생성
   BDto rs = dao.viewDB(id);
%>
 
<%@ include file="inc/aside.jsp" %>
         <section>
                <div class="write">
                    <h2 class="text-center mt-4 mb-5 pb-4">글쓰기</h2>
                    <form action="editok.jsp" name="writeform" id="writeform" class="writeform row" method="post">
                        <!-- 게스트일 때만 적용 -->
                        <div class="col-12 row">
                            <div class="col-6 row form-group">
                                <label class="form-label">이름</label>
                                <input type="text" name="writer" id="writer" class="form-control" placeholder="이름" value="<%=rs.getWriter() %>"/>
                            </div>
                            <div class="col-6 row form-group">
                                <label class="form-label">비밀번호</label>
                                <input type="password" name="pass" id="password" class="form-control" placeholder="비밀번호" />
                            </div> 
                        </div>
                        <div class="col-12 row form-group">
                            <label class="form-label">제목</label>
                            <input type="text" name="title" id="title" class="form-control col-10" placeholder="제목" value="<%=rs.getTitle() %>" />
                        </div>
                        <div class="col-12">
                            <textarea name="content" id="contents" class="form-control"> <%=rs.getContent() %>

                            </textarea>
                        </div>
                        <!-- 게스트일 때만 적용 -->
                        <div class="col-12 text-center my-5">
                            <a href="index.jsp" class="btn btn-danger px-5 mx-2">취소</a> <!--btn인 척 하는 a태그!!-->
                            <button class="btn btn-primary px-5 mx-2" type="submit">수정하기</button>
                        </div>
                        <input type="hidden" name="id" value="<%=id %>"/>
                        <!-- 답글을 쓸 때 뿌려줘야 할 값들 -->
                        <!-- 
                        <input type="hidden" name="refid" value="" />
                        <input type="hidden" name="depth" value="" />
                        <input type="hidden" name="renum" value="" />
                         -->
                    </form>
                </div>
        </section>
<%@ include file="inc/footer.jsp" %>    
        