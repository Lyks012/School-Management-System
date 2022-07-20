package app.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import app.entities.Administrateur;
import app.entities.User;


public class TestConnection {
	public static void main(String[] args) {
		try(Connection connection = DBManager.getConnection()){
			UserDAOImpl userDAOImpl = new UserDAOImpl();
			Administrateur a = new Administrateur(1, "test", "test");
			
			userDAOImpl.create(a);
			System.out.println("Connection OK");
			System.out.println(connection.getClass().getSimpleName());
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
