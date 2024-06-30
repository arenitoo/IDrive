package com.idrive.service;

import java.sql.ResultSet;

import com.idrive.daos.VeiculoDAO;
import com.idrive.models.Veiculo;

public class VeiculoService {

    private VeiculoDAO veiculoDao;

    public VeiculoService() {
        veiculoDao = new VeiculoDAO();
    }
    
         // Método setter para injetar o mock do VeiculoDao
    public void setVeiculoDAO(VeiculoDAO veiculoDao) {
        this.veiculoDao = veiculoDao;
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

    public boolean isDisponivel(int veiculoId){
        if (veiculoId <= 0) {
            throw new IllegalArgumentException();
        }
        return veiculoDao.isDisponivel(veiculoId);
    }
}
