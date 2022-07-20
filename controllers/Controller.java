package app.controllers;

import app.models.DAOImpl;
import app.models.Model;
import app.views.View;

public abstract class Controller {
	protected View view;
	protected Model model;
	
	public Controller() {
		this.model = new Model();
	}

	public void updateView(View newView) {
		this.view.dispose();
		this.view = newView;
		this.addListeners();
		this.view.setVisible(true);
	}

	public void addListeners() {
	}
}
