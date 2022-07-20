package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.entities.Chef_De_Classe;
import app.entities.Classe;
import app.entities.Enseignant;
import app.entities.EtatModule;
import app.entities.Matiere;
import app.entities.Paiement;
import app.entities.User;
import app.exception.db.ClasseDAOException;
import app.exception.db.DAOException;
import app.exception.db.MatieresDAOException;

public class ClassesDAOImpl implements DAOImpl <Classe> {

    @Override
    public Classe getOneById(int id) throws ClasseDAOException {
    	Classe classe = new Classe();
    	System.out.println(id + "dao");
    	try(Connection connection = DBManager.getConnection()){
    		String query = "SELECT * FROM classe WHERE id_classe = ?";
    		
    		PreparedStatement ps = connection.prepareStatement(query);
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.first()) {
    			classe.setId(rs.getInt("id_classe"));
    			classe.setNom(rs.getString("nom"));
    			
                UserDAOImpl userDAO = new UserDAOImpl();
				User enseignant = userDAO.getOneById(rs.getInt("id_enseignant"));
				User chefClasse = userDAO.getOneById(rs.getInt("id_chefDeClasse"));
				
				classe.setEnseignant((Enseignant) enseignant);
				classe.setChefDeClasse((Chef_De_Classe) chefClasse);
    		}
    	}
    	catch(Exception e) {
    		throw new ClasseDAOException(e.getClass() + " " + e.getMessage());
    	}
    	System.out.println("Aui" + classe.getNom());
    	return classe;

    }

    @Override
    public List<Classe> getAll() throws ClasseDAOException {
        List<Classe> classes = new ArrayList<Classe>();

		try (Connection connection  = DBManager.getConnection()){
			String query = "SELECT * FROM classe";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id_classe");
				String name = rs.getString("nom");
                
                UserDAOImpl userDAO = new UserDAOImpl();
				User enseignant = userDAO.getOneById(rs.getInt("id_enseignant"));
				User chefDeClasse = userDAO.getOneById(rs.getInt("id_chefDeClasse"));
				
				Classe classe = new Classe(id, name, (Enseignant)enseignant, (Chef_De_Classe)chefDeClasse);
				classes.add(classe);
			}
			
		} catch (Exception e) {
			throw new ClasseDAOException(e.getClass() + " " + e.getMessage());
		}
		return classes;
    }

    @Override
    public void create(Classe classe) throws ClasseDAOException {
        try(Connection connection = DBManager.getConnection()){
    		String query = "INSERT INTO classe (nom, id_chefDeClasse, id_enseignant) VALUES (?, ?, ?)";
    		PreparedStatement ps = connection.prepareStatement(query);
    		
    		ps.setString(1, classe.getNom());
    		ps.setInt(2, classe.getChefDeClasse().getId());
    		ps.setInt(3, classe.getEnseignant().getId());
    		
    		ps.execute();
    	}
    	catch(Exception e) {
    		throw new ClasseDAOException(e.getClass() + " " + e.getMessage());
    	}
    }

    @Override
    public void update(Classe t) throws ClasseDAOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Classe classe) throws ClasseDAOException {
    	try(Connection connection = DBManager.getConnection()){
        	String query = "DELETE FROM classe WHERE id_classe = ?";
        	PreparedStatement ps = connection.prepareStatement(query);
        	
        	ps.setInt(1, classe.getId());
        	ps.executeUpdate();
        }
        catch(Exception e) {
        	throw new ClasseDAOException(e.getClass() + " " + e.getMessage());
        }
    }
    
}
