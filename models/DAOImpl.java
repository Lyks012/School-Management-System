package app.models;

import java.util.List;

import app.exception.db.DAOException;

public interface DAOImpl<T>  {
    T getOneById(int id) throws DAOException;
    
    List<T> getAll() throws DAOException;
    
    void create(T t) throws DAOException;
    
    void update(T t) throws DAOException;
    
    void delete(T t) throws DAOException;
}
