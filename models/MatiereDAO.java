package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.entities.Enseignant;
import app.entities.EtatModule;
import app.entities.Matiere;
import app.entities.Paiement;
import app.entities.User;
import app.exception.db.DAOException;
import app.exception.db.MatieresDAOException;

public class MatiereDAO implements DAOImpl<Matiere>{

    @Override
    public Matiere getOneById(int id) throws MatieresDAOException {
    	Matiere matiere = new Matiere();
    	try(Connection connection = DBManager.getConnection()){
    		String query = "SELECT * FROM module WHERE id_module = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(query);
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.first()) {
    			matiere.setId(rs.getInt("id_module"));
				matiere.setNom(rs.getString("nom"));

                UserDAOImpl userDAO = new UserDAOImpl();
                PaiementDAOImpl paiementDAO = new PaiementDAOImpl();
                EtatModuleDAOImpl etatModuleDAO = new EtatModuleDAOImpl();

				User enseignant = userDAO.getOneById(rs.getInt("id_enseignant"));
				Paiement etatPaiement = paiementDAO.getOneById(rs.getInt("id_paiement"));
				EtatModule etatModule = etatModuleDAO.getOneById(rs.getInt("id_etat_module"));
				
				matiere.setEnseignant((Enseignant) enseignant);
				matiere.setEtatModule(etatModule);
				matiere.setEtatPaiement(etatPaiement);
    		}
    	}
    	catch(Exception e) {
    		throw new MatieresDAOException(e.getClass() + " " + e.getMessage());
    	}
    	return matiere;
    }

    @Override
    public List<Matiere> getAll() throws MatieresDAOException {
        List<Matiere> matieres = new ArrayList<Matiere>();
		try (Connection connection  = DBManager.getConnection()){
			String query = "SELECT * FROM module";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id_module");
				String name = rs.getString("nom");
                UserDAOImpl userDAO = new UserDAOImpl();
                PaiementDAOImpl paiementDAO = new PaiementDAOImpl();
                EtatModuleDAOImpl etatModuleDAO = new EtatModuleDAOImpl();
                
				User enseignant = userDAO.getOneById(rs.getInt("id_enseignant"));
				Paiement etatPaiement = paiementDAO.getOneById(rs.getInt("id_paiement"));
				EtatModule etatModule = etatModuleDAO.getOneById(rs.getInt("id_etat_module"));
				
				Matiere matiere = new Matiere(id, name, (Enseignant)enseignant, etatPaiement, etatModule);
				
				matieres.add(matiere);
			}
			
		} catch (Exception e) {
			throw new MatieresDAOException(e.getClass() + " " + e.getMessage());
		}
		System.out.println("dao " + matieres.size());
		return matieres;
    }

    
    public void create(Matiere matiere) throws MatieresDAOException {
        try (Connection connection = DBManager.getConnection()) {
        	EtatModuleDAOImpl etatModuleDAO = new EtatModuleDAOImpl();
        	PaiementDAOImpl paiementDAO = new PaiementDAOImpl();
        	
        	int idEtatModule = etatModuleDAO.createAndReturnId(matiere.getEtatModule());
        	int idPaiement = paiementDAO.createAndReturnId(matiere.getEtatPaiement());
        	
            String query = "INSERT INTO module (nom, id_enseignant, id_etat_module, id_paiement) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, matiere.getNom());
            ps.setInt(2, matiere.getEnseignant().getId());
            ps.setInt(3, idEtatModule);
            ps.setInt(4, idPaiement);
            
            ps.execute();
        } catch (Exception e) {
        	throw new MatieresDAOException(e.getMessage());
        }
    }

    @Override
    public void update(Matiere matiere) throws MatieresDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE module SET nom = ? WHERE id_module = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, matiere.getNom());
            ps.setInt(2, matiere.getId());
            ps.execute();
        } catch (Exception e) {
            throw new MatieresDAOException(e.getMessage());
        }
    }

    @Override
    public void delete(Matiere matiere) throws MatieresDAOException {
        try(Connection connection = DBManager.getConnection()){
        	String query = "DELETE FROM module WHERE id_module = ?";
        	PreparedStatement ps = connection.prepareStatement(query);
        	
        	ps.setInt(1, matiere.getId());
        	ps.executeUpdate();
        }
        catch(Exception e) {
        	throw new MatieresDAOException(e.getClass() + " " + e.getMessage());
        }
        
    }
    
}
