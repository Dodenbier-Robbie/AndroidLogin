package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Enumeration;
import org.json.simple.JSONObject;
 
@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        JSONObject json = new JSONObject();

        Enumeration<String> paramNames = request.getParameterNames();
        String params[] = new String[6];
        int i = 0;
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            params[i] = paramValues[0];
            i++;
        }
         
        try {
        		String firstname = params[0];
        		String lastname = params[1];
        		String email = params[2];
        		int age = Integer.parseInt(params[3]);
        		String username = params[4];
        		String password = params[5];
        		Date createDate = new Date();
        		
        		HibernateAddUser createUser = new HibernateAddUser();
        		createUser.addUser(firstname, lastname, email, age, createDate);
        		Integer userId = createUser.getUserId(email);
        		createUser.addLogin(userId, username, password, createDate);
        		
        		json.put("info", "success");
        		
        		//if (results != true) {
                //json.put("info","fail");
            //} else {
                //json.put("info", "success");
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}