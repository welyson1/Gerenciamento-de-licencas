package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBC.Conexao;
import model.Projeto;

public class ProjetoDAO {
    private Connection connection;

    //Construtor
    public ProjetoDAO() {
        //Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();        
    }

    /**
     * Este metodo adiciona um projeto no banco de dados
     * @param projeto objeto
     * @return Retorna true ou false sobre o status da operação
     */
    public boolean addProjeto(Projeto projeto) {
        //Query
        String sql = "INSERT into projeto (projeto_nome, projeto_tecnologia, projeto_valor) values(?, ?, ?)";

        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, projeto.getProjetoNome());
            preparedStatement.setString(2, projeto.getProjetoTecnologia());
            preparedStatement.setString(3, projeto.getProjetoValor());

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
