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
import jspBoard.dao.JBoardDao;
import jspBoard.dao.MembersDao;
import jspBoard.dto.BDto;
import jspBoard.dto.MDto;


@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		String mode = req.getParameter("mode");
		DBConnect db = new DBConnect();
		try {
			HttpSession session = req.getSession();  //Aside에서 로그인할 때 만든 세션 정보를 가져옴
			Connection conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			MembersDao mdao = new MembersDao(conn);
			BDto dto = new BDto();
			MDto mdto = new MDto();
			int result = 0;
			String userid = (String) session.getAttribute("userid"); 
			String txt = "";
			String link = "";
			if("rewrite".equals(mode)) {
				
			   	dto.setWriter(req.getParameter("writer"));
				dto.setPass(req.getParameter("pass"));
				dto.setTitle(req.getParameter("title"));
				dto.setContent(req.getParameter("content"));
				 if(req.getParameter("refid") != null ){
					   dto.setRefid(Integer.parseInt(req.getParameter("refid"))); //순서를 맞춰주기 위해 답글의 답글을 달 때는 id값이 아니라 refid값을 넘겨준다.    
				   }
				   else{
					   dto.setRefid(Integer.parseInt(req.getParameter("id")));   
				   }  
				   dto.setDepth(Integer.parseInt(req.getParameter("depth"))+1);
				   dto.setRenum(Integer.parseInt(req.getParameter("renum")));
				   dto.setImnum(req.getParameter("imnum"));
				   result = dao.insertDB(dto);
				   txt = "답변글을 썼습니다.";
				   link = "contents.jsp?id="+result+"&cpg=1";
				   
			}
			else if("join".equals(mode)) {
				mdto.setUserid(req.getParameter("userid"));
				mdto.setUserpass(req.getParameter("userpass"));
				mdto.setUsername(req.getParameter("username"));
				mdto.setUseremail(req.getParameter("useremail"));
				mdto.setUsertel(req.getParameter("usertel"));
				mdto.setZipcode(Integer.parseInt(req.getParameter("zipcode")));
				mdto.setAddr1(req.getParameter("addr1"));
				mdto.setAddr2(req.getParameter("addr2"));
				mdto.setUserlink(req.getParameter("userlink"));
				result = mdao.insertDB(mdto);
				txt = mdto.getUsername() + "님 환영합니다. 회원가입이 완료되었습니다.";
				link = "index.jsp";
			}
			else {
			 	dto.setWriter(req.getParameter("writer"));
				dto.setPass(req.getParameter("pass"));
				dto.setTitle(req.getParameter("title"));
				dto.setContent(req.getParameter("content"));
				 dto.setImnum(req.getParameter("imnum"));
				dto.setDepth(0);
				dto.setUserid(userid);
				result = dao.insertDB(dto);
				txt = "글을 등록했습니다.";
				link = "contents.jsp?id="+result+"&cpg=1";
			}
			
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
