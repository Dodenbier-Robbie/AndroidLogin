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

import password.GetSaltValue;
import password.RetrieveSaltedPassword;
 
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        		String userName = params[0];
        		String password = params[1];
        		
        		GetSaltValue saltValue = new GetSaltValue();
        		String salt = saltValue.getSaltDB(userName);
        		saltValue.closeConnection();
        		
        		if(salt.equals("Fail")) {
        			json.put("info", "fail");
        		} else {
	        		RetrieveSaltedPassword securePassword = new RetrieveSaltedPassword();
	        		String hashPassword = securePassword.RetrieveSaltedPassword(password, salt);
	        		
	        		HibernateLogin login = new HibernateLogin();
	        		boolean results = login.validateLogin(userName, hashPassword);
	        		if (results != true) {
	                json.put("info","fail");
	            } else {
	                json.put("info", "success");
	            }
	        		login.closeConnection();
        		}
        		
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}