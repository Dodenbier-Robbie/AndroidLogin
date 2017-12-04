package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Enumeration;
import org.json.simple.JSONObject;

import model.UserID;
 
@WebServlet("/add_wishlist")
public class AddWishListItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        JSONObject json = new JSONObject();

        Enumeration<String> paramNames = request.getParameterNames();
        String params[] = new String[2];
        int i = 0;
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            params[i] = paramValues[0];
            i++;
        }
         
        try {
        		String itemCategory = params[0];
        		String itemDetail = params[1];
        		Integer user_id = UserID.user_id;
        		Date createDate = new Date();
        		
        		HibernateAddWishList wishList = new HibernateAddWishList();
        		boolean results = wishList.addWishList(user_id, itemCategory, itemDetail, createDate);
        		wishList.closeConnection();
        		
        		if (results != true) {
                    json.put("info","fail");
                } else {
                    json.put("info", "success");
                }
        		
        		wishList.closeConnection();
        		
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}