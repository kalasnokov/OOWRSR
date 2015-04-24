package inputhandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import AFSFile.AFSFile;
import AFSFile.Presets;
import Engine.Game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Keybinder extends Game{
	AFSFile f;
	String WL;// walk left
	String WR;// walk right
	String J;// jump
	String C;// crouch
	String CH;// chat

	public Keybinder(Boolean debug) throws FileNotFoundException,
			IOException {
		f = new AFSFile("res/options/bindings.cfg", new Presets().bindings(),
				debug);
		WL = f.ReadSetting("wleft").toLowerCase();
		WR = f.ReadSetting("wright").toLowerCase();
		J = f.ReadSetting("jump").toLowerCase();
		C = f.ReadSetting("Crouch").toLowerCase();
		CH = f.ReadSetting("chat").toLowerCase();
	}

	public void readinput(Game game) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		while (Keyboard.next()) {
			String inp = Keyboard.getKeyName(Keyboard.getEventKey())
					.toLowerCase();
			Method method;
			try {
				method = game.getClass().getMethod(inp);
				method.invoke(this);
			} catch (Exception e) {
				System.out.println(e);
			}
			if (inp.equals(WL)) {
				System.out.println("Walk left");
			}
			if (inp.equals(WR)) {
				System.out.println("Walk right");
			}
			if (inp.equals(J)) {
				System.out.println("Jump");
			}
			if (inp.equals(C)) {
				System.out.println("Crouch");
			}
			if (inp.equals(CH)) {
				System.out.println("Chat");
			}
		}
	}
}
