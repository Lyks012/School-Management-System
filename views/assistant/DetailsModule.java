package app.views.assistant;
import javax.swing.JPanel;

import app.entities.Matiere;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class DetailsModule extends AssistantView {
	int enseignantId;
	private Matiere matiere;
	private JButton cloturerModule;
	private JButton declencherEtatPaiement;
	
	public Matiere getMatiere() {
		return this.matiere;
	}
	
	public void addListenerCloturerModule(ActionListener actionListener) {
		cloturerModule.addActionListener(actionListener);
	}
	public void addListenerDeclencherEtatPaiement(ActionListener actionListener) {
		declencherEtatPaiement.addActionListener(actionListener);
	}
	
	public DetailsModule(Matiere matiere) {
		enseignantId = matiere.getEnseignant().getId();
		this.matiere = matiere;
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Details " + matiere.getNom());
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Retour");
		panel_1.add(accueilBtn);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(5, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);
		
		JLabel lblNewLabel_1 = new JLabel("Enseignant :");
		panel_7.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setVgap(15);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_8);
		
		JLabel lblNewLabel_4 = new JLabel(matiere.getEnseignant().getLogin());
		panel_8.add(lblNewLabel_4);
		
		JPanel panel_4_1 = new JPanel();
		panel_2.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7_1.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4_1.add(panel_7_1);
		
		JLabel lblNewLabel_2 = new JLabel("Statut :");
		panel_7_1.add(lblNewLabel_2);
		
		JPanel panel_8_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_8_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		flowLayout_5.setVgap(15);
		panel_4_1.add(panel_8_1);
		System.out.println("2134241241234" + matiere.getEtatModule().getStatut());
		JLabel lblNewLabel_5 = new JLabel(matiere.getEtatModule().getStatut());
		panel_8_1.add(lblNewLabel_5);
		
		JPanel panel_4_2 = new JPanel();
		panel_2.add(panel_4_2);
		panel_4_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7_2.getLayout();
		flowLayout_2.setVgap(15);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_4_2.add(panel_7_2);
		
		JLabel lblNewLabel_3 = new JLabel("Etat paiement :");
		panel_7_2.add(lblNewLabel_3);
		
		JPanel panel_8_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_8_2.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setVgap(15);
		panel_4_2.add(panel_8_2);
		
		JLabel lblNewLabel_6 = new JLabel(matiere.getEtatPaiement().getStatut());
		panel_8_2.add(lblNewLabel_6);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_5.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		flowLayout_6.setVgap(15);
		panel_3.add(panel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Commentaires :");
		panel_5.add(lblNewLabel_7);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_6.getLayout();
		flowLayout_7.setVgap(15);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_6);
		
		JLabel lblNewLabel_8 = new JLabel(matiere.getEtatModule().getCommentairesAssistantProgramme());
		panel_6.add(lblNewLabel_8);
		
		JPanel panel_4_3 = new JPanel();
		panel_2.add(panel_4_3);
		panel_4_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7_3 = new JPanel();
		panel_4_3.add(panel_7_3);
		
		cloturerModule = new JButton("Cloturer");
		panel_7_3.add(cloturerModule);
		
		JPanel panel_8_3 = new JPanel();
		panel_4_3.add(panel_8_3);
		
		declencherEtatPaiement = new JButton("Declencher etat paiement");
		panel_8_3.add(declencherEtatPaiement);
	}

}
