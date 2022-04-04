package app.models;

import app.exception.db.AssistantDAOException;

public interface AssistantDAO<E> extends DAOImpl {
	// gestion emploi du temps
	public void createCours() throws AssistantDAOException;
	public void lireEmploiDuTemps() throws AssistantDAOException;
	public void modifierCours() throws AssistantDAOException;
	public void supprimerCours() throws AssistantDAOException;
	
	//gestion modules
	public void creerModule() throws AssistantDAOException;
	public void modifierModule() throws AssistantDAOException;
	public void modifierEtatModule() throws AssistantDAOException;
	public void declencherEtatPaiement() throws AssistantDAOException;
	
	
}
