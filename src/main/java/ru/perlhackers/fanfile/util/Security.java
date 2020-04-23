package ru.perlhackers.fanfile.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static String oneWayConvert(String stringToHash) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(stringToHash.getBytes());
        return new String(sha.digest());
    }

    public static SecretKeySpec getStaticKey() {
        byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        String algorithm  = "RawBytes";
        SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
        return key;
    }

    public static String encrypt(String stringToEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec key = getStaticKey();
        Cipher cipher = Cipher.getInstance("CBC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(stringToEncrypt.getBytes());
        return new String(encryptedBytes);
    }

    public static String decrypt(String stringToDecrypt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec key = getStaticKey();
        Cipher cipher = Cipher.getInstance("CBC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(stringToDecrypt.getBytes());
        return new String(decryptedBytes);
    }
}
