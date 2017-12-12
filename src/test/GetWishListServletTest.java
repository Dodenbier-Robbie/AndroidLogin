package test;

import static org.junit.Assert.*;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import controller.HibernateGetWishList;
import model.UserID;
import model.WishList;

public class GetWishListServletTest {

	@Test
	public void testDoGet() {
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
        
		Integer user_id = 1;
		
		HibernateGetWishList wishList = new HibernateGetWishList();
		List feed = wishList.getWishList(user_id);
		
		for(int i = 0; i < feed.size(); i++) {
    		WishList jsonFeed = (WishList) feed.get(i);
    		String itemCat = jsonFeed.getItemCategory();
    		String itemDet = jsonFeed.getItemName();
			JSONObject innerJson = new JSONObject();
			innerJson.put("itemCategory", itemCat);
			innerJson.put("itemDetail", itemDet);
			jArray.add(innerJson);
		}
		
		json.put("wishList", jArray);
		wishList.closeConnection();
        
        assertFalse("JSON feed is not empty!",json.isEmpty());
	}

}
