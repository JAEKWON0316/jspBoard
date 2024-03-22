<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp" %>
   <script src="js/summernote-bs4.js"></script>
    <script src="js/lang/summernote-ko-KR.min.js"></script>
    <script src="js/write.js"></script>
<%@ include file="inc/aside.jsp" %>
<% String id = request.getParameter("id"); 
   String refid = request.getParameter("refid");
   String depth = request.getParameter("depth");
   String renum = request.getParameter("renum"); //답글에게 넘겨줄 정보들
   String cpg = request.getParameter("cpg");
%>
         <section>
                <div class="write">
                    <h2 class="text-center mt-4 mb-5 pb-4">답글쓰기</h2>
                    <form action="rewriteok.jsp" name="writeform" id="writeform" class="writeform row" method="post">
                        <!-- 게스트일 때만 적용 -->
                        <div class="col-12 row">
                            <div class="col-6 row form-group">
                                <label class="form-label">이름</label>
                                <input type="text" name="writer" id="writer" class="form-control" value="" />
                            </div>
                            <div class="col-6 row form-group">
                                <label class="form-label">비밀번호</label>
                                <input type="password" name="pass" id="password" class="form-control" placeholder="비밀번호" value="" />
                            </div> 
                        </div>
                        <div class="col-12 row form-group">
                            <label class="form-label">제목</label>
                            <input type="text" name="title" id="title" class="form-control col-10" placeholder="제목" value="" />
                        </div>
                        <div class="col-12">
                            <textarea name="content" id="contents" class="form-control">

                            </textarea>
                        </div>
                        <!-- 게스트일 때만 적용 -->
                        <div class="col-12 text-center my-5">
                            <a href="list.html" class="btn btn-danger px-5 mx-2">취소</a> <!--btn인 척 하는 a태그!!-->
                            <button class="btn btn-primary px-5 mx-2" type="submit">답글쓰기</button>
                        </div>
                        <input type="hidden" name="id" value="<%=id %>" />
                        <!-- 답글을 쓸 때 뿌려줘야 할 값들 -->
                        <input type="hidden" name="refid" value="<%=refid %>" />
                        <input type="hidden" name="depth" value="<%=depth %>" />
                        <input type="hidden" name="renum" value="<%=renum %>" />
                        <input type="hidden" name="cpg" value="<%=cpg %>" />
                    </form>
                </div>
        </section>
<%@ include file="inc/footer.jsp" %>    
        