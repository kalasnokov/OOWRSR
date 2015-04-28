package inputhandler;

public class Key {
	private String action;
	private int key;
	private int num = 0;

	public Key(String action, int key) {
		this.setAction(action);
		this.setKey(key);
	}

	public Key(String action, int key, int num) {
		this.setAction(action);
		this.setKey(key);
		this.setNum(num);
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
