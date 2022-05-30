package app.views.assistant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmploiDuTempsEnseignant extends AssistantView {
	public EmploiDuTempsEnseignant()
	{

		setSize(450, 300);
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
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.NORTH);
		
		JLabel nomEnseignant = new JLabel("Emploi du temps \"name of enseignant\"");
		panel_4.add(nomEnseignant);

	}
}
