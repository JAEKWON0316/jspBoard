package jspBoard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jspBoard.dao.DBConnect;
import jspBoard.dao.JBoardDao;
import jspBoard.dto.BDto;

public class DbWorks {
    
	private int limitPage; 
	private int listCount; 
	private String st;  //검색 sname
	private String txt; //검색 svalue
	private Connection conn;
	private DBConnect db = new DBConnect();
	private String id;
	
	public DbWorks() { //view를 받을 빈 생성자
		   
	}
	
	public DbWorks(int limitPage, int listCount, String st, String txt) {
		   this.limitPage = limitPage;
    	   this.listCount = listCount;
    	   this.st = st;
    	   this.txt = txt;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//전체 게시판글 수
	public int getAllSelect() {
		int cnt = 0;
		try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			if(st == null) {
				cnt = dao.AllselectDB();
				System.out.println(dao.AllselectDB(st, txt));
			}
			else {
				cnt = dao.AllselectDB(st, txt);
			
			}
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			db.closeConnection();
		}
		
		return cnt;
	}
	
	//일반 페이징이 있는 목록
	 public ArrayList<BDto> getList(){
		 ArrayList<BDto> lists = null;
		 try {
				conn = db.getConnection();
				JBoardDao dao = new JBoardDao(conn);
				lists = dao.selectDB(limitPage, listCount);
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     finally {
				db.closeConnection();
			}
		 return lists;
	 }
	 
	 //검색 목록
	 public ArrayList<BDto> getSearchList(){
		 ArrayList<BDto> lists = null;
		 try {
				conn = db.getConnection();
				JBoardDao dao = new JBoardDao(conn);
				lists = dao.selectDB(limitPage, listCount, st, txt);
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     finally {
				db.closeConnection();
			}
		 return lists;
	 }
	 
	 //contents 보기
	 public BDto getSelectOne() {
		 BDto list = null;
		 try {
			conn = db.getConnection();
			JBoardDao dao = new JBoardDao(conn);
			list = dao.viewDB(getId());
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;
	 }
	 
	 //update (조회수 증가)
	 public void getUpdate(int count) {
		 int uid = Integer.parseInt(getId());
		 try {
				conn = db.getConnection();
				JBoardDao dao = new JBoardDao(conn);
				dao.updateDB(uid, count, "hit");
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		    	db.closeConnection();
		    }
	 }

	 
}
