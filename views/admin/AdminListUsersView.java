package app.views.admin;

import app.entities.User;
import app.views.View;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AdminListUsersView extends AdminView {
	private JTable table;
	private String[] columnNames = { "id", "login", "password", "type" };
	private String[][] usersData;
	private JButton appliquerFilterButton;
	private JButton supprimerButton;

	public void addListenerToModifierButton(ActionListener actionListener) {
		this.modifierButton.addActionListener(actionListener);
	}

	public void addListenerToSupprimerButton(ActionListener actionListener) {
		supprimerButton.addActionListener(actionListener);
	}

	public void addListenerToAppliquerFilterButton(ActionListener actionListener) {
		appliquerFilterButton.addActionListener(actionListener);
	}
	
	public int getIdSelectedItem() {
		int row = table.getSelectedRow();
		return Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	}

	public String getLoginSelectedItem() {
		int row = table.getSelectedRow();
		return table.getModel().getValueAt(row, 1).toString();
	}

	public String getPasswordSelectedItem() {
		int row = table.getSelectedRow();
		return table.getModel().getValueAt(row, 2).toString();
	}

	public String getTypeSelectedItem() {
		int row = table.getSelectedRow();
		return table.getModel().getValueAt(row, 3).toString();
	}

	public AdminListUsersView(List<User> users) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(621, 449);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Filtres");
		panel_5.add(lblNewLabel_2);

		JPanel panel_6 = new JPanel();
		panel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_7.add(panel_8);

		JLabel lblNewLabel_3 = new JLabel("Par type");
		panel_8.add(lblNewLabel_3);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);

		JComboBox comboBox = new JComboBox();
		panel_9.add(comboBox);

		JPanel panel_10 = new JPanel();
		panel.add(panel_10, BorderLayout.SOUTH);

		appliquerFilterButton = new JButton("Appliquer");
		panel_10.add(appliquerFilterButton);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Liste des utilisateurs");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		this.modifierButton = new JButton("Modifier");
		panel_2.add(this.modifierButton);

		supprimerButton = new JButton("Supprimer");
		panel_2.add(supprimerButton);

		this.annulerButton = new JButton("Retour");
		panel_2.add(this.annulerButton);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Nombre d'utilisateurs : 0");
		panel_4.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_3.add(scrollPane, BorderLayout.CENTER);
			
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for (User user : users) {
			
			String[] userInfos = {
					String.valueOf(user.getId()),
					user.getLogin(),
					user.getPassword(),
					String.valueOf(user.getRole())
			};
			model.addRow(userInfos);
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		
	}

}
