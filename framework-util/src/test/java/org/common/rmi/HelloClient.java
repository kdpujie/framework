package org.common.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.common.util.rmi.IHello;
import org.common.util.rmi.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloClient {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String url = "rmi://127.0.0.1:1099/hello";
		try {
			IHello hello = (IHello)Naming.lookup(url);
			User user = new User("蒲杰","北京市朝阳区军民团结路");
			System.out.println(hello.hello(user));
			System.out.println(hello.sayHello("武媚娘"));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
