package com.user;

public class KeystoneConnection {
	private String protocal;

	private String address;

	private String port;

	public KeystoneConnection() {

	}

	public KeystoneConnection(String protocal, String address, String port) {
		this.protocal = protocal;
		this.address = address;
		this.port = port;
	}

	public String getKeystoneUrl(String url) {
		return this.protocal + "://" + this.address + ":" + this.port + url;
	}

	public String getProtocal() {
		return protocal;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
