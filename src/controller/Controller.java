package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.print.DocFlavor.STRING;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LicencasNecessarias;
import model.LicencasObtidas;
import model.ManipuladorArquivo;
import model.Projeto;
import model.Recurso;

public class Controller implements Initializable{

    @FXML
    private Button btnCadastrarProjeto;
    @FXML
    private Button btnCadastrarRecurso;
    @FXML
    private Button btnCadastroLicencaNecessaria;
    @FXML
    private Button btnCadastroLicencaObtida;
    @FXML
    private Button btnExcluirRecursos;
    @FXML
    private Button btnExcluirRecursos1;
    @FXML
    private Button btnExcluirRecursos2;
    @FXML
    private Button btnExcluirRecursos21;
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
    private TableColumn<LicencasObtidas, String> tableViewColunaEmailLicencasObtidas;
    @FXML
    private TableColumn<LicencasObtidas, String> tableViewColunaLicencaLicencasObtidas;
    @FXML
    private TableColumn<LicencasObtidas, String> tableViewColunaDataLicencaObtida;
    @FXML
    private TextField entradaDataLicencaObtida;
    @FXML
    private TableColumn<LicencasNecessarias, String> tableViewColunaLinkLicencasNecessarias;
    @FXML
    private TableColumn<LicencasNecessarias, String> tableViewColunaNomeLicencasNecessarias;
    @FXML
    private TableColumn<Projeto, String> tableViewColunaNomeProjeto;
    @FXML
    private TableColumn<Projeto, String> tableViewColunaTecnologiaProjeto;
    @FXML
    private TableView<LicencasNecessarias> tableViewLicencaNecessarias;
    @FXML
    private TableView<LicencasObtidas> tableViewLicencasObtidas;
    @FXML
    private TableView<Projeto> tableViewProjeto;
    @FXML
    private TableColumn<LicencasNecessarias, String> tableviewColunaCategoriaLicencasNecessarias;
    @FXML
    private TableColumn<LicencasNecessarias, String> tableviewColunaLevelLicencasNecessarias;
    @FXML
    private TableColumn<Projeto, String> tableviewColunaValorProjeto;

    @FXML
    private TableColumn<Recurso, String> tableViewColunaEmail;
    @FXML
    private TableColumn<Recurso, String> tableViewColunaNome;
    @FXML
    private TableView<Recurso> tableViewRecursos;
    @FXML
    private TableColumn<Recurso, String> tableviewColunaProjeto;

//-------------------------------------------------------------

    ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();   
    
    String caminhoDataRecursos = "C:/Users/wcarlos/Documents/GitHub/atividadePratica01/dataBase/Recursos.txt";
    String caminhoDataLicencasObtidas = "C:/Users/wcarlos/Documents/GitHub/atividadePratica01/dataBase/LicencasObtidas.txt";
    String caminhoDataLicencasNecessarias = "C:/Users/wcarlos/Documents/GitHub/atividadePratica01/dataBase/LicencasNecessarias.txt";
    String caminhoDataProjetos = "C:/Users/wcarlos/Documents/GitHub/atividadePratica01/dataBase/Projetos.txt";
    /*
    String caminhoDataRecursos = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/Recursos.txt";
    String caminhoDataLicencasObtidas = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/LicencasObtidas.txt";
    String caminhoDataLicencasNecessarias = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/LicencasNecessarias.txt";
    String caminhoDataProjetos = "C:/Users/404/Documents/jAVA/atividadePratica01/dataBase/Projetos.txt";
    */
//-------------------------------------------------------------  

    //Declaração de arrayList
    private List<Recurso> listRecursos = new ArrayList<Recurso>();
    private List<LicencasObtidas> listLicencasObtidas = new ArrayList<LicencasObtidas>();
    private List<Projeto> listProjetos = new ArrayList<Projeto>();
    private List<LicencasNecessarias> listLicencasNecessarias = new ArrayList<LicencasNecessarias>();    
    
    //Declaração do observador
    private ObservableList<Recurso> observableList;
    private ObservableList<LicencasObtidas> observableListLicencasObtidas;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        //Observador de seleção do item na lista
        tableViewRecursos.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> recursoSelecionado(newValue));

        tableViewLicencasObtidas.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> recursoSelecionado(newValue));

        //Metodo chamado para iniciar com a lista carregada as informações do arquivo .txt        
        carregarRecursoNoArray();
        carregarRecursoNoArrayLicencasObtidas();
    }
//======================Recurso================================
//-------------------------------------------------------------    
    @FXML
    void cadastrarRecurso(ActionEvent event) throws FileNotFoundException, IOException {         
        //Instancia do recurso
        Recurso recurso = new Recurso(entradaNome.getText(), entradaEmail.getText(), entradaProjeto.getText());
 
        //Adição no arrayList
        listRecursos.add(recurso);
        tableViewRecursos();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaRecursos(listRecursos, caminhoDataRecursos);     
    }
    //Metodo exclui o item e salva em um arquivo .txt
    @FXML
    public void excluirRecurso() throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        Recurso recursoRemover = tableViewRecursos.getSelectionModel().getSelectedItem();
        //Log
        System.out.println("Recurso removido" + recursoRemover.getRecursoNome());
        //Remoção do recurso
        tableViewRecursos.getItems().remove(recursoRemover);
        //Remoção do ArrayList
        listRecursos.remove(recursoRemover);
        //Atualização da lista no arquivo
        manipuladorArquivo.manipuladorEscritaRecursos(listRecursos, caminhoDataRecursos);   
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarNoAquivo(ActionEvent event) throws FileNotFoundException, IOException {
        manipuladorArquivo.manipuladorEscritaRecursos(listRecursos, caminhoDataRecursos);
    }
    //Metodo de log para saber a seleção do cliente
    public void recursoSelecionado(Recurso recurso) {
        System.out.println("Seleção: " + recurso.getRecursoNome());
    }
    //Fazer um metodo que carrega os itens do .txt para a lista assim que abrir o software
    public void carregarRecursoNoArray() {
        //Declaração da lista provisoria para armazenar as strings brutas concatenadas
        List<String> listRecursosArquivo = new ArrayList<String>(); 

        //Retorno um arrayList  de Strings com a informação concatenada com as informações a partir da leitura
        listRecursosArquivo = manipuladorArquivo.manipuladorLeitura(caminhoDataRecursos);

        //Intera para cada item do arrayList
        for (String item : listRecursosArquivo) {
            //Declaração do array para guardar as informações
            Recurso recurso = new Recurso();

            //Define o delimitador
            StringTokenizer Linguicao = new StringTokenizer(item, ";");
            
            while (Linguicao.hasMoreTokens()){ //Executa enquanto tiver Tokens                
                recurso.setRecursoNome(Linguicao.nextToken());
                recurso.setRecursoEmail(Linguicao.nextToken());
                recurso.setRecursoProjeto(Linguicao.nextToken());                
            } 
            
            listRecursos.add(recurso);

            //Associa as variaveis nas colunas do tableView
            tableViewRecursos();
        }
    }
    //Metodo que faz coisas da tableView
    public void tableViewRecursos() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNome.setCellValueFactory(new PropertyValueFactory<>("recursoNome"));
        tableViewColunaEmail.setCellValueFactory(new PropertyValueFactory<>("recursoEmail"));
        tableviewColunaProjeto.setCellValueFactory(new PropertyValueFactory<>("recursoProjeto"));           
        
        observableList = FXCollections.observableArrayList(listRecursos);

        //Coloca os itens na lista
        tableViewRecursos.setItems(observableList); 
    }
//======================Recurso================================
//-------------------------------------------------------------

//======================Licenças necessarias===================
//-------------------------------------------------------------
    @FXML
    void cadastroLicencaObtida(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        LicencasObtidas licencasObtidas = new LicencasObtidas(entradaEmailRecursoLicencaObtida.getText(),entradaNomeLicencaObtida.getText(),entradaDataLicencaObtida.getText());
 
        //Adição no arrayList
        listLicencasObtidas.add(licencasObtidas);
        tableViewLicencasObtidas();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas);     
    }
    //Metodo exclui o item e salva em um arquivo .txt
    @FXML
    void excluirLicencaObtida(ActionEvent event) throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        LicencasObtidas licencasObtidasRemover = tableViewLicencasObtidas.getSelectionModel().getSelectedItem();
        //Log
        System.out.println("Recurso removido" + licencasObtidasRemover.getLicencasObtidasTreinamentoNome());
        //Remoção do recurso
        tableViewLicencasObtidas.getItems().remove(licencasObtidasRemover);
        //Remoção do ArrayList
        listLicencasObtidas.remove(licencasObtidasRemover);
        //Atualização da lista no arquivo
        manipuladorArquivo.manipuladorEscritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas);   
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarNoAquivoLicencasObtidas(ActionEvent event) throws FileNotFoundException, IOException {
        manipuladorArquivo.manipuladorEscritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas); 
    }
    //Metodo de log para saber a seleção do cliente
    public void recursoSelecionado(LicencasObtidas licencasObtidas) {
        System.out.println("Seleção: " + licencasObtidas.getLicencasObtidasTreinamentoNome());
    }
    //Fazer um metodo que carrega os itens do .txt para a lista assim que abrir o software
    public void carregarRecursoNoArrayLicencasObtidas() {
        //Declaração da lista provisoria para armazenar as strings brutas concatenadas
        List<String> listLicencasObtidasArquivo = new ArrayList<String>(); 

        //Retorno um arrayList  de Strings com a informação concatenada com as informações a partir da leitura
        listLicencasObtidasArquivo = manipuladorArquivo.manipuladorLeitura(caminhoDataLicencasObtidas);

        //Intera para cada item do arrayList
        for (String item : listLicencasObtidasArquivo) {
            //Declaração do array para guardar as informações
            LicencasObtidas licencasObtidas = new LicencasObtidas();

            //Define o delimitador
            StringTokenizer Linguicao = new StringTokenizer(item, ";");
            
            while (Linguicao.hasMoreTokens()){ //Executa enquanto tiver Tokens                
                licencasObtidas.setDataConclusao(Linguicao.nextToken());
                licencasObtidas.setLicencasObtidasRecursoEmail(Linguicao.nextToken());
                licencasObtidas.setLicencasObtidasTreinamentoNome(Linguicao.nextToken());                
            } 
            
            listLicencasObtidas.add(licencasObtidas);

            //Associa as variaveis nas colunas do tableView
            tableViewLicencasObtidas();
        }
    }
    //Metodo que faz coisas da tableView
    public void tableViewLicencasObtidas() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaEmailLicencasObtidas.setCellValueFactory(new PropertyValueFactory<>("licencasObtidasRecursoEmail"));          
        tableViewColunaLicencaLicencasObtidas.setCellValueFactory(new PropertyValueFactory<>("licencasObtidasRecursoEmail"));          
        tableViewColunaDataLicencaObtida.setCellValueFactory(new PropertyValueFactory<>("dataConclusao")); 

        observableListLicencasObtidas = FXCollections.observableArrayList(listLicencasObtidas);

        //Coloca os itens na lista
        tableViewLicencasObtidas.setItems(observableListLicencasObtidas); 
    }
//======================Licenças necessarias===================
//-------------------------------------------------------------

    @FXML
    void cadastrarProjeto(ActionEvent event) {

    }
    @FXML
    void excluirProjeto(ActionEvent event) {

    }
    @FXML
    void salvarNoAquivoProjeto(ActionEvent event) {

    }


    @FXML
    void cadastroLicencaNecessaria(ActionEvent event) {

    }
    @FXML
    void excluirLicencaNecessaria(ActionEvent event) {

    }
    @FXML
    void salvarNoAquivoLicencaNecessaria(ActionEvent event) {

    }
}