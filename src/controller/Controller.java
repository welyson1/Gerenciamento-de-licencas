package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.LicencasNecessariasDAO;
import DAO.LicencasObtidasDAO;
import DAO.ProjetoDAO;
import DAO.RecursosDAO;
import JDBC.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LicencasNecessarias;
import model.LicencasObtidas;
import model.ManipuladorArquivo;
import model.Projeto;
import model.Recurso;

public class Controller implements Initializable, Serializable{
    
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

//------------------------------------------------------------

    //Instanciação do objeto que faz manipulações no arquivo de texto
    ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo(); 

    
    String caminhoDataRecursos = "./dataBase/Recursos.txt";
    String caminhoDataLicencasObtidas = "./dataBase/LicencasObtidas.txt";
    String caminhoDataLicencasNecessarias = "./dataBase/LicencasNecessarias.txt";
    String caminhoDataProjetos = "./dataBase/Projetos.txt";
    
//-------------------------------------------------------------  

    //Declaração de arrayList
    private List<Recurso> listRecursos = new ArrayList<Recurso>();
    private List<LicencasObtidas> listLicencasObtidas = new ArrayList<LicencasObtidas>();
    private List<Projeto> listProjetos = new ArrayList<Projeto>();
    private List<LicencasNecessarias> listLicencasNecessarias = new ArrayList<LicencasNecessarias>();    
    
    //Declaração do observador
    private ObservableList<Recurso> observableList;
    private ObservableList<LicencasObtidas> observableListLicencasObtidas;
    private ObservableList<Projeto> observablelistProjetos;
    private ObservableList<LicencasNecessarias> observablelistLicencasNecessarias;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        //Observador de seleção do item na tablewView
        tableViewRecursos.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> recursoSelecionado(newValue)
        );

        tableViewLicencasObtidas.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> licencaObtidaSelecionada(newValue)
        );
        
        tableViewProjeto.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> projetoSelecionado(newValue)
        );

        tableViewLicencaNecessarias.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> licencasNecessariaSelecionado(newValue)
        );

        //Metodo chamado para iniciar com a lista carregada as informações do arquivo .txt        
        PreencherTableViewRecursos();
        carregarLicencasObtidasNoArray();
        carregarProjetoNoArray();
        carregarLicencasNecessariaNoArray();
    }
//======================Recurso================================
//-------------------------------------------------------------    
    @FXML
    void cadastrarRecurso(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        Recurso recurso = new Recurso(entradaNome.getText(), entradaEmail.getText(), entradaProjeto.getText());
                
        //Teste com banco de dados----------------------------------------------------------------------------------
        RecursosDAO recursosDAO = new RecursosDAO();
        recursosDAO.addRecurso(recurso);
        
        //Limpar campos do formulario
        limparCamposRecurso();

        PreencherTableViewRecursos();

    }
    //Metodo exclui o item
    @FXML
    public void excluirRecurso() throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        Recurso recursoRemover = tableViewRecursos.getSelectionModel().getSelectedItem();
        
        //Teste com banco de dados----------------------------------------------------------------------------------
        RecursosDAO recursosDAO = new RecursosDAO();
        recursosDAO.excluirRecursos(recursoRemover);

        //Remoção do recurso
        tableViewRecursos.getItems().remove(recursoRemover);
                
        limparCamposRecurso();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarRecurso(ActionEvent event) throws FileNotFoundException, IOException {
        editarRecurso();  

        //Atualização do tablewView do recurso
        tableViewRecursos.refresh();
        
        //Atualização da lista no arquivo
        manipuladorArquivo.escritaRecursos(listRecursos, caminhoDataRecursos);

        limparCamposRecurso();
    }
    
    /**
     * O recursoSelecionado pega o item selecionado pelo usuario e coloca os valores nos campos da interface
     * @param recurso selecionado pelo usuario na tablewView
     */
    public void recursoSelecionado(Recurso recurso) {
        //Passando o item selecionado para variavel
        Recurso recursoEditar = tableViewRecursos.getSelectionModel().getSelectedItem();

        entradaNome.setText(recursoEditar.getRecursoNome());
        entradaEmail.setText(recursoEditar.getRecursoEmail());
        entradaProjeto.setText(recursoEditar.getRecursoProjeto());
    }
    
    /**
     * Metodo edita o recurso com com as novas informações dos campos inseridas pelo usuario
     */
    public void editarRecurso() {
        //Passando o item selecionado para variavel
        Recurso recursoEditado = tableViewRecursos.getSelectionModel().getSelectedItem();   

        //Guarda os valores para buscar ----------------------------------------------------------------------------
        String emailBuscado = recursoEditado.getRecursoEmail();
        
        //Coloca o texto dos campos nas variaveis do objeto
        recursoEditado.setRecursoNome(entradaNome.getText());
        recursoEditado.setRecursoEmail(entradaEmail.getText());
        recursoEditado.setRecursoProjeto(entradaProjeto.getText());

        //Teste com banco de dados----------------------------------------------------------------------------------
        RecursosDAO recursosDAO = new RecursosDAO();
        recursosDAO.updateRecursos(recursoEditado, emailBuscado);
      
    }
     
    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void PreencherTableViewRecursos() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNome.setCellValueFactory(new PropertyValueFactory<>("recursoNome"));
        tableViewColunaEmail.setCellValueFactory(new PropertyValueFactory<>("recursoEmail"));
        tableviewColunaProjeto.setCellValueFactory(new PropertyValueFactory<>("recursoProjeto"));           
        
        RecursosDAO recursosDAO = new RecursosDAO();
        observableList = FXCollections.observableArrayList(recursosDAO.getListRecursos());

        //Coloca os itens na lista
        tableViewRecursos.setItems(observableList); 
    }
    
    /**
     * Metodo limpa os campos preenchidos do recurso 
     */
    public void limparCamposRecurso() {
        entradaNome.setText("");
        entradaEmail.setText("");
        entradaProjeto.setText("");        
    }
//======================Recurso================================
//-------------------------------------------------------------

//======================Licenças Obtidas===================
//-------------------------------------------------------------
    @FXML
    void cadastrorLicencaObtida(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        LicencasObtidas licencasObtidas = new LicencasObtidas(entradaEmailRecursoLicencaObtida.getText(),entradaNomeLicencaObtida.getText(),entradaDataLicencaObtida.getText());
 
        //Adição no arrayList
        listLicencasObtidas.add(licencasObtidas);
        tableViewLicencasObtidas();

        //Teste com banco de dados----------------------------------------------------------------------------------
        LicencasObtidasDAO licencasObtidasDAO = new LicencasObtidasDAO();
        licencasObtidasDAO.addLicensasObtidas(licencasObtidas);        

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.escritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas);     

        //Limpar campos do formulario
        limparCamposLicencasObtidas();
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
        manipuladorArquivo.escritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas); 
        
        limparCamposLicencasObtidas();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarLicencasObtidas(ActionEvent event) throws FileNotFoundException, IOException {
        editarLicencaObtida();

        tableViewLicencasObtidas.refresh();

        manipuladorArquivo.escritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas); 

        limparCamposLicencasObtidas();
    }
    /**
     * O recursoSelecionado pega o item selecionado pelo usuario e coloca os valores nos campos da interface
     * @param recurso selecionado pelo usuario na tablewView
     */
    public void licencaObtidaSelecionada(LicencasObtidas licencasObtidas) {
        //Passando o item selecionado para variavel
        LicencasObtidas licencasObtidasSelecionada = tableViewLicencasObtidas.getSelectionModel().getSelectedItem();

        entradaEmailRecursoLicencaObtida.setText(licencasObtidasSelecionada.getLicencasObtidasRecursoEmail());
        entradaNomeLicencaObtida.setText(licencasObtidasSelecionada.getLicencasObtidasTreinamentoNome());
        entradaDataLicencaObtida.setText(licencasObtidasSelecionada.getDataConclusao());
    }

    /**
     * Metodo edita o recurso com com as novas informações dos campos inseridas pelo usuario
     */
    public void editarLicencaObtida() {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Passando o item selecionado para variavel
        LicencasObtidas licencasObtidasSelecionada = tableViewLicencasObtidas.getSelectionModel().getSelectedItem();

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listLicencasObtidas.size(); i++){
            if (listLicencasObtidas.get(i).getLicencasObtidasRecursoEmail() == licencasObtidasSelecionada.getLicencasObtidasRecursoEmail()){
                idSelecao = i;                
            }
        } 

        //Coloca o texto dos campos nas variaveis do objeto
        licencasObtidasSelecionada.setLicencasObtidasRecursoEmail(entradaEmailRecursoLicencaObtida.getText());
        licencasObtidasSelecionada.setLicencasObtidasTreinamentoNome(entradaNomeLicencaObtida.getText());
        licencasObtidasSelecionada.setDataConclusao(entradaDataLicencaObtida.getText());

        //Salva o objeto editado no objeto selecionado
        listLicencasObtidas.set(idSelecao, licencasObtidasSelecionada);
    }

    /**
     * O carregarLicencasObtidasNoArray pega os objetos do arrayList e carrega no tableView da interface
     */
    public void carregarLicencasObtidasNoArray() {
        listLicencasObtidas = manipuladorArquivo.leituraLicencaObtida(caminhoDataLicencasObtidas);

        //Associa as variaveis nas colunas do tableView
        tableViewLicencasObtidas();
    }
    
    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void tableViewLicencasObtidas() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaEmailLicencasObtidas.setCellValueFactory(new PropertyValueFactory<>("licencasObtidasRecursoEmail"));          
        tableViewColunaLicencaLicencasObtidas.setCellValueFactory(new PropertyValueFactory<>("licencasObtidasTreinamentoNome"));          
        tableViewColunaDataLicencaObtida.setCellValueFactory(new PropertyValueFactory<>("dataConclusao")); 

        observableListLicencasObtidas = FXCollections.observableArrayList(listLicencasObtidas);

        //Coloca os itens na lista
        tableViewLicencasObtidas.setItems(observableListLicencasObtidas); 
    }

    /**
     * Metodo limpa os campos preenchidos do recurso 
     */
    public void limparCamposLicencasObtidas() {           
        entradaEmailRecursoLicencaObtida.setText("");
        entradaNomeLicencaObtida.setText("");
        entradaDataLicencaObtida.setText("");
    }
//======================Licenças Obtidas===================
//-------------------------------------------------------------

//======================Licencas Necessarias====================
//-------------------------------------------------------------
    @FXML
    void cadastrarLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        LicencasNecessarias licencasNecessarias = new LicencasNecessarias(entradaNomeLicencaNecessaria.getText(), entrataLinkLicencaNecessaria.getText(), entrataCategoriaLicencaNecessaria.getText(), entrataLevelLicencaNecessaria.getText());
 
        //Adição no arrayList
        listLicencasNecessarias.add(licencasNecessarias);
        tableViewLicencaNecessarias();

        //Teste com banco de dados----------------------------------------------------------------------------------
        LicencasNecessariasDAO licencasNecessariasDAO = new LicencasNecessariasDAO();
        licencasNecessariasDAO.addLicensasNecessarias(licencasNecessarias);

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.escritaLicencaNecessarias(listLicencasNecessarias, caminhoDataLicencasNecessarias);

        //Limpar campos do formulario
        limparCamposLicencasNecessarias();
    }
    @FXML
    void excluirLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        LicencasNecessarias licencasNecessariasRemover = tableViewLicencaNecessarias.getSelectionModel().getSelectedItem();
        //Log
        System.out.println("Recurso removido" + licencasNecessariasRemover.getTreinamentoNome());
        //Remoção do recurso
        tableViewLicencaNecessarias.getItems().remove(licencasNecessariasRemover);
        //Remoção do ArrayList
        listLicencasNecessarias.remove(licencasNecessariasRemover);
        //Atualização da lista no arquivo
        manipuladorArquivo.escritaLicencaNecessarias(listLicencasNecessarias, caminhoDataLicencasNecessarias);
    }
    @FXML
    void salvarLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        editarLicencasNecessarias();

        tableViewLicencaNecessarias.refresh();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.escritaLicencaNecessarias(listLicencasNecessarias, caminhoDataLicencasNecessarias);

        limparCamposLicencasNecessarias();
    }
    
    /**
     * O recursoSelecionado pega o item selecionado pelo usuario e coloca os valores nos campos da interface
     * @param licencasNecessarias selecionado pelo usuario na tablewView
     */
    public void licencasNecessariaSelecionado(LicencasNecessarias licencasNecessarias) {
        LicencasNecessarias licencasNecessariasSelecionada = tableViewLicencaNecessarias.getSelectionModel().getSelectedItem();
   
        entradaNomeLicencaNecessaria.setText(licencasNecessariasSelecionada.getTreinamentoNome());
        entrataLinkLicencaNecessaria.setText(licencasNecessariasSelecionada.getTreinamentoLink());
        entrataCategoriaLicencaNecessaria.setText(licencasNecessariasSelecionada.getTreinamentoCategoria());
        entrataLevelLicencaNecessaria.setText(licencasNecessariasSelecionada.getTreinamentoLevel());
    }
    
    /**
     * Metodo edita o recurso com com as novas informações dos campos inseridas pelo usuario
     */
    public void editarLicencasNecessarias() {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Passando o item selecionado para variavel
        LicencasNecessarias licencasNecessarias = tableViewLicencaNecessarias.getSelectionModel().getSelectedItem();    

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listLicencasNecessarias.size(); i++){
            if (listLicencasNecessarias.get(i).getTreinamentoLink() == licencasNecessarias.getTreinamentoLink()){
                idSelecao = i;                
            }
        } 

        //Coloca o texto dos campos nas variaveis do objeto
        licencasNecessarias.setTreinamentoNome(entradaNomeLicencaNecessaria.getText());
        licencasNecessarias.setTreinamentoLink(entrataLinkLicencaNecessaria.getText());
        licencasNecessarias.setTreinamentoCategoria(entrataCategoriaLicencaNecessaria.getText()); 
        licencasNecessarias.setTreinamentoLevel(entrataLevelLicencaNecessaria.getText()); 

        //Salva o objeto editado no objeto selecionado
        listLicencasNecessarias.set(idSelecao, licencasNecessarias);
    }
    
    /**
     * O carregarLicencasNecessariaNoArray pega os objetos do arrayList e carrega no tableView da interface
     */
    public void carregarLicencasNecessariaNoArray() {

        listLicencasNecessarias = manipuladorArquivo.leituraLicencaNecessarias(caminhoDataLicencasNecessarias);

        //Associa as variaveis nas colunas do tableView
        tableViewLicencaNecessarias();
    }
    
    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void tableViewLicencaNecessarias() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNomeLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoNome"));          
        tableViewColunaLinkLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoLink"));          
        tableviewColunaCategoriaLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoCategoria"));          
        tableviewColunaLevelLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoLevel"));  

        observablelistLicencasNecessarias = FXCollections.observableArrayList(listLicencasNecessarias);

        //Coloca os itens na lista
        tableViewLicencaNecessarias.setItems(observablelistLicencasNecessarias); 
    }

    /**
     * Metodo limpa os campos preenchidos do recurso 
     */
    public void limparCamposLicencasNecessarias() {
        entradaNomeLicencaNecessaria.setText("");
        entrataLinkLicencaNecessaria.setText("");
        entrataCategoriaLicencaNecessaria.setText("");
        entrataLevelLicencaNecessaria.setText("");
    }
//======================Licencas Necessarias====================
//-------------------------------------------------------------

//======================Projetos===============================
//-------------------------------------------------------------
    @FXML
    void cadastrarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        Projeto projeto = new Projeto(entradaNomeProjeto.getText(), entradaTecnologia.getText(), entradaValor.getText());

        //Adição no arrayList
        listProjetos.add(projeto);
        tableViewProjeto();

        //Teste com banco de dados----------------------------------------------------------------------------------
        ProjetoDAO projetoDAO = new ProjetoDAO();
        projetoDAO.addProjeto(projeto);

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.escritaProjetos(listProjetos, caminhoDataProjetos);

        //Limpar campos do formulario
        limparCamposProjeto();
    }
    //Metodo exclui o item e salva em um arquivo .txt
    @FXML
    void excluirProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        Projeto projetoRemover = tableViewProjeto.getSelectionModel().getSelectedItem();
        //Log
        System.out.println("Recurso removido" + projetoRemover.getProjetoNome());
        //Remoção do recurso
        tableViewProjeto.getItems().remove(projetoRemover);
        //Remoção do ArrayList
        listProjetos.remove(projetoRemover);
        //Atualização da lista no arquivo
        manipuladorArquivo.escritaProjetos(listProjetos, caminhoDataProjetos);

        limparCamposProjeto();
    }

    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        editarProjeto();

        tableViewProjeto.refresh();

        manipuladorArquivo.escritaProjetos(listProjetos, caminhoDataProjetos);

        limparCamposProjeto();
    }

    /**
     * O recursoSelecionado pega o item selecionado pelo usuario e coloca os valores nos campos da interface
     * @param projeto selecionado pelo usuario na tablewView
     */
    public void projetoSelecionado(Projeto projeto) {
        Projeto projetoEditar = tableViewProjeto.getSelectionModel().getSelectedItem();

        entradaNomeProjeto.setText(projetoEditar.getProjetoNome());
        entradaTecnologia.setText(projetoEditar.getProjetoTecnologia());
        entradaValor.setText(projetoEditar.getProjetoValor());
    }

    /**
     * Metodo edita o recurso com com as novas informações dos campos inseridas pelo usuario
     */
    public void editarProjeto() {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Passando o item selecionado para variavel
        Projeto projetoEditar = tableViewProjeto.getSelectionModel().getSelectedItem();    

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listProjetos.size(); i++){
            if (listProjetos.get(i).getProjetoNome() == projetoEditar.getProjetoNome()){
                idSelecao = i;                
            }
        } 

        //Coloca o texto dos campos nas variaveis do objeto
        projetoEditar.setProjetoNome(entradaNomeProjeto.getText());
        projetoEditar.setProjetoTecnologia(entradaTecnologia.getText());
        projetoEditar.setProjetoValor(entradaValor.getText()); 

        //Salva o objeto editado no objeto selecionado
        listProjetos.set(idSelecao, projetoEditar);
    }

    /**
     * O carregarProjetoNoArray pega os objetos do arrayList e carrega no tableView da interface
     */
    public void carregarProjetoNoArray() {
        listProjetos = manipuladorArquivo.leituraProjetos(caminhoDataProjetos);

        //Associa as variaveis nas colunas do tableView
        tableViewProjeto();
    }

    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void tableViewProjeto() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNomeProjeto.setCellValueFactory(new PropertyValueFactory<>("projetoNome"));          
        tableViewColunaTecnologiaProjeto.setCellValueFactory(new PropertyValueFactory<>("projetoTecnologia"));          
        tableviewColunaValorProjeto.setCellValueFactory(new PropertyValueFactory<>("projetoValor")); 

        observablelistProjetos = FXCollections.observableArrayList(listProjetos);

        //Coloca os itens na lista
        tableViewProjeto.setItems(observablelistProjetos); 
    }

    /**
     * Metodo limpa os campos preenchidos do recurso 
     */
    public void limparCamposProjeto() {
        entradaNomeProjeto.setText("");
        entradaTecnologia.setText("");
        entradaValor.setText("");        
    }
//======================Projetos===============================
//-------------------------------------------------------------
}