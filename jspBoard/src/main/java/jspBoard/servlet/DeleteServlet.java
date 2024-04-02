package jspBoard.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileUrl = request.getParameter("fileUrl");
		String filePath = getFilePathFromUrl(fileUrl);
		
		File file = new File(filePath);
		if(file.delete()) {
			response.getWriter().write(1);
		}
		else {
			response.getWriter().write(0);
		}
	} 
	
    //절대경로를 찾아서 보내주는 메소드
	private String getFilePathFromUrl(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		//절대경로
		ServletContext context = getServletContext();
		String realPath = context.getRealPath("/uploads"); //uploads라는 폴더에 실제경로를 구한다음에 잘라서 붙혀준다.
		String filePath = realPath + "\\" + fileName; //filePath에 절대경로가 잡힌다.
		return filePath;
	}
}
