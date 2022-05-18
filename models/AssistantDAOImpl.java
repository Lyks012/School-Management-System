package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.entities.Assistant_De_Programme;
import app.entities.CoursProgramme;
import app.exception.db.AssistantDAOException;

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
    public void creerModule(String nomModule) throws AssistantDAOException {
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO module (nom) VALUES (?)";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nomModule);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            // Creer Etat Module
            creerEtatModule(rs.getInt("id"));
        } catch (Exception e) {

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

    private void creerEtatModule(int id_module) {
        try (Connection connection = DBManager.getConnection()) {
            String query = "INSERT INTO etat_module (commentaires_assistant_de_programme, statut, id_module) VALUES ?, ?, ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, "");
            ps.setString(2, "");
            ps.setInt(2, id_module);

            ps.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
