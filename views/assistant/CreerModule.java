package app.views.assistant;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import app.entities.Enseignant;
import app.entities.User;

import javax.swing.JComboBox;

public class CreerModule extends AssistantView {
	private JTextField nomModuleTF;
	private JButton creerModuleBtn;
	private JButton retourBtn;
	
	JComboBox<String> enseignantsComboBox;
	private JTextField coutHoraireTF;
	
	public void addListenerToCreerModuleBtn(ActionListener actionListener) {
		creerModuleBtn.addActionListener(actionListener);
	}
	
	public void addListenerToRetourBtn(ActionListener actionListener) {
		retourBtn.addActionListener(actionListener);
	}
	
	public CreerModule(List<User> enseignants) {
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Creeer Module");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		retourBtn = new JButton("Retour");
		panel_1.add(retourBtn);
		
		creerModuleBtn = new JButton("Creer Module");
		panel_1.add(creerModuleBtn);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Nom Module :");
		panel_5.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setVgap(20);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_6);
		
		nomModuleTF = new JTextField();
		panel_6.add(nomModuleTF);
		nomModuleTF.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_10.getLayout();
		flowLayout_4.setVgap(20);
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_9.add(panel_10);
		
		JLabel lblNewLabel_3 = new JLabel("Cout Horaire :");
		panel_10.add(lblNewLabel_3);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_11.getLayout();
		flowLayout_5.setVgap(20);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_11);
		
		coutHoraireTF = new JTextField();
		panel_11.add(coutHoraireTF);
		coutHoraireTF.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Enseignant :");
		panel_7.add(lblNewLabel_2);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_8);
		
		enseignantsComboBox = new JComboBox();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		
		for(User enseignant : enseignants) {
			model.addElement(enseignant.getId()+ "-" +  enseignant.getLogin());
		}
		enseignantsComboBox.setModel(model);
		panel_8.add(enseignantsComboBox);
	}
	
	private void initComboBox(List<Enseignant> enseignants) {
		
	}
	
	public int getEnseignantSelectedId() {
		String selectedEnseignant = (String) enseignantsComboBox.getSelectedItem();
		
		return Integer.parseInt(
				selectedEnseignant.split("-")[0]
				);
	}

	public String getNomModule() {
		return nomModuleTF.getText();
	}
	public int getCoutHoraire() {
		return Integer.parseInt(coutHoraireTF.getText());
	}
}
