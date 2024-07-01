package com.idrive.service;

import com.idrive.daos.ClienteDAO;
import com.idrive.models.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteService {

    private ClienteDAO clienteDao;

    public ClienteService() {
        clienteDao = new ClienteDAO();
    }
    
     // Método setter para injetar o mock do ClienteDao
    public void setClienteDAO(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void inserir(Cliente cliente) {
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty()) {
            return;
        }
        clienteDao.inserir(cliente);

    }

    public void excluir(Cliente cliente) {
        if (cliente.getId() <= 0) {
            return;
        }
        clienteDao.excluir(cliente);

    }

    public void editar(Cliente cliente) {
        if (cliente.getNome().isEmpty() || cliente.getCpf().isEmpty()) {
            return;
        }
        clienteDao.editar(cliente);

    }

    public Cliente getById(int id) throws SQLException {
        if (id <= 0) {
            return null; // ou lançar uma exceção, dependendo do seu caso
        }

        ResultSet rs = clienteDao.getById(id);
        if (rs.next()) {
            // Construa o objeto Cliente com os dados do ResultSet
            Cliente cliente = new Cliente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("endereco")
            );

            // Feche o ResultSet para liberar recursos
            rs.close();

            return cliente;
        } else {
            rs.close(); // Feche o ResultSet se não houver cliente encontrado
            return null; // ou lançar uma exceção, dependendo do seu caso
        }
    }
}
