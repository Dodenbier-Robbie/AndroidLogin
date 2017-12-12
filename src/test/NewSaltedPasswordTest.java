package test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.junit.Test;

import password.NewSaltedPassword;

public class NewSaltedPasswordTest {

	@Test
	public void testGetSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt1 = new byte[16];
        byte[] salt2 = new byte[16];
        sr.nextBytes(salt1);
        sr.nextBytes(salt2);
     
        assertNotNull("Salt value is null!", salt1);
        assertNotNull("Salt value is null!", salt2);
        assertNotSame(salt1, salt2);
	}
	
	@Test
	public void testGetSecurePassword() throws NoSuchAlgorithmException, NoSuchProviderException {
		String password = "testPassword";
		
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        String salt1 = salt.toString();
        String securePassword = NewSaltedPassword.getSecurePassword(password, salt1);

        assertNotNull("Password value is null!", password);
        assertNotNull("Password value is null!", securePassword);
        assertNotEquals(password,securePassword);
	}

}
