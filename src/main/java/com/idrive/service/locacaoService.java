package com.idrive.service;

import com.idrive.daos.LocacaoDAO;
import com.idrive.models.Locacao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocacaoService {

    private LocacaoDAO locacaoDao;

    public LocacaoService() {
        locacaoDao = new LocacaoDAO();
    }
    
    // Método setter para injetar o mock do LocacaoDao
    public void setLocacaoDao(LocacaoDAO locacaoDAO) {
        this.locacaoDao = locacaoDAO;
    }

    public void inserir(Locacao locacao) {
        if (locacao.getCliente() == null || locacao.getVeiculo() == null) {
            return;
        }
        locacaoDao.inserir(locacao);

    }

    public void excluir(Locacao locacao) {
        if (locacao.getId() == 0) {
            return;
        }
        locacaoDao.excluir(locacao);

    }

    public void editar(Locacao locacao) {
        if (locacao.getCliente() == null || locacao.getVeiculo() == null) {
            return;
        }
        locacaoDao.editar(locacao);

    }
    public ResultSet getClienteByLocacao(int locacaoId) {
        if (locacaoId <= 0) {
            throw new IllegalArgumentException();
        }
        return locacaoDao.getClienteByLocacao(locacaoId);
    }
    
        public void getById(int id) throws SQLException {
        if (id <= 0) {
            return;
        }
        locacaoDao.getById(id);
    }
}
