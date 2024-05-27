package com.idrive.service;

import com.idrive.daos.clienteDAO;
import com.idrive.models.Cliente;

import com.idrive.daos.locacaoDAO;
import com.idrive.models.Locacao;

import java.sql.SQLException;
import java.util.List;

public class locacaoService {

    private locacaoDAO daoLocacao;

    public locacaoService(locacaoDAO daoLocacao) {
        this.daoLocacao = daoLocacao;
    }

    public void addLocacao(Locacao locacao) throws SQLException {
        daoLocacao.addLocacao(locacao);
    }

    public Locacao getLocacao(int id) throws SQLException {
        return daoLocacao.getLocacao(id);
    }

    public List<Locacao> getAllLocacoes() throws SQLException {
        return daoLocacao.getAllLocacoes();
    }

    public void updateLocacao(Locacao locacao) throws SQLException {
        daoLocacao.updateLocacao(locacao);
    }

    public void deleteLocacao(int id) throws SQLException {
        daoLocacao.deleteLocacao(id);
    }
}
