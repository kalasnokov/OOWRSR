package Objects;

import Engine.Game;
import UI.Sprite;

public class BObject {
	protected int x;
	protected int y;
	protected Sprite sprite;

	public BObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Game game) {
		sprite.render(x, y);
	}

	public void spriteupdate() {

	}

	public void printData() {
		s("X: " + x);
		s("Y: " + y);
	}

	public void s(Object s) {
		System.out.println(s);
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

}
