package UI.UIObjects;

import org.lwjgl.input.Mouse;

import Engine.Game;
import Objects.BObject;
import UI.Sprite;

public class Cursor extends BObject {

	public Cursor(Game game) {
		super(Mouse.getX(), (game.getHeight() - Mouse.getY()));
		sprite = new Sprite("res/sprites/UI/Cursor/cursor_normal.png");
	}

	public void update(Game game) {
		x = Mouse.getX();
		y = game.getHeight() - Mouse.getY();
	}

	public void readInput() {

	}
}
