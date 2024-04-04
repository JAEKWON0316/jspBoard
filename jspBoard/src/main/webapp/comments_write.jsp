<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
       <link rel="stylesheet" href="css/summernote-bs4.css">
         <script src="js/summernote-bs4.js"></script>
         <script>
            $(function(){
               $("#comment").summernote({
                  width:'85%',
                  height:'100px',
                  toolbar: [
                    ['style', ['bold', 'italic', 'underline', 'clear']],
                    ['font', ['strikethrouth','superscript', 'subscript']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']]
                  ] 
               });
            }); 

         </script>
         
               <li class="bg-light list-group-item flex-column p-5 mb-5 align-items-start">
                  <form name="commentForm" id="commentForm" class="d-flex" method="post" action="./insertcomment">
                     <div class="form-box">
                     <div class="mb-2">
                           <label>이름 :</label>
                                 <input type="text" name="username" value="<%=sess2.getAttribute("username")%>" readonly/> 
                       </div>  
                       <div class="d-flex">                     
                             <textarea name="comment" id="comment"></textarea>                 
                             <button type="submit" class="comment-btn">댓글쓰기</button>
                           </div>
                      </div>
                     <input type="hidden" name="jboard_id" value="<%=rs.getId() %>" />
                     <input type="hidden" name="chit" value="<%=rs.getChit() %>" />
                  </form>
               </li> 