package org.common.rmi;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.common.util.rmi.IHello;
import org.common.util.rmi.impl.HelloImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloServer {

	public static void main(String[] args) {
		try {
			IHello hello = new HelloImpl();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("hello", new HelloImpl());
			System.out.print("hello Server 启动OK");
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
