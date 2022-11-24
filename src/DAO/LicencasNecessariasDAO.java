package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.Conexao;
import model.LicencasNecessarias;

public class LicencasNecessariasDAO {

    private Connection connection;

    //Construtor
    public LicencasNecessariasDAO() {
        //Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();        
    }

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
   
    public boolean updateLicensasNecessarias(LicencasNecessarias licencasNecessarias, String chaveBusca) {
        //Query
        String sql = "UPDATE public.licencasnecessarias SET lnecessaria_nome=?, lnecessaria_link=?, lnecessaria_categoria=?, lnecessaria_level=? WHERE lnecessaria_nome=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, licencasNecessarias.getTreinamentoNome());
            preparedStatement.setString(2, licencasNecessarias.getTreinamentoLink());
            preparedStatement.setString(3, licencasNecessarias.getTreinamentoCategoria());
            preparedStatement.setString(4, licencasNecessarias.getTreinamentoLevel());

            preparedStatement.setString(5, chaveBusca);

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
       
    public boolean excluirLicensasNecessarias(LicencasNecessarias licencasNecessarias) {
        //Query
        String sql = "DELETE FROM public.licencasnecessarias WHERE lnecessaria_nome=?;";
        
        try {
            //Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //Passando os valores do objeto para os campos do banco de dados
            preparedStatement.setString(1, licencasNecessarias.getTreinamentoNome());

            //Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            //Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }         
    }
      
    public List<LicencasNecessarias> getListLicencasNecessarias(){
        List<LicencasNecessarias> licencasNecessarias = new ArrayList<>();
        String sql = "SELECT lnecessaria_nome, lnecessaria_link, lnecessaria_categoria, lnecessaria_level FROM public.licencasnecessarias;";
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LicencasNecessarias licencasNecessaria = new LicencasNecessarias();
                licencasNecessaria.setTreinamentoNome(resultSet.getString("lnecessaria_nome"));
                licencasNecessaria.setTreinamentoLink(resultSet.getString("lnecessaria_link"));
                licencasNecessaria.setTreinamentoCategoria(resultSet.getString("lnecessaria_categoria"));
                licencasNecessaria.setTreinamentoLevel(resultSet.getString("lnecessaria_level"));
                licencasNecessarias.add(licencasNecessaria);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro de carregamento");
            return null;
        }
        return licencasNecessarias;
    }

    public LicencasNecessarias buscaLicensasNecessarias(String chaveBusca){
        LicencasNecessarias lNecessariaResult = new LicencasNecessarias();
        String sql = "SELECT lnecessaria_nome, lnecessaria_link, lnecessaria_categoria, lnecessaria_level FROM public.licencasnecessarias WHERE lnecessaria_nome=?;";
        System.out.println(chaveBusca);
        //Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chaveBusca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lNecessariaResult.setTreinamentoNome(resultSet.getString("lnecessaria_nome"));
                lNecessariaResult.setTreinamentoLink(resultSet.getString("lnecessaria_link"));
                lNecessariaResult.setTreinamentoCategoria(resultSet.getString("lnecessaria_categoria"));
                lNecessariaResult.setTreinamentoLevel(resultSet.getString("lnecessaria_level"));
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar");
            return null;
        }
        return lNecessariaResult;
    }
    
}
