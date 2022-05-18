package app.models;

import app.exception.db.AssistantDAOException;
import app.entities.Assistant_De_Programme;
import app.entities.CoursProgramme;
import java.util.ArrayList;
import java.util.List;

public interface AssistantDAO<E> extends DAOImpl {

	// gestion emploi du temps
	public void createCours(String horaire, String date, int id_enseignant, int id_module, int id_classe)
			throws AssistantDAOException;

	public List<CoursProgramme> lireEmploiDuTempsClasse(int id_classe) throws AssistantDAOException;

	public List<CoursProgramme> lireEmploiDuTempsEnseignant(int id_classe) throws AssistantDAOException;

	public void modifierCours(String horaire, String date, int id_enseignant, int id_module, int id_classe,
			int id_cours) throws AssistantDAOException;

	public void supprimerCours(int id_cours) throws AssistantDAOException;

	// gestion modules
	public void creerModule(String nomModule) throws AssistantDAOException;

	public void modifierModule(String newModuleName) throws AssistantDAOException;

	public void modifierEtatModule(String commentairesAssistantDeProgramme, String statut) throws AssistantDAOException;

	public void declencherEtatPaiement() throws AssistantDAOException;
	
	// gestion perso
	public void modifierInformations(Assistant_De_Programme assistant) throws AssistantDAOException;

}
