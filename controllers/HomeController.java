package app.controllers;

import app.entities.Roles;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import app.models.AdminDAOImpl;
import app.models.AssistantDAOImpl;
import app.models.AuthService;
import app.models.DAOImpl;
import app.runtime.Application;
import app.session.Session;
import app.views.HomeView;
import app.views.View;
import app.views.admin.AdminHome;
import app.views.assistant.AssistantHome;

public class HomeController extends Controller {
	public HomeView view;

	public HomeController(HomeView homeView, AuthService authService) {
		super();
		this.view = homeView;
		this.model = authService;
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

			if (((AuthService) this.model).login(role, login, password)) {
//				System.out.println("Logged In");
//				Session.login = login;

				Application.view.setVisible(false);

				switch (role) {
					case admin:
						Application.model = new AdminDAOImpl();
						Application.view = new AdminHome();
						Application.controller = new AdminController(new AdminHome(), Application.model);
						break;
					case assistant_de_programme:
						Application.model = new AssistantDAOImpl();
						Application.view = new AssistantHome();
						Application.controller = new AssistantDeProgrammeController(Application.view, Application.model);
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
