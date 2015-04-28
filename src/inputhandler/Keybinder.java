package inputhandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import AFSFile.AFSFile;
import AFSFile.Presets;
import Engine.Game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class Keybinder {
	AFSFile f;
	Boolean debug;
	BFCC bfcc;
	Vector<Key> keys = new Vector<Key>();

	public Keybinder(Boolean debug, Game game) throws FileNotFoundException,
			IOException {
		bfcc = new BFCC(game);
		this.debug = debug;

		f = new AFSFile("res/options/bindings.cfg", new Presets().bindings(),
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
						keys.add(new ActionKey(AK[0].toLowerCase(), Integer
								.parseInt(AK[1]), Keyboard.getKeyIndex(AK[2]
								.toUpperCase())));
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
			for (Key key : keys) {
				if (key.getKey() == inp) {
					in = key.getAction();
					if(key.getClass().getSimpleName().equals("ActionKey")){
						
					}
				}
			}
			Boolean value = Keyboard.getEventKeyState();
			Method method;
			try {
				method = bfcc.getClass().getMethod(in.toLowerCase(),
						new Class[] { boolean.class });
				method.invoke(bfcc, value);
			} catch (Exception e) {
				if (debug) {
					s(e);
					s(inp);
				}
			}
		}
	}

	public void s(Object s) {
		System.out.println(s);
	}
}
