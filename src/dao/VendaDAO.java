package dao;

import connection.ConexaoMySQL;
import model.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Objeto que faz a conexão com banco de dados, com tabela venda

public class VendaDAO {

    public void create(Venda venda) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO venda (vlrTotal) VALUES (?)");
            stmt.setDouble(1, venda.getVlrTotal());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }

    public ArrayList<Venda> read(){
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Venda> listavendas = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM venda");
            rs = stmt.executeQuery();

            while (rs.next()){
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setVlrTotal(rs.getFloat("vlrTotal"));

                listavendas.add(venda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt, rs);
        }
        return listavendas;
    }

    public void update(Venda venda) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE venda SET vlrTotal = ? WHERE id = ?");
            stmt.setDouble(1, venda.getVlrTotal());
            stmt.setInt(2, venda.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
    public void delete(Venda venda) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM venda WHERE id = ?");
            stmt.setInt(1, venda.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
}