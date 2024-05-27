package com.idrive.service;

import com.idrive.daos.veiculoDAO;
import com.idrive.models.Veiculo;
import java.sql.SQLException;
import java.util.List;

public class veiculoService {

    private veiculoDAO daoVeiculo;

    public veiculoService(veiculoDAO daoVeiculo) {
        this.daoVeiculo = daoVeiculo;
    }

    public void addVeiculo(Veiculo veiculo) throws SQLException {
        daoVeiculo.addVeiculo(veiculo);
    }

    public Veiculo getVeiculo(int id) throws SQLException {
        return daoVeiculo.getVeiculo(id);
    }

    public List<Veiculo> getAllVeiculos() throws SQLException {
        return daoVeiculo.getAllVeiculos();
    }

    public void updateVeiculo(Veiculo veiculo) throws SQLException {
        daoVeiculo.updateVeiculo(veiculo);
    }

    public void deleteVeiculo(int id) throws SQLException {
        daoVeiculo.deleteVeiculo(id);
    }
}
