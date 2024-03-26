package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import jspBoard.dto.BDto;
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
	
	//회원로그인
	public MDto login(String userid, String userpass) {
		String sql = "select * from members where userid=? and userpass=?";
		MDto dto = new MDto();
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, userid);
			pstmt.setString(2, userpass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
		  	dto.setId(rs.getInt("id"));
		  	dto.setUserid(rs.getString("userid"));
		  	dto.setUseremail(rs.getString("useremail"));
		  	dto.setUsername(rs.getString("username"));
		  	dto.setRole(rs.getString("Role"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}	
	}
		System.out.println(dto.getId());
		return dto;
  }
	
	
	//회원 id로 정보 가져오기 로그인 오버로드
	public MDto login(int mid) {
		String sql = "select * from members where id=?";
		MDto dto = new MDto();
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
		  	dto.setUserid(rs.getString("userid"));
		  	dto.setUseremail(rs.getString("useremail"));
		  	dto.setUsername(rs.getString("username"));
		  	dto.setUserpass(rs.getString("userpass"));		
		  	dto.setZipcode(rs.getInt("zipcode"));
		  	dto.setUsertel(rs.getString("usertel"));
		  	dto.setAddr2(rs.getString("addr1"));
		  	dto.setAddr1(rs.getString("addr2"));
		    dto.setUserlink(rs.getString("userlink"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}	
	}
		System.out.println(dto.getId());
		return dto;
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
	
	//회원 업데이트
	 public int updateMem(MDto dto) {
		  int rs = 0;
		  String sql = "update members set userid =? , userpass = ?, username =?, useremail =?, usertel =?,zipcode =?, addr1 =?, addr2 =? userlink=? where id=?"; //column만 변수로 받음
		  
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
			pstmt.setInt(10, dto.getId());
			rs = pstmt.executeUpdate(); //executeUpdate의 리턴값은 int형 성공1 실패 0 -> update insert delete 때 사용.
		    System.out.println(pstmt);
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
	  
	 //회원정보 배열로 받아오기
	 public ArrayList<MDto> MselectDB(){ //select한것을 DBto에 집어넣는것 lesson20의 JBoardDao와 반대이다!
		  
		  ArrayList<MDto> mtos = new ArrayList<>(); //값을 담을 ArrayList객체 생성 
		                                            //왜? 저렇게 while문을 돌면 마지막 값만 담기기에 이렇게 List에 담아줘서 모든 정보를 뽑아낼 수 있도록 하기위함.
		  
		  String sql = "select * from members order by id desc"
				       +" limit ?, ?";
		  try {
		  pstmt = conn.prepareStatement(sql); //Connection 안에 있는 prepareStatement 메소드에 값을 넣어줘야지 PreparedStatement 타입으로 바뀐다.
		  pstmt.setInt(1, 0); // 변수 ? 순서대로 1,2,3 ... 이렇게 간다. 순서, 값 식으로
		  pstmt.setInt(2, 20);
		  rs = pstmt.executeQuery();
		  //"select * from jboard order by refid desc, renum asc limit 0, 20"; 이런 쿼리문을 res에 넣겠다.
		  
		  while(rs.next()) { //preparedStatement의 내장메소드 ResultSet으로 값이 없을 때 까지 한행씩 읽어간다. 
			  int id = rs.getInt("id");
			  String userid = rs.getString("userid");
			  String userpass = rs.getString("userpass");
			  String username = rs.getString("username");
			  String useremail = rs.getString("useremail");
			  String usertel = rs.getString("usertel");
			  String addr1 = rs.getString("addr1");
			  String addr2 = rs.getString("addr2");
			  String userlink = rs.getString("userlink");
			  int zipcode = rs.getInt("zipcode");
			  String role = rs.getString("role");
			  Timestamp wdate = rs.getTimestamp("wdate");
			  
			  MDto mDto = new MDto(); //BDto클래스 타입 bDto변수 객체 생성
			  mDto.setId(id);
			  mDto.setUserid(userid);
			  mDto.setUserpass(userpass);
			  mDto.setUsername(username);
			  mDto.setUseremail(useremail);
			  mDto.setUsertel(usertel);
			  mDto.setAddr1(addr1);
			  mDto.setAddr2(addr2);
			  mDto.setUserlink(userlink);
			  mDto.setZipcode(zipcode);
			  mDto.setRole(role);
			  mDto.setWdate(wdate);
			  
			  mtos.add(mDto); //이렇게 while문을 돌면서 next()행에 값이 없을 떄 까지 dtos List에 값을 담아준다.
			                  //List에 add메소드는 List에 값을 담아주는 내장 메소드.
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
				  e.printStackTrace();
			  }
		  }
		  return mtos;
	  }
	  
	
}
