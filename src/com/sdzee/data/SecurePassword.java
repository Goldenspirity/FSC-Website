package com.sdzee.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurePassword {

    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
    public static byte[] getSecurePassword(String passwordToHash, byte[] salt, String ENCRYPTION_ALGORITHM) {
        byte[] generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            md.update(salt);
            generatedPassword = md.digest(passwordToHash.getBytes());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    public static byte[] getSecurePassword(String passwordToHash, String ENCRYPTION_ALGORITHM) {
        byte[] generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            generatedPassword = md.digest(passwordToHash.getBytes());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
}
