package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ListeEmploiDuTempsEnseignants extends AssistantView {
	public ListeEmploiDuTempsEnseignants() {
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Liste des enseignants");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Retour a l'accueil");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voir emploi du temps");
		panel_1.add(btnNewButton_1);
	}

}
