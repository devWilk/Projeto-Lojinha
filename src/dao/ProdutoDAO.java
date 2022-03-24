package dao;

import connection.ConexaoMySQL;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Objeto que faz a conexão com banco de dados, com tabela Produto

public class ProdutoDAO {

    public void create(Produto produto) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO produto (nome, preco, quantidadeEstoque) VALUES (?,?,?)");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }

    public ArrayList<Produto> read(){
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> listaprodutos = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()){
                model.Produto produto = new model.Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                listaprodutos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt, rs);
        }
        return listaprodutos;
    }

    public void update(Produto produto) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("UPDATE produto SET nome = ?, preco = ?, quantidadeEstoque = ? WHERE id = ?");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
    public void delete(Produto produto) {
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setInt(1, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt);
        }
    }
    public Produto selecionarProduto(Produto produto){
        Connection connection = ConexaoMySQL.iniciarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> listaprodutos = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM produto WHERE id = ?");
            stmt.setInt(1, produto.getId());
            rs = stmt.executeQuery();

            while (rs.next()){
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                listaprodutos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoMySQL.encerrarConexao(connection, stmt, rs);
        }
        return produto;
    }
}

