package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import app.entities.EtatModule;
import app.exception.db.DAOException;
import app.exception.db.EtatModuleDAOException;

public class EtatModuleDAOImpl implements DAOImpl<EtatModule> {

    @Override
    public EtatModule getOneById(int id) throws EtatModuleDAOException {
        EtatModule etatModule = new EtatModule();
        try (Connection connection = DBManager.getConnection()) {
            String query = "SELECT * FROM etat_module WHERE id_etat_module = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                etatModule.setId(rs.getInt("id_etat_module"));
                etatModule.setStatut(rs.getString("statut"));
                etatModule.setCommentairesAssistantProgramme(rs.getString("commentaires_assistant_de_programme"));
            }

        } catch (Exception e) {
            throw new EtatModuleDAOException(e.getClass() + " " + e.getMessage());
        }

        return etatModule;
    }

    @Override
    public List<EtatModule> getAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    public int createAndReturnId(EtatModule t) throws EtatModuleDAOException {
    	int id = 0;
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO etat_module (commentaires_assistant_de_programme, statut) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            System.out.println("Creating etat module");
            ps.setString(1, "");
            ps.setString(2, "");
            
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                   id = generatedKeys.getInt(1);
                }
                else {
                    throw new EtatModuleDAOException("Echec de la creation de l'etat module");
                }
            }
        } catch (Exception e) {
            throw new EtatModuleDAOException(e.getClass() + " " + e.getMessage());
        }
        return id;
    }

    @Override
    public void update(EtatModule etatModule) throws EtatModuleDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE etat_module SET commentaires_assistant_de_programme = ?, statut = ? WHERE id_etat_module = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, etatModule.getCommentairesAssistantProgramme());
            ps.setString(2, etatModule.getStatut());
            ps.setInt(3, etatModule.getId());
            System.out.println("alsdjf;lajdsf;lajsfd" + etatModule.getStatut());
            ps.execute();
        } catch (Exception e) {
            throw new EtatModuleDAOException(e.getClass() + " " + e.getMessage());
        }
    }

    @Override
    public void delete(EtatModule t) throws DAOException {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void create(EtatModule t) throws DAOException {
		// TODO Auto-generated method stub
		
	}
    
}
