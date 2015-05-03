package UI.UIObjects;

import Engine.Game;
import Objects.BObject;

public class InputBar extends BObject {
	Game game;
	int width;
	int height;
	String url;
	Boolean focused;
	String[] line;
	int target = 0;

	public InputBar(int x, int y, int width, int height, String URL, Game game) {
		super(x, y, URL);
		this.game = game;
		if (width == 0) {
			this.width = game.getWidth() - x;
		}
		if (height == 0) {
			this.height = game.getHeight() - y;
		}
		sprite.setWidth(this.width);
		sprite.setHeight(this.height);
		if (x == game.getWidth()) {
			this.x -= sprite.getWidth();
		}
		if (y == game.getHeight()) {
			this.y -= sprite.getHeight();
		}
		setText("");
		setFont("Verdana");
	}

	public void update() {
		int KX = game.Keybinder.cursor.getX();
		int KY = game.Keybinder.cursor.getY();
		Boolean clicked = game.Keybinder.cursor.getClicked();
		focused = (KX > x && KX < (x + width) && KY > y && KY < (y + height) && clicked);
	}

	public void addChar(char c) {
		line[target]=String.valueOf(c);
		target++;
	}

	public void removeChar() {
		target--;
		line[target] = "";
	}
}
