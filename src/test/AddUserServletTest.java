package test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;
import org.junit.Test;

import controller.HibernateAddUser;
import model.Users;
import password.NewSaltedPassword;

public class AddUserServletTest {

	@Test
	public void testDoGet() throws NoSuchAlgorithmException, NoSuchProviderException {
		JSONObject json = new JSONObject();
		JSONObject json2 = new JSONObject();
		json2.put("info", "success");
		
		String firstname = "Test";
		String lastname = "User";
		String email = "testuser01@test.com"; // Need to increment email address value per test run
		int age = 5;
		String username = "testuser01"; // Need to increment username value per test run
		String password = "test";
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

		assertEquals(json,json2);
	}

}
