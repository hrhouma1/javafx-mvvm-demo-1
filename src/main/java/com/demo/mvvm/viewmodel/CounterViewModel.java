package com.demo.mvvm.viewmodel;

import com.demo.mvvm.model.Counter;
import javafx.beans.property.*;

/**
 * VIEW MODEL
 * Couche intermédiaire entre le Model et la View.
 * Expose des Properties observables pour le binding bidirectionnel.
 * Contient la logique de présentation (pas de logique métier).
 * Pas de référence directe à la View (pas de dépendance JavaFX UI).
 */
public class CounterViewModel {
    
    // Le Model (logique métier)
    private final Counter counter;
    
    // Properties observables pour le binding avec la View
    private final IntegerProperty counterValue;
    private final BooleanProperty canIncrement;
    private final BooleanProperty canDecrement;
    private final StringProperty statusMessage;
    
    public CounterViewModel() {
        // Initialisation du Model
        this.counter = new Counter(0, -100, 100);
        
        // Initialisation des Properties
        this.counterValue = new SimpleIntegerProperty(counter.getValue());
        this.canIncrement = new SimpleBooleanProperty(true);
        this.canDecrement = new SimpleBooleanProperty(false);
        this.statusMessage = new SimpleStringProperty("Prêt");
        
        // Mise à jour initiale de l'état
        updateState();
    }
    
    /**
     * Commande : Incrémenter le compteur
     */
    public void increment() {
        if (counter.increment()) {
            counterValue.set(counter.getValue());
            statusMessage.set("Incrémenté à " + counter.getValue());
            updateState();
        } else {
            statusMessage.set("Valeur maximale atteinte !");
        }
    }
    
    /**
     * Commande : Décrémenter le compteur
     */
    public void decrement() {
        if (counter.decrement()) {
            counterValue.set(counter.getValue());
            statusMessage.set("Décrémenté à " + counter.getValue());
            updateState();
        } else {
            statusMessage.set("Valeur minimale atteinte !");
        }
    }
    
    /**
     * Commande : Réinitialiser le compteur
     */
    public void reset() {
        counter.reset();
        counterValue.set(counter.getValue());
        statusMessage.set("Compteur réinitialisé");
        updateState();
    }
    
    /**
     * Met à jour l'état des boutons (actif/inactif)
     */
    private void updateState() {
        canIncrement.set(counter.canIncrement());
        canDecrement.set(counter.canDecrement());
    }
    
    // Getters des Properties pour le binding
    
    public IntegerProperty counterValueProperty() {
        return counterValue;
    }
    
    public BooleanProperty canIncrementProperty() {
        return canIncrement;
    }
    
    public BooleanProperty canDecrementProperty() {
        return canDecrement;
    }
    
    public StringProperty statusMessageProperty() {
        return statusMessage;
    }
    
    // Getters de valeurs (optionnel)
    
    public int getCounterValue() {
        return counterValue.get();
    }
    
    public String getStatusMessage() {
        return statusMessage.get();
    }
}

