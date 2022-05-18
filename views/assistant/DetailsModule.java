package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class DetailsModule extends AssistantView {
	public DetailsModule() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Details + nomModule");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Retour");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);
		
		JLabel lblNewLabel_1 = new JLabel("Enseignant");
		panel_7.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		JPanel panel_4_1 = new JPanel();
		panel_2.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4_1.add(panel_7_1);
		
		JLabel lblNewLabel_2 = new JLabel("Statut");
		panel_7_1.add(lblNewLabel_2);
		
		JPanel panel_8_1 = new JPanel();
		panel_4_1.add(panel_8_1);
		
		JPanel panel_4_2 = new JPanel();
		panel_2.add(panel_4_2);
		panel_4_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_4_2.add(panel_7_2);
		
		JLabel lblNewLabel_3 = new JLabel("Etat paiement");
		panel_7_2.add(lblNewLabel_3);
		
		JPanel panel_8_2 = new JPanel();
		panel_4_2.add(panel_8_2);
		
		JPanel panel_4_3 = new JPanel();
		panel_2.add(panel_4_3);
		panel_4_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7_3 = new JPanel();
		panel_4_3.add(panel_7_3);
		
		JButton btnNewButton_1 = new JButton("Cloturer");
		panel_7_3.add(btnNewButton_1);
		
		JPanel panel_8_3 = new JPanel();
		panel_4_3.add(panel_8_3);
		
		JButton btnNewButton_2 = new JButton("Declencher etat paiement");
		panel_8_3.add(btnNewButton_2);
	}

}
