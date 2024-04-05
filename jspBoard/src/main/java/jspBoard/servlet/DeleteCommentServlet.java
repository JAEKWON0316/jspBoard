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


@WebServlet("/cdel")
public class DeleteCommentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession(); //보안을 위해 userid값을 가져오기 위한 세션 생성
		String userid = (String) session.getAttribute("userid");	
		DBConnect db = new DBConnect();
		CDto dto = new CDto();
		int result = 0;
		String id = req.getParameter("id");    //아래 request로 받는 3개는 변수에 담아도 안담아도 된다.	
		// String jboard_id = req.getParameter("jboard_id");
		// String chit = req.getParameter("chit");
		
		try {
			
			Connection conn = db.getConnection();
			JBoardCommentDao dao = new JBoardCommentDao(conn);	
		    dto.setJboard_id(Integer.parseInt(req.getParameter("jboard_id")));
		    dto.setId(Integer.parseInt(req.getParameter("id")));	
			dto = dao.selectDB(id);
			System.out.println(dto);
			if(dto.getUserid().equals(userid) || "admin".equals(userid)) {
				//세션아이디와 코멘트 작성한 아이디가 같으면 삭제 작업을 진행한다. 또 관리자도 삭제할 수 있다.
				result = dao.deleteDB(dto.getJboard_id(), dto.getId(), Integer.parseInt(req.getParameter("chit")));
			}
			String link = req.getHeader("referer"); //헤더정보를 가지고 이전페이지의 정보를 가져와서 referer를 넣어준다. (서블릿안의 클래스)		
			String txt = "";	
			//String link = "contents.jsp?id="+req.getParameter("jboard_id");					   
			System.out.println(result);
			txt = "댓글을 삭제했습니다.";
			
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
