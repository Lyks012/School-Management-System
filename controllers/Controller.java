package app.controllers;

import app.models.DAOImpl;
import app.views.View;

public abstract class Controller {
	protected View view;
	protected DAOImpl model;

	public void updateView(View newView) {
		this.view.dispose();
		this.view = newView;
		this.addListeners();
		this.view.setVisible(true);
	}

	public void addListeners() {
	}
}
