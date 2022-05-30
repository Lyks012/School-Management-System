package app.entities;

public class Paiement {
	private int id;
	private String statut;
	private int coutHoraire;
	
	private Enseignant enseignant;
	private Matiere matiere;
	
	public Paiement(int id, String statut, int coutHoraire, Enseignant enseignant, Matiere matiere) {
		super();
		this.id = id;
		this.statut = statut;
		this.coutHoraire = coutHoraire;
		this.enseignant = enseignant;
		this.matiere = matiere;
	}
	public Paiement(int id, String statut, int coutHoraire, Enseignant enseignant) {
		super();
		this.id = id;
		this.statut = statut;
		this.coutHoraire = coutHoraire;
		this.enseignant = enseignant;
	}
	public Paiement(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getCoutHoraire() {
		return coutHoraire;
	}
	public void setCoutHoraire(int coutHoraire) {
		this.coutHoraire = coutHoraire;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	
}
