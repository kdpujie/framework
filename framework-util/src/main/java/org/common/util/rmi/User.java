package org.common.util.rmi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1109711676125380582L;
	private String name;
	private String addrress;
	private Map<String, String> map = new HashMap<String, String>();
	
	public User(String name,String addrress){
		this.name = name;
		this.addrress = addrress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddrress() {
		return addrress;
	}

	public void setAddrress(String addrress) {
		this.addrress = addrress;
	}
	
	
	
}
