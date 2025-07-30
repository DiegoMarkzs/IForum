package com.projeto.IForum.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CriptografiaAES {
    private SecretKey key;
    private int KEY_SIZE = 128;
    private Cipher encryptionCipher;

    public void init() throws NoSuchAlgorithmException{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        key = generator.generateKey();

    }


    public String criptografar(String senha) throws Exception{
        init();
        byte[] messageInBytes = senha.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init((Cipher.ENCRYPT_MODE), key);
        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
        
    }
    
    private String encode(byte[] data){ return Base64.getEncoder().encodeToString(data);}

}
