package app.views.assistant;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

import app.entities.Matiere;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

public class ProgrammerCours extends AssistantView {
	
	private JButton creerCours;
	private int classeId;
	private String horaire, jour;
	private int semaine;
	private JComboBox matiereCB;
	
	public String getHoraire() {
		return this.horaire;
	}
	
	public String getJour() {
		return this.jour;
	}
	
	public int getSemaine() {
		return this.semaine;
	}
	
	
	
	public int getIdModuleSelected() {
		String selectedMatiere = (String) matiereCB.getSelectedItem();
			
		return Integer.parseInt(
				selectedMatiere.split("-")[0]
				);
	}
	
	public void addListenerCreerCours(ActionListener actionListener) {
		creerCours.addActionListener(actionListener);
	}
	
	public int getClasseId() {
		return this.classeId;
	}

	public ProgrammerCours(String horaire, String jour, int semaine, List<Matiere> matieres, int classeId) {
		this.classeId = classeId;
		this.horaire = horaire;
		this.semaine = semaine;
		this.jour = jour;
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Programmer un cours");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Retour");
		panel_1.add(accueilBtn);
		
		creerCours = new JButton("Creer cours");
		panel_1.add(creerCours);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_13.getLayout();
		flowLayout_6.setVgap(20);
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_13);
		
		JLabel lblNewLabel_4 = new JLabel("Horaire :");
		panel_13.add(lblNewLabel_4);
		
		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_14.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		flowLayout_7.setVgap(20);
		panel_4.add(panel_14);
		
		JLabel horaireLabel = new JLabel(horaire);
		panel_14.add(horaireLabel);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_5.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Jour :");
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(20);
		panel_5.add(panel_7);
		
		JLabel jourLabel = new JLabel(jour);
		panel_7.add(jourLabel);
		
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_12.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Semaine :");
		panel_8.add(lblNewLabel_2);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_9.getLayout();
		flowLayout_4.setVgap(20);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_12.add(panel_9);
		
		JLabel semaineLabel = new JLabel(String.valueOf(semaine));
		panel_9.add(semaineLabel);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_10.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		flowLayout_2.setVgap(20);
		panel_3.add(panel_10);
		
		JLabel lblNewLabel_3 = new JLabel("Matiere :");
		panel_10.add(lblNewLabel_3);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_11.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		flowLayout_5.setVgap(20);
		panel_3.add(panel_11);
		
		matiereCB = new JComboBox();
		DefaultComboBoxModel<String> matieresCBModel = new DefaultComboBoxModel<>();
		for(Matiere matiere : matieres) {
			matieresCBModel.addElement(matiere.getId() + "-" + matiere.getNom());
		}
		matiereCB.setModel(matieresCBModel);
		panel_11.add(matiereCB);
	}

}
