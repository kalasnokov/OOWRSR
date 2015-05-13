package UI.UIObjects;

import Engine.Game;
import Objects.BObject;

public class UIObject extends BObject {
	int width;
	int height;
	public String url;
	Game game;

	public UIObject(int x, int y, int width, int height, String url, boolean anchorLeft, Game game) {
		super(x, y, url);
		this.game=game;
		this.url=url;
		this.width=width;
		this.height=height;
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
	
	public void setUIFont(String action){
		setText(action);
		setFont("Verdana");
	}
}
