package jspBoard.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardImgDao;
import jspBoard.dto.ImgDto;

public class TrashFile {
    
   private static final Logger logger = Logger.getLogger(TrashFile.class.getName());
   private ServletContext context;  //servletcontext 변수 생성
   
   public TrashFile(ServletContext cont) throws SQLException, NamingException { //생성자에 ServletContext를 매개변수로 받아 사용.
      this.context = cont;
      cleanupFiles();
   }   
   
   private void cleanupFiles() {
      try(Connection conn = new DBConnect().getConnection()){
         JBoardImgDao idao = new JBoardImgDao(conn);
         ArrayList<ImgDto> iList = idao.selectDB("jboard_id", "0");   //jboard_id가 0인 파일 목록 조회
         if(!iList.isEmpty()) { 
            for(ImgDto dto : iList) {  //향상된 for문 
               deleteFile(dto.getNfilename());  //파일 삭제
               idao.deleteDB("nfilename", dto.getNfilename()); //db삭제
            }
         }
      }catch(SQLException | NamingException e) {
         logger.log(Level.SEVERE, "정리파일 실행 중 에러", e);   
      }   
   }
   
   private void deleteFile(String filename) {
      //절대경로
      
      String realPath = context.getRealPath("/uploads"); //uploads라는 폴더에 실제경로를 구한다음에 잘라서 붙혀준다.
      //String filePath = realPath + "\\" + filename; //절대경로 뒤에 파일이름을 붙힌다. \\는 경로표시 구분자!
      String filePath = realPath + File.separator + filename; //File.separator를 써주면 운영체제에 따라 경로표시 구분자를 나눠준다.
      File file = new File(filePath);
      //파일이 존재하는지 확인한 후, 존재하면 삭제
      if(file.exists() && !file.isDirectory()) {
         if(file.delete()) {
            logger.info("파일 삭제 성공 :"+filePath);
         }else {
            logger.info("파일 삭제 실패 :" + filePath);
         }
      }
 
   }
   
}