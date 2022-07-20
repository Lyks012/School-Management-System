package app.entities;

public class Classe {
	private int id;
	private String nom;
	private Enseignant enseignant;
	private Chef_De_Classe chefDeClasse;

	public Classe(int id, String nom, Enseignant enseignant, Chef_De_Classe chefDeClasse) {
		super();
		this.id = id;
		this.nom = nom;
		this.enseignant = enseignant;
		this.chefDeClasse = chefDeClasse;
	}
	public Classe(int id) {
		this.id = id;
	}
	
	public Classe() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Chef_De_Classe getChefDeClasse() {
		return chefDeClasse;
	}
	public void setChefDeClasse(Chef_De_Classe chefDeClasse) {
		this.chefDeClasse = chefDeClasse;
	}
	
}
