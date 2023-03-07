package com.example.lab2_part2.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.lab2_part2.Exception.DAOException;
import com.example.lab2_part2.Model.Tenant;

/**
 * Tenant DAO implementation
 */
public class TenantDAO implements DAO<Tenant> {

    /*public TenantDAO () {
        connectionPool = ConnectionPool.getConnectionPool();
    }*/

    @Override
    public void createTable() throws DAOException {

    }

    @Override
    public void dropTable() throws DAOException {

    }

    @Override
    public ArrayList<Tenant> get(int id) throws DAOException, SQLException {
        return null;
    }

    @Override
    public ArrayList<Tenant> getAll() throws DAOException, SQLException {
        return null;
    }

    @Override
    public void insert(Tenant tenant, int id) throws DAOException {

    }

    @Override
    public void update(Tenant tenant, String[] params) throws DAOException {

    }

    @Override
    public void delete(Tenant tenant) throws DAOException {

    }
}
