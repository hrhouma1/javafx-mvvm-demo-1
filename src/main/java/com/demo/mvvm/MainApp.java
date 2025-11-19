package com.demo.mvvm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * APPLICATION PRINCIPALE
 * Point d'entrée de l'application JavaFX.
 * Charge la View FXML et applique les styles CSS.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Chargement du fichier FXML
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/demo/mvvm/view/CounterView.fxml")
        );
        Parent root = loader.load();
        
        // Création de la scène
        Scene scene = new Scene(root, 600, 700);
        
        // Application des styles CSS
        scene.getStylesheets().add(
            getClass().getResource("/com/demo/mvvm/view/styles.css").toExternalForm()
        );
        
        // Configuration de la fenêtre
        primaryStage.setTitle("Pattern MVVM - Démonstration Pédagogique");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

