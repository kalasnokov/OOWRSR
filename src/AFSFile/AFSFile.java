package AFSFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AFSFile {
	File f;
	String recUrl;
	boolean debug;
	boolean broken;
	long length;

	// create file and create path to file
	public AFSFile(String url, String[] strings, boolean debug) {
		this.debug = debug;
		String[] US = url.split("/");
		recUrl = "";
		for (int i = 0; i < US.length; i++) {

			if (i != US.length - 1) {
				recUrl = recUrl + US[i] + "\\";
			} else {
				recUrl += US[i];
			}
			f = new File(recUrl);
			if (!f.exists() && i != US.length - 1 && !f.isDirectory()) {
				f.mkdir();
				if (debug) {
					s("Path \"" + recUrl + "\" created");
				}
			}
			if (!f.exists() && i == US.length - 1) {
				try {
					PrintWriter writer = null;
					f.createNewFile();
					writer = new PrintWriter(recUrl, "UTF-8");
					for (int w = 0; w < strings.length; w++) {
						writer.println(strings[w]);
					}
					writer.close();
					if (debug) {
						s("File \"" + recUrl + "\" created");
					}
				} catch (Exception e) {
					s("ERROR: failed to create path");
					broken = true;
				}
			}
		}
		length = f.length();
		if (debug) {
			System.out.println("File length: " + length);
		}
		
	}

	// function to get true/false info from config lines
	public Boolean ReadBooleanSetting(String setting)
			throws FileNotFoundException, IOException {
		if (!broken) {
			if (debug) {
				s("Read file URL: \"" + recUrl + "\"");
			}
			String out = "";
			String[] set = { "" };
			try (BufferedReader br = new BufferedReader(new FileReader(recUrl))) {
				StringBuilder sb = new StringBuilder();
				while (true) {
					if (set[0].toLowerCase().equals(setting.toLowerCase())) {
						break;
					}
					String line = br.readLine();
					if (line != null) {
						set = line.split(":");
					} else {
						s("No setting with name " + setting + " found");
						break;
					}
				}
				br.close();
				if (set.length < 2) {
					s("Input \"" + setting + "\" was not a setting");
					return false;
				} else {
					sb.append(set[1]);
					out = sb.toString();
					if (debug) {
						s("Setting " + setting + " returning "
								+ Boolean.valueOf(out));
					}
					return Boolean.valueOf(out);
				}
			}
		} else {
			return false;
		}
	}

	public String ReadSetting(String setting) throws FileNotFoundException,
			IOException {
		if (!broken) {
			if (debug) {
				s("Read file URL: \"" + recUrl + "\"");
			}
			String out = "";
			String[] set = { "" };
			try (BufferedReader br = new BufferedReader(new FileReader(recUrl))) {
				StringBuilder sb = new StringBuilder();
				while (true) {
					if (set[0].toLowerCase().equals(setting.toLowerCase())) {
						break;
					}
					String line = br.readLine();
					if (line != null) {
						set = line.split(":");
					} else {
						s("No setting with name " + setting + " found");
						break;
					}
				}
				br.close();
				if (set.length < 2) {
					s("Input \"" + setting + "\" was not a setting");
					return null;
				} else {
					sb.append(set[1]);
					out = sb.toString();
					if (debug) {
						s("Setting " + setting + " returning "
								+ Boolean.valueOf(out));
					}
					return out;
				}
			}
		} else {
			return null;
		}
	}

	// read whole line from file
	public String ReadLine(int row) throws FileNotFoundException, IOException {
		if (!broken) {
			String out;
			String line;
			if (debug) {
				s("Read file URL: \"" + recUrl + "\"");
			}
			try (BufferedReader br = new BufferedReader(new FileReader(recUrl))) {
				StringBuilder sb = new StringBuilder();
				line = br.readLine();
				for (int i = 1; i <= row; i++) {

					if (i == row) {
						line = br.readLine();
					} else {
						br.readLine();
					}
				}
				sb.append(line);
				out = sb.toString();
				br.close();
			}

			if (debug) {
				s(out);
			}
			return out;
		} else {
			return null;
		}
	}

	// rewrite a particular setting to true/false
	public void WriteSetting(String setting, boolean inp)
			throws FileNotFoundException, IOException {
		String[] file = new String[1000];
		if (!broken) {
			if (debug) {
				s("Read file URL: \"" + recUrl + "\"");
			}

			try (BufferedReader br = new BufferedReader(new FileReader(recUrl))) {
				int i = 0;
				while (true) {
					String line = "";
					line = br.readLine();
					if (line != null) {
						file[i] = line;
					} else {
						break;
					}
					i++;
				}
				br.close();
			}
			boolean done = false;
			PrintWriter writer = null;
			writer = new PrintWriter(recUrl, "UTF-8");
			for (int i = 0; i < file.length; i++) {
				if (file[i] != null) {
					String[] split = file[i].split(":");
					if (split[0].toLowerCase().equals(setting.toLowerCase())) {
						writer.println(split[0] + ":" + inp);
						done = true;
						if (debug) {
							s(split[0] + " set to " + inp);
						}
					} else {
						writer.println(file[i]);
					}
				}
			}
			if (!done) {
				s("Could not find setting " + setting);
			}
			writer.close();
		}
	}

	public float getLength() {
		return length;
	}

	public void s(Object s) {
		System.out.println(s);
	}
}
