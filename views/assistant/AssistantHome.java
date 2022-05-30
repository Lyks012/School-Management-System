package app.views.assistant;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssistantHome extends AssistantView {
	private JButton goToEmploiDuTempsBtn;
	private JButton goToModulesBtn;
	private JButton goToClassesBtn;
	private JButton goToPersoBtn;
	private JButton logoutBtn;
	
	public void addListenerToGoToEmploiDuTempsBtn(ActionListener actionListener) {
		this.goToEmploiDuTempsBtn.addActionListener(actionListener);
	}
	public void addListenerToGoToModulesBtn(ActionListener actionListener) {
		this.goToModulesBtn.addActionListener(actionListener);
	}
	public void addListenerToGoToPersoBtn(ActionListener actionListener) {
		this.goToPersoBtn.addActionListener(actionListener);
	}
	public void addListenerToGoToClassesBtn(ActionListener actionListener) {
		this.goToClassesBtn.addActionListener(actionListener);
	}
	public void addListenerToLogoutBtn(ActionListener actionListener) {
		this.logoutBtn.addActionListener(actionListener);
	}
	
	public AssistantHome() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		setSize(475, 211);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Bienvenue");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		FlowLayout fl_panel_2 = new FlowLayout(FlowLayout.CENTER, 10, 50);
		panel_2.setLayout(fl_panel_2);
		
		goToEmploiDuTempsBtn = new JButton("Emploi du temps");
		panel_2.add(goToEmploiDuTempsBtn);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(50);
		panel_1.add(panel_3);
		
		goToModulesBtn = new JButton("Modules");
		goToModulesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_3.add(goToModulesBtn);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setVgap(50);
		panel_1.add(panel_4);
		
		goToClassesBtn = new JButton("Classes");
		panel_4.add(goToClassesBtn);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setHgap(10);
		flowLayout_2.setVgap(50);
		panel_1.add(panel_6);
		
		goToPersoBtn = new JButton("Perso");
		panel_6.add(goToPersoBtn);
		
		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5, BorderLayout.SOUTH);
		
		logoutBtn = new JButton("Se Deconnecter");
		panel_5.add(logoutBtn);
		
	}

}
