package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class AssistantEmploiDuTemps extends AssistantView {
	private JButton gestionEmploiParEnseignant;
	private JButton gestionEmploiParClasse;
	
	public void addListenerGestionEmploiParEnseignant(ActionListener actionListener){
		gestionEmploiParEnseignant.addActionListener(actionListener);
	}
	public void addListenerGestionEmploiParClasse(ActionListener actionListener){
		gestionEmploiParClasse.addActionListener(actionListener);
	}
	
	
	public AssistantEmploiDuTemps() {
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Gestion des emploi du temps");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(50);
		panel_1.add(panel_3);
		
		gestionEmploiParClasse = new JButton("Gerer par classe");
		panel_3.add(gestionEmploiParClasse);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setVgap(50);
		panel_1.add(panel_4);
		
		gestionEmploiParEnseignant = new JButton("Gerer par enseignant");
		panel_4.add(gestionEmploiParEnseignant);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		accueilBtn = new JButton("Retour");
		panel_2.add(accueilBtn);
	}

}
