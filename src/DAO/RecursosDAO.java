package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.Conexao;
import model.Recurso;

public class RecursosDAO {
    private Connection connection;

    //Construtor
    public RecursosDAO() {
        //Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();        
    }

    /**
     * Este metodo adiciona um recurso no banco de dados
     * @param recurso objeto recurso com nome, email e projeto. Respectivamente
     * @return Retorna true ou false sobre o status da operação
     */
    public boolean addRecurso(Recurso recurso) {
        //Query
        String sql = "INSERT into recurso (recurso_nome, recurso_email, recurso_projeto) values(?, ?, ?)";

        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, recurso.getRecursoNome());
            preparedStatement.setString(2, recurso.getRecursoEmail());
            preparedStatement.setString(3, recurso.getRecursoProjeto());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    public boolean updateRecursos(Recurso recurso, String chaveBusca) {
        //Query
        String sql = "UPDATE public.recurso SET recurso_nome=?, recurso_email=?, recurso_projeto=? WHERE recurso_email=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, recurso.getRecursoNome());
            preparedStatement.setString(2, recurso.getRecursoEmail());
            preparedStatement.setString(3, recurso.getRecursoProjeto());

            preparedStatement.setString(4, chaveBusca);

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }

    public boolean excluirRecursos(Recurso recurso) {
        //Query
        String sql = "DELETE FROM public.recurso WHERE recurso_email=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, recurso.getRecursoEmail());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }         
    }

    public List<Recurso> getListRecursos(){
        List<Recurso> recursos = new ArrayList<>();
        String sql = "SELECT recurso_nome, recurso_email, recurso_projeto FROM public.recurso;";
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recurso recurso = new Recurso();
                recurso.setRecursoNome(resultSet.getString("recurso_nome"));
                recurso.setRecursoEmail(resultSet.getString("recurso_email"));
                recurso.setRecursoProjeto(resultSet.getString("recurso_projeto"));
                recursos.add(recurso);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro de carregamento");
            return null;
        }
        return recursos;
    }
}
