package app.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Action;

import app.entities.Assistant_De_Programme;
import app.entities.Chef_De_Classe;
import app.entities.Classe;
import app.entities.CoursProgramme;
import app.entities.Enseignant;
import app.entities.EtatModule;
import app.entities.Matiere;
import app.entities.Paiement;
import app.entities.Roles;
import app.entities.User;
import app.exception.db.AdminDAOException;
import app.exception.db.ClasseDAOException;
import app.exception.db.CoursDAOException;
import app.exception.db.DAOException;
import app.exception.db.EtatModuleDAOException;
import app.exception.db.MatieresDAOException;
import app.runtime.Application;
import app.views.HomeView;
import app.views.View;
import app.views.assistant.AssistantEmploiDuTemps;
import app.views.assistant.AssistantHome;
import app.views.assistant.AssistantView;
import app.views.assistant.ClassesList;
import app.views.assistant.CreerClasse;
import app.views.assistant.CreerModule;
import app.views.assistant.DetailsCours;
import app.views.assistant.DetailsModule;
import app.views.assistant.EmploiDuTempsClasse;
import app.views.assistant.EmploiDuTempsEnseignant;
import app.views.assistant.ListeEmploiDuTempsClasses;
import app.views.assistant.ListeEmploiDuTempsEnseignants;
import app.views.assistant.MatieresList;
import app.views.assistant.PersoInfos;
import app.views.assistant.ProgrammerCours;

public class AssistantDeProgrammeController extends Controller {
	
	public AssistantDeProgrammeController(View view) {
		super();
		this.view = view;
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
			break;
		case "AssistantEmploiDuTemps":
			addListenersAssistantEmploiDuTemps();
			break;
		case "EmploiDuTempsClasse":
			addListenersEmploiDuTempsClasse();
			break;
		case "EmploiDuTempsEnseignant":
			addListenerEmploiDuTempsEnseignant();
			break;
		case "ListeEmploiDuTempsClasses":
			addListenersListeClasseEmploi();
			break;
		case "ListeEmploiDuTempsEnseignants":
			addListenersListeEnseignantsEmploi();
			break;
		case "ProgrammerCours":
			addListenersProgrammerCours();
			break;
		case "DetailsCours":
			addListenersDetailsCours();
			break;
		case "DetailsModule" :
			addListenersDetailsModule();
			break;
		case "PersoInfos":
			addListenersPersoInfos();
			break;
		}
	}
	
	 private void addListenersPersoInfos() {
		((PersoInfos) view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateView(new AssistantHome());
			}
		});
		
		((PersoInfos)view).addListenerModifierBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean confirmEdit = view.displayConfirmationMessage("Are you sure ?", "Supression de cours");
				if(confirmEdit) {
					String newUsername = ((PersoInfos) view).getNewUsername();
					String newPassword = ((PersoInfos) view).getNewPassword();
				
					try {
						User assistant = (User) model.userDAO.getAssistant();
						assistant.setLogin(newUsername);
						assistant.setPassword(newPassword);
						model.userDAO.update(assistant);
						view.displaySuccessMessage("Informations modifies avec succes");
						updateView(new AssistantHome());
					} catch (AdminDAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
	}

	private void addListenersDetailsModule() {
		((DetailsModule) view).addListenerCloturerModule(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String commentaires = view.displayGetInfosMessage("Commentaires");
				Matiere matiere = ((DetailsModule) view).getMatiere();
				EtatModule etatModule = matiere.getEtatModule();
				etatModule.setStatut("Cloture");
				etatModule.setCommentairesAssistantProgramme(commentaires);
				try {
					model.etatModuleDAO.update(etatModule);
					System.out.println(etatModule.getStatut());
					List<Matiere> matieres = getAllMatieres();
					updateView(new MatieresList(matieres));
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
			}}
		);
		
		((DetailsModule) view).addListenerDeclencherEtatPaiement(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Matiere matiere = ((DetailsModule) view).getMatiere();
				Paiement paiement = matiere.getEtatPaiement();
				paiement.setStatut("Declenche");
				try {
					model.paiementDAO.update(paiement);
					System.out.println(paiement.getStatut());
					List<Matiere> matieres = getAllMatieres();
					updateView(new MatieresList(matieres));
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		((DetailsModule) view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Matiere matiere = ((DetailsModule) view).getMatiere();
				List<Matiere> matieres = getAllMatieres();
				updateView(new MatieresList(matieres));
				
			}});
	}

	private void addListenerEmploiDuTempsEnseignant() {
		((EmploiDuTempsEnseignant) view).addListenerRetourBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateView(new AssistantHome());
			}
		});
	}

	private void addListenersListeEnseignantsEmploi() {
		 ((ListeEmploiDuTempsEnseignants) this.view).addListenerToAccueilBtn(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateView(new AssistantHome());
				}
			});
			
			((ListeEmploiDuTempsEnseignants) this.view).addListenerVoirEmploiEnseignant(new ActionListener() {
				@Override 
				public void actionPerformed(ActionEvent e) {
					try {
						Enseignant enseignant = (Enseignant) model.userDAO.getOneById(((ListeEmploiDuTempsEnseignants) view).getSelectedEnseignantId());
						List<CoursProgramme> emploi = model.coursDAO.getAllByEnseignant(enseignant);
						
						updateView(new EmploiDuTempsEnseignant(emploi, enseignant.getLogin()));
					} catch (DAOException e1) {
						view.displayErrorMessage(e1.getMessage());
					}
				}
				
			});
		
	}

	private void addListenersDetailsCours() {
		((DetailsCours) view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int classeId = ((DetailsCours) view).getClasseId();
					Classe classe = model.classesDAO.getOneById(classeId);
					List<CoursProgramme> emploi = model.coursDAO.getAllByClasse(classe);
				
					updateView(new EmploiDuTempsClasse(emploi, classe.getId(), classe.getNom()));
				} catch (DAOException e1) {
					view.displayErrorMessage(e1.getMessage());
				}
			}
		});
	}

	private void addListenersProgrammerCours() {
		 ((ProgrammerCours) this.view).addListenerToAccueilBtn(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Classe classe = model.classesDAO.getOneById(((ProgrammerCours) view).getClasseId());
					List<CoursProgramme> emploi = model.coursDAO.getAllByClasse(classe);
					
					updateView(new EmploiDuTempsClasse(emploi, classe.getId(), classe.getNom()));
				} catch (DAOException e1) {
					view.displayErrorMessage(e1.getMessage());
				}
			}
		 });
		 
		 ((ProgrammerCours) this.view).addListenerCreerCours(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String horaire = ((ProgrammerCours) view).getHoraire(); 
					String jour = ((ProgrammerCours) view).getJour();
					int semaine = ((ProgrammerCours) view).getSemaine();
					Matiere matiere = model.matiereDAO.getOneById(((ProgrammerCours) view).getIdModuleSelected());
					Classe classe = model.classesDAO.getOneById(((ProgrammerCours)view).getClasseId());
					CoursProgramme newCours = new CoursProgramme(0, horaire, jour, semaine, "", false, matiere, classe);
					
					model.coursDAO.create(newCours);
					
					List<CoursProgramme> emploi = model.coursDAO.getAllByClasse(classe);
					
					updateView(new EmploiDuTempsClasse(emploi, classe.getId(), classe.getNom()));
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		 });
	 }

	private void addListenersListeClasseEmploi() {
		((ListeEmploiDuTempsClasses) this.view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateView(new AssistantHome());
			}
		});
		
		((ListeEmploiDuTempsClasses) this.view).addListenerVoirEmploiClasse(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Classe classe = model.classesDAO.getOneById(((ListeEmploiDuTempsClasses) view).getSelectedClasseId());
					List<CoursProgramme> emploi = model.coursDAO.getAllByClasse(classe);
					
					updateView(new EmploiDuTempsClasse(emploi, classe.getId(), classe.getNom()));
				} catch (DAOException e1) {
					view.displayErrorMessage(e1.getMessage());
				}
			}
			
		});
	
	}

	private void addListenersEmploiDuTempsClasse() {
		((EmploiDuTempsClasse) this.view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateView(new AssistantHome());
			}
		});
		
		((EmploiDuTempsClasse) this.view).addListenerToAddCoursBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String horaire = ((EmploiDuTempsClasse) view).getSelectedHoraire();
				String jour = ((EmploiDuTempsClasse) view).getSelectedJour();
				
				if(((EmploiDuTempsClasse) view).getCoursAtHoraire(horaire, jour) == null && horaire != null && jour != null) {
					int semaine = ((EmploiDuTempsClasse) view).getSemaine();
					int classeId = ((EmploiDuTempsClasse) view).getClasseId();
					updateView(new ProgrammerCours(horaire, jour, semaine, getAllMatieres(), classeId));
				}
				
				else 
					view.displayErrorMessage("Veuillez choisir un horaire vide");
				
			}
		});
		
		((EmploiDuTempsClasse) this.view).addListenerToDeleteCoursBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CoursProgramme coursToDelete = null;
				try {
					coursToDelete = ((EmploiDuTempsClasse) view).getSelectedCours();
				}catch(Exception e) {}
				
				if(coursToDelete == null) {
					view.displayErrorMessage("Veuillez choisir un cours a supprimer");
				}
				else {
					boolean confirmedDeletion = view.displayConfirmationMessage("Are you sure ?", "Supression de cours");
					
					if(confirmedDeletion) {
						try {
							
							model.coursDAO.delete(coursToDelete);
							view.displaySuccessMessage("Cours supprime avec succes");
							
							int classeId = (((EmploiDuTempsClasse) view).getClasseId());
							Classe classe = model.classesDAO.getOneById(classeId);
							List<CoursProgramme> emploi = model.coursDAO.getAllByClasse(classe);
							
							updateView(new EmploiDuTempsClasse(emploi, classe.getId(), classe.getNom()));
						}
						catch(Exception e) {
							
						}
					}
				}
			}
		});
		
		((EmploiDuTempsClasse) view).addListenerToDetailsCoursBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CoursProgramme cours = ((EmploiDuTempsClasse) view).getSelectedCours();
					int classeId = ((EmploiDuTempsClasse) view).getClasseId();
					
					updateView(new DetailsCours(cours, classeId));
				}
				catch(Exception ex) {
					view.displayErrorMessage("Veuillez choisir un cours");
				}
				
			}
		});
	}

	private void addListenersAssistantEmploiDuTemps() {
		((AssistantEmploiDuTemps) this.view).addListenerGestionEmploiParClasse(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Classe> classes = null;
				try {
					classes = model.classesDAO.getAll();
				} catch (ClasseDAOException e) {
					view.displayErrorMessage(e.getMessage());
				}
				updateView(new ListeEmploiDuTempsClasses(classes));
			}
		});
		
		((AssistantEmploiDuTemps) this.view).addListenerGestionEmploiParEnseignant(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Enseignant> enseignants;
				try {
					enseignants = model.userDAO.getEnseignants();
					updateView(new ListeEmploiDuTempsEnseignants(enseignants));
				} catch (AdminDAOException e) {
					e.printStackTrace();
				}
			}
		});
		
		((AssistantEmploiDuTemps) this.view).addListenerToAccueilBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateView(new AssistantHome());
			}
		});
	}

	private void addListenersCreerClasse() {
		addListenerToCreerClasseBtn();
		addListenerToRetourCreerClasseBtn();
		
	}

	private void addListenerToRetourCreerClasseBtn() {
		((CreerClasse) this.view).addListenerToRetourBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Classe> classes = getAllClasses();
				updateView(new ClassesList(classes));
			}
		});
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
				try {
					Enseignant enseignant = (app.entities.Enseignant) model.userDAO.getOneById(((CreerModule) view).getEnseignantSelectedId());
					Paiement paiement = new Paiement(0, "non paye", ((CreerModule) view).getCoutHoraire());
					EtatModule etatModule = new EtatModule(0, "", "En Cours");
					Matiere matiere = new Matiere(0, ((CreerModule) view).getNomModule(), enseignant, paiement, etatModule);
					
					model.matiereDAO.create(matiere);
					view.displaySuccessMessage("Module cree avec succes");
					List<Matiere> matieres = getAllMatieres();
					updateView(new MatieresList(matieres));
				} catch (DAOException ex) {
					view.displayErrorMessage(ex.getClass() + ex.getMessage());
				}
				
			}
		});
	}

	private void addListenersMatieresList() {
		addListenerToCreateModule();
		addListenerToVoirDetails();
		addListenerToSupprimerModule();
		addListenerToAccueilBtn();
	}
	
	private void addListenerToVoirDetails() {
		((MatieresList) this.view).addListenerToDetailsBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedModule = ((MatieresList) view).getIdSelectedModule();
				
				if(selectedModule > 0) {
					Matiere matiere;
					try {
						matiere = model.matiereDAO.getOneById(selectedModule);
						updateView(new DetailsModule(matiere));
					} catch (MatieresDAOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					view.displayErrorMessage("Veuillez choisir un module");
				}
			}
		});
	}

	private void addListenerToSupprimerModule() {
		
		((MatieresList) this.view).addListenerToSupprimerBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean confirmedDeletion = view.displayConfirmationMessage("Etes vous sur ?", "Supression de Module");
				
				if(confirmedDeletion) {
					try {
						int idModule = ((MatieresList) view).getIdSelectedModule();
						
						Matiere moduleToDelete = model.matiereDAO.getOneById(idModule);
						model.matiereDAO.delete(moduleToDelete);
						view.displaySuccessMessage("Module supprime avec succes");
						
						updateView(new MatieresList(model.matiereDAO.getAll()));
					} catch (MatieresDAOException e) {
						// TODO Auto-generated catch block
						view.displayErrorMessage("Erreur lors de la suppression du module");
						System.out.println(e.getMessage());
					}
					
				}
			}
		});
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
		addListenerToSupprimerClasse();
		addListenerToAccueilBtn();
	}

	private void addListenerToSupprimerClasse() {
		((ClassesList) this.view).addListenerToDeleteBtn(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = ((ClassesList) view).getIdSelectedClasse();
				try {
					System.out.println(id);
					Classe classeToDelete = model.classesDAO.getOneById(id);
					
					System.out.println(classeToDelete.getNom());
					model.classesDAO.delete(classeToDelete);
					
					view.displaySuccessMessage("Classe supprimee avec succes");
					updateView(new ClassesList(model.classesDAO.getAll()));
				} catch (ClasseDAOException e) {
					view.displayErrorMessage("Erreur dans la suppression de la classe");
				}
				
			}
		
		});
	}

	private void addListenerToCreateClass() {
		((ClassesList) this.view).addListenerToCreateClasse(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<User> enseignants = getAllEnseignants();
				List<User> chefsDeClasse = getAllChefsDeClasse();
				
				updateView(new CreerClasse(enseignants, chefsDeClasse));
			}
			
		});
		
	}

	private List<User> getAllEnseignants() {
		List<User> enseignants = null;
		try {
			enseignants = model.userDAO.getAllByRole(Roles.enseignant); 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return enseignants;
	}
	private List<User> getAllChefsDeClasse() {
		List<User> chefsDeClasse = null;
		try {
			chefsDeClasse = model.userDAO.getAllByRole(Roles.chef_de_classe);
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
				List<User> enseignants = null;
				try {
					enseignants = model.userDAO.getAllByRole(Roles.enseignant);
				} catch (AdminDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
				Assistant_De_Programme assistant;
				try {
					assistant = model.userDAO.getAssistant();
					updateView(new PersoInfos(assistant));
				} catch (AdminDAOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		((AssistantHome) this.view).addListenerToLogoutBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Application.controller = new HomeController(new HomeView());
				updateView(new HomeView());
			}
		});
	}
	
	
	private List<Matiere> getAllMatieres() {
		List<Matiere> matieres = null;
		try {
			matieres = model.matiereDAO.getAll();
		} catch (MatieresDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matieres;
	}
	
	private void createModule(String nom, int id_enseignant, int coutHoraire) {
		try {
			Enseignant enseignant = new Enseignant(id_enseignant); 
			model.matiereDAO.create(new Matiere(0, nom, enseignant , null, null));
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
	}
	private List<Classe> getAllClasses() {
		List<Classe> classes = null;
		try {
			classes = model.classesDAO.getAll();
		} catch (ClasseDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}
	
	private void creerClasse(String nom, int idEnseignant, int idChefClasse) {
		try {
			Classe classe = new Classe(0, nom, new Enseignant(idEnseignant), new Chef_De_Classe(idChefClasse));
			model.classesDAO.create(classe);
		} catch (ClasseDAOException e) {
			System.out.println(e.getMessage());
		}
	}
}
