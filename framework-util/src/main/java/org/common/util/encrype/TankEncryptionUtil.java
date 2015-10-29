package org.common.util.encrype;


import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.common.util.NumberUtil;
/**
 * Description: tanx解密工具
 * Date: 2013年9月20日
 * @author pj
 */
public class TankEncryptionUtil {
    
	/**
	 * 解密tanx竞价的真实价格
	 * @param orig
	 * @param byte[] 解密秘钥
	 * @return byte[]  tanx成交价格
	 * @author pujie
	 * */
	public static byte[] getTanxRealPrice(byte[] orig,byte[] secretKey){
		byte[] cpDst = new byte[32];
		byte[] pEncrypt = new byte[4];
		byte[] h4 = new byte[4];
		byte[] price = new byte[4];
		System.arraycopy(orig, 1, cpDst, 0,16);
		System.arraycopy(orig, 17, pEncrypt, 0,pEncrypt.length);
		System.arraycopy(secretKey, 0, cpDst, 16,secretKey.length);		
		System.arraycopy(DigestUtils.md5(cpDst),0,h4, 0,h4.length);
		for(int i=0;i<h4.length;i++){
			price[i] = (byte) (h4[i] ^ pEncrypt[i]);
		}
		return price;
	}
	/**
	 * 计算tanx的CRC
	 * @param orig
	 * @param byte[] 成交交个price
	 * @param byte[] 解密秘钥
	 * @return byte[]  CRC
	 * @author pujie
	 * */
	public static byte[] getTanxCRC(byte[] orig,byte[] price,byte[] secretKey){
		byte[] crc = new byte[4];
		byte[] md5Src = new byte[37];
		System.arraycopy(orig, 0, md5Src, 0, 17);
		System.arraycopy(price, 0, md5Src, 17, price.length);
		System.arraycopy(secretKey, 0, md5Src, 21,secretKey.length);
		System.arraycopy(DigestUtils.md5(md5Src),0,crc, 0, crc.length);
		return crc;
	}
	/**
	 * CRC校验
	 * @param orig
	 * @param byte[]  CRC
	 * @return boolean
	 * @author pujie
	 * */
	public static boolean crcVerify(byte[] orig,byte[] crc){
		boolean flag = false;
		if(orig!=null && null != orig){
			byte[] crcSrc = new byte[4];
			System.arraycopy(orig, 21, crcSrc, 0, 4);
			flag = Arrays.equals(crc, crcSrc);
		}
		return flag;
	}
	/**
	 * tanx竞价反馈价格解密。解密失败返回-1
	 * @param orig
	 * @param key
	 * @return price
	 */
	public static int getWinPrice(byte[] orig,byte[] key){
		int result =-1;
		byte[] price = getTanxRealPrice(orig,key);
		byte[] crc = getTanxCRC(orig, price,key);
		result = crcVerify(orig, crc)?NumberUtil.byteArrayToInt(price):-1;
		return result;
	}
	
	public static void main(String[] args){
		
	}
}
