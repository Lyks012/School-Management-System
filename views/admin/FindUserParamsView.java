package app.views.admin;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class FindUserParamsView extends AdminView {
	private JTextField idTF;
	private JTextField loginTF;
	private JButton searchByLoginButton;
	private JButton searchByIdButton;
	
	
	public FindUserParamsView() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(356, 226);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Rechercher un utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		annulerButton = new JButton("Annuler");
		panel_1.add(annulerButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblParId = new JLabel("par id ------>");
		panel_5.add(lblParId);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		panel_3.add(panel_6);
		
		idTF = new JTextField();
		panel_6.add(idTF);
		idTF.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		searchByIdButton = new JButton("Rechercher");
		panel_7.add(searchByIdButton);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		JLabel lblNewLabel_1 = new JLabel("par login ------>");
		panel_8.add(lblNewLabel_1);
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		
		loginTF = new JTextField();
		panel_9.add(loginTF);
		loginTF.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_4.add(panel_10);
		
		searchByLoginButton = new JButton("Rechercher");
		panel_10.add(searchByLoginButton);
	}
	
	public String getId() {
		return idTF.getText();
	}
	public String getLogin() {
		return loginTF.getText();
	}
	 
	public void addListenerToSearchByIdButton(ActionListener listener) {
		searchByIdButton.addActionListener(listener);
	}
	public void addListenerToSearchByLoginButton(ActionListener listener) {
		searchByLoginButton.addActionListener(listener);
	}
}
