package system;

import javax.swing.JOptionPane;

public class GraficalUI implements Messageable {
	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	@Override
	public String getString(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	public boolean getBoolean(String msg) throws Exception {
		String answer=getString(msg);
		try {
			if(answer=="true") {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	public int getInt(String msg) {
		//return Integer.parseInt(getString(msg));
		String answer=getString(msg);
		try {
			int intAnswer=Integer.parseInt(answer);
			return intAnswer;
		}
		catch(Exception error){
			return -1;
		}
	}
	
}
