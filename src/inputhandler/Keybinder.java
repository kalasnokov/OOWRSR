package inputhandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import UI.UIObjects.Cursor;
import AFSFile.AFSFile;
import AFSFile.Preset;
import Engine.Game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class Keybinder {
	AFSFile f;
	Boolean debug;
	BFCC bfcc;
	Vector<Key> keys = new Vector<Key>();
	public Cursor cursor;

	public Keybinder(Boolean debug, Game game) throws FileNotFoundException,
			IOException {
		cursor = new Cursor(game);
		bfcc = new BFCC(game);
		this.debug = debug;

		f = new AFSFile("res/options/bindings.cfg", new Preset().bindings(),
				debug);
		int i = 0;
		while (true) {
			if (!f.ReadLine(i).equals("null")) {
				String binding = f.ReadLine(i);
				if (debug) {
					s("Read line " + (i + 1) + " as " + binding);
				}
				String[] AK = binding.split(":");
				if (AK.length != 1) {
					if (AK.length > 2) {
						keys.add(new Key(AK[0].toLowerCase(), Keyboard
								.getKeyIndex(AK[2].toUpperCase()), Integer
								.parseInt(AK[1])));
					} else {
						keys.add(new Key(AK[0].toLowerCase(), Keyboard
								.getKeyIndex(AK[1].toUpperCase())));
					}
				}
			} else {
				break;
			}
			i++;
		}
	}

	public void readinput(Game game) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		while (Keyboard.next()) {
			int inp = Keyboard.getEventKey();
			// s(Keyboard.getKeyName(inp));
			String in = null;
			int num = 0;
			for (Key key : keys) {
				if (key.getKey() == inp) {
					in = key.getAction();
					num = key.getNum();
				}
			}
			if (game.c.isActive()) {
				
			} else {
				Boolean value = Keyboard.getEventKeyState();
				Method method;
				try {
					if (num != 0) {
						method = bfcc.getClass().getMethod(in.toLowerCase(),
								new Class[] { boolean.class, int.class });
						method.invoke(bfcc, value, num);
					} else {
						method = bfcc.getClass().getMethod(in.toLowerCase(),
								new Class[] { boolean.class });
						method.invoke(bfcc, value);
					}
				} catch (Exception e) {
					if (debug) {
						s(e);
						s(inp);
					}
				}
			}
		}
	}

	public void update(Game game) {
		cursor.update(game);
	}

	public void render(Game game) {
		cursor.render(game);
	}

	public void s(Object s) {
		System.out.println(s);
	}
	public void setMode(boolean b){
		
	}
}
