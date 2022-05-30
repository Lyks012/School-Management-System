 package app.views.assistant;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.entities.Classe;
import app.entities.Matiere;

public class ClassesList extends AssistantView {
	private JTable table;
	private String[] columnName = {"Id", "Nom", "Chef De Classe", "Enseignant"};
	
	private JButton createClass;
	private JButton seeClasseDetails;
	private JButton supprimerClasse;
	
	public void addListenerToCreateClasse(ActionListener actionListener) {
		this.createClass.addActionListener(actionListener);
	}
	public void addListenerToSeeClassseDetailBtn(ActionListener actionListener) {
		this.seeClasseDetails.addActionListener(actionListener);
	}
	
	public ClassesList(List<Classe> classes) {
		
		setSize(450, 400);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Classes");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Accueil");
		panel_1.add(accueilBtn);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		createClass = new JButton("Creer classe");
		panel_3.add(createClass);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		seeClasseDetails = new JButton("Voir details ");
		panel_4.add(seeClasseDetails);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		supprimerClasse = new JButton("New button");
		panel_5.add(supprimerClasse);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnName);
		if(classes != null)
			for(Classe classe : classes) {
				String[] matiereInfos = {
						String.valueOf(classe.getId()),
						classe.getNom(),
						classe.getChefDeClasse().getLogin(),
						classe.getEnseignant().getLogin(),
				};
				
				model.addRow(matiereInfos);
			}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
