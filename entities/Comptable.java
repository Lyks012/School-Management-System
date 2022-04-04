package app.entities;

public class Comptable extends User {
	public Comptable(int id, String login, String password) {
		super(id, login, password, Roles.comptable);
	}
}
