 package app.views.assistant;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.entities.Classe;

public class ListeEmploiDuTempsClasses extends AssistantView {
	private String[] columnName = {"Id", "Nom", "Chef De Classe"};
	private JTable table;
	private JButton voirEmploiDuTempsClasse;
	
	public void addListenerVoirEmploiClasse(ActionListener actionListener) {
		voirEmploiDuTempsClasse.addActionListener(actionListener);
	}
	public int getSelectedClasseId() {
		int row = table.getSelectedRow();
		return Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
	}
	public ListeEmploiDuTempsClasses(List<Classe> classes) {
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Liste des classes");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		 accueilBtn = new JButton("Retour a l'accueil");
		panel_1.add(accueilBtn);
		
		voirEmploiDuTempsClasse = new JButton("Voir Emploi du temps");
		panel_1.add(voirEmploiDuTempsClasse);
		
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
				};
				
				model.addRow(matiereInfos);
			}
		
		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
