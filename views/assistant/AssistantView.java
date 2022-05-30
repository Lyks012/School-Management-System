package app.views.assistant;

import javax.swing.JButton;

import app.views.View;
import java.awt.event.ActionListener;

public abstract class AssistantView extends View {
	protected JButton accueilBtn;
	
	public void addListenerToAccueilBtn(ActionListener actionListener) {
		accueilBtn.addActionListener(actionListener);
	}
}
