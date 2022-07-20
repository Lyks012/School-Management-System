package app.utils;

import java.util.ArrayList;
import java.util.List;

import app.entities.CoursProgramme;
import app.models.CoursDAOImpl;

public class EmploiDuTemps {

    public List<CoursProgramme> lireEmploiDuTempsClasse(int id_classe) throws AssistantDAOException {
        List<CoursProgramme> emploi_du_temps = new ArrayList<CoursProgramme>();

        CoursDAOImpl coursDAO = new CoursDAOImpl();

        List<CoursProgramme> cours = coursDAO.getAllByClasse();
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

}
