package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import model.UserID;
import model.WishList;
 
@WebServlet("/get_wishlist")
public class GetWishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        JSONObject json = new JSONObject();
        JSONArray jArray = new JSONArray();
         
        try {
        		Integer user_id = UserID.user_id;
        		
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
        		
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}