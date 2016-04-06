package Utility;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;


public class Hash {

    public String hashPassword = "null";


    public Hash(String password) {
        try {
            int iterations = 100;
            char[] chars = password.toCharArray();
            byte[] salt = getSalt().getBytes();

            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 12 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            hashPassword = iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getSalt() throws Exception {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt.toString();
        } catch (Exception e) {
            return null;
        }

    }

    private static String toHex(byte[] array) throws Exception

    {
        try {
            BigInteger bi = new BigInteger(1, array);
            String hex = bi.toString(16);
            int paddingLength = (array.length * 2) - hex.length();
            if (paddingLength > 0) {
                return String.format("%0" + paddingLength + "d", 0) + hex;
            } else {
                return hex;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateHash(String password) {
        Hash hashPassword = new Hash(password);
        return hashPassword.hashPassword;
    }
}