package Datahandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Vector;

import Objects.BObject;
import Objects.MObjects.MObject;
import UI.Server;

public class Base {

	boolean server;
	boolean running = true;
	boolean debug;
	boolean ICCheck = true;
	String serverlog = "";
	String rcvd;
	String FL;
	String[] Rsplit;
	DatagramSocket s;
	int port = 25565;
	LocalDateTime d = LocalDateTime.now();
	Server leader;
	Vector<BObject> objects = new Vector<BObject>();

	public Base(boolean debug, boolean ICCheck, Object leader)
			throws SocketException {

		if (leader != null) {
			server = true;
			this.leader = (Server) leader;
		}

		if (ICCheck && this.server) {
			try {
				URL whatismyip = new URL("http://checkip.amazonaws.com");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						whatismyip.openStream()));

				String ip = in.readLine();
				s("Your IP is " + ip);
			} catch (Exception e) {
				s("Failed to retrive IP, are you sure you are connected to the internet?");
			}
		}

		MObject m = new MObject(0, 10, null, null, server, this);
		m.printData();
		try {
			s = new DatagramSocket(port);
			s("Server starting");
			new Thread(new Runnable() {
				public void run() {
					while (running) {

						byte[] buf = new byte[1024];
						DatagramPacket dgp = new DatagramPacket(buf, buf.length);
						try {
							s.receive(dgp);
						} catch (IOException e) {
							s("failed to receive");
							e.printStackTrace();
						}
						rcvd = new String(dgp.getData());
						rcvd.trim();
						if (debug) {
							s(rcvd);
						}
						Rsplit = rcvd.split(":");
						FL = Rsplit[0];

					}
				}
			}).start();
		} catch (Exception e) {
			s("Failed to initiate server");
		}
	}

	public void connect() {
		if (FL.equals("!")) {

		}
	}

	public void s(Object s) {
		s = getTime() + s + "\n";
		System.out.println(s);
		serverlog += s;
		if (server) {
			leader.ap(getserverlog());
		}
	}

	public String getserverlog() {
		String i = serverlog;
		serverlog = "";
		return i;
	}

	public String getTime() {
		int Ihour = d.getHour();
		String Shour = "" + Ihour;
		if (Ihour < 10) {
			Shour = "0" + Ihour;
		}

		int Imin = d.getMinute();
		String Smin = "" + Imin;
		if (Imin < 10) {
			Smin = "0" + Imin;
		}
		return "[" + Shour + ":" + Smin + "]: ";
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
		s("Debug: " + debug);
	}

	public void setICCheck(boolean ICCheck) {
		this.ICCheck = ICCheck;
		s("ICCheck: " + ICCheck);
	}

	public void send(String msg, InetAddress address, int port)
			throws IOException {
		byte[] buf = new byte[1024];
		buf = msg.getBytes();
		DatagramPacket out = new DatagramPacket(buf, buf.length, address, port);
		s.send(out);
	}
}
