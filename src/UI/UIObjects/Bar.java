package UI.UIObjects;

import Engine.Game;
import Objects.BObject;

public class Bar extends BObject {
	int width;
	int height;
	public Bar(int x, int y, int width, int height, String url, Game game) {
		super(x, y, url);
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
	}
}
