package app.models;

import java.util.List;

import app.entities.Roles;
import app.exception.db.DAOException;

public interface AdminDAO <E> extends DAOImpl{
public void create(E entity) throws DAOException;
	
	public E read(int id, Roles role) throws DAOException;
	
	public List<E> list(Roles role) throws DAOException;
	
	public List<E> listAll() throws DAOException;
	
	public void update(E entity, Roles role) throws DAOException;
	
	public void delete(int id) throws DAOException;
}
