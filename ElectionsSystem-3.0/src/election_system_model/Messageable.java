package election_system_model;

public interface Messageable {
	void showMessage(String msg);
	String getString(String msg);
	int getInt(String msg);
	boolean getBoolean(String msg) throws Exception;
}
