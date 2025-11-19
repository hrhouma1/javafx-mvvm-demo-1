=======================================================================
                    STRUCTURE DU PROJET MVVM DEMO
=======================================================================

mvvm1/
│
├── pom.xml                         Configuration Maven (dépendances JavaFX)
├── run.bat                         Script de lancement rapide (Windows)
├── .gitignore                      Fichiers à ignorer par Git
│
├── documentation/                  Documentation pédagogique complète
│   ├── 01-README.md               Vue d'ensemble et concepts
│   ├── 02-REFERENCE_RAPIDE.md     Référence rapide
│   ├── 03-STRUCTURE_PROJET.md     Ce fichier (structure)
│   ├── 04-POINTS_CLES_DEMO.md     Points essentiels
│   └── 05-GUIDE_DEMO.md           Guide de présentation
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java               Configuration modules Java
│   │   │   │
│   │   │   └── com/demo/mvvm/
│   │   │       │
│   │   │       ├── MainApp.java               APPLICATION PRINCIPALE
│   │   │       │                              - Point d'entrée
│   │   │       │                              - Lance JavaFX
│   │   │       │                              - Charge FXML et CSS
│   │   │       │
│   │   │       ├── model/                     COUCHE MODEL
│   │   │       │   └── Counter.java           - Logique métier pure
│   │   │       │                              - Aucune dépendance UI
│   │   │       │                              - Règles métier (min/max)
│   │   │       │                              - Opérations (increment, etc.)
│   │   │       │
│   │   │       ├── viewmodel/                 COUCHE VIEW MODEL
│   │   │       │   └── CounterViewModel.java  - Properties observables
│   │   │       │                              - Commandes (actions)
│   │   │       │                              - Logique de présentation
│   │   │       │                              - Pas de référence à la View
│   │   │       │
│   │   │       └── view/                      COUCHE VIEW
│   │   │           └── CounterViewController.java
│   │   │                                      - Configure les bindings
│   │   │                                      - Délègue actions au ViewModel
│   │   │                                      - Minimal, pas de logique
│   │   │
│   │   └── resources/
│   │       └── com/demo/mvvm/view/
│   │           ├── CounterView.fxml           Interface utilisateur (XML)
│   │           │                              - Structure déclarative
│   │           │                              - Boutons, labels, layout
│   │           │                              - Références fx:id
│   │           │
│   │           └── styles.css                 Styles professionnels
│   │                                          - Design moderne
│   │                                          - Gradients, ombres
│   │                                          - États hover/pressed
│   │
│   └── test/
│       └── java/
│           └── com/demo/mvvm/viewmodel/
│               └── CounterViewModelTest.java  Tests unitaires (exemple)
│                                              - Démo de testabilité
│                                              - Sans lancer l'UI
│
└── target/                                    (généré par Maven)
    └── ...                                    Fichiers compilés


=======================================================================
                        FLUX DE DONNÉES MVVM
=======================================================================

┌─────────────────────────────────────────────────────────────────────────┐
│                                                                         │
│  USER ACTION (Click button)                                            │
│          │                                                              │
│          ▼                                                              │
│  ┌───────────────────┐                                                 │
│  │   CounterView     │  VIEW (FXML + Controller)                       │
│  │   .fxml           │  - Affichage                                    │
│  └─────────┬─────────┘  - Capture événements                           │
│            │                                                            │
│            │ onIncrement()                                              │
│            ▼                                                            │
│  ┌───────────────────┐                                                 │
│  │ CounterView       │  CONTROLLER                                     │
│  │ Controller.java   │  - Configure bindings                           │
│  └─────────┬─────────┘  - Délègue au ViewModel                         │
│            │                                                            │
│            │ viewModel.increment()                                     │
│            ▼                                                            │
│  ┌───────────────────┐                                                 │
│  │ CounterViewModel  │  VIEW MODEL                                     │
│  │ .java             │  - Properties observables                       │
│  └─────────┬─────────┘  - Logique présentation                         │
│            │                                                            │
│            │ counter.increment()                                       │
│            ▼                                                            │
│  ┌───────────────────┐                                                 │
│  │ Counter.java      │  MODEL                                          │
│  │                   │  - Logique métier                               │
│  └─────────┬─────────┘  - Données                                      │
│            │                                                            │
│            │ return true/false                                         │
│            ▼                                                            │
│  ┌───────────────────┐                                                 │
│  │ CounterViewModel  │  Mise à jour Properties                         │
│  │ .java             │  counterValue.set(...)                          │
│  └─────────┬─────────┘                                                 │
│            │                                                            │
│            │ BINDING AUTOMATIQUE                                       │
│            ▼                                                            │
│  ┌───────────────────┐                                                 │
│  │   CounterView     │  UI UPDATE AUTOMATIQUE                          │
│  │   .fxml           │  - Label change                                 │
│  └───────────────────┘  - Boutons activés/désactivés                   │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘


=======================================================================
                    RESPONSABILITÉS DE CHAQUE CLASSE
=======================================================================

┌─────────────────────┬──────────────────────────────────────────────────┐
│ FICHIER             │ RESPONSABILITÉ                                   │
├─────────────────────┼──────────────────────────────────────────────────┤
│ Counter.java        │ • Gérer la valeur du compteur                   │
│ (MODEL)             │ • Valider les règles métier (min/max)           │
│                     │ • Operations: increment(), decrement(), reset()  │
│                     │ • ZÉRO dépendance JavaFX                         │
├─────────────────────┼──────────────────────────────────────────────────┤
│ CounterViewModel    │ • Exposer des Properties observables             │
│ .java               │ • Fournir des commandes (méthodes publiques)     │
│ (VIEW MODEL)        │ • Gérer l'état de l'UI (boutons actifs/inactifs)│
│                     │ • Pas de référence directe à la View             │
├─────────────────────┼──────────────────────────────────────────────────┤
│ CounterView         │ • Définir la structure de l'interface            │
│ Controller.java     │ • Configurer les bindings                        │
│ (CONTROLLER)        │ • Déléguer les actions au ViewModel              │
│                     │ • Pas de logique métier ou présentation          │
├─────────────────────┼──────────────────────────────────────────────────┤
│ CounterView.fxml    │ • Structure déclarative de l'UI                  │
│ (VIEW)              │ • Définition des composants visuels              │
│                     │ • Références fx:id pour le Controller            │
├─────────────────────┼──────────────────────────────────────────────────┤
│ styles.css          │ • Apparence visuelle professionnelle             │
│                     │ • Styles modernes et attractifs                  │
├─────────────────────┼──────────────────────────────────────────────────┤
│ MainApp.java        │ • Point d'entrée de l'application                │
│                     │ • Chargement FXML et CSS                         │
│                     │ • Configuration de la fenêtre                    │
└─────────────────────┴──────────────────────────────────────────────────┘


=======================================================================
                        TYPES DE BINDING UTILISÉS
=======================================================================

1. UNIDIRECTIONNEL (ViewModel → View)
   
   counterLabel.textProperty().bind(viewModel.counterValueProperty().asString());
   
   Quand counterValue change → Label se met à jour automatiquement

2. AVEC TRANSFORMATION
   
   incrementButton.disableProperty().bind(viewModel.canIncrementProperty().not());
   
   Quand canIncrement = false → Bouton devient désactivé

3. STRING BINDING
   
   statusLabel.textProperty().bind(viewModel.statusMessageProperty());
   
   Quand statusMessage change → Label affiche nouveau message


=======================================================================
                        COMMANDES UTILES
=======================================================================

COMPILATION
    mvn clean compile

LANCEMENT
    mvn javafx:run
    
    ou
    
    run.bat  (Windows)

TESTS (après ajout de JUnit)
    mvn test

NETTOYAGE
    mvn clean


=======================================================================
                        POINTS CLÉS POUR LA DÉMO
=======================================================================

1. SÉPARATION DES COUCHES
   ✓ Model : Logique métier pure (pas de JavaFX)
   ✓ ViewModel : Properties + Commandes
   ✓ View : Interface + Bindings

2. DATA BINDING AUTOMATIQUE
   ✓ Pas de .setText() manuel
   ✓ Synchronisation automatique
   ✓ Moins d'erreurs

3. TESTABILITÉ
   ✓ ViewModel testable sans UI
   ✓ Tests unitaires simples
   ✓ Voir CounterViewModelTest.java

4. ÉTAT DE L'UI
   ✓ Boutons désactivés automatiquement aux limites
   ✓ Messages de status dynamiques
   ✓ Géré par le ViewModel


=======================================================================
                        AVANTAGES DÉMONTRÉS
=======================================================================

✓ Code propre et organisé
✓ Maintenance facilitée
✓ Tests plus simples
✓ Réutilisabilité du Model
✓ Moins de bugs de synchronisation
✓ Data binding réduit le boilerplate
✓ Interface professionnelle et moderne


=======================================================================

