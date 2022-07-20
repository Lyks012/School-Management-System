package app.models;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import app.entities.Paiement;
import app.exception.db.DAOException;
import app.exception.db.EtatModuleDAOException;
import app.exception.db.MatieresDAOException;
import app.exception.db.PaiementDAOException;

public class PaiementDAOImpl implements DAOImpl<Paiement> {

    @Override
    public Paiement getOneById(int id) throws PaiementDAOException {
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

    @Override
    public List<Paiement> getAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    public int createAndReturnId(Paiement paiement) throws DAOException {
    	int id = 0;
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO paiement (statut, coutHoraire) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Non Paye");
            ps.setInt(2, paiement.getCoutHoraire());
            

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
            System.out.println(e.getMessage());
        }
        
        return id;
    }

    @Override
    public void update(Paiement t) throws DAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "UPDATE paiement SET statut = ?, coutHoraire = ? WHERE id_paiement = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, t.getStatut());
            ps.setInt(2, t.getCoutHoraire());
            ps.setInt(3, t.getId());
            ps.execute();
        } catch (Exception e) {
            throw new MatieresDAOException(e.getMessage());
        }
    }

    @Override
    public void delete(Paiement t) throws DAOException {
        // TODO Auto-generated method stub
        
    }

	@Override
	public void create(Paiement t) throws DAOException {
		// TODO Auto-generated method stub
		
	}
    
}
