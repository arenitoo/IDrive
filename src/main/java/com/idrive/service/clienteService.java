package com.idrive.service;

import com.idrive.daos.ClienteDAO;
import com.idrive.models.Cliente;

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

    public void mostrarCliente(Cliente cliente) {
        if (cliente.getId() <= 0) {
            return;
        }
        clienteDao.mostrarDadosCliente(cliente);
    }
}
