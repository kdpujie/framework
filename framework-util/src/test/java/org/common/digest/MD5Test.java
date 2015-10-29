package org.common.digest;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.common.util.NumberUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MD5Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String str = "2015-04-10_589_699_北京市";
		String md5Str = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++){
//			 md5Str = MD5OperatorUtil.MD5_little("abcd");
			 md5Str = NumberUtil.binary2HexStr(DigestUtils.md5(md5Str));
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(md5Str);
		start = System.currentTimeMillis();
	}

}
