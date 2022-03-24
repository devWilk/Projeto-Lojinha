package connection;

import java.sql.*;

public class ConexaoMySQL {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/sa4e2";
    private static String USER = "root";
    private static String PASS = "123456-+";


    public static Connection iniciarConexao(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER , PASS);
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException("Erro na conexão: "+ e);
        }
    }

    public static void encerrarConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void encerrarConexao(Connection connection, PreparedStatement stmt) {
        encerrarConexao(connection);
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void encerrarConexao(Connection connection, PreparedStatement stmt, ResultSet rs) {
        encerrarConexao(connection, stmt);
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
