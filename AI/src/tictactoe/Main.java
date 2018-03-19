package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static tictactoe.FXMLDocumentController.timer;

/**
 * Tic Tac Toe - Main class 
 * @author JorgeDíaz
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Tic-Tac-Toe");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest((event) -> timer.stop());
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
