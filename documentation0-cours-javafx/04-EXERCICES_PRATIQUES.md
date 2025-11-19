# Exercices Pratiques JavaFX

Ce fichier contient des exercices progressifs pour maîtriser JavaFX et le pattern MVVM.

---

## Exercice 1 : Application Calculatrice

### Niveau : Débutant
### Concepts : Bases JavaFX, Layout, Événements

### Objectif

Créer une calculatrice simple avec :
- Deux champs de texte pour les nombres
- Quatre boutons (+, -, ×, ÷)
- Un label pour afficher le résultat

### Contraintes

- Utiliser un VBox ou GridPane
- Gérer les erreurs (division par zéro, texte non numérique)
- Afficher un message d'erreur approprié

### Structure minimale

```
CalculatriceApp.java
└── start() : crée l'interface en Java pur (pas de FXML)
```

### Solution attendue

```java
public class CalculatriceApp extends Application {
    @Override
    public void start(Stage stage) {
        TextField nombre1Field = new TextField();
        TextField nombre2Field = new TextField();
        Label resultat = new Label("Résultat : ");
        
        Button addBtn = new Button("+");
        Button subBtn = new Button("-");
        Button mulBtn = new Button("×");
        Button divBtn = new Button("÷");
        
        // Actions des boutons...
        // Layout...
        // Scene et Stage...
    }
}
```

---

## Exercice 2 : Liste de Tâches (Todo List)

### Niveau : Débutant/Intermédiaire
### Concepts : ListView, ObservableList, FXML

### Objectif

Créer une application de gestion de tâches avec :
- Un champ de texte pour entrer une nouvelle tâche
- Un bouton "Ajouter"
- Une ListView affichant les tâches
- Un bouton "Supprimer" pour retirer la tâche sélectionnée

### Contraintes

- Utiliser FXML pour l'interface
- Utiliser une ObservableList pour les tâches
- Désactiver le bouton "Supprimer" si aucune tâche n'est sélectionnée

### Structure

```
TodoApp.java (Application principale)
TodoView.fxml (Interface)
TodoController.java (Controller)
```

### Exemple FXML

```xml
<VBox spacing="10" padding="20">
    <HBox spacing="10">
        <TextField fx:id="taskField" promptText="Nouvelle tâche..."/>
        <Button text="Ajouter" onAction="#handleAdd"/>
    </HBox>
    
    <ListView fx:id="taskListView" prefHeight="300"/>
    
    <Button fx:id="deleteButton" text="Supprimer" onAction="#handleDelete"/>
</VBox>
```

### Indications

```java
public class TodoController {
    @FXML
    private TextField taskField;
    
    @FXML
    private ListView<String> taskListView;
    
    @FXML
    private Button deleteButton;
    
    private ObservableList<String> tasks;
    
    @FXML
    public void initialize() {
        tasks = FXCollections.observableArrayList();
        taskListView.setItems(tasks);
        
        // Désactiver le bouton si aucune sélection
        deleteButton.disableProperty().bind(
            taskListView.getSelectionModel()
                .selectedItemProperty().isNull()
        );
    }
    
    @FXML
    private void handleAdd() {
        // À compléter
    }
    
    @FXML
    private void handleDelete() {
        // À compléter
    }
}
```

---

## Exercice 3 : Formulaire avec Validation

### Niveau : Intermédiaire
### Concepts : Properties, Binding, Validation

### Objectif

Créer un formulaire d'inscription avec validation en temps réel :
- Nom (obligatoire, minimum 3 caractères)
- Email (obligatoire, doit contenir @)
- Mot de passe (obligatoire, minimum 8 caractères)
- Confirmation mot de passe (doit correspondre)
- Bouton "S'inscrire" (activé seulement si tout est valide)

### Contraintes

- Utiliser des Properties pour les champs
- Utiliser le Binding pour la validation
- Afficher des messages d'erreur dynamiques
- Changer la couleur des champs invalides en rouge

### Structure MVVM

```
FormApp.java
FormView.fxml
FormController.java
FormViewModel.java
```

### FormViewModel (à compléter)

```java
public class FormViewModel {
    
    private final StringProperty nom = new SimpleStringProperty("");
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty motDePasse = new SimpleStringProperty("");
    private final StringProperty confirmMotDePasse = new SimpleStringProperty("");
    
    private final BooleanProperty formulaireValide = new SimpleBooleanProperty(false);
    private final StringProperty messageErreur = new SimpleStringProperty("");
    
    public FormViewModel() {
        setupValidation();
    }
    
    private void setupValidation() {
        // TODO : Configurer les bindings de validation
        // - nom.length() >= 3
        // - email.contains("@")
        // - motDePasse.length() >= 8
        // - motDePasse.equals(confirmMotDePasse)
    }
    
    public void submit() {
        // TODO : Logique de soumission
    }
    
    // Getters des Properties...
}
```

### Bonus

Ajouter une barre de force du mot de passe (ProgressBar) qui se remplit selon :
- Rouge (0-33%) : Faible
- Orange (34-66%) : Moyen
- Vert (67-100%) : Fort

---

## Exercice 4 : Gestionnaire de Contacts

### Niveau : Intermédiaire/Avancé
### Concepts : TableView, MVVM complet, Navigation

### Objectif

Créer une application de gestion de contacts avec :
- Liste des contacts dans un TableView
- Formulaire pour ajouter/modifier un contact
- Boutons Ajouter, Modifier, Supprimer
- Recherche par nom

### Structure

```
Contact.java (Model)
ContactViewModel.java
ContactService.java (Gestion des données)
ContactListView.fxml + ContactListController.java
ContactFormView.fxml + ContactFormController.java
MainApp.java
```

### Modèle Contact

```java
public class Contact {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    
    // Constructeur, getters, setters...
    
    public boolean isValid() {
        return nom != null && !nom.isEmpty()
            && prenom != null && !prenom.isEmpty();
    }
}
```

### TableView dans FXML

```xml
<TableView fx:id="contactTable">
    <columns>
        <TableColumn text="Nom" prefWidth="150"/>
        <TableColumn text="Prénom" prefWidth="150"/>
        <TableColumn text="Téléphone" prefWidth="120"/>
        <TableColumn text="Email" prefWidth="200"/>
    </columns>
</TableView>
```

### Configuration du TableView

```java
@FXML
private TableView<Contact> contactTable;

@FXML
private TableColumn<Contact, String> nomColumn;

@FXML
public void initialize() {
    // Lier les colonnes aux propriétés
    nomColumn.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getNom())
    );
    
    // Charger les données
    contactTable.setItems(viewModel.getContacts());
}
```

### Fonctionnalités à implémenter

1. Ajouter un contact
2. Modifier un contact sélectionné
3. Supprimer un contact sélectionné
4. Rechercher par nom (filtrage dynamique)
5. Double-clic sur un contact pour le modifier

---

## Exercice 5 : Application Multi-Vues

### Niveau : Avancé
### Concepts : Navigation, Plusieurs vues, État partagé

### Objectif

Créer une application avec navigation entre plusieurs écrans :
- Écran de connexion (LoginView)
- Écran principal avec menu (MainView)
- Écran de profil utilisateur (ProfileView)
- Écran de paramètres (SettingsView)

### Architecture

```
User.java (Model)
SessionManager.java (Singleton pour l'utilisateur connecté)
NavigationManager.java (Gestion de la navigation)

view/
├── LoginView.fxml + LoginController.java
├── MainView.fxml + MainController.java
├── ProfileView.fxml + ProfileController.java
└── SettingsView.fxml + SettingsController.java

viewmodel/
├── LoginViewModel.java
├── ProfileViewModel.java
└── SettingsViewModel.java
```

### SessionManager

```java
public class SessionManager {
    private static SessionManager instance;
    private User currentUser;
    
    private SessionManager() {}
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    public void login(User user) {
        this.currentUser = user;
    }
    
    public void logout() {
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
```

### NavigationManager (à compléter dans l'exercice 3)

Doit permettre :
- `loadView(String fxmlPath, String title)`
- `showDialog(String fxmlPath)`
- `goBack()` (historique de navigation)

### Fonctionnalités à implémenter

1. Écran de connexion avec validation
2. Navigation vers l'écran principal après connexion
3. Menu latéral ou barre de menu pour naviguer
4. Passage de données entre les vues
5. Déconnexion (retour à l'écran de connexion)

---

## Exercice 6 : Application de Notes avec Sauvegarde

### Niveau : Avancé
### Concepts : Persistance, Fichiers, JSON, Threading

### Objectif

Créer une application de prise de notes avec :
- Liste des notes (titre + aperçu)
- Éditeur de note (TextArea)
- Sauvegarde automatique dans un fichier JSON
- Chargement au démarrage

### Technologies

- Gson ou Jackson pour JSON
- FileWriter/FileReader pour les fichiers
- Task pour le chargement asynchrone

### Modèle Note

```java
public class Note {
    private String id;
    private String titre;
    private String contenu;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    
    // Constructeur, getters, setters...
}
```

### Service de persistance

```java
public class NoteService {
    private static final String FILE_PATH = "notes.json";
    private Gson gson = new Gson();
    
    public void saveNotes(List<Note> notes) {
        // TODO : Sauvegarder dans notes.json
    }
    
    public List<Note> loadNotes() {
        // TODO : Charger depuis notes.json
    }
}
```

### Chargement asynchrone

```java
@FXML
public void initialize() {
    // Afficher un indicateur de chargement
    loadingIndicator.setVisible(true);
    
    // Charger les notes dans un thread séparé
    Task<List<Note>> loadTask = new Task<>() {
        @Override
        protected List<Note> call() {
            return noteService.loadNotes();
        }
    };
    
    loadTask.setOnSucceeded(event -> {
        notes.setAll(loadTask.getValue());
        loadingIndicator.setVisible(false);
    });
    
    new Thread(loadTask).start();
}
```

### Fonctionnalités à implémenter

1. Créer une nouvelle note
2. Sélectionner une note pour l'éditer
3. Sauvegarder automatiquement après chaque modification
4. Supprimer une note
5. Rechercher dans les notes

---

## Exercice 7 : Mini-Jeu avec Canvas

### Niveau : Avancé
### Concepts : Canvas, AnimationTimer, Graphisme

### Objectif

Créer un jeu simple (Snake, Pong, ou Casse-briques) avec :
- Canvas pour le rendu graphique
- AnimationTimer pour la boucle de jeu
- Gestion du clavier pour les contrôles
- Score et game over

### Structure de base

```java
public class GameApp extends Application {
    
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer timer;
    
    @Override
    public void start(Stage stage) {
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        
        // Gestion du clavier
        Scene scene = new Scene(new StackPane(canvas));
        scene.setOnKeyPressed(this::handleKeyPress);
        
        // Boucle de jeu
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        timer.start();
        
        stage.setScene(scene);
        stage.show();
    }
    
    private void update() {
        // Logique du jeu
    }
    
    private void render() {
        // Affichage
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Dessiner les éléments...
    }
    
    private void handleKeyPress(KeyEvent event) {
        // Gestion des touches
    }
}
```

---

## Exercice 8 : Application de Chat (Avancé)

### Niveau : Expert
### Concepts : Sockets, Threading, Communication réseau

### Objectif

Créer une application de chat en temps réel avec :
- Serveur (écoute les connexions)
- Client (se connecte au serveur)
- Envoi/réception de messages
- Liste des utilisateurs connectés

### Architecture

```
Server.java (Serveur Socket)
Client.java (Client Socket)
ChatApp.java (Interface JavaFX)
ChatController.java
Message.java (Model)
```

### Indications

- Utiliser `ServerSocket` pour le serveur
- Utiliser `Socket` pour le client
- Séparer la logique réseau dans des threads
- Utiliser `Platform.runLater()` pour mettre à jour l'UI depuis un thread

---

## Correction et Solutions

Les solutions complètes de tous ces exercices sont disponibles dans un repository séparé :

```
github.com/votre-repo/javafx-exercices-solutions
```

Chaque exercice dispose :
- Du code source complet
- D'explications détaillées
- De variantes et améliorations possibles

---

## Critères d'évaluation

Pour chaque exercice, évaluez votre code selon :

1. **Fonctionnalité** : L'application fonctionne-t-elle correctement ?
2. **Architecture** : Respect du pattern MVVM
3. **Qualité du code** : Lisibilité, nommage, commentaires
4. **Gestion des erreurs** : Les cas limites sont-ils gérés ?
5. **Interface utilisateur** : L'UI est-elle intuitive et agréable ?

---

## Progression recommandée

1. **Semaine 1** : Exercices 1-2 (Bases)
2. **Semaine 2** : Exercice 3 (Properties et Binding)
3. **Semaine 3** : Exercice 4 (MVVM complet)
4. **Semaine 4** : Exercice 5 (Navigation)
5. **Semaine 5** : Exercice 6 (Persistance)
6. **Semaine 6+** : Exercices 7-8 (Avancé)

---

## Ressources complémentaires

- Documentation officielle JavaFX : https://openjfx.io/
- Scene Builder (outil visuel) : https://gluonhq.com/products/scene-builder/
- Tutoriels Oracle : https://docs.oracle.com/javafx/

---

Bon courage et amusez-vous bien avec JavaFX !

