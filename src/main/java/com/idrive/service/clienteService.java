package com.idrive.service;

import com.idrive.daos.clienteDAO;
import com.idrive.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public class clienteService {

    private clienteDAO daoCliente;

    public clienteService(clienteDAO daoCliente) {
        this.daoCliente = daoCliente;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        daoCliente.addCliente(cliente);
    }

    public Cliente getCliente(int id) throws SQLException {
        return daoCliente.getCliente(id);
    }

    public List<Cliente> getAllClientes() throws SQLException {
        return daoCliente.getAllClientes();
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        daoCliente.updateCliente(cliente);
    }

    public void deleteCliente(int id) throws SQLException {
        daoCliente.deleteCliente(id);
    }
}
