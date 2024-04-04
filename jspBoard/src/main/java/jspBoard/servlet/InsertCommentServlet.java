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


@WebServlet("/insertcomment")
public class InsertCommentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		
		DBConnect db = new DBConnect();
		try {
			HttpSession session = req.getSession();  //Aside에서 로그인할 때 만든 세션 정보를 가져옴
			Connection conn = db.getConnection();
			JBoardCommentDao dao = new JBoardCommentDao(conn);
			CDto dto = new CDto();
			String link = req.getHeader("referer"); //헤더정보를 가지고 이전페이지의 정보를 가져와서 referer를 넣어준다. (서블릿안의 클래스)
			int result = 0;
			String userid = (String) session.getAttribute("userid"); 
			String txt = "";
			//String link = "contents.jsp?id="+req.getParameter("jboard_id");		
			
		    dto.setJboard_id(Integer.parseInt(req.getParameter("jboard_id")));
		    dto.setUserid(userid);
		    dto.setUsername(req.getParameter("username"));
		    dto.setComment(req.getParameter("comment"));
		
			result = dao.insertDB(dto, Integer.parseInt(req.getParameter("chit")));
			txt = "글을 등록했습니다.";
			
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
