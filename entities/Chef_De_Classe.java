package app.entities;

public class Chef_De_Classe extends User {
	private int classeId;
	
	public Chef_De_Classe(int id, String login, String password, int classeId) {
		super(id, login, password, Roles.chef_de_classe);
		this.classeId = classeId;
	}
	public Chef_De_Classe(int id, String login, String password) {
		super(id, login, password, Roles.chef_de_classe);
	}
	public Chef_De_Classe(int id){
		super(id);
	}
	public int getClasseId() {
		return this.classeId;
	}
	
	public void setClasseId(int classeId) {
		this.classeId = classeId;
	}
}
