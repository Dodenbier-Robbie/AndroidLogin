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

import password.NewSaltedPassword;
 
@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet implements Runnable {
	private static final long serialVersionUID = 1L;
	Thread searcher;
	long lastprime = 0;
	Date lastprimeModified = new Date();
	
	public void init() throws ServletException {
		searcher = new Thread(this);
        searcher.setPriority(Thread.MIN_PRIORITY);
        searcher.start();
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
        		
        		NewSaltedPassword securePassword = new NewSaltedPassword();
        		String[] hashPassword = securePassword.NewSaltedPassword(password);
        		
        		String newPassword = hashPassword[0];
        		String salt = hashPassword[1];
        		
        		HibernateAddUser createUser = new HibernateAddUser();
        		createUser.addUser(firstname, lastname, email, age, createDate);
        		Integer userId = createUser.getUserId(email);
        		boolean results = createUser.addLogin(userId, username, newPassword, createDate, salt);
        		createUser.closeConnection();
        		
        		if (results != true) {
                json.put("info","fail");
            } else {
                json.put("info", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
    
    public void run() {
		long canidate = 2;
		
		while (true) {
			canidate +=2;
			try {
				searcher.sleep(5000);
			} catch (InterruptedException ignored) {
				
			}
			lastprime = 1;
		}
	}
    
    public void destroy() {
    		searcher.stop();
    }
}