# Architecture d'une Application JavaFX Complète

## Structure d'un projet professionnel

### Organisation des dossiers

```
mon-projet/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/monapp/
│   │   │       ├── MainApp.java
│   │   │       ├── model/
│   │   │       │   └── User.java
│   │   │       ├── viewmodel/
│   │   │       │   └── UserViewModel.java
│   │   │       ├── view/
│   │   │       │   └── UserController.java
│   │   │       └── util/
│   │   │           └── NavigationManager.java
│   │   └── resources/
│   │       └── com/monapp/
│   │           ├── view/
│   │           │   ├── UserView.fxml
│   │           │   └── styles.css
│   │           └── images/
│   │               └── logo.png
│   └── test/
│       └── java/
│           └── com/monapp/
│               └── viewmodel/
│                   └── UserViewModelTest.java
```

### Responsabilités de chaque couche

```
┌──────────────────────────┐
│         VIEW             │  Interface utilisateur (FXML + Controller)
│  - Affichage             │  - Capture événements
│  - Bindings              │  - Délègue au ViewModel
└─────────┬────────────────┘
          │
┌─────────▼────────────────┐
│      VIEW MODEL          │  Logique de présentation
│  - Properties            │  - État de l'UI
│  - Commandes             │  - Transformations
└─────────┬────────────────┘
          │
┌─────────▼────────────────┐
│        MODEL             │  Logique métier
│  - Données               │  - Règles métier
│  - Validation            │  - Calculs
└──────────────────────────┘
```

---

## Le Model (Modèle)

Le Model représente les **données métier** et la **logique métier**.

### Caractéristiques

- Pas de dépendance JavaFX
- Pas de référence à la View ou au ViewModel
- Classes POJO (Plain Old Java Object)
- Réutilisable dans d'autres contextes (console, web, etc.)

### Exemple : Classe User

```java
package com.monapp.model;

public class User {
    
    private String username;
    private String email;
    private int age;
    
    // Constructeur
    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
    
    // Validation métier
    public boolean isValid() {
        return username != null && !username.isEmpty()
            && email != null && email.contains("@")
            && age >= 0 && age <= 150;
    }
    
    // Logique métier
    public boolean isAdult() {
        return age >= 18;
    }
    
    // Getters et Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "User{username='" + username + "', email='" + email + "', age=" + age + "}";
    }
}
```

### Bonnes pratiques pour le Model

1. **Immutabilité** (quand possible)
2. **Validation** dans le Model
3. **Pas de logique UI**
4. **Tests unitaires** faciles

---

## Le ViewModel

Le ViewModel fait le **pont** entre le Model et la View.

### Caractéristiques

- Contient des **Properties** JavaFX (observables)
- Expose des **commandes** (méthodes d'action)
- Gère l'**état de l'UI** (boutons activés/désactivés)
- Pas de référence directe aux composants UI (Button, Label, etc.)

### Exemple : UserViewModel

```java
package com.monapp.viewmodel;

import com.monapp.model.User;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;

public class UserViewModel {
    
    // Le Model
    private User user;
    
    // Properties exposées pour le binding
    private final StringProperty username;
    private final StringProperty email;
    private final IntegerProperty age;
    private final BooleanProperty isAdult;
    private final BooleanProperty canSave;
    private final StringProperty statusMessage;
    
    public UserViewModel() {
        // Initialisation des Properties
        this.username = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.age = new SimpleIntegerProperty(0);
        this.isAdult = new SimpleBooleanProperty(false);
        this.canSave = new SimpleBooleanProperty(false);
        this.statusMessage = new SimpleStringProperty("Remplissez le formulaire");
        
        // Calculs automatiques
        setupBindings();
    }
    
    private void setupBindings() {
        // L'utilisateur est adulte si age >= 18
        isAdult.bind(age.greaterThanOrEqualTo(18));
        
        // On peut sauvegarder si tous les champs sont remplis
        BooleanBinding usernameValid = username.isNotEmpty();
        BooleanBinding emailValid = email.isNotEmpty();
        BooleanBinding ageValid = age.greaterThan(0);
        
        canSave.bind(usernameValid.and(emailValid).and(ageValid));
    }
    
    // Commande : Sauvegarder l'utilisateur
    public void save() {
        // Créer le Model à partir des Properties
        user = new User(username.get(), email.get(), age.get());
        
        if (user.isValid()) {
            // Logique de sauvegarde (base de données, fichier, etc.)
            statusMessage.set("Utilisateur sauvegardé avec succès");
            System.out.println("Saved: " + user);
        } else {
            statusMessage.set("Erreur : données invalides");
        }
    }
    
    // Commande : Réinitialiser
    public void reset() {
        username.set("");
        email.set("");
        age.set(0);
        statusMessage.set("Formulaire réinitialisé");
    }
    
    // Getters des Properties (pour le binding dans le Controller)
    
    public StringProperty usernameProperty() {
        return username;
    }
    
    public StringProperty emailProperty() {
        return email;
    }
    
    public IntegerProperty ageProperty() {
        return age;
    }
    
    public BooleanProperty isAdultProperty() {
        return isAdult;
    }
    
    public BooleanProperty canSaveProperty() {
        return canSave;
    }
    
    public StringProperty statusMessageProperty() {
        return statusMessage;
    }
    
    // Getters de valeurs (optionnels, pour accès direct)
    
    public String getUsername() {
        return username.get();
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public int getAge() {
        return age.get();
    }
    
    public String getStatusMessage() {
        return statusMessage.get();
    }
}
```

---

## La View (Vue)

La View est composée de deux parties : **FXML** et **Controller**.

### FXML : L'interface graphique

Fichier : `UserView.fxml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.*?>
<?import javafx.geometry.Insets?>

<VBox xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.monapp.view.UserController"
      spacing="10" padding="20"
      prefWidth="400" prefHeight="350">
    
    <Label text="Formulaire Utilisateur" 
           style="-fx-font-size: 20px; -fx-font-weight: bold;"/>
    
    <Label text="Nom d'utilisateur:"/>
    <TextField fx:id="usernameField" promptText="Votre nom"/>
    
    <Label text="Email:"/>
    <TextField fx:id="emailField" promptText="votre@email.com"/>
    
    <Label text="Âge:"/>
    <TextField fx:id="ageField" promptText="25"/>
    
    <Label fx:id="adultLabel" text=""/>
    
    <HBox spacing="10">
        <Button fx:id="saveButton" text="Sauvegarder" onAction="#handleSave"/>
        <Button text="Réinitialiser" onAction="#handleReset"/>
    </HBox>
    
    <Separator/>
    
    <Label fx:id="statusLabel" text="" style="-fx-text-fill: blue;"/>
</VBox>
```

### Controller : Le lien entre View et ViewModel

Fichier : `UserController.java`

```java
package com.monapp.view;

import com.monapp.viewmodel.UserViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

public class UserController {
    
    // Référence au ViewModel
    private UserViewModel viewModel;
    
    // Éléments de l'interface (injectés depuis FXML)
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private Label adultLabel;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Button saveButton;
    
    @FXML
    public void initialize() {
        // Créer le ViewModel
        viewModel = new UserViewModel();
        
        // Configuration des bindings
        setupBindings();
    }
    
    private void setupBindings() {
        // Binding bidirectionnel pour username et email
        usernameField.textProperty().bindBidirectional(viewModel.usernameProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        
        // Binding pour l'âge (avec conversion String ↔ Integer)
        ageField.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                int age = Integer.parseInt(newVal);
                viewModel.ageProperty().set(age);
            } catch (NumberFormatException e) {
                viewModel.ageProperty().set(0);
            }
        });
        
        // Binding pour le label "Adulte" ou "Mineur"
        adultLabel.textProperty().bind(
            javafx.beans.binding.Bindings.when(viewModel.isAdultProperty())
                .then("Statut : Adulte")
                .otherwise("Statut : Mineur")
        );
        
        // Binding pour le bouton (activé seulement si formulaire valide)
        saveButton.disableProperty().bind(viewModel.canSaveProperty().not());
        
        // Binding pour le message de status
        statusLabel.textProperty().bind(viewModel.statusMessageProperty());
    }
    
    @FXML
    private void handleSave() {
        viewModel.save();
    }
    
    @FXML
    private void handleReset() {
        viewModel.reset();
    }
}
```

---

## L'Application principale

Fichier : `MainApp.java`

```java
package com.monapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/monapp/view/UserView.fxml")
        );
        
        Parent root = loader.load();
        
        // Créer la scène
        Scene scene = new Scene(root);
        
        // Optionnel : Charger un fichier CSS
        scene.getStylesheets().add(
            getClass().getResource("/com/monapp/view/styles.css").toExternalForm()
        );
        
        // Configurer la fenêtre
        primaryStage.setTitle("Application Utilisateur");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Navigation entre plusieurs vues

Pour une application avec plusieurs écrans, créez un **NavigationManager**.

### NavigationManager.java

```java
package com.monapp.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationManager {
    
    private static Stage primaryStage;
    
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public static void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(
                NavigationManager.class.getResource(fxmlPath)
            );
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossible de charger la vue : " + fxmlPath);
        }
    }
    
    public static void showDialog(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(
                NavigationManager.class.getResource(fxmlPath)
            );
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Utilisation

```java
// Dans MainApp
@Override
public void start(Stage primaryStage) {
    NavigationManager.setPrimaryStage(primaryStage);
    NavigationManager.loadView("/com/monapp/view/LoginView.fxml", "Connexion");
    primaryStage.show();
}

// Dans un Controller
@FXML
private void goToUserList() {
    NavigationManager.loadView("/com/monapp/view/UserListView.fxml", "Liste des utilisateurs");
}
```

---

## Tests unitaires du ViewModel

Le ViewModel est facilement testable sans lancer l'interface.

### UserViewModelTest.java

```java
package com.monapp.viewmodel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserViewModelTest {
    
    @Test
    void testInitialState() {
        UserViewModel viewModel = new UserViewModel();
        
        assertEquals("", viewModel.getUsername());
        assertEquals("", viewModel.getEmail());
        assertEquals(0, viewModel.getAge());
        assertFalse(viewModel.canSaveProperty().get());
    }
    
    @Test
    void testIsAdultWhenAgeIs18() {
        UserViewModel viewModel = new UserViewModel();
        
        viewModel.ageProperty().set(18);
        
        assertTrue(viewModel.isAdultProperty().get());
    }
    
    @Test
    void testCanSaveWhenAllFieldsFilled() {
        UserViewModel viewModel = new UserViewModel();
        
        viewModel.usernameProperty().set("Jean");
        viewModel.emailProperty().set("jean@email.com");
        viewModel.ageProperty().set(25);
        
        assertTrue(viewModel.canSaveProperty().get());
    }
    
    @Test
    void testCannotSaveWhenFieldsEmpty() {
        UserViewModel viewModel = new UserViewModel();
        
        assertFalse(viewModel.canSaveProperty().get());
    }
    
    @Test
    void testResetClearsAllFields() {
        UserViewModel viewModel = new UserViewModel();
        
        viewModel.usernameProperty().set("Jean");
        viewModel.emailProperty().set("jean@email.com");
        viewModel.ageProperty().set(25);
        
        viewModel.reset();
        
        assertEquals("", viewModel.getUsername());
        assertEquals("", viewModel.getEmail());
        assertEquals(0, viewModel.getAge());
    }
}
```

---

## Gestion des données persistantes

### Service de données

```java
package com.monapp.service;

import com.monapp.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    
    private static UserService instance;
    private List<User> users = new ArrayList<>();
    
    private UserService() {
        // Singleton
    }
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public void save(User user) {
        users.add(user);
        System.out.println("User saved: " + user);
    }
    
    public List<User> getAll() {
        return new ArrayList<>(users);
    }
    
    public User findByUsername(String username) {
        return users.stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }
    
    public void delete(User user) {
        users.remove(user);
    }
}
```

### Utilisation dans le ViewModel

```java
public void save() {
    user = new User(username.get(), email.get(), age.get());
    
    if (user.isValid()) {
        UserService.getInstance().save(user);
        statusMessage.set("Utilisateur sauvegardé");
    } else {
        statusMessage.set("Données invalides");
    }
}
```

---

## Résumé de l'architecture

### Flux de données

```
USER ACTION (Click)
    ↓
CONTROLLER capture l'événement
    ↓
CONTROLLER appelle ViewModel.commande()
    ↓
VIEWMODEL exécute la logique
    ↓
VIEWMODEL met à jour le MODEL
    ↓
VIEWMODEL met à jour ses Properties
    ↓
BINDINGS notifient automatiquement la VIEW
    ↓
VIEW se met à jour (Label, Button, etc.)
```

### Règles d'or

1. **Model** : Pas de JavaFX, logique métier pure
2. **ViewModel** : Properties + Commandes, pas de références UI
3. **View** : FXML + Controller, délègue tout au ViewModel
4. **Binding** : Synchronisation automatique View ↔ ViewModel
5. **Tests** : ViewModel et Model sont testables sans UI

---

## Points clés à retenir

1. **Séparation des responsabilités** : Chaque couche a son rôle
2. **ViewModel testable** : Sans lancer l'interface
3. **Binding automatique** : Moins de code, moins d'erreurs
4. **Navigation centralisée** : NavigationManager
5. **Service de données** : Séparé du ViewModel

---

## Prochaine leçon

Dans la prochaine leçon, nous verrons des **exercices pratiques complets** pour mettre en œuvre tous ces concepts.

**Fichier suivant** : `04-EXERCICES_PRATIQUES.md`

