package inputhandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import AFSFile.AFSFile;
import AFSFile.Presets;
import Engine.Game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Keybinder {
	AFSFile f;
	String WL;// walk left
	String WR;// walk right
	String J;// jump
	String C;// crouch
	String CH;// chat
	Boolean debug;
	String[] coms = new String[1000];
	BFCC bfcc;

	public Keybinder(Boolean debug, Game game) throws FileNotFoundException,
			IOException {
		bfcc = new BFCC(game);
		this.debug = debug;
		f = new AFSFile("res/options/bindings.cfg", new Presets().bindings(),
				debug);
		int i = 1;
		while (true) {
			if (!f.ReadLine(i).isEmpty()) {
				coms[i - 1] = f.ReadLine(i);
				if (debug) {
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
			String[] com = new String[2];
			String inp = Keyboard.getKeyName(Keyboard.getEventKey())
					.toLowerCase();
			String fin = null;
			for (int i = 0; i < coms.length; i++) {
				if (!(coms[i] == null)) {
					try {
						com = coms[i].split(":");
						if (inp.equals(com[1].toLowerCase())) {
							fin = com[0].toLowerCase();
							if (fin.toLowerCase().equals("bfcc")) {
								fin = "";
								if (debug) {
									System.out.println("Illegal operation");
								}
							}
						}
					} catch (Exception e) {
						if (debug) {
							System.out.println("Failed to split line "
									+ (i + 1));
						}
					}

				} else {
					break;
				}
			}

			Method method;
			try {
				method = bfcc.getClass().getMethod(fin.toLowerCase());
				method.invoke(bfcc);
			} catch (Exception e) {
				if (debug) {
					System.out.println(e);
					System.out.println(inp);
				}
			}
		}
	}

	public void error() {
		System.out.println("this is bad");
	}
}
