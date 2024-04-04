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
	
}
