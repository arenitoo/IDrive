package com.idrive.service;

import java.sql.ResultSet;

import com.idrive.daos.veiculoDAO;
import com.idrive.models.Veiculo;

public class veiculoService {

    private veiculoDAO veiculoDao;

    public veiculoService() {
        veiculoDao = new veiculoDAO();
    }

    public void inserir(Veiculo veiculo) {
        if (veiculo.getMarca().isEmpty() || veiculo.getModelo().isEmpty() || veiculo.getPlaca().isEmpty()) {
            return;
        }
        veiculoDao.inserir(veiculo);

    }

    public void excluir(Veiculo veiculo) {
        if (veiculo.getId() == 0) {
            return;
        }
        veiculoDao.excluir(veiculo);

    }

    public void editar(Veiculo veiculo) {
        if (veiculo.getMarca().isEmpty() || veiculo.getModelo().isEmpty() || veiculo.getPlaca().isEmpty()) {
            return;
        }
        veiculoDao.editar(veiculo);

    }

    public ResultSet quantidadeVeiculoPorMarca(Veiculo veiculo) {
        if (veiculo.getMarca().isEmpty()) {
            return null;
        }
        veiculoDao.quantidadeVeiculoPorMarca(veiculo);
        return null;
    }

    public boolean isDisponivel(int veiculoId, java.util.Date dataInicio, java.util.Date dataTermino){
        if (veiculoId <= 0) {
            throw new IllegalArgumentException();
        }
        return veiculoDao.isDisponivel(veiculoId, dataInicio, dataTermino);
    }
}
