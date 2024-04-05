package jspBoard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardCommentDao;
import jspBoard.dto.CDto;


@WebServlet("/updatecomment")
public class UpdateCommentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
    	res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String userid = (String) session.getAttribute("userid");	
		String id = req.getParameter("id");
		CDto dto = new CDto();
		dto.setUsername(req.getParameter("username"));
		dto.setComment(req.getParameter("comment"));
		DBConnect db = new DBConnect();
		try {
			Connection conn = db.getConnection();
			JBoardCommentDao dao = new JBoardCommentDao(conn);			
			String link = req.getHeader("referer"); //헤더정보를 가지고 이전페이지의 정보를 가져와서 referer를 넣어준다. (서블릿안의 클래스)
			int result = 0;
			String txt = "";
			//String link = "contents.jsp?id="+req.getParameter("jboard_id");
			CDto cdto = new CDto();
		    cdto = dao.selectDB(id);
		    if(cdto.getUserid().equals(userid) || "admin".equals(userid)) {
			result = dao.updateDB(Integer.parseInt(req.getParameter("id")), dto.getUsername(), dto.getComment(), "username", "comment");
		    }
			txt = "댓글을 수정했습니다.";
			
			if(result == 0) {
				txt = "문제가 발생했습니다.";
			}
			
			String str = "<script>alert('"+txt+"'); " + "location.href='"+link+"';" + "</script>";

		    out.println(str);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
   


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
