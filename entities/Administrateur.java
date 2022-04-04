package app.entities;

public class Administrateur extends User {
	public Administrateur(int id, String login, String password) {
		super(id, login, password, Roles.admin);
	}	
}
