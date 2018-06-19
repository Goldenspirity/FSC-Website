package com.sdzee.daoInterfaces;

import java.util.ArrayList;

import com.sdzee.beans.User;
import com.sdzee.dao.DAOException;

public interface UserDAOInterface {

    void create ( User user ) throws DAOException;
    
    void updatePassword ( User user ) throws DAOException;
    
    void updateEmail ( User user ) throws DAOException;
    
    void updateRole ( User user ) throws DAOException;

    User findByEmail ( String email ) throws DAOException;
    
    User findByName ( String name ) throws DAOException;
    
    ArrayList<User> getAllUsers () throws DAOException;
	
}
