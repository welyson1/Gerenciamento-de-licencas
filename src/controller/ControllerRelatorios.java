package controller;

import java.sql.Connection;
import java.util.Map;

import JDBC.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * A classe ControllerRelatorios controla a exibição dos relatórios Jasper
 */
public class ControllerRelatorios {
    /**
     * O metódo exibirRelatorioSimples controi o relatorio Jasper para exibir para o usuário
     * @param nomeRelatorio nome do arquivo .jasper sem a extensão
     */
    public void exibirRelatorioSimples(String nomeRelatorio) {
        Conexao conexao = new Conexao();
        try {
            JasperPrint print = JasperFillManager.fillReport(
                "src/relatorios/"+nomeRelatorio+".jasper",
                null,
                (Connection) conexao.getConnection()
            );

            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * O método exibirRelatorioParametros constroi o relatorio Jasper com a passagem de parametros
     * @param nomeRelatorio nome do arquivo .jasper sem a extensão
     * @param parametros parametros para construção do relatorio personalizado pelo usuário
     */
    public void exibirRelatorioParametros(String nomeRelatorio, Map parametros) {
        Conexao conexao = new Conexao();
        try {
            JasperPrint print = JasperFillManager.fillReport(
                "src/relatorios/"+nomeRelatorio+".jasper",
                parametros,
                (Connection) conexao.getConnection()
            );

            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    } 
}
