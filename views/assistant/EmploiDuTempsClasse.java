package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmploiDuTempsClasse extends AssistantView {
	private JTable table;
	public EmploiDuTempsClasse() {
		
		setSize(454, 299);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Retour a l'accueil");
		panel.add(btnNewButton);
		
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
		
		JButton btnNewButton_1 = new JButton("Ajouter un cours");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_5.add(btnNewButton_1);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setVgap(20);
		panel_2.add(panel_6);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		panel_6.add(btnNewButton_2);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JButton btnNewButton_3 = new JButton("Supprimer");
		panel_7.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JLabel nomClasse = new JLabel("Emploi du temps \"name of class\"");
		panel_4.add(nomClasse);
	}

}
