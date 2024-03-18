package jspBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import jspBoard.dto.BDto;

//MVC 만들기 위한 클래스 (여기에 뽑아올것들을 만들어줘서 writeok.jsp에서는 여기서 만든것만 가져와서 쓴다!)
public class JBoardDao {
    
	  PreparedStatement pstmt = null; //쿼리문에 ? 변수가 있으면 PreparedStatement 아니면 변수가 없으면 그냥 Statement
	  ResultSet res = null; //sql 받을 때 preparedStatement로 받은것을 읽는 클래스
	  Connection conn;
	  
	  public JBoardDao(Connection conn) {
		  this.conn = conn; //위에 만들어놓은 conn에 값을 넣어주는것 JBoardDao dao = new JBoardDao(conn); 를하면 바로 연결가능 생성자를 통해
	  }
	  
	  //select
	  public ArrayList<BDto> selectDB(){ //select한것을 DBto에 집어넣는것 lesson20의 JBoardDao와 반대이다!
		  
		  ArrayList<BDto> dtos = new ArrayList<>(); //값을 담을 ArrayList객체 생성 
		                                            //왜? 저렇게 while문을 돌면 마지막 값만 담기기에 이렇게 List에 담아줘서 모든 정보를 뽑아낼 수 있도록 하기위함.
		  
		  String sql = "select * from jboard order by refid desc, renum asc"
				       +" limit ?, ?";
		  try {
		  pstmt = conn.prepareStatement(sql); //Connection 안에 있는 prepareStatement 메소드에 값을 넣어줘야지 PreparedStatement 타입으로 바뀐다.
		  pstmt.setInt(1, 0); // 변수 ? 순서대로 1,2,3 ... 이렇게 간다. 순서, 값 식으로
		  pstmt.setInt(2, 20);
		  res = pstmt.executeQuery();
		  //"select * from jboard order by refid desc, renum asc limit 0, 20"; 이런 쿼리문을 res에 넣겠다.
		  
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
				
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }
		  }
		  return dtos;
	  }
	  
	  //오버로드
	  public ArrayList<BDto> selectDB(String st, String txt){  //st는 컬럼명 txt는 검색내용
		  
		     ArrayList<BDto> dtos = new ArrayList<>();
		     String sql = "select * from jboard where " + st + " like ? order by refid desc, renum asc" //sql ?에 String으로 데이터를 입력하면 ''따옴표가 들어가기 때문에 변수로 써준다.
				       +" limit ?, ?";
		    		    	 
			  try {
			  pstmt = conn.prepareStatement(sql);
			
			  pstmt.setString(1, "%"+txt+"%");
			  pstmt.setInt(2, 0); 
			  pstmt.setInt(3, 20);
			  System.out.println(pstmt);
			  res = pstmt.executeQuery();
			//왜? 저렇게 while문을 돌면 마지막 값만 담기기에 이렇게 List에 담아줘서 모든 정보를 뽑아낼 수 있도록 하기위함.
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
					  
				  }
				  catch(SQLException e) {
					  e.printStackTrace();
				  }
				
			  }
	    	
			return dtos;    	 	    		 
	 }
	 
	         
	  
	  
	  
	  public BDto viewDB(String nums) {
		  int num = Integer.parseInt(nums); //위에 request.getParameter때문에 String으로 들어온 id 를 int로 바꿔서 num이란 변수에 저장
		  String sql = "select * from jboard where id=?";
		  BDto bDto = new BDto();
		  try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num); 
			res = pstmt.executeQuery(); //쿼리문에 결과값을 뿌려주는 메소드 이건 ResultSet이라는 타입으로 받아진다. ->주로 select할 때 사용
			
			while(res.next()) {
				
				  int id = res.getInt("id"); //Resultset 으로 DB에서 가져온걸 변수에 담는다.
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
				
				  //BDto클래스 타입 bDto변수 객체 생성
				  bDto.setId(id);   //bDto에 DB에서 가져온 값을 다시 세팅한다.
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
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			  try {
				  if(res != null) res.close();
				  if(pstmt != null) pstmt.close();
				 
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  return bDto; 
				  
	  }
	  //쓰기
	  public int insertDB(BDto dto) {
		  int num = 0;
		  String sql ="insert into jboard (depth, title, content, writer, pass, userid) values (?, ?, ?, ?, ?, ?)";
		  try {
			  pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //입력할 때 프라이머리키 값 반환하는 법(id값을 모를 때 써줘야 id값을 뽑아올 수 있다!!!)
			                                       //지금 등록한 ID값에 대하여 받아오겠다!!
			  pstmt.setInt(1, dto.getDepth());
			  pstmt.setString(2, dto.getTitle());
			  pstmt.setString(3, dto.getContent());
			  pstmt.setString(4, dto.getWriter());
			  pstmt.setString(5, dto.getPass());
			  if(dto.getUserid()!=null) {
				  pstmt.setString(6, dto.getUserid());
			  }
			  else {
				  pstmt.setString(6, "GUEST");
			  }
			  pstmt.executeUpdate();
			  res = pstmt.getGeneratedKeys(); //입력 후 auto increment 값을 반환 받음.
			  if(res.next()) {
				  num = res.getInt(1);
			  }
			  
			  if(dto.getDepth() > 0 ) {
				  updateDB(dto.getRefid(), dto.getRenum()); //입력한 수 보다 큰 숫자를 1씩 증가시킴
				  updateDB(num, dto.getRefid(), "refid"); //새로 번호를 입력 (수정중 renum도 증가시켜 넣어 줘야함)
			  }
			  else {			 
				  
		         updateDB(num, num, "refid"); //refid 업데이트 매개변수로 넘겨줌
				     
			  }
			
			 
		  }
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
		  return num;
	  }
	  
	  //업데이트
	  public int updateDB(int id, int num, String column) {
		  int rs = 0;
		  String sql = "update jboard set " + column + "=? where id=?"; //column만 변수로 받음
		  
		  try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, num);
			pstmt.setInt(2, id);
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
	  
	  //업데이트DB 오버로드
	  public int updateDB(BDto dto) {
		  int rs = 0;
		  String sql = "update jboard set writer =? , pass = ?, title =?, content=? where id=?"; //column만 변수로 받음
		  
		  try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getPass());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getId());
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
	  
	  //글 번호증가 업데이트
	  public int updateDB(int refid, int renum) {
		  int rs = 0;
		  String sql = "update jboard set renum = renum + 1 where refid=? and renum > ? ";
		  try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, refid);
			pstmt.setInt(2, renum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	  
	  //비번검증
	   public int findPass(String id, String pass) {
	      int nid = Integer.parseInt(id);
	      int result = 0;
	      
	      String sql = "select id from jboard where id=? and pass=?";
	              BDto bDto = new BDto();
	              try {
	                 pstmt = conn.prepareStatement(sql);
	                 pstmt.setInt(1, nid);
	                 pstmt.setString(2, pass);
	                 res = pstmt.executeQuery();
	                 if(res.next()) {
	                    result = 1;
	                 }        
	   }catch (SQLException e) {
	       e.printStackTrace();
	    } finally {
	        try {
	            if(res != null) res.close();
	            if(pstmt != null) pstmt.close();
	         }catch(SQLException e) {e.printStackTrace();}   
	      }
	     
	                
	      return result;
	   }
	    
	   
	   //삭제
	   public int deleteDB(String id, String pass) {
	      int nid = Integer.parseInt(id);
	      int result = 0;
	      String sql = "delete from jboard where id=? and pass=?";
	       BDto bDto = new BDto();
	         try {
	            pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, nid);
	      pstmt.setString(2, pass);
	      result = pstmt.executeUpdate();
	      return result;
	         
	   }catch (SQLException e) {
	       e.printStackTrace();
	    } finally {
	        try {
	            if(res != null) res.close();
	            if(pstmt != null) pstmt.close();
	         }catch(SQLException e) {e.printStackTrace();}   
	      }
	     
	                
	      return result;
	   }
	  
}
