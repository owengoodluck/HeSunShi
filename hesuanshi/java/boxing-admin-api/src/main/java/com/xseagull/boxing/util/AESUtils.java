package com.xseagull.boxing.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.xseagull.boxing.pay.weixin.WechatRefundInfoNotify;
import com.xseagull.boxing.pay.weixin.WeiChartConfig;

public class AESUtils {
	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "AES";
	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";
	/**
	 * 生成key
	 */
	private static SecretKeySpec key = new SecretKeySpec(EncryptUtils.md5Encode(WeiChartConfig.MCH_KEY).toLowerCase().getBytes(), ALGORITHM);
	
	static {
		
	}
	
	/**
	 * AES加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String data) throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}
 
	/**
	 * AES解密
	 * 
	 * @param base64Data
	 * @return
	 * @throws Exception
	 */
	public static String decryptData(String base64Data) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(base64Data)));
	}
 
	public static WechatRefundInfoNotify decrypt(String reqInfo) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			Object object = XMLBeanUtils.xmlToBean(WechatRefundInfoNotify.class, decryptData(reqInfo));
			if (object != null && object instanceof WechatRefundInfoNotify) {
				WechatRefundInfoNotify notify = (WechatRefundInfoNotify) object;
				return notify;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
