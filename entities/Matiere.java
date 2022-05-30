package app.entities;

public class Matiere {
	public Matiere(int id, String nom, Enseignant enseignant, Paiement etatPaiement, EtatModule etatModule) {
		super();
		this.id = id;
		this.nom = nom;
		this.enseignant = enseignant;
		this.etatPaiement = etatPaiement;
		this.etatModule = etatModule;
	}
	private int id;
	private String nom;
	
	private Enseignant enseignant;
	private Paiement etatPaiement;
	private EtatModule etatModule;
	
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
	public Paiement getEtatPaiement() {
		return etatPaiement;
	}
	public void setEtatPaiement(Paiement etatPaiement) {
		this.etatPaiement = etatPaiement;
	}
	public EtatModule getEtatModule() {
		return etatModule;
	}
	public void setEtatModule(EtatModule etatModule) {
		this.etatModule = etatModule;
	}
}
