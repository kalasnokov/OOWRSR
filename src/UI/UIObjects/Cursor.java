package UI.UIObjects;

import org.lwjgl.input.Mouse;

import Engine.Game;
import Objects.BObject;

public class Cursor extends BObject {
boolean clicked;
	public Cursor(Game game) {
		super(Mouse.getX(), (game.getHeight() - Mouse.getY()), "res/sprites/UI/Cursor/cursor_normal.png");
	}

	public void update(Game game) {
		x = Mouse.getX();
		y = game.getHeight() - Mouse.getY();
		clicked=Mouse.isButtonDown(0);
	}

	public void readInput() {

	}

	public Boolean getClicked() {
		return clicked;
	}
}
