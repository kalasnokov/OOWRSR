package UI.UIObjects;

import Engine.Game;
import Objects.BObject;

public class Button extends BObject {
	Game game;
	int width;
	int height;
	String url;
	String action;

	public Button(int x, int y, int width, int height, String url,
			String action, Game game) {
		super(x, y, url + "1.png");

		this.url = url;
		this.game = game;
		this.width = width;
		this.height = height;
		this.action = action;
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
		setText(action);
		setFont("Verdana");
	}

	public void update() {
		int KX = game.Keybinder.cursor.getX();
		int KY = game.Keybinder.cursor.getY();
		Boolean clicked = game.Keybinder.cursor.getClicked();
		if (KX > x && KX < (x + width) && KY > y && KY < (y + height)
				&& clicked) {
			sprite.changeURL(url + "2.png");
		} else {
			sprite.changeURL(url + "1.png");
		}
	}
}
