package model;

import java.util.Date;

public class WishList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer itemId;
	private Integer userId;
	private String itemCategory;
	private String itemName;
	private Date createDate;

	public WishList() {
	}

	public WishList(Integer userId, String itemCategory, String itemName, Date createDate) {
		this.userId = userId;
		this.itemCategory = itemCategory;
		this.itemName = itemName;
		this.createDate = createDate;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
    public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
	    this.createDate = createDate;
	} 
}