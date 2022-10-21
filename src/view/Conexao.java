package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Classe com as configurações iniciais do banco de dados postgres
 */
public class Conexao {

    private String url;
    private String usuario;
    private String senha;
    private Connection con;

    public Conexao() {
        url = "jdbc:postgresql://localhost:5432/postgres";
        usuario = "postgres";
        senha = "mariamole1";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executaSQL(String sqlString) {
        try {
            java.sql.Statement statement = con.createStatement();
            //Pega a quantidade de conexões
            int res = statement.executeUpdate(sqlString);
            con.close();
            //Retona a quantidade de execuções
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }   
    }

    public ResultSet consultaBancoDeDados(String sql) {
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            con.close();
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }
}

/**
 * Tasks:
 *  Fazer uma coluna ID para as tabelas.
 *  Criar as tabelas
 *  Setar o ID como primaryKey
 */
