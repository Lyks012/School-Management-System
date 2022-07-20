package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import app.entities.Assistant_De_Programme;

import java.awt.FlowLayout;
import java.awt.Font;

public class PersoInfos extends AssistantView {
	private JTextField usernameTF;
	private JTextField passwordTF;
	
	private JButton modifierBtn;

	public void addListenerModifierBtn(ActionListener actionListener) {
		modifierBtn.addActionListener(actionListener);
	}
	
	public String getNewUsername() {
		return usernameTF.getText();
	}
	public String getNewPassword() {
		return passwordTF.getText();
	}
	
	public PersoInfos(Assistant_De_Programme assistant) {
		setSize(450, 200);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Informations personnelles");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		modifierBtn = new JButton("Modifer");
		panel_1.add(modifierBtn);
		
		accueilBtn = new JButton("Accueil");
		panel_1.add(accueilBtn);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		panel_5.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_6);
		
		usernameTF = new JTextField(assistant.getLogin());
		panel_6.add(usernameTF);
		usernameTF.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setVgap(20);
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		panel_7.add(lblNewLabel_2);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setVgap(20);
		panel_3.add(panel_8);
		
		passwordTF = new JTextField(assistant.getPassword());
		panel_8.add(passwordTF);
		passwordTF.setColumns(10);
	}

}
