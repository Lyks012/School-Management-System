package app.views.admin;

import app.entities.Roles;
import java.awt.event.ActionListener;
import app.entities.User;
import app.views.View;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class UserFoundView extends AdminView {
	private JButton modifierButton;
	private JButton supprimerButton;
	private JLabel idLabel;
	
	public UserFoundView(User user) {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		setSize(356, 226);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Resultat Recherche");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		modifierButton = new JButton("Modifier");
		panel_1.add(modifierButton);
		
		supprimerButton = new JButton("Supprimer");
		panel_1.add(supprimerButton);
		
		annulerButton = new JButton("Accueil");
		panel_1.add(annulerButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);
		
		JLabel lblNewLabel_1 = new JLabel("Id :");
		panel_7.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_8);
		
		idLabel = new JLabel(String.valueOf(user.getId()));
		panel_8.add(idLabel);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_9.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_5.add(panel_9);
		
		JLabel lblNewLabel_2 = new JLabel("Login :");
		panel_9.add(lblNewLabel_2);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_10.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_10);
		
		JLabel loginLabel = new JLabel(user.getLogin());
		panel_10.add(loginLabel);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_11.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_6.add(panel_11);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		panel_11.add(lblNewLabel_3);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_12.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_12);
		
		JLabel passwordLabel = new JLabel(user.getPassword());
		panel_12.add(passwordLabel);
		
		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_16 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_16.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_15.add(panel_16);
		
		JLabel lblNewLabel_4 = new JLabel("Type :");
		panel_16.add(lblNewLabel_4);
		
		JPanel panel_17 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_17.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_15.add(panel_17);
		
		JLabel typeLabel = new JLabel(user.getRole().toString());
		panel_17.add(typeLabel);
		
		
		if(user.getRole() == Roles.enseignant || user.getRole() == Roles.chef_de_classe) {
			JPanel panel_3 = new JPanel();
			panel_2.add(panel_3);
			panel_3.setLayout(new GridLayout(0, 2, 0, 0));
			
			JPanel panel_13 = new JPanel();
			FlowLayout flowLayout_4 = (FlowLayout) panel_13.getLayout();
			flowLayout_4.setAlignment(FlowLayout.RIGHT);
			panel_3.add(panel_13);
			
			JLabel lblNewLabel_5 = new JLabel("Classe : ");
			panel_13.add(lblNewLabel_5);
			
			JPanel panel_14 = new JPanel();
			FlowLayout flowLayout_9 = (FlowLayout) panel_14.getLayout();
			flowLayout_9.setAlignment(FlowLayout.LEFT);
			panel_3.add(panel_14);

			JLabel classeLabel = new JLabel("");
			panel_14.add(classeLabel);
		}
	}
	
	
	public void addListenerToModifierButton(ActionListener actionListener) {
		modifierButton.addActionListener(actionListener);
	}
	public void addListenerToSupprimerButton(ActionListener actionListener) {
		supprimerButton.addActionListener(actionListener);
	}
	public int getId() {
		return Integer.parseInt(idLabel.getText());
	}
	
}
