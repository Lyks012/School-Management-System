package app.entities;

public class Assistant_De_Programme extends User{
	public Assistant_De_Programme(int id, String login, String password) {
		super(id, login, password, Roles.assistant_de_programme);
	}
}
