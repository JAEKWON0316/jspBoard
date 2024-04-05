package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspBoard.dto.CDto;

public class JBoardCommentDao {
       
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public JBoardCommentDao(Connection conn){//생성자를 통해서 필드와 db연결
		this.conn = conn;
	}
	
	//select
	public ArrayList<CDto> selectDB(int jboard_id){
		ArrayList<CDto> dtos = new ArrayList<>();
		String sql = "select * from jboard_comment where jboard_id = ?";
		try {
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, jboard_id); //? 1번에 jboard_id를 int로 담아준다.
		    rs = pstmt.executeQuery(); //resultset(여기서 rs는 resultset이다)에 쿼리를 넣어주면 아래 while문으로 돌려서 컬럼값이 나온다 그걸 dto에 담는다.
		                               //즉, while 문으로 돌릴 땐 executeQuery()를 아니라면 executeUpdate()를 하면된다.
		                                
		    	while(rs.next()) { //while문을 돌면서 값이 없을 때 까지 CDto 필드에 값을 넣어준다.
		    		CDto dto = new CDto();
		    		dto.setId(rs.getInt("id"));
		    		dto.setJboard_id(rs.getInt("jboard_id"));
		    		dto.setUserid(rs.getString("userid"));
		    		dto.setUsername(rs.getString("username"));
		    		dto.setComment(rs.getString("comment"));
		    		dto.setWdate(rs.getTimestamp("wdate"));
		    		dtos.add(dto);
		    		//이렇게 다 뽑은 후 array 배열에 넣어줘야함.
		    	}
		    }
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
		   try {
			   if(rs != null) rs.close();
			   if(pstmt != null) pstmt.close();
		   }
		   catch(SQLException e) {
			   
		   }
		}
		return dtos;
	}
	
	//comment 쓰기
	public int insertDB(CDto dto, int chit) {
		JBoardDao dao = new JBoardDao(conn);
		int rs = 0;
		String sql = "insert into jboard_comment (jboard_id, userid, username, comment)"
				+ "values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getJboard_id());
			pstmt.setString(2,  dto.getUserid());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getComment());
			rs = pstmt.executeUpdate(); //이것은 변수 rs에 넣어둔것
			//원글에 코멘트 숫자를 업데이트
			dao.updateDB(dto.getJboard_id(), chit+1, "chit");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
		   try {		   
			   if(pstmt != null) pstmt.close();
		   }
		   catch(SQLException e) {
			   
		   }
		}
		return rs;
	}
	
	//comment 수정
	 public int updateDB(int id, String username, String comment, String column1, String column2) {  
		  int rs = 0;
		  String sql =  "update jboard_comment set "+ column1 +"=? , "+ column2 + "=? where id=?";  //column만 변수로 받음
		  
		  try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, username);
			pstmt.setString(2, comment);
			pstmt.setInt(3, id);		
			rs = pstmt.executeUpdate(); //executeUpdate의 리턴값은 int형 성공1 실패 0 -> update insert delete 때 사용.
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		  finally {
			  try {
				  if(pstmt != null) pstmt.close();
				
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }
		  }
		
		  return rs;
	  }
	/*
	 * public int updateDB(CDto dto){
	 * int cid = Integer.parseInt("id");
	 * int rs = 0;
	 * String sql = "update jboard_comment set username = ?, comment = ? wehre id = ?";
	 * try{
	 *  pstmt = conn.prepareStatment(sql);
	 *  pstmt.setString(1, dto.getUsername());
	 *  pstmt.setString(2, dto.getComment());
	 *  pstmt.setInt(3, dto.getId());
	 *  rs = pstmt.executeUpdate(); //executeUpdate의 리턴값은 int형 성공1 실패 0 -> update insert delete 때 사용.
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		  finally {
			  try {
				  if(pstmt != null) pstmt.close();				
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }
		  }
		
		  return rs;
	  }
	 */ 
	
	 //select view (userid 꺼내오기 위함)
	 public CDto selectDB(String id){ 
		 int cid = Integer.parseInt(id);
		 CDto dto = new CDto();
			String sql = "select * from jboard_comment where id = ?";
			try {
			    pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, cid); //? 1번에 jboard_id를 int로 담아준다.
			    rs = pstmt.executeQuery(); //resultset(여기서 rs는 resultset이다)에 쿼리를 넣어주면 아래 while문으로 돌려서 컬럼값이 나온다 그걸 dto에 담는다.
			                               //즉, while 문으로 돌릴 땐 executeQuery()를 아니라면 executeUpdate()를 하면된다.
			                                
			    	if(rs.next()) { //while문을 돌면서 값이 없을 때 까지 CDto 필드에 값을 넣어준다.
			    	
			    		dto.setId(rs.getInt("id"));
			    		dto.setJboard_id(rs.getInt("jboard_id"));
			    		dto.setUserid(rs.getString("userid"));
			    		dto.setUsername(rs.getString("username"));
			    		dto.setComment(rs.getString("comment"));
			    		dto.setWdate(rs.getTimestamp("wdate"));
			    		
			    	}
			    }
			
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
			   try {
				   if(rs != null) rs.close();
				   if(pstmt != null) pstmt.close();
			   }
			   catch(SQLException e) {
				   
			   }
			}
			return dto;
		}
	 
	//comment 삭제
	public int deleteDB(int jboard_id, int id, int chit) {
		JBoardDao dao = new JBoardDao(conn);
		int rs = 0;
		String sql = "delete from jboard_comment where jboard_id = ? and id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jboard_id);
			pstmt.setInt(2, id);
			rs = pstmt.executeUpdate();
			dao.updateDB(jboard_id, chit-1, "chit");
			System.out.println(pstmt);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		 finally {
			  try {
				  if(pstmt != null) pstmt.close();
				
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }
		  }
		return rs;
	}
}
