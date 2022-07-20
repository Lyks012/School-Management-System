package app.entities;

public class CoursProgramme {
    private int id;
    private String horaire;
    private String jour;
    private int semaine;
    private String contenu;
    private boolean valideParEnseignant;

    private Matiere matiere;
    private Classe classe;

    public CoursProgramme(int id, String horaire, String jour,int semaine, String contenu, boolean valideParEnseignant, Matiere matiere,
             Classe classe) {
        super();
        this.id = id;
        this.horaire = horaire;
        this.jour = jour;
        this.semaine = semaine;
        this.contenu = contenu;
        this.valideParEnseignant = valideParEnseignant;
        this.matiere = matiere;
        this.classe = classe;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }
    
    public int getSemaine() {
        return semaine;
    }

    public boolean getValideParEnseignat() {
    	return valideParEnseignant;
    }
    
    public String getContenu() {
    	return contenu;
    }
    
    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setId_classe(Classe classe) {
        this.classe = classe;
    }
}
