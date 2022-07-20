package app.entities;

public class Enseignant extends User {
	private int classeId;
	public Enseignant(int id, String login, String password, int classeId) {
		super(id, login, password, Roles.enseignant);
		this.classeId = classeId;
	}

	public Enseignant(int id, String login, String password){
		super(id, login, password, Roles.enseignant);
	}
	public Enseignant(int id){
		super(id);
	}
	public int getClasseId() {
		return this.classeId;
	}
	
	public void setClasseId(int classeId) {
		this.classeId = classeId;
	}
}
