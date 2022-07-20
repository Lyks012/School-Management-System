package app.views.assistant;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.entities.Classe;
import app.entities.Enseignant;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ListeEmploiDuTempsEnseignants extends AssistantView {
	private String[] columnName = {"Id", "Nom"};
	private JTable table;
	private JButton voirEmploiDuTempsEnseignant;
	
	public void addListenerVoirEmploiEnseignant(ActionListener actionListener) {
		voirEmploiDuTempsEnseignant.addActionListener(actionListener);
	}
	public int getSelectedEnseignantId() {
		int row = table.getSelectedRow();
		return Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	}
	
	public ListeEmploiDuTempsEnseignants(List<Enseignant> enseignants) {
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Liste des enseignants");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Retour a l'accueil");
		panel_1.add(accueilBtn);
		
		voirEmploiDuTempsEnseignant = new JButton("Voir emploi du temps");
		panel_1.add(voirEmploiDuTempsEnseignant);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnName);
		
		if(enseignants != null)
			for(Enseignant enseignant : enseignants) {
				String[] matiereInfos = {
						String.valueOf(enseignant.getId()),
						enseignant.getLogin(),
				};
				
				model.addRow(matiereInfos);
			}
		
		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
