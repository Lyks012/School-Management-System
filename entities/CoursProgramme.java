package app.entities;

public class CoursProgramme {
    private String horaire;
    private String date;
    private int id_enseignant;
    private String nom_emseignant;

    private int id_module;
    private String nom_module;

    private int id_classe;
    private String nom_classe;

    public CoursProgramme(String horaire, String date, int id_enseignant, String nom_emseignant, int id_module,
            String nom_module, int id_classe, String nom_classe) {
        super();
        this.horaire = horaire;
        this.date = date;
        this.id_enseignant = id_enseignant;
        this.nom_emseignant = nom_emseignant;
        this.id_module = id_module;
        this.nom_module = nom_module;
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_enseignant() {
        return id_enseignant;
    }

    public void setId_enseignant(int id_enseignant) {
        this.id_enseignant = id_enseignant;
    }

    public String getNom_emseignant() {
        return nom_emseignant;
    }

    public void setNom_emseignant(String nom_emseignant) {
        this.nom_emseignant = nom_emseignant;
    }

    public int getId_module() {
        return id_module;
    }

    public void setId_module(int id_module) {
        this.id_module = id_module;
    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }
}
