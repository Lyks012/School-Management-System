package app.views;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import app.entities.Roles;
import java.awt.Font;

public class HomeView extends View {
	private JTextField loginTextField;
	private JButton seConnecter;
	private JComboBox<Roles> roleComboBox;
	private JPasswordField passwordField;

	public HomeView() {
		super();
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(500, 200);
		setLocationRelativeTo(null);

		JPanel NorthPanel = new JPanel();
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		NorthPanel.add(lblNewLabel);

		JPanel SouthPanel = new JPanel();
		getContentPane().add(SouthPanel, BorderLayout.SOUTH);

		seConnecter = new JButton("Se connecter");
		SouthPanel.add(seConnecter);

		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		CenterPanel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5);
		
				JLabel loginLabel = new JLabel("Login :");
				panel_5.add(loginLabel);
				
				JPanel panel_6 = new JPanel();
				FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
				flowLayout_1.setAlignment(FlowLayout.LEFT);
				panel_4.add(panel_6);
				
						loginTextField = new JTextField();
						panel_6.add(loginTextField);
						loginTextField.setColumns(10);

		JPanel panel = new JPanel();
		CenterPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_3.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_3);
		
		JLabel lblPassword = new JLabel("Password :");
		panel_3.add(lblPassword);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_2.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		passwordField = new JPasswordField();
		panel_2.add(passwordField);
		passwordField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		CenterPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_7);
		
		JLabel roleLabel = new JLabel("Vous etes : ");
		panel_7.add(roleLabel);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_8);
		
		roleComboBox = new JComboBox<Roles>();
		panel_8.add(roleComboBox);
		roleComboBox.setModel(new DefaultComboBoxModel<Roles>(Roles.values()));
	}

	public void addEcouteur(ActionListener actionListener) {
		System.out.println(actionListener);
		System.out.println(seConnecter);
		seConnecter.addActionListener(actionListener);
		
	}

	public String getLogin() {
		return loginTextField.getText();
	}
	public String getPassword() {
		return passwordField.getText();
	}
	
	public Roles getRole() {
		return (Roles) roleComboBox.getSelectedItem();
	}

}
