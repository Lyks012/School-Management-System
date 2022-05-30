package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import app.entities.Assistant_De_Programme;
import app.entities.Chef_De_Classe;
import app.entities.Classe;
import app.entities.CoursProgramme;
import app.entities.Matiere;
import app.entities.Paiement;
import app.entities.Roles;
import app.entities.Enseignant;
import app.entities.EtatModule;
import app.exception.db.AssistantDAOException;
import app.utils.Utils;

public class AssistantDAOImpl implements AssistantDAO {

    @Override
    public void createCours(String horaire, String date, int id_enseignant, int id_module, int id_classe)
            throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO emploi_du_temps (horaire, date, id_enseignant, id_module, id_classe) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, horaire);
            ps.setString(2, date);
            ps.setInt(3, id_enseignant);
            ps.setInt(4, id_module);
            ps.setInt(5, id_classe);

            ps.execute();
        } catch (Exception e) {

        }
    }

    @Override
    public List<CoursProgramme> lireEmploiDuTempsClasse(int id_classe) throws AssistantDAOException {
        List<CoursProgramme> emploi_du_temps = new ArrayList<CoursProgramme>();

        try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM emploi_du_temps WHERE id_classe = ?, ORDER BY horaire";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_classe);
            ResultSet rs = ps.executeQuery();

            emploi_du_temps = generateEmploiDuTemps(rs);
        } catch (Exception e) {

        }

        return emploi_du_temps;
    }

    public List<CoursProgramme> lireEmploiDuTempsEnseignant(int id_enseignant) {
        List<CoursProgramme> emploi_du_temps = new ArrayList<CoursProgramme>();

        try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM emploi_du_temps WHERE id_enseignant = ?, ORDER BY horaire";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_enseignant);
            ResultSet rs = ps.executeQuery();

            emploi_du_temps = generateEmploiDuTemps(rs);
        } catch (Exception e) {

        }

        return emploi_du_temps;
    }

    @Override
    public void modifierCours(String horaire, String date, int id_enseignant, int id_module, int id_classe,
            int id_cours) throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE emploi_du_temps SET horaire = ?, date = ?, id_enseignant = ?, id_module = ?, id_classe = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, horaire);
            ps.setString(2, date);
            ps.setInt(3, id_enseignant);
            ps.setInt(4, id_module);
            ps.setInt(5, id_classe);

            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    @Override
    public void supprimerCours(int id_cours) throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "DELETE FROM emploi_du_temps WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id_cours);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    @Override
    public void creerModule(String nomModule, int id_enseignant, int coutHoraire) throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO module (nom, id_enseignant) VALUES (?, ?)";

            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, nomModule);
            ps.setInt(2, id_enseignant);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            
            // Creer Etat Module
            creerEtatModule(rs.getInt(1));
            //creer etat paiement
            creerEtatPaiementModule(rs.getInt(1), id_enseignant, coutHoraire);
        } catch (Exception e) {
        	throw new AssistantDAOException(e.getMessage());
        }
    }
    
    public void CreerClasse(String nomClasse, int idEnseignant, int idChefDeClasse) throws AssistantDAOException {
    	try(Connection connection = DBManager.getConnection()){
    		String query = "INSERT INTO classe (nom, id_chefDeClasse, id_enseignant) VALUES (?, ?, ?)";
    		PreparedStatement ps = connection.prepareStatement(query);
    		
    		ps.setString(1, nomClasse);
    		ps.setInt(2, idChefDeClasse);
    		ps.setInt(3, idEnseignant);
    		
    		ps.execute();
    	}
    	catch(Exception e) {
    		throw new AssistantDAOException(e.getClass() + " " + e.getMessage());
    	}
    }

    

    @Override
    public void modifierModule(String newModuleName) throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE module SET nom = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(0, newModuleName);
            ps.execute();
        } catch (Exception e) {

        }
    }

    @Override
    public void modifierEtatModule(String commentairesAssistantDeProgramme, String statut)
            throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE etat_module SET commentaires_assistant_de_programme = ?, statut = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, commentairesAssistantDeProgramme);
            ps.setString(2, statut);

            ps.execute();
        } catch (Exception e) {

        }
    }

    @Override
    public void declencherEtatPaiement() throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {

        } catch (Exception e) {

        }
    }

    @Override
    public void modifierInformations(Assistant_De_Programme assistant) throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String sql = "UPDATE users SET WHERE login = ?, password = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, assistant.getLogin());
            ps.setString(2, assistant.getPassword());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private List<CoursProgramme> generateEmploiDuTemps(ResultSet rs) {
        List<CoursProgramme> emploi_du_temps = new ArrayList<CoursProgramme>();

        try {
            while (rs.next()) {
                String horaire = rs.getString("horaire");
                String date = rs.getString("date");

                int id_enseignant = rs.getInt("id_enseignant");
                String nom_enseignant = getNomEnseignant(id_enseignant);

                int id_module = rs.getInt("id_module");
                String nom_module = getNomModule(id_module);

                int id_classe = rs.getInt("id_classe");
                String nom_classe = getNomClasse(id_classe);

                emploi_du_temps.add(new CoursProgramme(horaire, date, id_enseignant, nom_enseignant, id_module,
                        nom_module, id_classe, nom_classe));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return emploi_du_temps;
    }

    private String getNomClasse(int id_classe) {
        String nomClasse = "";

        try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT nom FROM classe WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_classe);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                nomClasse = rs.getString("nom");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return nomClasse;
    }

    private String getNomEnseignant(int id_enseignant) {
        String nomEnseignant = "";

        try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT nom FROM user WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_enseignant);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                nomEnseignant = rs.getString("nom");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return nomEnseignant;
    }

    private String getNomModule(int id_module) {
        String nomModule = "";
        try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT nom FROM module WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_module);

            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                nomModule = rs.getString("nom");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        return nomModule;
    }

    private void creerEtatPaiementModule(int id_module, int id_enseignant, int coutHoraire){
    	System.out.println("enter create paiement");
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO paiement (statut, coutHoraire, id_module, id_enseignant) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("creating paiment module");
            ps.setString(1, "Non Paye");
            ps.setInt(2, coutHoraire);
            ps.setInt(3, id_module);
            ps.setInt(4, id_enseignant);

            ps.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void creerEtatModule(int id_module) {
    	System.out.println("Enter creer etat Module");
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO etat_module (commentaires_assistant_de_programme, statut, id_module) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Creating etat module");
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setInt(3, id_module);

            ps.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    

	public List<Matiere> GetAllMatieres() throws AssistantDAOException{
		List<Matiere> matieres = new ArrayList<Matiere>();
		try (Connection connection  = DBManager.getConnection()){
			String query = "SELECT m.id_module, m.nom, m.id_enseignant, e_m.id_etat_module, p.id_paiement FROM module as m "
					+ "INNER JOIN etat_module as e_m ON e_m.id_module = m.id_module "
					+ "INNER JOIN paiement as p ON m.id_module = p.id_module";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				int id = rs.getInt("id_module");
				String name = rs.getString("nom");
				Enseignant enseignant = getEnseignant(rs.getInt("id_enseignant"));
				Paiement etatPaiement = getEtatPaiement(rs.getInt("id_paiement"));
				EtatModule etatModule = getEtatModule(rs.getInt("id_etat_module"));
				
				Matiere matiere = new Matiere(id, name, enseignant, etatPaiement, etatModule);
				matieres.add(matiere);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return matieres;
	}
	
	public List<Classe> GetAllClasses() throws AssistantDAOException{
		List<Classe> classes = new ArrayList<Classe>();
		try (Connection connection  = DBManager.getConnection()){
			String query = "SELECT * FROM classe";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id_classe");
				String name = rs.getString("nom");
				Enseignant enseignant = getEnseignant(rs.getInt("id_enseignant"));
				Chef_De_Classe chefDeClasse = getChefDeClasse(rs.getInt("id_chefDeClasse"));
				
				Classe classe = new Classe(id, name, enseignant, chefDeClasse);
				classes.add(classe);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return classes;
	}
	
	
	

	public List<Enseignant> getAllEnseignants() throws AssistantDAOException {
		List<Enseignant> enseignants = new ArrayList<Enseignant>();;
		try(Connection connection = DBManager.getConnection()){
			String query = "SELECT * FROM users LEFT OUTER JOIN user_classe ON users.id_user = user_classe.user_id WHERE users.role = \"enseignant\"";
			
			PreparedStatement ps =  connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Enseignant enseignant = new Enseignant(rs.getInt("id_user"), rs.getString("login"), rs.getString("password"), rs.getInt("classe_id"));
				enseignants.add(enseignant);
			}
		}
		catch(Exception e) {
			throw new AssistantDAOException(e.getMessage());
		}
		
		return enseignants;
	}
	
	private EtatModule getEtatModule(int id) {
        EtatModule etatModule = new EtatModule();
		try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM etat_module WHERE id_etat_module = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            if(rs.first()) {
            	etatModule.setId(rs.getInt("id_etat_module"));
            	etatModule.setStatut(rs.getString("statut"));
            	etatModule.setCommentairesAssistantProgramme(rs.getString("commentaires_assistant_de_programme"));
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
		return etatModule;
	}

	private Paiement getEtatPaiement(int id) {
		Paiement paiement = new Paiement();
		try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM paiement WHERE id_paiement = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            if(rs.first()) {
            	paiement.setId(rs.getInt("id_paiement"));
            	paiement.setCoutHoraire(rs.getInt("coutHoraire"));
            	paiement.setStatut(rs.getString("statut"));
            	
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return paiement;

	}

	private Enseignant getEnseignant(int id) {
        Enseignant enseignant = (Enseignant) Utils.getUserInstance(Roles.enseignant);
		try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM users WHERE id_user= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            if(rs.first()) {
            	enseignant.setId(rs.getInt("id_user"));
            	enseignant.setPassword("password");
            	enseignant.setLogin("login");
            	
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
		return enseignant;
	}
	
	private Chef_De_Classe getChefDeClasse(int id) {
		Chef_De_Classe chefDeClasse = (Chef_De_Classe) Utils.getUserInstance(Roles.chef_de_classe);
		try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM users WHERE id_user= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            
            if(rs.first()) {
            	chefDeClasse.setId(rs.getInt("id_user"));
            	chefDeClasse.setPassword("password");
            	chefDeClasse.setLogin("login");
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
		return chefDeClasse;
	}

	public List<Chef_De_Classe> getAllChefsDeClasse() throws AssistantDAOException {
        List<Chef_De_Classe> chefsDeClasse = new ArrayList<Chef_De_Classe>();;
		try(Connection connection = DBManager.getConnection()){
			String query = "SELECT * FROM users LEFT OUTER JOIN user_classe ON users.id_user = user_classe.user_id WHERE users.role = \"chef_de_classe\"";
			
			PreparedStatement ps =  connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Chef_De_Classe chefDeClasse = new Chef_De_Classe(rs.getInt("id_user"), rs.getString("login"), rs.getString("password"), rs.getInt("classe_id"));
				chefsDeClasse.add(chefDeClasse);
			}
		}
		catch(Exception e) { 
			throw new AssistantDAOException(e.getMessage());
		}
		
		return chefsDeClasse;
	}

}
