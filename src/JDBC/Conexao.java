package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe com as configurações iniciais do banco de dados postgres
 */
public class Conexao {

    public Connection getConnection(){        
        try {
            //Variaveis com informações do banco de dados
            String url = "jdbc:postgresql://localhost:5432/";
            String usuario = "postgres";
            String senha = "mariamole1";

            //Log de conexão
            System.out.println("Conexão funcionando");

            //Retorno do link formado
            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }         
    }
}