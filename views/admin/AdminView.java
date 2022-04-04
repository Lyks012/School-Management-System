package app.views.admin;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.views.View;

public abstract class AdminView extends View {
	// bouton pour retourner a l'accueil
	protected JButton annulerButton;
	protected JButton modifierButton;

	public void addListenerToModifierButton(ActionListener actionListener) {
		modifierButton.addActionListener(actionListener);
	};

	public void addListenerToAnnulerButton(ActionListener listener) {
		annulerButton.addActionListener(listener);
	}
}
