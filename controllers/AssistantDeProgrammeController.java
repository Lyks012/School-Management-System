package app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Action;

import app.entities.Chef_De_Classe;
import app.entities.Classe;
import app.entities.Enseignant;
import app.entities.Matiere;
import app.exception.db.AdminDAOException;
import app.exception.db.AssistantDAOException;
import app.models.AdminDAOImpl;
import app.models.AssistantDAOImpl;
import app.models.DAOImpl;
import app.views.View;
import app.views.assistant.AssistantEmploiDuTemps;
import app.views.assistant.AssistantHome;
import app.views.assistant.AssistantView;
import app.views.assistant.ClassesList;
import app.views.assistant.CreerClasse;
import app.views.assistant.CreerModule;
import app.views.assistant.MatieresList;

public class AssistantDeProgrammeController extends Controller {
	
	public AssistantDeProgrammeController(View view, DAOImpl model) {
		super();
		this.view = view;
		this.model = model;
		this.view.setVisible(true);

		addListeners();
	}
	
	@Override
	public void addListeners() {
		switch(this.view.getClass().getSimpleName()) {
		case "AssistantHome":
			addListenersAssistantHome();
			break;
		case "MatieresList":
			addListenersMatieresList();
			break;
		case "CreerModule":
			addListenersCreerModule();
			break;
		case "ClassesList":
			addListenersClassesList();
			break;
		case "CreerClasse":
			addListenersCreerClasse();
		}
		
	}
	
	

	private void addListenersCreerClasse() {
		addListenerToCreerClasseBtn();
//		addListenerToRetourBtn();
		
	}

	private void addListenerToCreerClasseBtn() {
		((CreerClasse) this.view).addListenerToCreerClasseBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creerClasse(((CreerClasse) view).getNomClasse(), ((CreerClasse) view).getEnseignantSelectedId(), ((CreerClasse) view).getChefClasseSelectedId());
				view.displaySuccessMessage("Classe cree avec succes");
				List<Classe> classes = getAllClasses();
				updateView(new ClassesList(classes));
			}
		});
	}

	private void addListenersCreerModule() {
		addListenerToCreerModuleBtn();
		addListenerToRetourBtn();
		
	}

	private void addListenerToRetourBtn() {
		((CreerModule) this.view).addListenerToRetourBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Matiere> matieres = getAllMatieres();
				updateView(new MatieresList(matieres));
			}
		});
	}

	private void addListenerToCreerModuleBtn() {
		((CreerModule) this.view).addListenerToCreerModuleBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createModule(((CreerModule) view).getNomModule(), ((CreerModule) view).getEnseignantSelectedId(), ((CreerModule) view).getCoutHoraire());
				view.displaySuccessMessage("Module cree avec succes");
				List<Matiere> matieres = getAllMatieres();
				updateView(new MatieresList(matieres));
			}
		});
	}

	private void addListenersMatieresList() {
		addListenerToCreateModule();
//		addListenerToVoirDetails();
//		addListenerToSupprimerModule();
		addListenerToAccueilBtn();
	}
	
	private void addListenerToAccueilBtn() {
		((AssistantView) this.view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateView(new AssistantHome());
			}
		});
	}

	private void addListenersClassesList() {
		addListenerToCreateClass();
//		addListenerToVoirClasse();
//		addListenerToSupprimerModule();
		addListenerToAccueilBtn();
	}

	private void addListenerToCreateClass() {
		((ClassesList) this.view).addListenerToCreateClasse(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Enseignant> enseignants = getAllEnseignants();
				List<Chef_De_Classe> chefsDeClasse = getAllChefsDeClasse();
				
				updateView(new CreerClasse(enseignants, chefsDeClasse));
			}
			
		});
		
	}

	private List<Enseignant> getAllEnseignants() {
		List<Enseignant> enseignants = null;
		try {
			enseignants = ((AssistantDAOImpl) this.model).getAllEnseignants();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(enseignants);
		return enseignants;
	}
	private List<Chef_De_Classe> getAllChefsDeClasse() {
		List<Chef_De_Classe> chefsDeClasse = null;
		try {
			chefsDeClasse = ((AssistantDAOImpl) this.model).getAllChefsDeClasse();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return chefsDeClasse;
	}
		
	private void addListenerToCreateModule() {
		((MatieresList) this.view).addListenerToCreateModuleBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Enseignant> enseignants = getAllEnseignants();
				
				updateView(new CreerModule(enseignants));
			}
		});
	}

	private void addListenersAssistantHome() {
		((AssistantHome) this.view).addListenerToGoToEmploiDuTempsBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateView(new AssistantEmploiDuTemps());
			}
		});
		
		((AssistantHome) this.view).addListenerToGoToModulesBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Matiere> matieres = getAllMatieres();
				updateView(new MatieresList(matieres));
			}
		});
		
		((AssistantHome) this.view).addListenerToGoToClassesBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Classe> classes = getAllClasses();
				updateView(new ClassesList(classes));
			}

			
		});
		
		((AssistantHome) this.view).addListenerToGoToPersoBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////////
			}
		});
		((AssistantHome) this.view).addListenerToLogoutBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				///////////////////////////////////////
			}
		});
	}
	
	
	private List<Matiere> getAllMatieres() {
		List<Matiere> matieres = null;
		try {
			matieres = ((AssistantDAOImpl) model).GetAllMatieres();
		} catch (AssistantDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matieres;
	}
	
	private void createModule(String nom, int id_enseignant, int coutHoraire) {
		try {
			((AssistantDAOImpl) this.model).creerModule(nom, id_enseignant, coutHoraire);
		} catch (AssistantDAOException e) {
			System.out.println(e.getMessage());
		}
	}
	private List<Classe> getAllClasses() {
		List<Classe> classes = null;
		try {
			classes = ((AssistantDAOImpl) model).GetAllClasses();
		} catch (AssistantDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	private void creerClasse(String nom, int idEnseignant, int idClasse) {
		try {
			((AssistantDAOImpl) this.model).CreerClasse(nom, idEnseignant, idClasse);
		} catch (AssistantDAOException e) {
			System.out.println(e.getMessage());
		}
	}
}
