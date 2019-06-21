package com.tmi.emprendedores.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.tmi.emprendedores.exception.CryptoException;

public class CryptUtil {

	private static final Logger logger = LoggerFactory.getLogger(CryptUtil.class);

	private static final String ALGORITMO = "DES";
	private static final String CHARSET = "UTF8";

	private static SecretKey key;;

	private static Cipher ecipher;
	private static Cipher dcipher;

	static {
		try {
			key = KeyGenerator.getInstance(ALGORITMO).generateKey();
			
			ecipher = Cipher.getInstance(ALGORITMO);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher = Cipher.getInstance(ALGORITMO);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NO PUDO INSTANCIARSE UN ENCRIPTADOR / DECRIPTADOR CON EL ALGORITMO '"+ALGORITMO+"'");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			logger.error("NO PUDO INSTANCIARSE UN ENCRIPTADOR / DECRIPTADOR CON EL ALGORITMO '"+ALGORITMO+"'");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			logger.error("LA KEY NO ES VALIDA.");
			e.printStackTrace();
		}
	}

	public static String encrypt(String str) throws CryptoException {
		try {
			return new String(BASE64EncoderStream.encode(ecipher.doFinal(str.getBytes(CHARSET))));
		} catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			throw new CryptoException(e.getMessage(), e);
		}
	}

	public static String decrypt(String str) throws CryptoException {
		try {
			return new String(dcipher.doFinal(BASE64DecoderStream.decode(str.getBytes())), CHARSET);
		} catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CryptoException(e.getMessage(), e);
		}
		
	}

}
