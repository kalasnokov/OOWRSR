package UI.UIObjects;

import Engine.Game;
import Objects.BObject;

public class Button extends BObject {

	public Button(int x, int y, Game game) {
		super(x, y);

	}

	public int woah(int i) {
		System.out.println("Holy shit");
		return 0;
	}
}
