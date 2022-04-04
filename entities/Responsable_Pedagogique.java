package app.entities;

public class Responsable_Pedagogique extends User{
	public Responsable_Pedagogique(int id, String login, String password) {
		super(id, login, password, Roles.responsable_pedagogique);
	}
}
