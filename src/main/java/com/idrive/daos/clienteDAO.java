package com.idrive.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.idrive.models.Cliente;
import com.idrive.conf.Conexao;


public class clienteDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public clienteDAO(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM cliente");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void inserir(Cliente cliente){
        try {
            String SQL = "INSERT INTO cliente(nome, cpf, telefone, endereco) " +
                    "VALUES (?, ?, ?, ?)";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void excluir(Cliente cliente){
        try {
            String SQL = "DELETE FROM cliente WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, cliente.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Cliente cliente){
        try {
            String SQL = "UPDATE cliente SET " +
                    "nome= ?, cpf= ?, telefone= ?, endereco= ? " +
                    "WHERE id=?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());
            ps.setInt(5, cliente.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet mostrarDadosCliente(Cliente cliente) {
        try {
            String SQL = "SELECT * FROM cliente WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);
            ps.setInt(1, cliente.getId());

            ResultSet rs = ps.executeQuery();

            ps.close();

            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}