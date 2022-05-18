package app.controllers;

import app.models.DAOImpl;
import app.views.View;

public class AssistantDeProgrammeController extends Controller {
	
	public AssistantDeProgrammeController(View view, DAOImpl model) {
		super();
		this.view = view;
		this.model = model;
		this.view.setVisible(true);

		addListeners();
	}
}
