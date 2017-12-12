package password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class NewSaltedPassword {
	
	private String password;
	
	public String[] NewSaltedPassword(String passwordToHash) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.password = passwordToHash;
		String salt = getSalt();
		String securePassword = getSecurePassword(passwordToHash, salt);
		String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);
		String[] hashAndSalt = {securePassword, salt};
		return hashAndSalt;
	}
	
	private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
	
	public static String getSecurePassword(String passwordToHash, String salt)
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
