package Objects.MObjects;

import java.io.IOException;

import Datahandlers.Base;
import Datahandlers.Clientinfo;
import Objects.BObject;
import Objects.ODC;

public class MObject extends BObject {
	boolean running = true;
	boolean moving = false;
	boolean server;
	int xspd = 0;
	int yspd = 0;
	Clientinfo CI;
	Base base;

	public MObject(int x, int y, ODC odc, Clientinfo CI, boolean server,
			Base base) {
		super(x, y, "make reader first");
		this.CI = CI;
		this.server = server;
		this.base = base;
		new Thread(new Runnable() {
			public void run() {
				while (running) {
					update();
					try {
						Thread.sleep(17);// change to be more dynamic, take into
											// account the time it takes to
											// execute the thread
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		if (!server) {
			new Thread(new Runnable() {
				public void run() {
					while (running) {
						try {
							sendData();
							Thread.sleep(500);// change to be more dynamic, take
												// into account the time it
												// takes to execute the thread
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void sendData() throws IOException {
		if (moving) {
			String msg = "@:" + CI.getId() + ":" + x + ":" + y + ":";
			base.send(msg, CI.getAddress(), CI.getPort());
		}
	}

	public void update() {
		x += xspd;
		y += yspd;
		moving = (yspd != 0 || xspd != 0);
	}

	public void infYspd(int s) {
		yspd -= s;
	}

	public void infXspd(int s) {
		xspd += s;
	}
}
