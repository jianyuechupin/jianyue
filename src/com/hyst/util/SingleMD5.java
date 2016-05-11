package com.hyst.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @author DongYi
 * @version 创建时间：2016年4月11日 下午2:27:03
 */
public class SingleMD5 {
	/** 加密类型*/
	private static String algorithm = "MD5";
	/**
	 * MD5加密，返回byte[]类型
	 * @param data 需要加密的字符串
	 * @return 加密后的byte数字
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] encryptMD5(byte[] data)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(data);
		return digest.digest();
	}

	/**
	 * 把MD5加密数据转换String类型
	 * @param data byte转换后数组
	 * @return 经过MD5加密的字符串
	 * @throws NoSuchAlgorithmException
	 */
	private static String encryptMD5toString(byte[] data)
			throws NoSuchAlgorithmException {
		String str = "";
		String str16;
		System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {
			str16 = Integer.toHexString(0xFF & data[i]);
			if (str16.length() == 1) {
				str = str + "0" + str16;
			} else {
				str = str + str16;
			}
		}
		return str;
	}
	/**
	 * 字符串MD5加密
	 * @param str 需要加密的字符串
	 * @return 加密后32位字符串
	 */
	public static String encryptByMD5(String str){
		if (str==null) {
			return null;
		}
		try {
			byte []bytys=encryptMD5(str.getBytes());
			return encryptMD5toString(bytys);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	private SingleMD5(){
		
	}
}
