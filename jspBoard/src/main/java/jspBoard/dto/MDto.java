package jspBoard.dto;

import java.sql.Timestamp;

public class MDto {
	//필드
    private int id;
    private String userid;
    private String userpass;
    private String username;
    private String useremail;
	private String usertel;
    private String address;
    private String userlink;
    private String role;
    private Timestamp wdate;
	private String addr1;
    private String addr2;
    private String usertel2;
    private String usertel3;
    
    
    //생성자 생성
    public MDto() { //기본생성자
    	
    }
	public MDto(int id, String userid, String userpass, String username, String useremail, String usertel,
			String address, String userlink, String role, Timestamp wdate, String addr1, String addr2, String usertel2,
			String usertel3) {
		super();
		this.id = id;
		this.userid = userid;
		this.userpass = userpass;
		this.username = username;
		this.useremail = useremail;
		this.usertel = usertel;
		this.address = address;
		this.userlink = userlink;
		this.role = role;
		this.wdate = wdate;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.usertel2 = usertel2;
		this.usertel3 = usertel3;
	}
	
	//getter setter
	  public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getUserpass() {
			return userpass;
		}
		public void setUserpass(String userpass) {
			this.userpass = userpass;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUseremail() {
			return useremail;
		}
		public void setUseremail(String useremail) {
			this.useremail = useremail;
		}
		public String getUsertel() {
			return usertel;
		}
		public void setUsertel(String usertel) {
			this.usertel = usertel;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getUserlink() {
			return userlink;
		}
		public void setUserlink(String userlink) {
			this.userlink = userlink;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public Timestamp getWdate() {
			return wdate;
		}
		public void setWdate(Timestamp wdate) {
			this.wdate = wdate;
		}
		public String getAddr1() {
			return addr1;
		}
		public void setAddr1(String addr1) {
			this.addr1 = addr1;
		}
		public String getAddr2() {
			return addr2;
		}
		public void setAddr2(String addr2) {
			this.addr2 = addr2;
		}
		public String getUsertel2() {
			return usertel2;
		}
		public void setUsertel2(String usertel2) {
			this.usertel2 = usertel2;
		}
		public String getUsertel3() {
			return usertel3;
		}
		public void setUsertel3(String usertel3) {
			this.usertel3 = usertel3;
		}
		@Override
		public String toString() {
			return "MDto [id=" + id + ", userid=" + userid + ", userpass=" + userpass + ", username=" + username
					+ ", useremail=" + useremail + ", usertel=" + usertel + ", address=" + address + ", userlink="
					+ userlink + ", role=" + role + ", wdate=" + wdate + ", addr1=" + addr1 + ", addr2=" + addr2
					+ ", usertel2=" + usertel2 + ", usertel3=" + usertel3 + "]";
		}
		
		//tostring
		
    
   
}
