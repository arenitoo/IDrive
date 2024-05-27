package com.idrive.daos;

import com.idrive.models.Cliente;
import com.idrive.models.Locacao;
import com.idrive.models.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class locacaoDAO {

    private Connection connection;

    public locacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addLocacao(Locacao locacao) throws SQLException {
        String sql = "INSERT INTO locacoes (id, cliente_id, veiculo_id, data_inicio, data_termino, valor_diaria, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getId());
            stmt.setInt(2, locacao.getCliente().getId());
            stmt.setInt(3, locacao.getVeiculo().getId());
            stmt.setDate(4, new java.sql.Date(locacao.getDataInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(locacao.getDataTermino().getTime()));
            stmt.setDouble(6, locacao.getValorDiaria());
            stmt.setDouble(7, locacao.getValorTotal());
            stmt.executeUpdate();
        }
    }

    public Locacao getLocacao(int id) throws SQLException {
        String sql = "SELECT * FROM locacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = getClienteById(rs.getInt("cliente_id"));
                    Veiculo veiculo = getVeiculoById(rs.getInt("veiculo_id"));
                    return new Locacao(
                            rs.getInt("id"),
                            cliente,
                            veiculo,
                            rs.getDate("data_inicio"),
                            rs.getDate("data_termino"),
                            rs.getDouble("valor_diaria"),
                            rs.getDouble("valor_total")
                    );
                }
            }
        }
        return null;
    }

    public List<Locacao> getAllLocacoes() throws SQLException {
        String sql = "SELECT * FROM locacoes";
        List<Locacao> locacoes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = getClienteById(rs.getInt("cliente_id"));
                Veiculo veiculo = getVeiculoById(rs.getInt("veiculo_id"));
                Locacao locacao = new Locacao(
                        rs.getInt("id"),
                        cliente,
                        veiculo,
                        rs.getDate("data_inicio"),
                        rs.getDate("data_termino"),
                        rs.getDouble("valor_diaria"),
                        rs.getDouble("valor_total")
                );
                locacoes.add(locacao);
            }
        }
        return locacoes;
    }

    public void updateLocacao(Locacao locacao) throws SQLException {
        String sql = "UPDATE locacoes SET cliente_id = ?, veiculo_id = ?, data_inicio = ?, data_termino = ?, valor_diaria = ?, valor_total = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getCliente().getId());
            stmt.setInt(2, locacao.getVeiculo().getId());
            stmt.setDate(3, new java.sql.Date(locacao.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(locacao.getDataTermino().getTime()));
            stmt.setDouble(5, locacao.getValorDiaria());
            stmt.setDouble(6, locacao.getValorTotal());
            stmt.setInt(7, locacao.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteLocacao(int id) throws SQLException {
        String sql = "DELETE FROM locacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Cliente getClienteById(int clienteId) throws SQLException {

        return null;
    }

    private Veiculo getVeiculoById(int veiculoId) throws SQLException {

        return null;
    }
}
