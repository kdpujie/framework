package org.common.util.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.common.util.rmi.IHello;
import org.common.util.rmi.User;

public class HelloImpl extends UnicastRemoteObject  implements IHello {
	
	private static final long serialVersionUID = 1L;

	public HelloImpl() throws RemoteException {
		super();
	}

	@Override
	public String hello(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return "hello,"+user.getName()+" ,确认您的地址："+user.getAddrress();
	}

	@Override
	public String sayHello(String name) throws RemoteException {
		return "hello,"+name;
	}

}
