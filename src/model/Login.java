package model;

import java.util.Date;

public class Login implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer loginId;
	private Integer userId;
	private String username;
	private String password;
	private Date createDate;

	public Login() {
	}

	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Login(Integer userId, String username, String password, Date createDate) {
		this.username = username;
		this.password = password;
		this.createDate = createDate;
	}

	public Integer getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
	    this.createDate = createDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	} 
}