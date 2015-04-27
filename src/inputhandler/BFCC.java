package inputhandler;

import Engine.Game;

public class BFCC {
	Game game;

	public BFCC(Game game) {
		// Binder Function Container Class
		this.game = game;
	}

	public void jump(boolean state) {
		s("jump "+state);
	}

	public void wright(boolean state) {
		s("walk right "+state);
	}

	public void wleft(boolean state) {
		s("walk left "+state);
	}

	public void crouch(boolean state) {
		s("crouch "+state);
	}

	public void chat(boolean state) {
		s("chat "+state);
	}

	public void s(Object s) {
		System.out.println(s);
	}
}
