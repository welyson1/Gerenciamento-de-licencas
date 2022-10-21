package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* Task 1
 * Definição de localização de arquivo texto de forma estática, poderia utilizar a definição dinâmica para 
 * funcionamento em diferentes computadores, como visto em aula, definindo a localização dos
 * arquivos em uma pasta padrão, como por exemplo String pasta = System.getProperty("user.dir");
 * 
 * Task 2
 * Implementou apenas uma classe controller, quando poderia dividir o código e tentar generalizar.
 */

public class Main extends Application{
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/tela.fxml"));
        primaryStage.setTitle("Controle de licenças para trabalho");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();   
    }
}
