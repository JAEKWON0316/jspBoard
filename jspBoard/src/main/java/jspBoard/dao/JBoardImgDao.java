package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jspBoard.dto.ImgDto;

public class JBoardImgDao {

	  PreparedStatement pstmt = null; //쿼리문에 ? 변수가 있으면 PreparedStatement 아니면 변수가 없으면 그냥 Statement
	  Statement stmt = null;
	  ResultSet res = null; //sql 받을 때 preparedStatement로 받은것을 읽는 클래스
	  Connection conn;
	  
	  public JBoardImgDao(Connection conn) {
		  this.conn = conn; //위에 만들어놓은 conn에 값을 넣어주는것 JBoardDao dao = new JBoardDao(conn); 를하면 바로 연결가능 생성자를 통해
	  }
      
	  //쓸 때 없이 저장된 db 지우기
	  /*
	  public ArrayList<ImgDto> AllSelectDB(){
		  
	  }
	  */
	  
	  //저장
	  public String insertDB(ImgDto dto) {
		  String result = "";
		  int rs = 0;
		  String sql = "insert into jboard_img (ofilename, nfilename, ext, filesize, userid, imnum)"
	    			+ "values (?,?,?,?,?,?)";		
		  try {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, dto.getOfilename());
			  pstmt.setString(2, dto.getNfilename());
			  pstmt.setString(3, dto.getExt());
			  pstmt.setLong(4, dto.getFilesize());
			  pstmt.setString(5, dto.getUserid());
			  pstmt.setString(6, dto.getImnum());			
			  rs = pstmt.executeUpdate();
			  if(rs != 0) {
				  result = dto.getImnum(); //result에는 임시넘버가있다.
			  }			    
		  }
		  catch(SQLException e) {
			  System.out.println("에러");
			  e.printStackTrace();
		  }
		  finally {
			  try {
				  if(pstmt != null) {
					  pstmt.close();
				  }
								  
			    } catch(SQLException e) {
			    	
			    }
			  }		
			  return result; //임시넘버를 뱉어낸다.
	    	 
	}
	  //업데이트
	  public int updateDB(int jboard_id, String imnum) {
		  int result = 0;
		  String sql = "update jboard_img set jboard_id = ? where imnum = ?";
		  
		  try {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setInt(1, jboard_id);
			  pstmt.setString(2, imnum);
			  result = pstmt.executeUpdate();
		  }
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
		  finally {
			  try {
				  if(pstmt != null) {
					  pstmt.close();
				  }
								  
			    } catch(SQLException e) {
			    	
			    }
			  }		
			  return result;
	  }
	  
	  //전체 삭제를 위한 select
	  public ArrayList<ImgDto> selectDB(String key,String val){
		  String where = " where " + key + " = " + val;
		  String sql = "select * from jboard_img" + where;
		  ArrayList<ImgDto> dtos = new ArrayList<>();
		  
		  try {
			stmt = conn.createStatement();
			  System.out.println(sql);
			  res = stmt.executeQuery(sql);
			  while(res.next()) {
				  ImgDto dto = new ImgDto();
				  dto.setId(res.getInt("id"));
				  dto.setJboard_id(res.getInt("jboard_id"));
				  dto.setNfilename(res.getString("nfilename"));
				  dtos.add(dto);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  finally {
			  try {
				  if(res != null) {
					  res.close();
				  }
				  if(stmt != null) {
					  stmt.close();
				  }
								  
			    } catch(SQLException e) {
			    	
			    }
			  }		
			  return dtos;
		
	  }
	  
	  //삭제
	  public int deleteDB(String key, String val) {
		  int result = 0;
		  String sql = "delete from jboard_img where "+key+" = ?";
		  
		  try {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, val);
			  result = pstmt.executeUpdate()
;		  }  
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
		  finally {
			  try {
				  if(pstmt != null) {
					  pstmt.close();
				  }
								  
			    } catch(SQLException e) {
			    	
			    }
			  }		
			  return result;
	  }
}
