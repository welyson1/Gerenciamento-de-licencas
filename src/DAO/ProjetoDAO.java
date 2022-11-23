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
        String sql = "INSERT INTO public.projeto(projeto_nome, projeto_tecnologia, projeto_valor)VALUES (?, ?, ?);";

        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto projeto para os campos do banco de dados
            preparedStatement.setString(1, projeto.setProjetoNome());
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
      
    public boolean updateRecursos(Projeto projeto, String chaveBusca) {
        //Query
        String sql = "UPDATE public.projeto SET projeto_nome=?, projeto_tecnologia=?, projeto_valor=? WHERE projeto_nome=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, projeto.setProjetoNome());
            preparedStatement.setString(2, projeto.getProjetoTecnologia());
            preparedStatement.setString(3, projeto.getProjetoValor());

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
       
    public boolean excluirRecursos(Projeto projeto) {
        //Query
        String sql = "DELETE FROM public.recurso WHERE recurso_email=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, projeto.setProjetoNome());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }         
    }
       
    public List<Projeto> getListRecursos(){
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT recurso_nome, recurso_email, recurso_projeto FROM public.recurso;";
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Projeto projeto = new Projeto();
                projeto.setProjetoNome(resultSet.getString("recurso_nome"));
                projeto.setProjetoTecnologia(resultSet.getString("recurso_email"));
                projeto.setProjetoValor(resultSet.getString("recurso_projeto"));
                projetos.add(projeto);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro de carregamento");
            return null;
        }
        return projetos;
    }

    public Projeto buscaRecurso(String chaveBusca){
        Projeto projetoResult = new Projeto();
        String sql = "SELECT recurso_nome, recurso_email, recurso_projeto FROM public.recurso WHERE recurso_nome=?;";
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chaveBusca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                projetoResult.setProjetoNome(resultSet.getString("recurso_nome"));
                projetoResult.setProjetoTecnologia(resultSet.getString("recurso_email"));
                projetoResult.setProjetoValor(resultSet.getString("recurso_projeto"));
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
