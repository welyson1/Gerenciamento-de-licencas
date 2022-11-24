package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.Conexao;
import model.LicencasObtidas;

public class LicencasObtidasDAO {
    private Connection connection;

    // Construtor
    public LicencasObtidasDAO() {
        // Passando conexão para variavel da classe
        this.connection = new Conexao().getConnection();
    }

    /**
     * Este metodo adiciona uma licença obtida no banco de dados
     * 
     * @param licencasObtidas objeto
     * @return Retorna true ou false sobre o status da operação
     */
    public boolean addLicensasObtidas(LicencasObtidas licencasObtidas) {
        // Query
        String sql = "INSERT into licencasobtidas (lobtida_nome, lobtida_recurso_email, lobtida_conclusao) values(?, ?, ?)";

        try {
            // Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, licencasObtidas.getLicencasObtidasTreinamentoNome());
            preparedStatement.setString(2, licencasObtidas.getLicencasObtidasRecursoEmail());
            preparedStatement.setString(3, licencasObtidas.getDataConclusao());

            // Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            // Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    
    /** 
     * @param licencasObtidas
     * @param chaveBusca
     * @return boolean
     */
    public boolean updateLicencaObtida(LicencasObtidas licencasObtidas, String chaveBusca) {
        // Query
        String sql = "UPDATE public.licencasobtidas SET lobtida_nome=?, lobtida_recurso_email=?, lobtida_conclusao=? WHERE lobtida_recurso_email=?;";

        try {
            // Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, licencasObtidas.getDataConclusao());
            preparedStatement.setString(2, licencasObtidas.getLicencasObtidasRecursoEmail());
            preparedStatement.setString(3, licencasObtidas.getLicencasObtidasTreinamentoNome());

            preparedStatement.setString(4, chaveBusca);

            // Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            // Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    /** 
     * @param licencasObtidas
     * @return boolean
     */
    public boolean excluirLicencaObtida(LicencasObtidas licencasObtidas) {
        // Query
        String sql = "DELETE FROM public.licencasobtidas WHERE lobtida_recurso_email=?;";

        try {
            // Preparando a conexão
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Passando os valores do objeto recurso para os campos do banco de dados
            preparedStatement.setString(1, licencasObtidas.getLicencasObtidasRecursoEmail());
            System.out.println(licencasObtidas.getLicencasObtidasRecursoEmail());
            // Execução da entrada de informações no banco de dados
            preparedStatement.execute();

            // Retorno se verdadeiro
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    /** 
     * @return List<LicencasObtidas>
     */
    public List<LicencasObtidas> getListLicencaObtida() {
        List<LicencasObtidas> licencasObtidas = new ArrayList<>();
        String sql = "SELECT lobtida_nome, lobtida_recurso_email, lobtida_conclusao FROM public.licencasobtidas;";
        // Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LicencasObtidas licencasObtida = new LicencasObtidas();
                licencasObtida.setLicencasObtidasTreinamentoNome(resultSet.getString("lobtida_nome"));
                licencasObtida.setLicencasObtidasRecursoEmail(resultSet.getString("lobtida_recurso_email"));
                licencasObtida.setDataConclusao(resultSet.getString("lobtida_conclusao"));
                licencasObtidas.add(licencasObtida);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro de carregamento");
            return null;
        }
        return licencasObtidas;
    }

    
    /** 
     * @param chaveBusca
     * @return LicencasObtidas
     */
    public LicencasObtidas buscaLicencasObtidas(String chaveBusca) {
        LicencasObtidas lObtidasResult = new LicencasObtidas();
        String sql = "SELECT lobtida_nome, lobtida_recurso_email, lobtida_conclusao FROM public.licencasobtidas WHERE lobtida_recurso_email=?;";
        System.out.println(chaveBusca);
        // Preparando a conexão
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chaveBusca);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lObtidasResult.setLicencasObtidasTreinamentoNome(resultSet.getString("lobtida_nome"));
                lObtidasResult.setLicencasObtidasRecursoEmail(resultSet.getString("lobtida_recurso_email"));
                lObtidasResult.setDataConclusao(resultSet.getString("lobtida_conclusao"));
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Licença Obtida");
            return null;
        }
        return lObtidasResult;
    }

}
