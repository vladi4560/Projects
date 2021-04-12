package system;

public interface Messageable {
	void showMessage(String msg);
	String getString(String msg);
	int getInt(String msg);
	boolean getBoolean(String msg) throws Exception;
}
