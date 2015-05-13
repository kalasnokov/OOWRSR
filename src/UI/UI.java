package UI;

import java.util.Vector;

import AFSFile.AFSFile;
import AFSFile.Preset;
import Engine.Game;
import Objects.BObject;
import UI.UIObjects.Bar;
import UI.UIObjects.Button;

public class UI {
	boolean active;
	String url;
	Game game;
	Vector<BObject> items = new Vector<BObject>();

	public UI(String url, Game game) throws Exception {
		this.url = url;
		this.game = game;
		active = true;
		readURL();
	}

	public void render() {
		for (BObject b : items) {
			{
				b.render(game);
			}
		}
	}

	public void update() {
		for (BObject b : items) {
			{
				if (b.getClass().getSimpleName().equals("Button")) {
					((Button) b).update();
				}
			}
		}
	}

	public void readURL() throws Exception {
		AFSFile f = new AFSFile(url, new Preset().creatorUI(), true);
		Boolean primed = false;
		Boolean anchorLeft = false;
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		String URL = null;
		String action = null;
		for (int i = 0; true; i++) {
			String line = f.ReadLine(i);
			if (!line.equals("null")) {
				if (line.equals("{")) {
					primed = true;
				}
				if (line.equals("}")) {
					primed = false;
				}
				if (line.contains(":") && primed) {
					line = line.replaceAll("\\s+", "");
					line = line.toLowerCase();
					String[] part = line.split(":");
					for (int p = 0; p < part.length; p++) {
						if (part[p].contains("anchor=\"")) {
							String[] tmp = part[p].split("\"");
							anchorLeft = (tmp[1].equals("left"));
						}
						if (part[p].contains("x=\"")) {
							String[] tmp = part[p].split("\"");
							if (tmp[1].equals("right")) {
								x = game.getWidth();
							} else {
								x = Integer.parseInt(tmp[1]);
							}
						}
						if (part[p].contains("y=\"")) {
							String[] tmp = part[p].split("\"");
							if (tmp[1].equals("bottom")) {
								y = game.getHeight();
							} else {
								y = Integer.parseInt(tmp[1]);
							}
						}
						if (part[p].contains("width=\"")) {
							String[] tmp = part[p].split("\"");
							if (tmp[1].equals("stretch")) {
								tmp[1] = "0";
							} else {
								width = Integer.parseInt(tmp[1]);
							}
						}
						if (part[p].contains("height=\"")) {
							String[] tmp = part[p].split("\"");
							if (tmp[1].equals("stretch")) {
								tmp[1] = "0";
							}
							height = Integer.parseInt(tmp[1]);
						}
						if (part[p].contains("url=\"")) {
							String[] tmp = part[p].split("\"");
							URL = tmp[1];
						}
						if (part[p].contains("action=\"")) {
							String[] tmp = part[p].split("\"");
							action = tmp[1];
						}
					}
					System.out.println(x + " : " + y + " : " + width + " : "
							+ height + " : " + URL + " : " + action);

					if (part[0].toLowerCase().equals("button")) {
						items.add(new Button(x, y, width, height, URL, action,
								anchorLeft, game));
					}
					if (part[0].toLowerCase().equals("bar")) {
						items.add(new Bar(x, y, width, height, URL, anchorLeft,
								game));
					}
				}

			} else {
				break;
			}
		}
	}

	public boolean isActive() {
		return active;
	}
}
