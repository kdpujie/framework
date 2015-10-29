package org.common.util.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {

	public String hello(User user) throws RemoteException;
	
	public String sayHello(String name) throws RemoteException;
}
