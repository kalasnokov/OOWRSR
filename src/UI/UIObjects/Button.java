package UI.UIObjects;

import Engine.Game;

public class Button extends UIObject {
	public Button(int x, int y, int width, int height, String url,
			String action, Boolean anchorLeft, Game game) {
		super(x, y, height, height, url + "1.png", anchorLeft, game);

		setText(action);
		setFont("Verdana");
	}

	public void update() {

		int KX = game.Keybinder.cursor.getX();
		int KY = game.Keybinder.cursor.getY();
		Boolean clicked = game.Keybinder.cursor.getClicked();
		if (KX > x && KX < (x + width) && KY > y && KY < (y + height)
				&& clicked) {
			sprite.changeURL(super.url + "2.png");
		} else {
			sprite.changeURL(super.url + "1.png");
		}

	}
}
