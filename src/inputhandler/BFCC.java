package inputhandler;

import Engine.Game;

public class BFCC {
	Game game;

	public BFCC(Game game) {
		// Binder Function Container Class
		this.game = game;
	}

	public void jump(boolean state) {
		s("jump " + state);
	}

	public void walk_right(boolean state) {
		s("walk right " + state);
	}

	public void walk_left(boolean state) {
		s("walk left " + state);
	}

	public void crouch(boolean state) {
		s("crouch " + state);
	}

	public void chat(boolean state) {
		s("chat " + state);
	}

	public void light_attack(boolean state) {
		s("light attack " + state);
	}

	public void medium_attack(boolean state) {
		s("medium attack " + state);
	}

	public void heavy_attack(boolean state) {
		s("heavy attack " + state);
	}

	public void special_attack(boolean state) {
		s("special attack " + state);
	}

	public void menu(boolean state) {
		s("escape " + state);
	}

	public void action(boolean state, int num) {
		s("action " + num + " " + state);
	}

	public void s(Object s) {
		System.out.println(s);
	}
}
