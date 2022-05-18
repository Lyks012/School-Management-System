package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class AssistantEmploiDuTemps extends AssistantView {
	public AssistantEmploiDuTemps() {
		
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
		
		JButton GestionEmploiParClasse = new JButton("Gerer par classe");
		panel_3.add(GestionEmploiParClasse);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setVgap(50);
		panel_1.add(panel_4);
		
		JButton GestionEmploiParEnseignant = new JButton("Gerer par enseignant");
		panel_4.add(GestionEmploiParEnseignant);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton retourBtn = new JButton("Retour");
		panel_2.add(retourBtn);
	}

}
