package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.entities.Classe;
import app.entities.CoursProgramme;
import app.entities.Enseignant;
import app.entities.Matiere;
import app.exception.db.CoursDAOException;
import app.exception.db.DAOException;

public class CoursDAOImpl implements DAOImpl<CoursProgramme>{

    @Override
    public CoursProgramme getOneById(int id) throws CoursDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public List<CoursProgramme> getAllByEnseignant(Enseignant enseignant) throws CoursDAOException {
        List<CoursProgramme> emploi = new ArrayList<CoursProgramme>();
        
        try(Connection connection = DBManager.getConnection()){
        	String query = "SELECT * FROM cours as c INNER JOIN module as m WHERE c.id_module = m.id_module AND m.id_enseignant = ?;";
        	
        	PreparedStatement ps = connection.prepareStatement(query);
        	ps.setInt(1, enseignant.getId());
        	ResultSet rs = ps.executeQuery();
        	
        	MatiereDAO matiereDAO = new MatiereDAO();
        	ClassesDAOImpl classeDAO = new ClassesDAOImpl();
        	
        	while(rs.next()){
                int id = rs.getInt("id");
                String contenu = rs.getString("contenu");
                String horaire = rs.getString("horaire");
                String jour = rs.getString("jour");
                int semaine = rs.getInt("semaine");
                boolean valideParEnseignant = rs.getBoolean("valide_par_enseignant");
                Classe classe = classeDAO.getOneById(rs.getInt("id_classe"));
                Matiere matiere = matiereDAO.getOneById(rs.getInt("id_module"));
                
                CoursProgramme cours = new CoursProgramme(id, horaire, jour, semaine, contenu, valideParEnseignant, matiere, classe);
                emploi.add(cours);
            }
        	
        }catch(Exception e) {
        	throw new CoursDAOException(e.getClass() + " " + e.getMessage());
        }
        
        return emploi;
    }

    public List<CoursProgramme> getAllByClasse(Classe classe) throws CoursDAOException{
    	List<CoursProgramme> emploi = new ArrayList<CoursProgramme>();;
        try(Connection connection = DBManager.getConnection()){
            String query = "SELECT * FROM cours WHERE id_classe = ?";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, classe.getId());
            ResultSet rs = ps.executeQuery();

            MatiereDAO matiereDAO = new MatiereDAO();
            while(rs.next()){
                int id = rs.getInt("id");
                String contenu = rs.getString("contenu");
                String horaire = rs.getString("horaire");
                String jour = rs.getString("jour");
                int semaine = rs.getInt("semaine");
                boolean valideParEnseignant = rs.getBoolean("valide_par_enseignant");
                
                Matiere matiere = matiereDAO.getOneById(rs.getInt("id_module"));
                
                CoursProgramme cours = new CoursProgramme(id, horaire, jour, semaine, contenu, valideParEnseignant, matiere, classe);
                emploi.add(cours);
            }
        }
        catch(Exception e){
            throw new CoursDAOException(e.getClass() + " " + e.getMessage());
        }
        return emploi;
        
    }

    @Override
    public void create(CoursProgramme cours) throws CoursDAOException {
    	System.out.println(cours.getMatiere().getId());
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO cours (contenu, horaire, jour, semaine, valide_par_enseignant, id_module, id_classe) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cours.getContenu());
            ps.setString(2, cours.getHoraire());
            ps.setString(3, cours.getJour());
            ps.setInt(4, cours.getSemaine());
            ps.setBoolean(5, cours.getValideParEnseignat());
            ps.setInt(6, cours.getMatiere().getId());
            ps.setInt(7, cours.getClasse().getId());

            ps.execute();
        } catch (Exception e) {
        	throw new CoursDAOException(e.getClass() + " " + e.getMessage());
        }
    }

    @Override
    public void update(CoursProgramme cours) throws CoursDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE cours SET contenu = ?, horaire = ?, jour = ?, semaine = ?, valide_par_enseignant = ?, id_module = ?, id_classe = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cours.getContenu());
            ps.setString(2, cours.getHoraire());
            ps.setString(3, cours.getJour());
            ps.setInt(4, cours.getSemaine());
            ps.setBoolean(5, cours.getValideParEnseignat());
            ps.setInt(6, cours.getMatiere().getId());
            ps.setInt(7, cours.getClasse().getId());

            ps.executeUpdate();
        } catch (Exception e) {
        	throw new CoursDAOException(e.getClass() + " " + e.getMessage());
        }
    }

    @Override
    public void delete(CoursProgramme cours) throws CoursDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "DELETE FROM cours WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cours.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        	throw new CoursDAOException(e.getClass() + " " + e.getMessage());	
        }
    }


	@Override
	public List<CoursProgramme> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
    
}
