package app.runtime;

import app.controllers.Controller;
import app.controllers.HomeController;
import app.models.AuthService;
import app.models.DAOImpl;
import app.views.HomeView;
import app.views.View;
import app.session.Session;

public class Application {
	public static Controller controller;
	public static View view;
//	public static DAOImpl model;
	
	public Application(View homeView){
		Application.view = homeView;
//		Application.model = authService;
	}
	
	public static void main(String[] args) {
		
		view = new HomeView();
		
		
		controller = new HomeController((HomeView) view);
		
//		Application app = new Application(view, null);
		
		view.setVisible(true);
	}
	
	
}
