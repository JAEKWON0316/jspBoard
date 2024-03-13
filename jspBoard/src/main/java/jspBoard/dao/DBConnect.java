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
   
   public DBConnect() { //생성자
	   try {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   try {
		this.conn = DriverManager.getConnection(url+option, user, pass);
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
