package jspBoard.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import jspBoard.dto.BDto;

public class Select extends JBoardDao {
    
	
	
	public Select(Connection conn) {
		super(conn);
	
	}
   
    public ArrayList<BDto> selectDB(String st){
    	 ArrayList<BDto> dtos = new ArrayList<>();
    	
         //왜? 저렇게 while문을 돌면 마지막 값만 담기기에 이렇게 List에 담아줘서 모든 정보를 뽑아낼 수 있도록 하기위함.
    	 String sql = "select * from jboard where  like '%?%' writer order by refid desc, renum asc"
			       +" limit ?, ?";
	  try {
	  pstmt = conn.prepareStatement(sql);
	  pstmt.setString(1, st);
	  pstmt.setInt(3, 0); 
	  pstmt.setInt(4, 20);
	  res = pstmt.executeQuery();
	  
	  while(res.next()) { //preparedStatement의 내장메소드 ResultSet으로 값이 없을 때 까지 한행씩 읽어간다. 
		  int id = res.getInt("id");
		  int refid = res.getInt("refid");
		  int depth = res.getInt("depth");
		  int renum = res.getInt("renum");
		  String title = res.getString("title");
		  String content = res.getString("content");
		  String writer = res.getString("writer");
		  String pass = res.getString("pass");
		  String userid = res.getString("userid");
		  int hit = res.getInt("hit");
		  int chit = res.getInt("chit");
		  Timestamp wdate = res.getTimestamp("wdate");
		  
		  BDto bDto = new BDto(); //BDto클래스 타입 bDto변수 객체 생성
		  bDto.setId(id);
		  bDto.setRefid(refid);
		  bDto.setDepth(depth);
		  bDto.setRenum(renum);
		  bDto.setTitle(title);
		  bDto.setContent(content);
		  bDto.setWriter(writer);
		  bDto.setPass(pass);
		  bDto.setUserid(userid);
		  bDto.setHit(hit);
		  bDto.setChit(chit);
		  bDto.setWdate(wdate);
		  
		  dtos.add(bDto); //이렇게 while문을 돌면서 next()행에 값이 없을 떄 까지 dtos List에 값을 담아준다.
		                  //List에 add메소드는 List에 값을 담아주는 내장 메소드.
	  }
	  }
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
	  finally {
		  try {
			  if(res != null) res.close();
			  if(pstmt != null) pstmt.close();
			  if(conn != null) conn.close();
		  }
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  return dtos;

    }

}
