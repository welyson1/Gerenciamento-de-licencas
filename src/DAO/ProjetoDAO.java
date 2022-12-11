package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.Conexao;
import model.Projeto;

public class ProjetoDAO {
    private Connection connection;

    //Construtor
    public ProjetoDAO() {
        //Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();        
    }
 
    public boolean addProjeto(Projeto projeto) {
        //Query
        String sql = "INSERT INTO public.projeto(projeto_nome, projeto_tecnologia, projeto_valor) VALUES (?, ?, ?);";

        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto projeto para os campos do banco de dados
            preparedStatement.setString(1, projeto.getProjetoNome());
            preparedStatement.setString(2, projeto.getProjetoTecnologia());
            preparedStatement.setFloat(3, projeto.getProjetoValor());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
      
    public boolean updateProjeto(Projeto projeto, String chaveBusca) {
        //Query
        String sql = "UPDATE public.projeto SET projeto_nome=?, projeto_tecnologia=?, projeto_valor=? WHERE projeto_nome=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, projeto.getProjetoNome());
            preparedStatement.setString(2, projeto.getProjetoTecnologia());
            preparedStatement.setFloat(3, projeto.getProjetoValor());

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
       
    public boolean excluirProjeto(Projeto projeto) {
        //Query
        String sql = "DELETE FROM public.projeto WHERE projeto_nome=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, projeto.getProjetoNome());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }         
    }
       
    public List<Projeto> getListProjeto(){
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT projeto_nome, projeto_tecnologia, projeto_valor FROM public.projeto;";
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Projeto projeto = new Projeto();
                projeto.getProjetoNome(resultSet.getString("projeto_nome"));
                projeto.setProjetoTecnologia(resultSet.getString("projeto_tecnologia"));
                projeto.setProjetoValor(resultSet.getFloat("projeto_valor"));
                projetos.add(projeto);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro de carregamento nos Projetos");
            return null;
        }
        return projetos;
    }

    public Projeto buscaRecurso(String chaveBusca){
        Projeto projetoResult = new Projeto();
        String sql = "SELECT projeto_nome, projeto_tecnologia, projeto_valor FROM public.projeto WHERE projeto_nome=?;";
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chaveBusca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                projetoResult.getProjetoNome(resultSet.getString("projeto_nome"));
                projetoResult.setProjetoTecnologia(resultSet.getString("projeto_tecnologia"));
                projetoResult.setProjetoValor(resultSet.getFloat("projeto_valor"));
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar");
            return null;
        }
        return projetoResult;
    }
    
}
