package com.idrive.service;

import com.idrive.daos.locacaoDAO;
import com.idrive.models.Locacao;

import java.sql.ResultSet;

public class locacaoService {

    private locacaoDAO locacaoDao;

    public locacaoService() {
        locacaoDao = new locacaoDAO();
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
}
