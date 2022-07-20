package app.views.assistant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.entities.CoursProgramme;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmploiDuTempsEnseignant extends AssistantView {
	private String[] jours = {"Horaire", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
	private String[] horaires = {"08 - 10", "10 - 12", "15 - 17"};
	private int semaine = 1;
	
	private List<CoursProgramme> emploi;
	private JTable table;
	private JButton btnNewButton;
	
	public void addListenerRetourBtn(ActionListener actionListener) {
		btnNewButton.addActionListener(actionListener);
	}
	
	public EmploiDuTempsEnseignant(List<CoursProgramme> emploi, String enseignantNom)
	{
		this.emploi = emploi;
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		btnNewButton = new JButton("Accueil");
		panel.add(btnNewButton);
	
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JLabel nomEnseignant = new JLabel("Emploi du temps " + enseignantNom);
		panel_4.add(nomEnseignant);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(jours);
		
		for(String horaire : horaires) {
			String[] infos = {
				horaire,
				getCoursNameAtHoraire(horaire, "lundi"),
				getCoursNameAtHoraire(horaire, "mardi"),
				getCoursNameAtHoraire(horaire, "mercredi"),
				getCoursNameAtHoraire(horaire, "jeudi"),
				getCoursNameAtHoraire(horaire, "vendredi"),
				getCoursNameAtHoraire(horaire, "samedi")
			};
			
			model.addRow(infos);
		}
		
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
	
	public CoursProgramme getCoursAtHoraire(String horaire, String jour){
		CoursProgramme coursFound = null;
		for(CoursProgramme cours : emploi) {
			if(cours.getJour().equalsIgnoreCase(jour) && cours.getHoraire().equals(horaire) && cours.getSemaine() == this.semaine) {
				coursFound = cours;
				break;
			}
		}
		return coursFound;
	}
	
	public String getCoursNameAtHoraire(String horaire, String jour) {
		CoursProgramme cours = getCoursAtHoraire(horaire, jour);
		
		String coursName = "Pas de cours";
		if(cours != null)
				coursName = cours.getClasse().getNom();
		
		return coursName;
	}
}
