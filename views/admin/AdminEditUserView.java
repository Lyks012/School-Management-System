package app.views.admin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.entities.Roles;

public class AdminEditUserView extends AdminView {
	private JTextField loginTF;
	private JTextField passwordTF;
	private JTextField repeatPasswordTF;
	private JComboBox<Roles> roleCB;
	private JButton editButton;
	private JComboBox comboBox;
	private int userId;

	public AdminEditUserView(int id, String login, String password, Roles role) {
		this.userId = id;
		getContentPane().setLayout(new GridLayout(7, 1, 0, 0));
		setSize(403, 247);
		setLocationRelativeTo(null);

		JPanel first_row_panel = new JPanel();
		getContentPane().add(first_row_panel);

		JLabel lblNewLabel_4 = new JLabel("Modifier un utilisateur");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		first_row_panel.add(lblNewLabel_4);

		JPanel second_row_panel = new JPanel();
		getContentPane().add(second_row_panel);
		second_row_panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel loginLabelPanel = new JPanel();
		FlowLayout fl_loginLabelPanel = (FlowLayout) loginLabelPanel.getLayout();
		fl_loginLabelPanel.setAlignment(FlowLayout.RIGHT);
		second_row_panel.add(loginLabelPanel);

		JLabel lblNewLabel = new JLabel("Login :");
		loginLabelPanel.add(lblNewLabel);

		JPanel loginInputPanel = new JPanel();
		FlowLayout fl_loginInputPanel = (FlowLayout) loginInputPanel.getLayout();
		fl_loginInputPanel.setAlignment(FlowLayout.LEFT);
		second_row_panel.add(loginInputPanel);

		loginTF = new JTextField();
		loginTF.setText(login);
		loginInputPanel.add(loginTF);
		loginTF.setColumns(10);

		JPanel third_row_panel = new JPanel();
		getContentPane().add(third_row_panel);
		third_row_panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel password_label_panel = new JPanel();
		FlowLayout fl_password_label_panel = (FlowLayout) password_label_panel.getLayout();
		fl_password_label_panel.setAlignment(FlowLayout.RIGHT);
		third_row_panel.add(password_label_panel);

		JLabel lblNewLabel_1 = new JLabel("Password :");
		password_label_panel.add(lblNewLabel_1);

		JPanel password_input_panel = new JPanel();
		FlowLayout fl_password_input_panel = (FlowLayout) password_input_panel.getLayout();
		fl_password_input_panel.setAlignment(FlowLayout.LEFT);
		third_row_panel.add(password_input_panel);

		passwordTF = new JTextField();
		passwordTF.setText(password);
		password_input_panel.add(passwordTF);
		passwordTF.setColumns(10);

		JPanel fouth_row_panel = new JPanel();
		getContentPane().add(fouth_row_panel);
		fouth_row_panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel repeat_password_label_panel = new JPanel();
		FlowLayout fl_repeat_password_label_panel = (FlowLayout) repeat_password_label_panel.getLayout();
		fl_repeat_password_label_panel.setAlignment(FlowLayout.RIGHT);
		fouth_row_panel.add(repeat_password_label_panel);

		JLabel lblNewLabel_2 = new JLabel("Repeter mot de passe :");
		repeat_password_label_panel.add(lblNewLabel_2);

		JPanel repeat_password_input_panel = new JPanel();
		FlowLayout fl_repeat_password_input_panel = (FlowLayout) repeat_password_input_panel.getLayout();
		fl_repeat_password_input_panel.setAlignment(FlowLayout.LEFT);
		fouth_row_panel.add(repeat_password_input_panel);

		repeatPasswordTF = new JTextField();
		repeat_password_input_panel.add(repeatPasswordTF);
		repeatPasswordTF.setColumns(10);

		JPanel fifth_row_panel = new JPanel();
		getContentPane().add(fifth_row_panel);
		fifth_row_panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel type_label_panel = new JPanel();
		FlowLayout fl_type_label_panel = (FlowLayout) type_label_panel.getLayout();
		fl_type_label_panel.setAlignment(FlowLayout.RIGHT);
		fifth_row_panel.add(type_label_panel);

		JLabel lblNewLabel_3 = new JLabel("Type :");
		type_label_panel.add(lblNewLabel_3);

		JPanel select_type_panel = new JPanel();
		FlowLayout fl_select_type_panel = (FlowLayout) select_type_panel.getLayout();
		fl_select_type_panel.setAlignment(FlowLayout.LEFT);
		fifth_row_panel.add(select_type_panel);

		roleCB = new JComboBox<Roles>();
		select_type_panel.add(roleCB);
		roleCB.setModel(new DefaultComboBoxModel<Roles>(Roles.values()));
		roleCB.setSelectedItem(role);

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		if (role.equals(Roles.chef_de_classe) || role.equals(Roles.enseignant)) {
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
			flowLayout_1.setAlignment(FlowLayout.RIGHT);
			panel.add(panel_1);
			JLabel lblNewLabel_5 = new JLabel("Classe :");
			panel_1.add(lblNewLabel_5);

			JPanel panel_2 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel.add(panel_2);

			comboBox = new JComboBox();

			panel_2.add(comboBox);
		}

		JPanel sixth_row_panel = new JPanel();
		getContentPane().add(sixth_row_panel);

		annulerButton = new JButton("Annuler");
		annulerButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		sixth_row_panel.add(annulerButton);

		editButton = new JButton("Modifier");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		editButton.setForeground(Color.BLACK);
		sixth_row_panel.add(editButton);
	}

	public int getUserId() {
		return this.userId;
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

	public void addListenerToEditUserButton(ActionListener actionListener) {
		editButton.addActionListener(actionListener);
	}
}
