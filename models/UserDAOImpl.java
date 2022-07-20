package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import app.entities.Administrateur;
import app.entities.Assistant_De_Programme;
import app.entities.Chef_De_Classe;
import app.entities.Comptable;
import app.entities.Enseignant;
import app.entities.Responsable_Pedagogique;
import app.entities.Roles;
import app.entities.User;
import app.exception.db.AdminDAOException;

public class UserDAOImpl implements DAOImpl<User> {
	// ajouter classe
    public void create(User user) throws AdminDAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "INSERT INTO users (login, password, role) VALUES(?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, String.valueOf(user.getRole()));
			preparedStatement.execute();
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}
	
	public User getOneById(int id_user) throws AdminDAOException {
        User user = null;
		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE id_user = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id_user);
			ResultSet rs = preparedStatement.executeQuery();

            if (rs.first()) {
				user = generateUserFromResultSet(rs);
			}
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
        return user;
	}

	
	public List<User> getAllByRole(Roles role) throws AdminDAOException {
		List<User> users = new ArrayList<User>();

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE role = ?";
			PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, role.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
                User user = generateUserFromResultSet(rs);
				users.add(user);
			}
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
        return users;
	}

	
	public void update(User user) throws AdminDAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "UPDATE users SET login=?, password=? WHERE id_user=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}

	
	public List<User> getAll() throws AdminDAOException {
		List<User> users = new ArrayList<User>();

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user = generateUserFromResultSet(rs);
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}

	}


	public User getOneByLogin(String login) throws AdminDAOException {
		User user = null;

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE login=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = generateUserFromResultSet(rs);
			}
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
        return user;
	}

    

	public void delete(User user) throws AdminDAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "DELETE FROM users WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}

	}

	private User createUserByType(int id, String login, String password, Roles role) {
		User user = null;

		switch (role) {
			case admin:
				user = new Administrateur(id, login, password);
				break;
			case responsable_pedagogique:
				user = new Responsable_Pedagogique(id, login, password);
				break;
			case comptable:
				user = new Comptable(id, login, password);
				break;
			case assistant_de_programme:
				user = new Assistant_De_Programme(id, login, password);
				break;
			case chef_de_classe:
				user = new Chef_De_Classe(id, login, password);
				break;
			case enseignant:
				user = new Enseignant(id, login, password);
				break;
			default:
				break;
		}
		return user;
	}
    private User generateUserFromResultSet(ResultSet rs) throws Exception{
        User user = null;
        try {
            int id = rs.getInt("id_user");
            String login = rs.getString("login");
            String password = rs.getString("password");
            Roles role = Roles.valueOf(rs.getString("role"));
            user = createUserByType(id, login, password, role);
        } catch (Exception e) {
            throw new Exception(e.getClass() + " " + e.getMessage());
        }
        System.out.println(user);
        return user;
    }

	public List<Enseignant> getEnseignants() throws AdminDAOException {
		List<Enseignant> enseignants = new ArrayList<Enseignant>();

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE role = 'enseignant'";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Enseignant user = (Enseignant) generateUserFromResultSet(rs);
				enseignants.add(user);
			}
			return enseignants;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}


	}

	public Assistant_De_Programme getAssistant() throws AdminDAOException {
        Assistant_De_Programme assistant = null;
		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE role = 'assistant_de_programme'";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			ResultSet rs = preparedStatement.executeQuery();

            if (rs.first()) {
				assistant = (Assistant_De_Programme) generateUserFromResultSet(rs);
			}
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
        return assistant;
	}
}
