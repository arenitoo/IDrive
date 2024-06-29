package com.idrive.service;

import com.idrive.daos.clienteDAO;
import com.idrive.models.Cliente;

public class clienteService {

    private clienteDAO clienteDao;

    public clienteService() {
        clienteDao = new clienteDAO();
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
