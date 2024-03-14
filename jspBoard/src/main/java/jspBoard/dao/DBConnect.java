package jspBoard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
       
   public Connection conn = null;
   private String url = "jdbc:mysql://localhost:3306/javaboard";
   private String option = "?useUnicode=true&characterEncoding=utf-8"; //getter url주소 후에 ?키=값 해주는것
   private String user = "root";
   private String pass = "diwo0206^";
   
   public DBConnect() { //생성자 DBConnect가 생성되면 안에 내용이 실행되고 필드로 Connection conn을 뱉는다.
	   try {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   try {
		this.conn = DriverManager.getConnection(url+option, user, pass); //this -> 여기 위에 있는 conn 필드에 값을 넣겠다.
		System.out.println("db접속 성공");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }
	   catch(ClassNotFoundException e) {
		   e.printStackTrace();
	   }
   }
}
