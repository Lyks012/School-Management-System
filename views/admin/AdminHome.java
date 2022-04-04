package app.views.admin;

import app.views.View;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;

public class AdminHome extends View {
	private JButton createUserButton;
	private JButton findUserButton;
	private JButton listUsersButton;
	
	public AdminHome() {
		getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		setSize(434, 168);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		createUserButton = new JButton("Creer un utilisateur");
		panel.add(createUserButton);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		findUserButton = new JButton("Rechercher un utilisateur");
		panel_1.add(findUserButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		listUsersButton = new JButton("Lister les utilisateurs");
		panel_2.add(listUsersButton);
	}
	
	public void addListenerToCreateUserButton(ActionListener actionListener){
		createUserButton.addActionListener(actionListener);
	}
	public void addListenerToFindUserButton(ActionListener actionListener){
		findUserButton.addActionListener(actionListener);
	}
	public void addListenerToListUsersButton(ActionListener actionListener){
		listUsersButton.addActionListener(actionListener);
	}
	public void addListeners() {
		
	}
}
