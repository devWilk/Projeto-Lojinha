package dao;

import connection.ConexaoMySQL;
import model.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Objeto que faz a conexão com banco de dados, com tabela Pagamento

public class PagamentoDAO {
    public void create(Pagamento pagamento) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO pagamento (tipoPagamento) VALUES (?)");
            stmt.setInt(1, pagamento.getTipoPagamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }

    public ArrayList<Pagamento> read(){
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Pagamento> listapagamentos = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM pagamento");
            rs = stmt.executeQuery();

            while (rs.next()){
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setTipoPagamento(rs.getInt("tipoPagamento"));
                listapagamentos.add(pagamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt, rs);
        }
        return listapagamentos;
    }

    public void update(Pagamento pagamento) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE pagamento SET tipoPagamento = ? WHERE id = ?");
            stmt.setInt(1, pagamento.getTipoPagamento());
            stmt.setInt(2, pagamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
    public void delete(Pagamento pagamento) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM pagamento WHERE id = ?");
            stmt.setInt(1, pagamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
}
