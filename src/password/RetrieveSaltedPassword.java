package password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class RetrieveSaltedPassword {
private String password;
private String salt;

	public RetrieveSaltedPassword() {
		
	}
	
	public String RetrieveSaltedPassword(String password, String salt) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.password = password;
		this.salt = salt;
		String securePassword = getSecurePassword(password, salt);
		String regeneratedPassowrdToVerify = getSecurePassword(password, salt);
		return securePassword;
	}
	
	private static String getSecurePassword(String passwordToHash, String salt)
    {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
    }
}
