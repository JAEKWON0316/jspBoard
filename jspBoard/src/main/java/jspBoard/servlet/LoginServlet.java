package jspBoard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspBoard.dao.DBConnect;
import jspBoard.dao.MembersDao;
import jspBoard.dto.MDto;


@WebServlet("/login")  //로그인 정보를 확인하기 위한 로그인 서블릿!!
public class LoginServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
    private Connection conn = null;   
    DBConnect db = new DBConnect();
 
    public LoginServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String[] rid = request.getParameterValues("rid"); //rid 값으로 파라미터들을 받아옴(아이디기억)
		String uid = request.getParameter("uid"); //유저 아이디
		String upass = request.getParameter("upass"); //유저 비밀번호
		if(rid != null) {
			Cookie remId = new Cookie("uid", uid);  //uid라는 이름으로 uid 쿠키를 생성
			response.addCookie(remId); //사용자로 쿠키를 보내주는 작업
		}
		
		try {
		conn = db.getConnection();
		MembersDao dao = new MembersDao(conn);
		MDto mdto = dao.login(uid, upass);
		if(mdto.getId() != 0 ) { //로그인 성공시, 세션 객체 생성
		   HttpSession session = request.getSession();
		   session.setAttribute("mid", mdto.getId());
		   session.setAttribute("userid", mdto.getUserid());
		   session.setAttribute("usereamil", mdto.getUseremail());
		   session.setAttribute("username", mdto.getUsername());
		   session.setAttribute("role", mdto.getRole()); //로그인 시 세션 생성 -> 이걸로 권한부여를 할것임!
		   response.sendRedirect("index.jsp");		   
		}
		else { //실패시
			System.out.println("로그인 실패");
			String script = "<script>alert('아이디 또는 비밀번호가 틀렸습니다. 다시 확인해주세요.');"
					+ "location.href='index.jsp';</script>";
			out.println(script);
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(NamingException e) {
			e.printStackTrace();
		}
		
		}
}


