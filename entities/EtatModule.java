package app.entities;

public class EtatModule {
	private int id;
	private String commentairesAssistantProgramme;
	private String statut;
	private Matiere matiere;

	public EtatModule(int id, String commentairesAssistantProgramme, String statut, Matiere matiere) {
		super();
		this.id = id;
		this.commentairesAssistantProgramme = commentairesAssistantProgramme;
		this.statut = statut;
		this.matiere = matiere;
	}

	public EtatModule(int id, String commentairesAssistantProgramme, String statut) {
		super();
		this.id = id;
		this.commentairesAssistantProgramme = commentairesAssistantProgramme;
		this.statut = statut;
		this.matiere = null;
	}

	public EtatModule() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommentairesAssistantProgramme() {
		return commentairesAssistantProgramme;
	}
	public void setCommentairesAssistantProgramme(String commentairesAssistantProgramme) {
		this.commentairesAssistantProgramme = commentairesAssistantProgramme;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
}
