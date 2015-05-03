package Objects;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import Engine.Game;
import UI.Sprite;

public class BObject {
	protected int x;
	protected int y;
	protected Sprite sprite;
	TrueTypeFont font;
	String text = "";
	boolean set;
	double fontX;
	double fontY;
	int fontsize = 16;

	public BObject(int x, int y, String url) {
		setFont("Verdana");
		sprite = new Sprite(url);
		this.x = x;
		this.y = y;

	}

	public void render(Game game) {
		fontX = (x + (sprite.width / 2) - (font.getWidth(text) / 2));
		fontY = (y + (sprite.height / 2) - (font.getHeight(text) / 2));
		sprite.render(x, y);
		font.drawString((int) fontX, (int) fontY, text, Color.black);
	}

	public void spriteupdate() {

	}

	public void printData() {
		s("X: " + x);
		s("Y: " + y);
	}

	public void setFont(String Sfont) {
		Font awtFont = new Font(Sfont, Font.CENTER_BASELINE, fontsize);
		font = new TrueTypeFont(awtFont, false);
	}

	public void setText(String text) {
		this.text = text.toUpperCase();
		while (true) {
			if (font.getWidth(this.text) > sprite.getWidth()) {
				fontsize--;
				setFont("Verdana");

			} else {
				break;
			}
		}
	}

	public void s(Object s) {
		System.out.println(s);
	}

	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
