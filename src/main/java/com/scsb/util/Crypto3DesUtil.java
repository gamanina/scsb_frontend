package com.scsb.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 建立日期：2020/05/29
 * 程式摘要：com.scsb.util
 * 類別名稱：Crypto3DesUtil.java 
 * 程式內容說明：3Des加密程式
 * @author Stan
 * @version 1.0
 * @since 1.0
 */
public class Crypto3DesUtil {

    private static final String KEY_STRING= new BASE64Encoder().encode("20HoyeEmersNikeSecretkey".getBytes());
    private static final byte[] KEY_IV="00000000".getBytes();//加密IV偏移量，必須為8為字節
    private static final String KEY_ALGORITHM = "DESede";//3DES加密算法
    private static final String PADDING_PATTERN="DESede/CBC/PKCS5Padding";//填充方式

    /**
     * 3Des CBC 模式加密
     * @param data 明文
     * @return Base64編碼字串密文
     * @throws Exception
     */
    public static String Encrypt3DesCBC(String data)throws Exception{
        Cipher cipher = Cipher.getInstance(PADDING_PATTERN);
        IvParameterSpec ips = new IvParameterSpec(KEY_IV);
        cipher.init(Cipher.ENCRYPT_MODE, get3DesKey(), ips);
        byte[] bOut = cipher.doFinal(data.getBytes());
        return new BASE64Encoder().encode(bOut);
    }

    /**
     * 3DES解密
     * @param cipherBase64Str Base64編碼字串密文
     * @return 解密後內容
     * @throws Exception
     */
    public static String Decrypt3DesCBC(String cipherBase64Str) throws Exception {
        byte[] cipherStrByte= new BASE64Decoder().decodeBuffer(cipherBase64Str);
        Cipher cipher = Cipher.getInstance(PADDING_PATTERN);
        IvParameterSpec ips = new IvParameterSpec(KEY_IV);
        cipher.init(Cipher.DECRYPT_MODE, get3DesKey(), ips);
        byte[] bOut = cipher.doFinal(cipherStrByte);
        return new String(bOut, "UTF-8");

    }

    /**
     * 獲取3Des密鑰
     * @return
     * @throws Exception
     */
    public static Key get3DesKey()throws Exception{
        //将keyString从Base64编码字符串转为原始正常的byte[]
        byte[] key= new BASE64Decoder().decodeBuffer(KEY_STRING);
        if(key.length<24){
        	key=build3DesKey(new String(key, "UTF-8"));
        }
        //初始化DES密鑰規則
        DESedeKeySpec spec = new DESedeKeySpec(key);
        //初始化3DES（desede）密鑰工廠
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        //生成密鑰
        return keyfactory.generateSecret(spec);
    }
    
	/**
	 * 根據字串生成密鑰
	 * 
	 * @param keyStr 密鑰字串
	 */
	private static byte[] build3DesKey(String keyStr) {
	    try {
	        byte[] key = new byte[24];    //聲明一個24位元的陣列，默認裡面為0
	        byte[] temp = keyStr.getBytes("UTF-8");    //將字串轉成字串陣列
	        if (key.length > temp.length) {
	            //如果temp不夠24位元，則複製temp陣列整個長度的內容到key陣列中
	            System.arraycopy(temp, 0, key, 0, temp.length);
	        } else {
	            //如果temp大於24位元，則複製temp陣列整個長度的內容到key陣列中
	            System.arraycopy(temp, 0, key, 0, key.length);
	        }
	        return key;
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();       
	        return null;
	    }
	}
	
	/**
	 * @param keys
	 * @param encoding
	 * @return
	 */
	public static String URLDecoder(String keys, String encoding) {
		try {
			return java.net.URLDecoder.decode(keys, encoding);
		} catch (UnsupportedEncodingException uee) {
			return keys;
		}
	}
}
