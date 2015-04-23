package Objects.MObjects;

import java.io.IOException;

import Datahandlers.Base;
import Datahandlers.Clientinfo;
import Objects.BObject;
import Objects.PChar;

public class MObject extends BObject {
	PChar pc;
	boolean running = true;
	boolean moving = false;
	boolean server;
	int xspd = 0;
	int yspd = 0;
	Clientinfo CI;
	Base base;

	public MObject(int x, int y, PChar pc, Clientinfo CI, boolean server,
			Base base) {
		super(x, y);
		this.CI = CI;
		this.server = server;
		this.pc = pc;
		this.base = base;
		new Thread(new Runnable() {
			public void run() {
				while (running) {
					update();
					try {
						Thread.sleep(17);
					} catch (InterruptedException e) {
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
							Thread.sleep(500);
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
