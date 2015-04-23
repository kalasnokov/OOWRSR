package CC;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;

public class Game extends Head {

	public Keys keys;
	public Cursor cursor;
	public boolean paused;
	public State gameState;
	public State oldState;
	public int xoffset = 0;
	public int yoffset = 0;


	public enum State {
		MENU, PLAYING, STARTING
	}

	public void preGLInit() {
	}

	public void init() throws InterruptedException, FileNotFoundException,
			IOException {

		UPDATES_PER_SECOND = 60;
		gameState = State.MENU;

		keys = new Keys();
	}

	public void loadAssets() {
	}

	public void handleInputs(double dt) throws IOException {
		if (keys.keyPressed(Keyboard.KEY_ESCAPE)) {
			if (gameState != State.MENU)
				paused = !paused;
		}
		
		
		keys.setKeys();
	}

	public void quit() {
	}

	public void update(double dt) throws IOException {
		handleInputs(dt);
		if (gameState == State.STARTING) {
			gameState = State.PLAYING;
			oldState = State.MENU;
		}
	}

	public int render(double interpolation) {
		super.render(interpolation);
		if (gameState == State.PLAYING) {
			@SuppressWarnings("unused")
			double interp = interpolation;
			if (gameState != State.PLAYING)
				interp = 0;
		}

		return 0;
	}

	public void so(String o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
