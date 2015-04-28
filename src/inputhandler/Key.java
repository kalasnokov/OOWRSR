package inputhandler;

public class Key {
	private String action;
	private int key;

	public Key(String action, int key) {
		this.setAction(action);
		this.setKey(key);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
