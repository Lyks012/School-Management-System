package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.entities.Classe;
import app.entities.CoursProgramme;

public class EmploiDuTempsClasse extends AssistantView {
	private JTable table;
	
	JButton goToAddCours;
	JButton goToDetailsBtn;
	JButton deleteCoursBtn;
	private String[] jours = {"Horaire", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
	private String[] horaires = {"08 - 10", "10 - 12", "15 - 17"};
	private int semaine = 1;
	private int classeId;
	private List<CoursProgramme> emploi;
	
	public void addListenerToAddCoursBtn(ActionListener actionListener) {
		goToAddCours.addActionListener(actionListener);
	}
	public void addListenerToDetailsCoursBtn(ActionListener actionListener) {
		goToDetailsBtn.addActionListener(actionListener);
	}
	public void addListenerToDeleteCoursBtn(ActionListener actionListener) {
		deleteCoursBtn.addActionListener(actionListener);
	}
	
	public String getSelectedJour() {
		if(table.getSelectedColumn() == -1) return null;
		return jours[table.getSelectedColumn()];
	}
	public String getSelectedHoraire() {
		if(table.getSelectedRow() == -1) return null;
		
		return horaires[table.getSelectedRow()];
	}
	
	public CoursProgramme getSelectedCours() {
		return getCoursAtHoraire(getSelectedHoraire(), getSelectedJour());
	}
	public int getSemaine() {
		return this.semaine;
	}
	public int getClasseId() {
		return this.classeId;
	}
	
	
	public EmploiDuTempsClasse(List<CoursProgramme> emploiDuTemps, int classeId, String classeName) {
		emploi = emploiDuTemps;
		this.classeId = classeId;
		setSize(512, 299);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Retour a l'accueil");
		panel.add(accueilBtn);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setVgap(30);
		panel_2.add(panel_5);
		
		goToAddCours = new JButton("Ajouter un cours");
		panel_5.add(goToAddCours);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setVgap(20);
		panel_2.add(panel_6);
		
		goToDetailsBtn = new JButton("Details");
		panel_6.add(goToDetailsBtn);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		deleteCoursBtn = new JButton("Supprimer");
		panel_7.add(deleteCoursBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
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
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		
		JLabel nomClasse = new JLabel("Emploi du temps <dynamic>");
		panel_3.add(nomClasse);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		JButton previousWeekBtn = new JButton("Precedent");
		panel_10.add(previousWeekBtn);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11);
		
		JLabel semaineLabel = new JLabel("Semaine " + this.semaine);
		panel_11.add(semaineLabel);
		
		JPanel panel_12 = new JPanel();
		panel_8.add(panel_12);
		
		JButton nextWeekBtn = new JButton("Suivant");
		panel_12.add(nextWeekBtn);
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
		
		String coursName = "TPE";
		if(cours != null)
				coursName = cours.getMatiere().getNom();
		
		return coursName;
	}
}
