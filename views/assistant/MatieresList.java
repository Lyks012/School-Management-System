package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.List;

import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.entities.Matiere;

public class MatieresList extends AssistantView {
	private JTable table;
	private String[] columnName = {"Id", "Nom", "Enseignant", "Etat Module", "Etat paiement"};
	
	private JButton createModule;
	private JButton supprimerModule;
	private JButton detailsModule;
	
	public void addListenerToCreateModuleBtn(ActionListener actionListener) {
		this.createModule.addActionListener(actionListener);
	}
	public void addListenerToSupprimerBtn(ActionListener actionListener) {
		this.supprimerModule.addActionListener(actionListener);
	}
	
	public void addListenerToDetailsBtn(ActionListener actionListener) {
		this.detailsModule.addActionListener(actionListener);
	}
	
	public int getIdSelectedModule() {
		int row = table.getSelectedRow();
		if(row >= 0) return Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		return -1;
	}
	
	public MatieresList(List<Matiere> matieres) {
		
		setSize(450, 400);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Modules");
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
		
		createModule = new JButton("Creer module");
		panel_3.add(createModule);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		detailsModule = new JButton("Voir Details");
		panel_4.add(detailsModule);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		supprimerModule = new JButton("Supprimer");
		panel_5.add(supprimerModule);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnName);
		if(matieres != null)
			for(Matiere matiere : matieres) {
				String[] matiereInfos = {
						String.valueOf(matiere.getId()),
						matiere.getNom(),
						matiere.getEnseignant().getLogin(),
						matiere.getEtatModule().getStatut(),
						matiere.getEtatPaiement().getStatut()
				};
				
				model.addRow(matiereInfos);
			}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
