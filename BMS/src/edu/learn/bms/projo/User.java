package edu.learn.bms.projo;

public class User {
	private String userid;
	private String username;
	private String userpwd;
	private String usertruename;
	private Integer userstate;
	
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsertruename() {
		return usertruename;
	}
	public void setUsertruename(String usertruename) {
		this.usertruename = usertruename;
	}
	public Integer getUserstate() {
		return userstate;
	}
	public void setUserstate(Integer userstate) {
		this.userstate = userstate;
	}
	
	public User() {
		super();
	}
	public User(String username, String userpwd) {
		super();
		this.username = username;
		this.userpwd = userpwd;
	}
	public User(String userid, String username, String userpwd, String usertruename, Integer userstate) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.usertruename = usertruename;
		this.userstate = userstate;
	}
	@Override
	public String toString() {
		return usertruename;
	}
	
	
	
}
