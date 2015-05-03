package Engine;

import inputhandler.Keybinder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import UI.UI;
import UI.UIObjects.Button;

public class Game extends Head {
	public UI c;
	public boolean paused;
	public State gameState;
	public State oldState;
	public int xoffset = 0;
	public int yoffset = 0;
	public Keybinder Keybinder;
	boolean debug = false;
	Button button;

	public enum State {
		MENU, PLAYING, STARTING
	}

	public void preGLInit() {

	}

	public void init() throws Exception {

		UPDATES_PER_SECOND = 60;
		gameState = State.MENU;
		Keybinder = new Keybinder(debug, this);
		c = new UI("res/options/UIS/Creator.UICT", this);
	}

	public void loadAssets() {

	}

	public void handleInputs(double dt) throws IOException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Keybinder.readinput(this);
	}

	public void quit() {
	}

	public void update(double dt) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		handleInputs(dt);
		if (gameState == State.STARTING) {
			gameState = State.PLAYING;
			oldState = State.MENU;
		}
		c.update();
		Keybinder.update(this);
	}

	public int render(double interpolation) {
		super.render(interpolation);
		if (gameState == State.PLAYING) {
			@SuppressWarnings("unused")
			double interp = interpolation;
			if (gameState != State.PLAYING)
				interp = 0;
		}
		if (c.isActive()) {
			c.render();
		}
		Keybinder.render(this);
		return 0;
	}

	public void so(String o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
