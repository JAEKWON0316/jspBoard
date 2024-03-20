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
		             + "(userid, userpass, username, useremail, usertel, zipcode, addr1, addr2, userlink)"
				     + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpass());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getUseremail());
			pstmt.setString(5, dto.getUsertel());
			pstmt.setInt(6, dto.getZipcode());
			pstmt.setString(7, dto.getAddr1());
			pstmt.setString(8, dto.getAddr2());
			pstmt.setString(9, dto.getUserlink());
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
	
	//회원 중복 검증
	public boolean findUser(String column, String uname) {
		boolean res = true;
		String sql = "select * from members where "+ column + " = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				res = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}	
	    }					
		System.out.println(res);
		return res;
	}
	
}
