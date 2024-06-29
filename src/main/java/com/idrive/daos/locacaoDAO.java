package com.idrive.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import com.idrive.models.Locacao;
import com.idrive.conf.Conexao;

public class LocacaoDAO {

    private Conexao conexao;
    private PreparedStatement ps;

    public LocacaoDAO(){
        conexao = new Conexao();
    }

    public ResultSet listar(){
        try {
            return conexao.getConn()
                    .createStatement().executeQuery("SELECT * FROM locacao");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void inserir(Locacao locacao){
        try {
            String SQL = "INSERT INTO locacao(id_cliente, id_veiculo, data_inicio, data_termino, valor_diaria, valor_total) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, locacao.getCliente().getId());
            ps.setInt(2, locacao.getVeiculo().getId());
            ps.setDate(3, new Date(locacao.getDataInicio().getTime()));
            ps.setDate(4, new Date(locacao.getDataTermino().getTime()));
            ps.setDouble(5, locacao.getValorDiaria());
            ps.setDouble(6, locacao.getValorTotal());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void excluir(Locacao locacao){
        try {
            String SQL = "DELETE FROM locacao WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, locacao.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editar(Locacao locacao){
        try {
            String SQL = "UPDATE locacao SET " +
                    "id_cliente = ?, id_veiculo = ?, data_inicio = ?, data_termino = ?, valor_diaria = ?, valor_total = ? " +
                    "WHERE id = ?";

            ps = conexao.getConn().prepareStatement(SQL);

            ps.setInt(1, locacao.getCliente().getId());
            ps.setInt(2, locacao.getVeiculo().getId());
            ps.setDate(3, new Date(locacao.getDataInicio().getTime()));
            ps.setDate(4, new Date(locacao.getDataTermino().getTime()));
            ps.setDouble(5, locacao.getValorDiaria());
            ps.setDouble(6, locacao.getValorTotal());
            ps.setInt(7, locacao.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public ResultSet getClienteByLocacao(int locacaoId) {
        try {
            String SQL = "SELECT cliente.* FROM cliente JOIN locacao ON cliente.id = locacao.id_cliente WHERE locacao.id = ?";

            ps = conexao.getConn().prepareStatement(SQL);
            ps.setInt(1, locacaoId);

            return ps.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
