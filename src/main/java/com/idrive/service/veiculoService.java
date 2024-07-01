package com.idrive.service;

import java.sql.ResultSet;

import com.idrive.daos.VeiculoDAO;
import com.idrive.models.Veiculo;
import java.sql.SQLException;
import java.util.Date;

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

    public boolean isDisponivel(int veiculoId, Date dataInicio, Date dataFim){
        if (veiculoId <= 0) {
            throw new IllegalArgumentException();
        }
        return veiculoDao.isDisponivel(veiculoId, dataInicio, dataFim);
    }
    
    public Veiculo getById(int id) throws SQLException {
        if (id <= 0) {
            return null; // ou lançar uma exceção, dependendo do seu caso
        }

        ResultSet rs = veiculoDao.getById(id);
        if (rs.next()) {
            // Construa o objeto Veiculo com os dados do ResultSet
            Veiculo veiculo = new Veiculo(
                rs.getInt("id"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getInt("ano"),
                rs.getString("placa"),
                rs.getBoolean("disponibilidade")
            );
            // Feche o ResultSet para liberar recursos
            rs.close();

            return veiculo;
        } else {
            rs.close(); // Feche o ResultSet se não houver veículo encontrado
            return null; // ou lançar uma exceção, dependendo do seu caso
        }
    }
}
