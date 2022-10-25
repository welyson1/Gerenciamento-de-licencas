package controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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

public class Controller implements Initializable{
    
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
    private TextField entradaBuscadorRecurso;
    @FXML
    private TextField entradaBuscalNecessarias;
    @FXML
    private TextField entradaBuscalObtidas;
    @FXML
    private TextField entradaBuscaProjeto;

//-------------------------------------------------------------
    //Instanciação do objeto que faz manipulações no arquivo de texto
    ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo(); 

    //Caminho relativo dos arquivos
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
        carregarRecursoNoArray();
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
 
        //Adição no arrayList
        listRecursos.add(recurso);
        tableViewRecursos();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaRecursos(listRecursos, caminhoDataRecursos); 
        
        //Limpa os campos do formulario
        limparCamposRecurso();
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
        
        limparCamposRecurso();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarRecurso(ActionEvent event) throws FileNotFoundException, IOException { 
        Recurso recursoEditar = tableViewRecursos.getSelectionModel().getSelectedItem();         
        salvarEdicaoRecurso(recursoEditar);

        //Atualização do tablewView do recurso
        tableViewRecursos.refresh();

        //Atualização da lista no arquivo
        manipuladorArquivo.manipuladorEscritaRecursos(listRecursos, caminhoDataRecursos);

        limparCamposRecurso();
    }
    //Metodo disparado ao clicar o botão buscar na interface
    @FXML
    void buscarRecurso(ActionEvent event) {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listRecursos.size(); i++){
            //Condicional
            if (listRecursos.get(i).getRecursoEmail().equals(entradaBuscadorRecurso.getText())){
                idSelecao = i;             
            }
        }
        entradaNome.setText(listRecursos.get(idSelecao).getRecursoNome());
        entradaEmail.setText(listRecursos.get(idSelecao).getRecursoEmail());
        entradaProjeto.setText(listRecursos.get(idSelecao).getRecursoProjeto());
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
    public void salvarEdicaoRecurso(Recurso recurso) {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listRecursos.size(); i++){
            //Logs
            System.out.println("Email objeto recurso: "+recurso.getRecursoEmail());
            System.out.println("Email listRecursos: "+listRecursos.get(i).getRecursoEmail());
            System.out.println(listRecursos.get(i).getRecursoEmail().equals(recurso.getRecursoEmail()));

            //Condicional
            if (listRecursos.get(i).getRecursoEmail().equals(recurso.getRecursoEmail())){
                idSelecao = i;             
            }
        } 
        
        //Coloca o texto dos campos nas variaveis do objeto
        recurso.setRecursoNome(entradaNome.getText());
        recurso.setRecursoEmail(entradaEmail.getText());
        recurso.setRecursoProjeto(entradaProjeto.getText());

        //Salva o objeto editado no objeto selecionado
        listRecursos.set(idSelecao, recurso);
    }
   
    /**
     * O carregarRecursoNoArray pega as linhas do arquivo de texto, separa pelo delimitador e coloca nas variaveis do objeto que depois passa para um ArrayList
     */
    public void carregarRecursoNoArray() {
        //Declaração da lista provisoria para armazenar as strings brutas concatenadas
        List<String> listRecursosArquivo = new ArrayList<String>(); 

        //Retorno um arrayList de Strings com a informação concatenada com as informações a partir da leitura
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
            
            //Adiciona o recurso no ArrayList
            listRecursos.add(recurso);
            
            tableViewRecursos();
        }
    }
    
    /**
     * O tableViewRecursos carrega os valores dos objetos nas colunas da tableView de recursos
     */
    public void tableViewRecursos() {
        //Associação das variaveis da classe com as probliedades da tabela 
        tableViewColunaNome.setCellValueFactory(new PropertyValueFactory<>("recursoNome"));
        tableViewColunaEmail.setCellValueFactory(new PropertyValueFactory<>("recursoEmail"));
        tableviewColunaProjeto.setCellValueFactory(new PropertyValueFactory<>("recursoProjeto"));           
        
        observableList = FXCollections.observableArrayList(listRecursos);

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

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas);

        //Limpa os campos do formulario
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
        manipuladorArquivo.manipuladorEscritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas); 
        
        limparCamposLicencasObtidas();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarLicencasObtidas(ActionEvent event) throws FileNotFoundException, IOException {
        editarLicencaObtida();

        tableViewLicencasObtidas.refresh();

        manipuladorArquivo.manipuladorEscritaLicencaObtida(listLicencasObtidas, caminhoDataLicencasObtidas); 

        limparCamposLicencasObtidas();
    }
    
    @FXML
    void buscalObtidas(ActionEvent event) {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listLicencasObtidas.size(); i++){
            //Condicional
            if (listLicencasObtidas.get(i).getLicencasObtidasRecursoEmail().equals(entradaBuscalObtidas.getText())){
                idSelecao = i;      
            }
        }
        entradaEmailRecursoLicencaObtida.setText(listLicencasObtidas.get(idSelecao).getLicencasObtidasRecursoEmail());
        entradaNomeLicencaObtida.setText(listLicencasObtidas.get(idSelecao).getLicencasObtidasTreinamentoNome());
        entradaDataLicencaObtida.setText(listLicencasObtidas.get(idSelecao).getDataConclusao());
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
     * O carregarLicencasObtidasNoArray pega as linhas do arquivo de texto, separa pelo delimitador e coloca nas variaveis do objeto que depois passa para um ArrayList
     */
    public void carregarLicencasObtidasNoArray() {
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
                licencasObtidas.setLicencasObtidasRecursoEmail(Linguicao.nextToken());
                licencasObtidas.setLicencasObtidasTreinamentoNome(Linguicao.nextToken());                
                licencasObtidas.setDataConclusao(Linguicao.nextToken());
            } 
            
            listLicencasObtidas.add(licencasObtidas);

            //Associa as variaveis nas colunas do tableView
            tableViewLicencasObtidas();
        }
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

//======================Projetos===============================
//-------------------------------------------------------------
    @FXML
    void cadastrarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        Projeto projeto = new Projeto(entradaNomeProjeto.getText(), entradaTecnologia.getText(), entradaValor.getText());
 
        //Adição no arrayList
        listProjetos.add(projeto);
        tableViewProjeto();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaProjetos(listProjetos, caminhoDataProjetos);

        //Limpa os campos do formulario
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
        manipuladorArquivo.manipuladorEscritaProjetos(listProjetos, caminhoDataProjetos);

        limparCamposProjeto();
    }
    //Metodo salva em um arquivo txt ao ser acionado
    @FXML
    void salvarProjeto(ActionEvent event) throws FileNotFoundException, IOException {
        editarProjeto();

        tableViewProjeto.refresh();

        manipuladorArquivo.manipuladorEscritaProjetos(listProjetos, caminhoDataProjetos);

        limparCamposProjeto();
    }

    @FXML
    void buscarProjeto(ActionEvent event) {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listProjetos.size(); i++){
            //Condicional
            if (listProjetos.get(i).getProjetoNome().equals(entradaBuscaProjeto.getText())){
                idSelecao = i;      
            }
        }
        entradaNomeProjeto.setText(listProjetos.get(idSelecao).getProjetoNome());
        entradaTecnologia.setText(listProjetos.get(idSelecao).getProjetoTecnologia());
        entradaValor.setText(listProjetos.get(idSelecao).getProjetoValor());
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
     * O carregarProjetoNoArray pega as linhas do arquivo de texto, separa pelo delimitador e coloca nas variaveis do objeto que depois passa para um ArrayList
     */
    public void carregarProjetoNoArray() {
        //Declaração da lista provisoria para armazenar as strings brutas concatenadas
        List<String> listProjetoArquivo = new ArrayList<String>(); 

        //Retorno um arrayList de Strings com a informação concatenada com as informações a partir da leitura
        listProjetoArquivo = manipuladorArquivo.manipuladorLeitura(caminhoDataProjetos);

        //Intera para cada item do arrayList
        for (String item : listProjetoArquivo) {
            //Declaração do array para guardar as informações
            Projeto projeto = new Projeto();

            //Define o delimitador
            StringTokenizer Linguicao = new StringTokenizer(item, ";");
            
            while (Linguicao.hasMoreTokens()){ //Executa enquanto tiver Tokens                
                projeto.setProjetoNome(Linguicao.nextToken());
                projeto.setProjetoTecnologia(Linguicao.nextToken());
                projeto.setProjetoValor(Linguicao.nextToken());                
            } 
            
            listProjetos.add(projeto);

            tableViewProjeto();
        }
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

//======================Licencas Necessarias====================
//-------------------------------------------------------------
    @FXML
    void cadastrarLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        //Instancia do recurso
        LicencasNecessarias licencasNecessarias = new LicencasNecessarias(entradaNomeLicencaNecessaria.getText(), entrataLinkLicencaNecessaria.getText(), entrataCategoriaLicencaNecessaria.getText(), entrataLevelLicencaNecessaria.getText());
 
        //Adição no arrayList
        listLicencasNecessarias.add(licencasNecessarias);
        tableViewLicencaNecessarias();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaLicencaNecessarias(listLicencasNecessarias, caminhoDataLicencasNecessarias);
        
        //Limpa os campos do formulario
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
        manipuladorArquivo.manipuladorEscritaLicencaNecessarias(listLicencasNecessarias, caminhoDataLicencasNecessarias);
    }
    @FXML
    void salvarLicencaNecessaria(ActionEvent event) throws FileNotFoundException, IOException {
        editarLicencasNecessarias();

        tableViewLicencaNecessarias.refresh();

        //Salvar no arquivo de texto a cada cadastro
        manipuladorArquivo.manipuladorEscritaLicencaNecessarias(listLicencasNecessarias, caminhoDataLicencasNecessarias);

        limparCamposLicencasNecessarias();
    }
    
    @FXML
    void buscarlNecessarias(ActionEvent event) {
        //Variavel para armazenar o id do objeto selecionado
        int idSelecao = 0;

        //Procura id do objeto na lista para fazer alteração
        for (int i = 0; i < listLicencasNecessarias.size(); i++){
            //Condicional
            if (listLicencasNecessarias.get(i).getTreinamentoNome().equals(entradaBuscaProjeto.getText())){
                idSelecao = i;      
            }
        }
        entradaNomeLicencaNecessaria.setText(listLicencasNecessarias.get(idSelecao).getTreinamentoNome());
        entrataLinkLicencaNecessaria.setText(listLicencasNecessarias.get(idSelecao).getTreinamentoLink());
        entrataCategoriaLicencaNecessaria.setText(listLicencasNecessarias.get(idSelecao).getTreinamentoCategoria());
        entrataLevelLicencaNecessaria.setText(listLicencasNecessarias.get(idSelecao).getTreinamentoLevel());
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
     * O carregarProjetoNoArray pega as linhas do arquivo de texto, separa pelo delimitador e coloca nas variaveis do objeto que depois passa para um ArrayList
     */
    public void carregarLicencasNecessariaNoArray() {
        //Declaração da lista provisoria para armazenar as strings brutas concatenadas
        List<String> listLicencasNecessaria = new ArrayList<String>(); 

        //Retorno um arrayList de Strings com a informação concatenada com as informações a partir da leitura
        listLicencasNecessaria = manipuladorArquivo.manipuladorLeitura(caminhoDataLicencasNecessarias);

        //Intera para cada item do arrayList
        for (String item : listLicencasNecessaria) {
            //Declaração do array para guardar as informações
            LicencasNecessarias licencasNecessarias = new LicencasNecessarias();

            //Define o delimitador
            StringTokenizer Linguicao = new StringTokenizer(item, ";");
            
            while (Linguicao.hasMoreTokens()){ //Executa enquanto tiver Tokens                
                licencasNecessarias.setTreinamentoNome(Linguicao.nextToken());
                licencasNecessarias.setTreinamentoLink(Linguicao.nextToken());
                licencasNecessarias.setTreinamentoCategoria(Linguicao.nextToken());
                licencasNecessarias.setTreinamentoLevel(Linguicao.nextToken());                  
            } 
            
            listLicencasNecessarias.add(licencasNecessarias);

            //Associa as variaveis nas colunas do tableView
            tableViewLicencaNecessarias();
        }
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

}