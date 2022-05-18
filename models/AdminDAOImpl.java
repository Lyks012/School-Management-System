package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import app.entities.Administrateur;
import app.entities.Roles;
import app.entities.User;
import app.exception.db.AdminDAOException;

public class AdminDAOImpl implements AdminDAO<User> {

	@Override
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

	@Override
	public User read(int id_user, Roles role) throws AdminDAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM " + role + " WHERE id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id_user);
			ResultSet rs = preparedStatement.executeQuery();

			User user = null;

			if (rs.first()) {
				int id = rs.getInt("id");
				String login = rs.getString("login");
				String password = rs.getString("password");
				user = createUserByType(id, login, password, role);
			}

			return user;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public List<User> list(Roles role) throws AdminDAOException {
		List<User> users = new ArrayList<User>();

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM" + role;
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login = rs.getString("login");
				String password = rs.getString("password");

				users.add(createUserByType(id, login, password, role));
			}

			return users;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void update(User user, Roles role) throws AdminDAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "UPDATE" + role + "SET login=?, password=? WHERE id=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public List<User> listAll() throws AdminDAOException {
		List<User> users = new ArrayList<User>();

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login = rs.getString("login");
				String password = rs.getString("password");
				Roles role = Roles.valueOf(rs.getString("role"));

				users.add(createUserByType(id, login, password, role));
			}

			return users;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}

	}

	// find by id et login a concatener
	public User findById(int id) throws AdminDAOException {
		User user = null;

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE id=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = createUserByType(
						rs.getInt("id"),
						rs.getString("login"),
						rs.getString("password"),
						Roles.valueOf(rs.getString("role")));
			}

			return user;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}

	public User findByLogin(String login) throws AdminDAOException {
		User user = null;

		try (Connection connection = DBManager.getConnection()) {
			String query = "SELECT * FROM users WHERE login=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = createUserByType(
						rs.getInt("id"),
						rs.getString("login"),
						rs.getString("password"),
						Roles.valueOf(rs.getString("role")));
			}

			return user;
		} catch (Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws AdminDAOException {
		try (Connection connection = DBManager.getConnection()) {
			String query = "DELETE FROM users WHERE id = ?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
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
				user = null;
				break;
			case comptable:
				user = null;
				break;
			case assistant_de_programme:
				user = null;
				break;
			case chef_de_classe:
				break;
			case enseignant:
				break;
			default:
				break;
		}
		return user;
	}
}
