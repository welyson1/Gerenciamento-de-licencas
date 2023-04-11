package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import DAO.LicencasNecessariasDAO;
import DAO.LicencasObtidasDAO;
import DAO.ProjetoDAO;
import DAO.RecursosDAO;
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
import model.Projeto;
import model.Recurso;

public class Controller implements Initializable, Serializable{
//region Declaração de variaveis
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
    @FXML
    private TextField entradaBuscador;
    @FXML
    private TextField entradaBuscadorProjeto;
    @FXML
    private TextField entradaBuscadorlNecessaria;
    @FXML
    private TextField entradaBuscadorlObtidas;

    @FXML
    private TextField parametroRelatorioLobtidas;
 //endregion

//region Declaração do observador
    private ObservableList<Recurso> observableList;
    private ObservableList<LicencasObtidas> observableListLicencasObtidas;
    private ObservableList<Projeto> observablelistProjetos;
    private ObservableList<LicencasNecessarias> observablelistLicencasNecessarias;
//endregion
    
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
        preencherTableViewRecursos();
        preencherTableViewProjeto();
        preencherTableViewLicencasObtidas();
        preencherTableViewLicencaNecessarias();
    }

//region Recurso
    //Botão que chama a exibição do relatorio para listar os recursos cadastrados no bando de dados
    @FXML
    void relatorioListarRecursos(ActionEvent event) {
        ControllerRelatorios relatorioSimples = new ControllerRelatorios();
        //Passa o nome do arquivo jasper relatório para o método
        relatorioSimples.exibirRelatorioSimples("RelatorioRecursos");
    } 
    //Botão para cadastrar um novo recurso no banco de dados  
    @FXML
    void cadastrarRecurso(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        Recurso recurso = new Recurso(entradaNome.getText(), entradaEmail.getText(), entradaProjeto.getText());
         
        RecursosDAO recursosDAO = new RecursosDAO();
        recursosDAO.addRecurso(recurso);
        
        //Limpar campos do formulario
        limparCamposRecurso();

        preencherTableViewRecursos();

    }
    //Metodo exclui o item
    @FXML
    public void excluirRecurso() throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        Recurso recursoRemover = tableViewRecursos.getSelectionModel().getSelectedItem();
                
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

        limparCamposRecurso();
    }
    //Ação do botão para buscar
    @FXML
    void btnBuscadorEmail(ActionEvent event) {
        Recurso recurso = new Recurso();

        //Instanciação do objeto 
        RecursosDAO recursosDAO = new RecursosDAO();  
        //Busca do objeto pelo valor do campo de busca      
        recurso = recursosDAO.buscaRecurso(entradaBuscador.getText());

        //Preenche os campos do form com as informações encontradas
        entradaNome.setText(recurso.getRecursoNome());
        entradaEmail.setText(recurso.getRecursoEmail());
        entradaProjeto.setText(recurso.getRecursoProjeto());
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

        //Guarda o valor para buscar 
        String emailBuscado = recursoEditado.getRecursoEmail();
        
        //Coloca o texto dos campos nas variaveis do objeto
        recursoEditado.setRecursoNome(entradaNome.getText());
        recursoEditado.setRecursoEmail(entradaEmail.getText());
        recursoEditado.setRecursoProjeto(entradaProjeto.getText());

        //Teste com banco de dados
        RecursosDAO recursosDAO = new RecursosDAO();
        recursosDAO.updateRecursos(recursoEditado, emailBuscado);
      
    }
     
    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void preencherTableViewRecursos() {
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
//endregion

//region Licenças Obtidas
    //Botão para recuperar as licenças obtidas de um recurso
    @FXML
    void relatorioLicencasObtidas(ActionEvent event) {
        ControllerRelatorios controllerRelatorios = new ControllerRelatorios();
        //Passa o nome do arquivo jasper relatório para o método
        controllerRelatorios.exibirRelatorioParametros("RelatorioLicencasObtidas", construtorParametrosLicencasObtidas());
    }
    /**
     * O metodo constroi os parametros para passar para o relatório
     * @return parametro contruído para importar no relatório do Jasper
     */
    private Map construtorParametrosLicencasObtidas() {
        Map params = new HashMap();
        try {
            params.put("emailBuscado", parametroRelatorioLobtidas.getText());
            
        } catch (Exception e) {
            return null;
        } 
        return params;      
    }
    //Botão para cadastrar uma nova licença oibtida
    @FXML
    void cadastrorLicencaObtida(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        LicencasObtidas licencasObtidas = new LicencasObtidas(entradaEmailRecursoLicencaObtida.getText(),entradaNomeLicencaObtida.getText(),entradaDataLicencaObtida.getText());
         
        //Cadastro no banco de dados
        LicencasObtidasDAO licencasObtidasDAO = new LicencasObtidasDAO();
        licencasObtidasDAO.addLicensasObtidas(licencasObtidas);        
  
        //Limpar campos do formulario
        limparCamposLicencasObtidas();

        //Atualizar o tableView
        preencherTableViewLicencasObtidas();
    }
    //Metodo exclui o item e salva em um arquivo .txt
    @FXML
    void excluirLicencaObtida(ActionEvent event) throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        LicencasObtidas licencasObtidasRemover = tableViewLicencasObtidas.getSelectionModel().getSelectedItem();
            
        LicencasObtidasDAO licencasObtidasDAO = new LicencasObtidasDAO();
        licencasObtidasDAO.excluirLicencaObtida(licencasObtidasRemover); 

        //Remoção do recurso
        tableViewLicencasObtidas.getItems().remove(licencasObtidasRemover);
        
        limparCamposLicencasObtidas();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarLicencasObtidas(ActionEvent event) throws FileNotFoundException, IOException {
        editarLicencaObtida();

        //Atualização do tablewView
        tableViewLicencasObtidas.refresh();

        limparCamposLicencasObtidas();
    }

    @FXML
    void btnBuscadorlObtidas(ActionEvent event) {
        LicencasObtidas licencasObtidas = new LicencasObtidas();

        LicencasObtidasDAO licencasObtidasDAO = new LicencasObtidasDAO();
        licencasObtidas = licencasObtidasDAO.buscaLicencasObtidas(entradaBuscadorlObtidas.getText());

        entradaEmailRecursoLicencaObtida.setText(licencasObtidas.getLicencasObtidasRecursoEmail());
        entradaNomeLicencaObtida.setText(licencasObtidas.getLicencasObtidasTreinamentoNome());
        entradaDataLicencaObtida.setText(licencasObtidas.getDataConclusao());
    
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
        //Passando o item selecionado para variavel
        LicencasObtidas licencasObtidasEditado = tableViewLicencasObtidas.getSelectionModel().getSelectedItem();

        //Guarda o valor para buscar
        String emailBuscado = licencasObtidasEditado.getLicencasObtidasRecursoEmail();

        //Coloca o texto dos campos nas variaveis do objeto
        licencasObtidasEditado.setLicencasObtidasRecursoEmail(entradaEmailRecursoLicencaObtida.getText());
        licencasObtidasEditado.setLicencasObtidasTreinamentoNome(entradaNomeLicencaObtida.getText());
        licencasObtidasEditado.setDataConclusao(entradaDataLicencaObtida.getText());

        LicencasObtidasDAO licencasObtidasDAO = new LicencasObtidasDAO();
        licencasObtidasDAO.updateLicencaObtida(licencasObtidasEditado, emailBuscado);
    }
    
    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void preencherTableViewLicencasObtidas() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaEmailLicencasObtidas.setCellValueFactory(new PropertyValueFactory<>("licencasObtidasRecursoEmail"));          
        tableViewColunaLicencaLicencasObtidas.setCellValueFactory(new PropertyValueFactory<>("licencasObtidasTreinamentoNome"));          
        tableViewColunaDataLicencaObtida.setCellValueFactory(new PropertyValueFactory<>("dataConclusao")); 

        LicencasObtidasDAO licencasObtidasDAO = new LicencasObtidasDAO();
        observableListLicencasObtidas = FXCollections.observableArrayList(licencasObtidasDAO.getListLicencaObtida());

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
//endregion

//region Licencas Necessaria
    @FXML
    void cadastrarLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        LicencasNecessarias licencasNecessarias = new LicencasNecessarias(entradaNomeLicencaNecessaria.getText(), entrataLinkLicencaNecessaria.getText(), entrataCategoriaLicencaNecessaria.getText(), entrataLevelLicencaNecessaria.getText());
 
        //Teste com banco de dados----------------------------------------------------------------------------------
        LicencasNecessariasDAO licencasNecessariasDAO = new LicencasNecessariasDAO();
        licencasNecessariasDAO.addLicensasNecessarias(licencasNecessarias);

        //Limpar campos do formulario
        limparCamposLicencasNecessarias();

        preencherTableViewLicencaNecessarias();
    }
    @FXML
    void excluirLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        LicencasNecessarias licencasNecessariasRemover = tableViewLicencaNecessarias.getSelectionModel().getSelectedItem();
        
        LicencasNecessariasDAO licencasNecessariasDAO = new LicencasNecessariasDAO();
        licencasNecessariasDAO.excluirLicensasNecessarias(licencasNecessariasRemover);

        //Remoção do recurso
        tableViewLicencaNecessarias.getItems().remove(licencasNecessariasRemover);
        
        limparCamposLicencasNecessarias();
    }
    @FXML
    void salvarLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        editarLicencasNecessarias();

        tableViewLicencaNecessarias.refresh();

        limparCamposLicencasNecessarias();
    }

    @FXML
    void btnBuscadorlNecessaria(ActionEvent event) {
        LicencasNecessarias licencasNecessarias = new LicencasNecessarias();

        LicencasNecessariasDAO licencasNecessariasDAO = new LicencasNecessariasDAO();
        licencasNecessarias = licencasNecessariasDAO.buscaLicensasNecessarias(entradaBuscadorlNecessaria.getText());

        entradaNomeLicencaNecessaria.setText(licencasNecessarias.getTreinamentoNome());
        entrataLinkLicencaNecessaria.setText(licencasNecessarias.getTreinamentoLink());
        entrataCategoriaLicencaNecessaria.setText(licencasNecessarias.getTreinamentoCategoria());
        entrataLevelLicencaNecessaria.setText(licencasNecessarias.getTreinamentoLevel());
    
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
        //Passando o item selecionado para variavel
        LicencasNecessarias licencasNecessariasEditado = tableViewLicencaNecessarias.getSelectionModel().getSelectedItem();    

        String nomeBuscado = licencasNecessariasEditado.getTreinamentoNome();
        
        //Coloca o texto dos campos nas variaveis do objeto
        licencasNecessariasEditado.setTreinamentoNome(entradaNomeLicencaNecessaria.getText());
        licencasNecessariasEditado.setTreinamentoLink(entrataLinkLicencaNecessaria.getText());
        licencasNecessariasEditado.setTreinamentoCategoria(entrataCategoriaLicencaNecessaria.getText()); 
        licencasNecessariasEditado.setTreinamentoLevel(entrataLevelLicencaNecessaria.getText()); 

        LicencasNecessariasDAO licencasNecessariasDAO = new LicencasNecessariasDAO();
        licencasNecessariasDAO.updateLicensasNecessarias(licencasNecessariasEditado, nomeBuscado);
            
    }

    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void preencherTableViewLicencaNecessarias() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNomeLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoNome"));          
        tableViewColunaLinkLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoLink"));          
        tableviewColunaCategoriaLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoCategoria"));          
        tableviewColunaLevelLicencasNecessarias.setCellValueFactory(new PropertyValueFactory<>("treinamentoLevel"));  

        LicencasNecessariasDAO licencasNecessariasDAO = new LicencasNecessariasDAO();
        observablelistLicencasNecessarias = FXCollections.observableArrayList(licencasNecessariasDAO.getListLicencasNecessarias());

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
//endregion

//region Projetos
    //Botão para recuperar o relatório dos projetos
    @FXML
    void relatorioAnalisesDosProjetos(ActionEvent event) {
        ControllerRelatorios relatorioSimples = new ControllerRelatorios();
        //Passa o nome do arquivo jasper relatório para o método
        relatorioSimples.exibirRelatorioSimples("RelatorioProjetos");
    }
    //Botão para cadastrar um novo projeto
    @FXML
    void cadastrarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        Projeto projeto = new Projeto(entradaNomeProjeto.getText(), entradaTecnologia.getText(), Float.parseFloat(entradaValor.getText()));
                
        ProjetoDAO projetoDAO = new ProjetoDAO();
        projetoDAO.addProjeto(projeto);
        
        //Limpar campos do formulario
        limparCamposProjeto();

        //Atualizar a tablewView
        preencherTableViewProjeto();
    }
    //Metodo exclui o item e salva em um arquivo .txt
    @FXML
    void excluirProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        //Passando o item selecionado para variavel
        Projeto projetoRemover = tableViewProjeto.getSelectionModel().getSelectedItem();
        
        ProjetoDAO projetoDAO = new ProjetoDAO();
        projetoDAO.excluirProjeto(projetoRemover);

        //Remoção do recurso
        tableViewProjeto.getItems().remove(projetoRemover);
       
        limparCamposProjeto();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        editarProjeto();
               
        //Atualização do tablewView
        tableViewProjeto.refresh();

        limparCamposProjeto();
    }    
    //Ação do botão para buscar
    @FXML
    void btnBuscadorProjeto(ActionEvent event) {
        Projeto projeto = new Projeto();

        //Instanciação do objeto 
        ProjetoDAO projetoDAO = new ProjetoDAO();
        //Busca do objeto pelo valor do campo de busca      
        projeto = projetoDAO.buscaRecurso(entradaBuscadorProjeto.getText());

        //Preenche os campos do form com as informações encontradas
        entradaNomeProjeto.setText(projeto.getProjetoNome());
        entradaTecnologia.setText(projeto.getProjetoTecnologia());
        entradaValor.setText(String.valueOf(projeto.getProjetoValor()));

    }
    
    public void projetoSelecionado(Projeto projeto) {
        Projeto projetoEditar = tableViewProjeto.getSelectionModel().getSelectedItem();

        entradaNomeProjeto.setText(projetoEditar.getProjetoNome());
        entradaTecnologia.setText(projetoEditar.getProjetoTecnologia());
        entradaValor.setText(String.valueOf(projeto.getProjetoValor()));
    }

    /**
     * Metodo edita o recurso com com as novas informações dos campos inseridas pelo usuario
     */
    public void editarProjeto() {        

        //Passando o item selecionado para variavel
        Projeto projetoEditar = tableViewProjeto.getSelectionModel().getSelectedItem();    
        
        //Guarda o valor para buscar
        String nomeBuscado = projetoEditar.getProjetoNome();

        //Coloca o texto dos campos nas variaveis do objeto
        projetoEditar.getProjetoNome(entradaNomeProjeto.getText());
        projetoEditar.setProjetoTecnologia(entradaTecnologia.getText());
        projetoEditar.setProjetoValor(Float.parseFloat(entradaValor.getText())); 

        ProjetoDAO projetoDAO = new ProjetoDAO();
        projetoDAO.updateProjeto(projetoEditar, nomeBuscado);

    }

    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void preencherTableViewProjeto() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNomeProjeto.setCellValueFactory(new PropertyValueFactory<>("projetoNome"));          
        tableViewColunaTecnologiaProjeto.setCellValueFactory(new PropertyValueFactory<>("projetoTecnologia"));          
        tableviewColunaValorProjeto.setCellValueFactory(new PropertyValueFactory<>("projetoValor")); 

        ProjetoDAO projetoDAO = new ProjetoDAO();        
        observablelistProjetos = FXCollections.observableArrayList(projetoDAO.getListProjeto());

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
//endregion

}