package model;

import java.util.Date;

public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String firstname;
	private String lastname;
	private String email;
	private int age;
	private Date createDate;

	public Users() {
	}

	public Users(String firstname, String lastname, String email, int age, Date createDate) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.age = age;
		this.createDate = createDate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
    public Date getCreateDate() {
    		return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    } 
}