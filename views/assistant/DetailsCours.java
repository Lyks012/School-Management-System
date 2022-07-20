package app.views.assistant;
import javax.swing.JPanel;

import app.entities.CoursProgramme;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class DetailsCours extends AssistantView {
	private int classeId;
	
	public int getClasseId() {
		return this.classeId;
	}
	public DetailsCours(CoursProgramme cours, int classeId) {
		this.classeId = classeId;
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Retour");
		panel.add(accueilBtn);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Cours de ? du ? semaine ?");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setVgap(25);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Horaire :");
		panel_6.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setVgap(25);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_7);
		
		JLabel horaireLabel = new JLabel(cours.getHoraire());
		panel_7.add(horaireLabel);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(25);
		panel_5.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Contenu : ");
		panel_8.add(lblNewLabel_2);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_9.getLayout();
		flowLayout_4.setVgap(25);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_9);
		
		JLabel contenuLabel = new JLabel(cours.getContenu());
		panel_9.add(contenuLabel);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
		flowLayout.setVgap(25);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_10);
		
		JLabel lblNewLabel_3 = new JLabel("Valide par enseignant :");
		panel_10.add(lblNewLabel_3);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_11.getLayout();
		flowLayout_5.setVgap(25);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_11);
		
		String valideParEnseignant = "Non";
		
		if(cours.getValideParEnseignat())
			valideParEnseignant = "Oui";
		
		JLabel valideParEnseignantLabel = new JLabel(valideParEnseignant);
		panel_11.add(valideParEnseignantLabel);
		
	}

}
