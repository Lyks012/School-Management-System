package app.entities;

public class Paiement {
	private int id;
	private String statut;
	private int coutHoraire;
	
	
	public Paiement(int id, String statut, int coutHoraire) {
		super();
		this.id = id;
		this.statut = statut;
		this.coutHoraire = coutHoraire;
	}
	public Paiement() {
		super();
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
	
	
}
