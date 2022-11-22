package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBC.Conexao;
import model.LicencasNecessarias;

public class LicencasNecessariasDAO {

    private Connection connection;

    //Construtor
    public LicencasNecessariasDAO() {
        //Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();        
    }

    /**
     * Este metodo adiciona uma licença necessaria no banco de dados
     * @param licencasNecessarias
     * @return Retorna true ou false sobre o status da operação
     */
    public boolean addLicensasNecessarias(LicencasNecessarias licencasNecessarias) {
        //Query
        String sql = "INSERT into licencasnecessarias (lnecessaria_nome, lnecessaria_link, lnecessaria_categoria, lnecessaria_level) values(?, ?, ?, ?)";

        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto licenças Necessarias para os campos do banco de dados
            preparedStatement.setString(1, licencasNecessarias.getTreinamentoCategoria());
            preparedStatement.setString(2, licencasNecessarias.getTreinamentoLevel());
            preparedStatement.setString(3, licencasNecessarias.getTreinamentoLink());
            preparedStatement.setString(4, licencasNecessarias.getTreinamentoNome());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }      
        
    }
    
}
