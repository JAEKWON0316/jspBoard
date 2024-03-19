package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspBoard.dto.MDto;

public class MembersDao {
    //필드
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Connection conn;
	
	//생성자에서 db 접속
	public MembersDao(Connection conn) {
		this.conn = conn;
	}
	
	//회원가입
	public int insertDB(MDto dto) {
		int num = 0;
	
		String sql = "insert into members " 
		             + "(userid, userpass, username, useremail, usertel, address, userlink)"
				     + "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpass());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getUseremail());
			pstmt.setString(5, dto.getUsertel());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getUserlink());
			num = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(pstmt != null) pstmt.close();
				} catch (SQLException e) {	
					e.printStackTrace();
				}	
		}					
		return num;
	}
	
}
