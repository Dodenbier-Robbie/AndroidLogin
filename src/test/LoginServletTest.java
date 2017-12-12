package test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.junit.Test;

import controller.HibernateLogin;
import password.GetSaltValue;
import password.RetrieveSaltedPassword;

public class LoginServletTest {

	@Test
	public void testDoGet() throws NoSuchAlgorithmException, NoSuchProviderException {

	    		String userName = "admin";
	    		String password = "123";
	    		
	    		GetSaltValue saltValue = new GetSaltValue();
	    		String salt = saltValue.getSaltDB(userName);
	    		saltValue.closeConnection();
	    		
	    		RetrieveSaltedPassword securePassword = new RetrieveSaltedPassword();
        		String hashPassword = securePassword.RetrieveSaltedPassword(password, salt);
        		
        		HibernateLogin login = new HibernateLogin();
        		boolean results = login.validateLogin(userName, hashPassword);
        		login.closeConnection();
        		
        		assertTrue(results);
	}

}
