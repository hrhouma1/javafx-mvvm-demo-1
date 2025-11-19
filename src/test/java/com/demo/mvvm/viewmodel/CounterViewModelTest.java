package com.demo.mvvm.viewmodel;

/**
 * EXEMPLE DE TESTS UNITAIRES
 * 
 * Démo pour montrer comment tester le ViewModel sans lancer l'interface.
 * Pour utiliser ces tests, ajoutez JUnit 5 dans pom.xml :
 * 
 * <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter</artifactId>
 *     <version>5.10.1</version>
 *     <scope>test</scope>
 * </dependency>
 * 
 * Puis exécutez : mvn test
 */

// Décommentez ces imports si vous ajoutez JUnit
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import static org.junit.jupiter.api.Assertions.*;

public class CounterViewModelTest {
    
    private CounterViewModel viewModel;
    
    // @BeforeEach
    public void setUp() {
        viewModel = new CounterViewModel();
    }
    
    /**
     * Test : L'incrémentation augmente la valeur de 1
     */
    // @Test
    public void testIncrement() {
        // Arrange
        int initialValue = viewModel.getCounterValue();
        
        // Act
        viewModel.increment();
        
        // Assert
        // assertEquals(initialValue + 1, viewModel.getCounterValue());
        // assertEquals("Incrémenté à 1", viewModel.getStatusMessage());
    }
    
    /**
     * Test : La décrémentation diminue la valeur de 1
     */
    // @Test
    public void testDecrement() {
        // Arrange
        viewModel.increment(); // Commencer à 1
        
        // Act
        viewModel.decrement();
        
        // Assert
        // assertEquals(0, viewModel.getCounterValue());
        // assertEquals("Décrémenté à 0", viewModel.getStatusMessage());
    }
    
    /**
     * Test : Le reset remet le compteur à 0
     */
    // @Test
    public void testReset() {
        // Arrange
        viewModel.increment();
        viewModel.increment();
        viewModel.increment();
        
        // Act
        viewModel.reset();
        
        // Assert
        // assertEquals(0, viewModel.getCounterValue());
        // assertEquals("Compteur réinitialisé", viewModel.getStatusMessage());
    }
    
    /**
     * Test : Les boutons sont correctement activés/désactivés
     */
    // @Test
    public void testButtonStates() {
        // Au départ (valeur = 0)
        // assertTrue(viewModel.canIncrementProperty().get());
        // assertFalse(viewModel.canDecrementProperty().get());
        
        // Après incrémentation
        viewModel.increment();
        // assertTrue(viewModel.canIncrementProperty().get());
        // assertTrue(viewModel.canDecrementProperty().get());
    }
    
    /**
     * Test : On ne peut pas dépasser la valeur maximale
     */
    // @Test
    public void testMaxValue() {
        // Incrémenter jusqu'à la limite
        for (int i = 0; i < 100; i++) {
            viewModel.increment();
        }
        
        // Vérifier qu'on est à 100
        // assertEquals(100, viewModel.getCounterValue());
        
        // Essayer d'incrémenter encore
        viewModel.increment();
        
        // Vérifier qu'on est toujours à 100
        // assertEquals(100, viewModel.getCounterValue());
        // assertEquals("Valeur maximale atteinte !", viewModel.getStatusMessage());
        // assertFalse(viewModel.canIncrementProperty().get());
    }
    
    /**
     * Test : On ne peut pas descendre sous la valeur minimale
     */
    // @Test
    public void testMinValue() {
        // Décrémenter jusqu'à la limite
        for (int i = 0; i < 100; i++) {
            viewModel.decrement();
        }
        
        // Vérifier qu'on est à -100
        // assertEquals(-100, viewModel.getCounterValue());
        
        // Essayer de décrémenter encore
        viewModel.decrement();
        
        // Vérifier qu'on est toujours à -100
        // assertEquals(-100, viewModel.getCounterValue());
        // assertEquals("Valeur minimale atteinte !", viewModel.getStatusMessage());
        // assertFalse(viewModel.canDecrementProperty().get());
    }
}

