package app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import app.entities.Roles;
import app.exception.db.AdminDAOException;

public class AuthService implements DAOImpl {
	public boolean login(Roles role, String login, String password) throws Exception {
		try(Connection connection = DBManager.getConnection()){
			String query = "SELECT * FROM users WHERE login= ? AND password = ?";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.first() && Roles.valueOf(rs.getString("role")).compareTo(role) == 0) {
				return true;
			}
			return false;
			
		} catch(Exception e) {
			throw new AdminDAOException(e.getClass() + ":" + e.getMessage());
		}
	}
}