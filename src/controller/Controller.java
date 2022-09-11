package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.ManipuladorArquivo;

public class Controller {

    @FXML
    private Button btnCadastrarProjeto;
    @FXML
    private Button btnCadastrarRecurso;
    @FXML
    private Button btnCadastroLicencaNecessaria;
    @FXML
    private Button btnCadastroLicencaObtida;
    @FXML
    private Button btnConsultaLicencaNecessaria;
    @FXML
    private Button btnConsultaLicencaObtida;
    @FXML
    private Button btnConsultaProjetos;
    @FXML
    private Button btnConsultaRecursos;
    @FXML
    private TextField entradaEmail;
    @FXML
    private TextField entradaEmailRecursoLicencaObtida;
    @FXML
    private TextField entradaNome;
    @FXML
    private TextField entradaNomeLicencaNecessaria;
    @FXML
    private TextField entradaNomeLicencaObtida;
    @FXML
    private TextField entradaNomeProjeto;
    @FXML
    private TextField entradaProjeto;
    @FXML
    private TextField entradaTecnologia;
    @FXML
    private TextField entradaValor;
    @FXML
    private TextField entrataCategoriaLicencaNecessaria;
    @FXML
    private TextField entrataLevelLicencaNecessaria;
    @FXML
    private TextField entrataLinkLicencaNecessaria;
    @FXML
    private TextArea txtAreaConsulta;
    @FXML
    private TextArea txtAreaConsultaLicencaNecessaria;
    @FXML
    private TextArea txtAreaConsultaLicencaObtida;
    @FXML
    private TextArea txtAreaConsultaProjetos;

//-------------------------------------------------------------

    ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();
    String caminhoDataRecursos = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/Recursos.txt";
    String caminhoDataLicencasObtidas = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/LicencasObtidas.txt";
    String caminhoDataLicencasNecessarias = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/LicencasNecessarias.txt";
    String caminhoDataProjetos = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/Projetos.txt";
    
//-------------------------------------------------------------  

    @FXML
    void cadastrarRecurso(ActionEvent event) throws FileNotFoundException, IOException {        
        String entradaInfos = 
            entradaNome.getText() + ";" 
            + entradaEmail.getText() + ";" 
            + entradaProjeto.getText();
        manipuladorArquivo.manipuladorEscrita(entradaInfos, caminhoDataRecursos);
    }
    @FXML
    void consultaRecursos(ActionEvent event) {
        //Declaração do array para guardar as informações
        String infoArrayExterno[] = null;  

        //Atribuição da saida do medodo manipuladorLeitura para o array
        infoArrayExterno = manipuladorArquivo.manipuladorLeitura(caminhoDataRecursos);

        /*
         * Corre o array pegando a linha e separando no delimitador
         * Exemplo: welyson;welyson@gmail.com;CALRO RAN
         * Para:
         *  welyson
         *  welyson@gmail.com
         *  CLARO RAN
         * Depois exibie no textArea
         */
        for (String item : infoArrayExterno) {

            StringTokenizer Linguicao = 
                new StringTokenizer(item, ";");
            while (Linguicao.hasMoreTokens()){ //Executa enquanto tiver Tokens
                txtAreaConsulta.appendText("\n" + Linguicao.nextToken()); //Acrescenta no textArea
            } 
            
            //Perfumaria
            txtAreaConsulta.appendText("\n");
            txtAreaConsulta.appendText("----------------");          
        }
    }
    
//-------------------------------------------------------------

    @FXML
    void cadastrarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        String entradaInfos = 
            entradaNomeProjeto.getText() + ";"
            + entradaTecnologia.getText() + ";" 
            + entradaValor.getText();
        manipuladorArquivo.manipuladorEscrita(entradaInfos, caminhoDataProjetos);
    }
    @FXML
    void consultaProjetos(ActionEvent event) {
        String infoArrayExterno[] = null;    
        infoArrayExterno = manipuladorArquivo.manipuladorLeitura(caminhoDataProjetos);
        for (String item : infoArrayExterno) {            
            StringTokenizer Linguicao = 
                new StringTokenizer(item, ";");
            while (Linguicao.hasMoreTokens()){
                txtAreaConsultaProjetos.appendText("\n" + Linguicao.nextToken());
            }  
            txtAreaConsultaProjetos.appendText("\n");
            txtAreaConsultaProjetos.appendText("----------------");          
        }
    }

//-------------------------------------------------------------

    @FXML
    void cadastroLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        String entradaInfos = 
            entradaNomeLicencaNecessaria.getText() + ";" 
            + entrataLinkLicencaNecessaria.getText() + ";" 
            + entrataCategoriaLicencaNecessaria.getText() + ";" 
            + entrataLevelLicencaNecessaria.getText();
        manipuladorArquivo.manipuladorEscrita(entradaInfos, caminhoDataLicencasNecessarias);
    }
    @FXML
    void consultaLicencaNecessaria(ActionEvent event) {
        String infoArrayExterno[] = null;    
        infoArrayExterno = manipuladorArquivo.manipuladorLeitura(caminhoDataLicencasNecessarias);
        for (String item : infoArrayExterno) {            
            StringTokenizer Linguicao = 
                new StringTokenizer(item, ";");
            while (Linguicao.hasMoreTokens()){
                txtAreaConsultaLicencaNecessaria.appendText("\n" + Linguicao.nextToken());
            }  
            txtAreaConsultaLicencaNecessaria.appendText("\n");
            txtAreaConsultaLicencaNecessaria.appendText("----------------");          
        }
    } 

//-------------------------------------------------------------

    @FXML
    void cadastroLicencaObtida(ActionEvent event) throws FileNotFoundException, IOException {
        String entradaInfos = 
            entradaEmailRecursoLicencaObtida.getText() + ";" 
            + entradaNomeLicencaObtida.getText();

        manipuladorArquivo.manipuladorEscrita(entradaInfos, caminhoDataLicencasObtidas);
    }
    @FXML
    void consultaLicencaObtida(ActionEvent event) {
        String infoArrayExterno[] = null;    
        infoArrayExterno = manipuladorArquivo.manipuladorLeitura(caminhoDataLicencasObtidas);
        for (String item : infoArrayExterno) {            
            StringTokenizer Linguicao = 
                new StringTokenizer(item, ";");
            while (Linguicao.hasMoreTokens()){
                txtAreaConsultaLicencaObtida.appendText("\n" + Linguicao.nextToken());
            }  
            txtAreaConsultaLicencaObtida.appendText("\n");
            txtAreaConsultaLicencaObtida.appendText("----------------");          
        }
    } 
     
}