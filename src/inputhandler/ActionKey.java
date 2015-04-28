package inputhandler;

public class ActionKey extends Key {
	int num;

	public ActionKey(String action, int key, int num) {
		super(action, key);
		this.num = num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}
}
