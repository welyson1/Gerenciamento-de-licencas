package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBC.Conexao;
import model.LicencasObtidas;

public class LicencasObtidasDAO {
    private Connection connection;

    //Construtor
    public LicencasObtidasDAO() {
        //Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();        
    }

    /**
     * Este metodo adiciona uma licença obtida no banco de dados
     * @param licencasObtidas objeto
     * @return Retorna true ou false sobre o status da operação
     */
    public boolean addLicensasObtidas(LicencasObtidas licencasObtidas) {
        //Query
        String sql = "INSERT into licencasobtidas (lobtida_nome, lobtida_recurso_email, lobtida_conclusao) values(?, ?, ?)";

        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, licencasObtidas.getLicencasObtidasTreinamentoNome());
            preparedStatement.setString(2, licencasObtidas.getLicencasObtidasRecursoEmail());
            preparedStatement.setString(3, licencasObtidas.getDataConclusao());

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
