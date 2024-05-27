package com.idrive.daos;

import com.idrive.models.Cliente;
import com.idrive.models.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class veiculoDAO {

    private Connection connection;

    public veiculoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (id, nome, cpf, telefone, endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.executeUpdate();
        }
    }

    public Cliente getCliente(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getString("telefone"),
                            rs.getString("endereco")
                    );
                }
            }
        }
        return null;
    }

    public List<Cliente> getAllClientes() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, cpf = ?, telefone = ?, endereco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void addVeiculo(Veiculo veiculo) {
    }

    public Veiculo getVeiculo(int id) {
        return null;
    }

    public List<Veiculo> getAllVeiculos() {
        return null;
    }

    public void updateVeiculo(Veiculo veiculo) {
    }

    public void deleteVeiculo(int id) {
    }
}
