package app.views.admin;

import app.entities.Roles;
import app.views.View;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;

public class AdminCreateUserView extends AdminView {
	private JPanel second_row_panel;
	private JPanel third_row_panel;
	private JPanel fouth_row_panel;
	private JPanel fifth_row_panel;
	private JPanel sixth_row_panel;
	private JLabel lblNewLabel;
	private JTextField loginTF;
	private JLabel lblNewLabel_1;
	private JTextField passwordTF;
	private JLabel lblNewLabel_2;
	private JTextField repeatPasswordTF;
	private JLabel lblNewLabel_3;
	private JComboBox<Roles> roleCB;
	private JPanel first_row_panel;
	private JLabel lblNewLabel_4;
	private JButton createButton;
	private JPanel loginLabelPanel;
	private JPanel loginInputPanel;
	private JPanel password_label_panel;
	private JPanel password_input_panel;
	private JPanel repeat_password_label_panel;
	private JPanel repeat_password_input_panel;
	private JPanel type_label_panel;
	private JPanel select_type_panel;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_5;
	private JComboBox comboBox;
	
	public AdminCreateUserView() {
		getContentPane().setLayout(new GridLayout(7, 1, 0, 0));
		setSize(403, 247);
		setLocationRelativeTo(null);
		
		first_row_panel = new JPanel();
		getContentPane().add(first_row_panel);
		
		lblNewLabel_4 = new JLabel("Creer un utilisateur");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		first_row_panel.add(lblNewLabel_4);
		
		second_row_panel = new JPanel();
		getContentPane().add(second_row_panel);
		second_row_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		loginLabelPanel = new JPanel();
		FlowLayout fl_loginLabelPanel = (FlowLayout) loginLabelPanel.getLayout();
		fl_loginLabelPanel.setAlignment(FlowLayout.RIGHT);
		second_row_panel.add(loginLabelPanel);
		
		lblNewLabel = new JLabel("Login :");
		loginLabelPanel.add(lblNewLabel);
		
		loginInputPanel = new JPanel();
		FlowLayout fl_loginInputPanel = (FlowLayout) loginInputPanel.getLayout();
		fl_loginInputPanel.setAlignment(FlowLayout.LEFT);
		second_row_panel.add(loginInputPanel);
		
		loginTF = new JTextField();
		loginInputPanel.add(loginTF);
		loginTF.setColumns(10);
		
		third_row_panel = new JPanel();
		getContentPane().add(third_row_panel);
		third_row_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		password_label_panel = new JPanel();
		FlowLayout fl_password_label_panel = (FlowLayout) password_label_panel.getLayout();
		fl_password_label_panel.setAlignment(FlowLayout.RIGHT);
		third_row_panel.add(password_label_panel);
		
		lblNewLabel_1 = new JLabel("Password :");
		password_label_panel.add(lblNewLabel_1);
		
		password_input_panel = new JPanel();
		FlowLayout fl_password_input_panel = (FlowLayout) password_input_panel.getLayout();
		fl_password_input_panel.setAlignment(FlowLayout.LEFT);
		third_row_panel.add(password_input_panel);
		
		passwordTF = new JTextField();
		password_input_panel.add(passwordTF);
		passwordTF.setColumns(10);
		
		fouth_row_panel = new JPanel();
		getContentPane().add(fouth_row_panel);
		fouth_row_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		repeat_password_label_panel = new JPanel();
		FlowLayout fl_repeat_password_label_panel = (FlowLayout) repeat_password_label_panel.getLayout();
		fl_repeat_password_label_panel.setAlignment(FlowLayout.RIGHT);
		fouth_row_panel.add(repeat_password_label_panel);
		
		lblNewLabel_2 = new JLabel("Repeter mot de passe :");
		repeat_password_label_panel.add(lblNewLabel_2);
		
		repeat_password_input_panel = new JPanel();
		FlowLayout fl_repeat_password_input_panel = (FlowLayout) repeat_password_input_panel.getLayout();
		fl_repeat_password_input_panel.setAlignment(FlowLayout.LEFT);
		fouth_row_panel.add(repeat_password_input_panel);
		
		repeatPasswordTF = new JTextField();
		repeat_password_input_panel.add(repeatPasswordTF);
		repeatPasswordTF.setColumns(10);
		
		fifth_row_panel = new JPanel();
		getContentPane().add(fifth_row_panel);
		fifth_row_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		type_label_panel = new JPanel();
		FlowLayout fl_type_label_panel = (FlowLayout) type_label_panel.getLayout();
		fl_type_label_panel.setAlignment(FlowLayout.RIGHT);
		fifth_row_panel.add(type_label_panel);
		
		lblNewLabel_3 = new JLabel("Type :");
		type_label_panel.add(lblNewLabel_3);
		
		select_type_panel = new JPanel();
		FlowLayout fl_select_type_panel = (FlowLayout) select_type_panel.getLayout();
		fl_select_type_panel.setAlignment(FlowLayout.LEFT);
		fifth_row_panel.add(select_type_panel);
		
		roleCB = new JComboBox<Roles>();
		select_type_panel.add(roleCB);
		roleCB.setModel(new DefaultComboBoxModel<Roles>(Roles.values()));
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_1);
		
		lblNewLabel_5 = new JLabel("Classe :");
		panel_1.add(lblNewLabel_5);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		comboBox = new JComboBox();
		panel_2.add(comboBox);
		
		sixth_row_panel = new JPanel();
		getContentPane().add(sixth_row_panel);
		
		annulerButton = new JButton("Annuler");
		annulerButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		sixth_row_panel.add(annulerButton);
		
		createButton = new JButton("Creer");
		createButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		createButton.setForeground(Color.BLACK);
		sixth_row_panel.add(createButton);
	}
	
	public String getLogin() {
		return this.loginTF.getText();
	}
	public String getPassword() {
		return this.passwordTF.getText();
	}
	public String getRepeatPassword() {
		return this.repeatPasswordTF.getText();
	}
	public Roles getRole() {
		return (Roles) this.roleCB.getSelectedItem();
	}

	public void addListenerToCreateUserButton(ActionListener actionListener) {
		createButton.addActionListener(actionListener);
	}
}
