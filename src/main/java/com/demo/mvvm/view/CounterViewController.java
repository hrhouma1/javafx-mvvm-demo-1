package com.demo.mvvm.view;

import com.demo.mvvm.viewmodel.CounterViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * VIEW CONTROLLER
 * Fait le lien entre la View (FXML) et le ViewModel.
 * Configure les bindings bidirectionnels.
 * Délègue les actions au ViewModel (pas de logique ici).
 */
public class CounterViewController {
    
    // Référence au ViewModel
    private CounterViewModel viewModel;
    
    // Éléments de l'interface (injectés depuis FXML)
    @FXML
    private Label counterLabel;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Button incrementButton;
    
    @FXML
    private Button decrementButton;
    
    @FXML
    private Button resetButton;
    
    /**
     * Méthode appelée automatiquement après le chargement du FXML.
     * Configure tous les bindings entre la View et le ViewModel.
     */
    @FXML
    public void initialize() {
        // Création du ViewModel
        viewModel = new CounterViewModel();
        
        // BINDINGS BIDIRECTIONNELS
        // La View s'update automatiquement quand le ViewModel change
        
        // Binding de l'affichage du compteur
        counterLabel.textProperty().bind(
            viewModel.counterValueProperty().asString()
        );
        
        // Binding du message de status
        statusLabel.textProperty().bind(
            viewModel.statusMessageProperty()
        );
        
        // Binding de l'état des boutons (actif/inactif)
        incrementButton.disableProperty().bind(
            viewModel.canIncrementProperty().not()
        );
        
        decrementButton.disableProperty().bind(
            viewModel.canDecrementProperty().not()
        );
    }
    
    /**
     * Action déclenchée par le bouton "Incrémenter"
     * Délègue au ViewModel, pas de logique ici
     */
    @FXML
    private void onIncrement() {
        viewModel.increment();
    }
    
    /**
     * Action déclenchée par le bouton "Décrémenter"
     * Délègue au ViewModel, pas de logique ici
     */
    @FXML
    private void onDecrement() {
        viewModel.decrement();
    }
    
    /**
     * Action déclenchée par le bouton "Réinitialiser"
     * Délègue au ViewModel, pas de logique ici
     */
    @FXML
    private void onReset() {
        viewModel.reset();
    }
}

