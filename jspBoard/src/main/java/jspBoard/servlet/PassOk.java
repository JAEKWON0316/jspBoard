package jspBoard.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardDao;


@WebServlet("/passok")
public class PassOk extends HttpServlet {
		
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		   String id = req.getParameter("id");
		   String cpass = req.getParameter("cpass");
		   String mode = req.getParameter("mode");
		   int result = 0;
		   DBConnect db = new DBConnect();
		   Connection conn;
		 	   
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
		    result = dao.findPass(id, cpass);
		    
		} catch (SQLException | NamingException e) {			
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
		if(result == 0) { //비밀번호가 틀렸을 때
			RequestDispatcher reqDispatcher = req.getRequestDispatcher("passno.jsp"); //RequestDispatcher : 페이지 내부로 이동하는 클래스
			reqDispatcher.forward(req, res); //forward로 req를 받아서 res(passno.jsp)로 이동 (forward니까 현재 서블릿페이지에서 passno.jsp가 실행!!)
		}
		else {
			if("edit".equals(mode)) {		
			   res.sendRedirect("edit.jsp?id="+id);	
			}
			else {		
			    res.sendRedirect("del?id="+id+"&cpass="+cpass);	 //del로 갈 때는 id와 cpass가 가져가도록.
			}
		}
		 		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res); //doPost로 보낸값을 doGet으로 전부 보냄. --> post로 쓴걸 get에서 다 받아서 쓸 수 있다.(request, response 다)
	}

}
