package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class ModulesList extends AssistantView {
	public ModulesList() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Modules");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Accueil");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JButton btnNewButton_2 = new JButton("Creer module");
		panel_3.add(btnNewButton_2);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JButton btnNewButton_1 = new JButton("Voir details ");
		panel_4.add(btnNewButton_1);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_5.add(btnNewButton_3);
	}

}
