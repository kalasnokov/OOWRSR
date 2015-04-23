package Datahandlers;

import java.net.InetAddress;

public class Clientinfo {
	private int port;
	private int id;
	private InetAddress address;

	public Clientinfo(InetAddress address, int port, int id) {
		this.address = address;
		this.port = port;
		this.id = id;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public int getId() {
		return id;
	}
}
