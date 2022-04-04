package app.views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class View extends JFrame {
	public View() {		
		super("Sunu School Management");
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean displayConfirmationMessage(String message, String title) {
		int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		return result == JOptionPane.YES_OPTION ? true : false;
	}
	
	public void displayWarningMessage(String message) {
		JOptionPane.showMessageDialog(this, message,
                "WARNING", JOptionPane.WARNING_MESSAGE);
	}
	
	public void displaySuccessMessage(String message) {
		JOptionPane.showMessageDialog(this, message,"Success", JOptionPane.INFORMATION_MESSAGE);
	}
}
