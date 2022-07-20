package app.controllers;

import app.entities.Roles;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import app.models.AuthService;
import app.runtime.Application;
import app.views.HomeView;
import app.views.admin.AdminHome;
import app.views.assistant.AssistantHome;

public class HomeController extends Controller {
	public HomeView view;

	public HomeController(HomeView homeView) {
		super();
		this.view = homeView;
		
		ajouterEcouteurAuBoutonSeConnecter();
	}

	public void ajouterEcouteurAuBoutonSeConnecter() {
		view.addEcouteur(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						log();
					}
				});
	}

	public void log() {
		try {
			Roles role = view.getRole();
			String login = view.getLogin();
			String password = view.getPassword();

			if (model.authService.login(role, login, password)) {

				Application.view.setVisible(false);

				switch (role) {
					case admin:
//						Application.view = new AdminHome(); 
						Application.controller = new AdminController(new AdminHome());
						break;
					case assistant_de_programme:
						Application.view = new AssistantHome();
						Application.controller = new AssistantDeProgrammeController(Application.view);
						break;
					case chef_de_classe:
						break;
					case comptable:
						break;
					case enseignant:
						break;
					case responsable_pedagogique:
						break;
					default:
						break;
				}
			} else {
				this.view.displayErrorMessage("Incorrect Credentials");
			}
		} catch (Exception e) {
			this.view.displayErrorMessage(e.getClass() + " : " + e.getMessage());
		}
	}
}
