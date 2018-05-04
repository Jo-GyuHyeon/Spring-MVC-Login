package org.regyu.sts.cmmn.security;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.jcajce.provider.digest.Keccak.DigestKeccak;

public class CryptoUtil {
	
	public static String CryptoSHA3(String key, int hash) {	// hash --> 바이트수 / 256 , 118 , 384 , 512 등등의 암호화 방식 선택 (4가지 지원)
		DigestKeccak	md = new DigestKeccak(hash);
		
		try {
			md.update(key.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		byte[] digest = md.digest();
		
		return org.bouncycastle.util.encoders.Hex.toHexString(digest);
	}
}
