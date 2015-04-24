package inputhandler;

import Engine.Game;

public class BFCC {
	Game game;

	public BFCC(Game game) {
		// Binder Function Container Class
		this.game = game;
	}

	public void jump() {
		s("jump");
	}

	public void wright() {
		s("walk right");
	}

	public void wleft() {
		s("walk left");
	}

	public void crouch() {
		s("crouch");
	}

	public void chat() {
		s("chat");
	}

	public void s(Object s) {
		System.out.println(s);
	}
}
