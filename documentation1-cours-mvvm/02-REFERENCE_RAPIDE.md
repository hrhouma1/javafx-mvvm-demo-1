# Référence Rapide - Pattern MVVM

## Définition en une phrase

MVVM sépare l'application en 3 couches (Model, ViewModel, View) avec du **data binding bidirectionnel** entre View et ViewModel.

---

## Les 3 Couches

### MODEL
**Quoi :** Logique métier pure  
**Contient :** Données, règles métier, algorithmes  
**Ne contient PAS :** Références UI, JavaFX Properties  
**Exemple :** `Counter.java`

### VIEW MODEL
**Quoi :** Logique de présentation  
**Contient :** Properties observables, commandes (méthodes), état UI  
**Ne contient PAS :** Références directes à la View  
**Exemple :** `CounterViewModel.java`

### VIEW
**Quoi :** Interface utilisateur  
**Contient :** FXML (structure), Controller (bindings)  
**Ne contient PAS :** Logique métier ou présentation  
**Exemple :** `CounterView.fxml` + `CounterViewController.java`

---

## Flux de données

```
USER → View → Controller → ViewModel → Model
                            ↓
                      Properties changent
                            ↓
                      View s'update AUTO
```

---

## Concepts clés JavaFX

### Properties Observables

```java
// Déclaration
private final IntegerProperty counterValue = new SimpleIntegerProperty(0);

// Getter pour binding
public IntegerProperty counterValueProperty() {
    return counterValue;
}

// Modification (notifie automatiquement les observers)
counterValue.set(10);
```

**Types courants :**
- `IntegerProperty` / `SimpleIntegerProperty`
- `StringProperty` / `SimpleStringProperty`
- `BooleanProperty` / `SimpleBooleanProperty`
- `DoubleProperty` / `SimpleDoubleProperty`

### Data Binding

```java
// Unidirectionnel (Property → UI)
label.textProperty().bind(viewModel.valueProperty().asString());

// Bidirectionnel (Property ↔ UI)
textField.textProperty().bindBidirectional(viewModel.nameProperty());

// Avec transformation
button.disableProperty().bind(viewModel.canSaveProperty().not());
```

### Transformations

```java
// Integer → String
intProperty.asString()

// Boolean inversion
boolProperty.not()

// Conditions
Bindings.when(condition).then(value1).otherwise(value2)

// Combinaisons
property1.and(property2)
property1.or(property2)
```

---

## Template de code

### Model

```java
public class MyModel {
    private int data;
    
    public void businessLogic() {
        // Règles métier
    }
    
    public int getData() {
        return data;
    }
}
```

### ViewModel

```java
public class MyViewModel {
    private final MyModel model;
    private final IntegerProperty data;
    
    public MyViewModel() {
        this.model = new MyModel();
        this.data = new SimpleIntegerProperty(model.getData());
    }
    
    // Commande
    public void doAction() {
        model.businessLogic();
        data.set(model.getData());
    }
    
    // Property getter pour binding
    public IntegerProperty dataProperty() {
        return data;
    }
}
```

### Controller

```java
public class MyController {
    private MyViewModel viewModel;
    
    @FXML
    private Label dataLabel;
    
    @FXML
    public void initialize() {
        viewModel = new MyViewModel();
        
        // Configuration bindings
        dataLabel.textProperty().bind(
            viewModel.dataProperty().asString()
        );
    }
    
    @FXML
    private void onButtonClick() {
        viewModel.doAction();
    }
}
```

### FXML

```xml
<VBox xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.example.MyController">
    
    <Label fx:id="dataLabel" text="0"/>
    <Button text="Action" onAction="#onButtonClick"/>
    
</VBox>
```

---

## Checklist : "Est-ce du bon MVVM ?"

### Model
- [ ] Pas de dépendance JavaFX
- [ ] Pas de référence à ViewModel ou View
- [ ] Contient uniquement logique métier
- [ ] Testable indépendamment

### ViewModel
- [ ] Utilise des Properties observables
- [ ] Pas de référence à la View (pas de Label, Button, etc.)
- [ ] Contient les commandes (actions)
- [ ] Gère l'état de l'UI via Properties
- [ ] Testable sans lancer l'UI

### View (Controller)
- [ ] Configure les bindings dans `initialize()`
- [ ] Délègue toutes les actions au ViewModel
- [ ] Pas de logique métier
- [ ] Pas de logique de présentation
- [ ] Minimal

### View (FXML)
- [ ] Structure déclarative
- [ ] fx:id pour les éléments à binder
- [ ] onAction pour les événements
- [ ] Pas de logique

---

## Erreurs courantes

### 1. Logique dans le Controller

**Mauvais :**
```java
@FXML
private void onIncrement() {
    int current = Integer.parseInt(label.getText());
    label.setText(String.valueOf(current + 1));
}
```

**Bon :**
```java
@FXML
private void onIncrement() {
    viewModel.increment();
}
```

### 2. Référence à la View dans le ViewModel

**Mauvais :**
```java
public class MyViewModel {
    private Label label; // NON !
}
```

**Bon :**
```java
public class MyViewModel {
    private final StringProperty text = new SimpleStringProperty();
}
```

### 3. Logique métier dans le ViewModel

**Mauvais :**
```java
public void calculateTotal() {
    // Algorithme complexe ici
    double total = item1 * 1.2 + item2 * 0.8;
}
```

**Bon :**
```java
public void calculateTotal() {
    model.calculateTotal();
    totalProperty.set(model.getTotal());
}
```

---

## Quand utiliser MVVM

**Bon choix pour :**
- Applications JavaFX / WPF
- UI complexe avec beaucoup d'interactions
- Projets nécessitant forte testabilité
- Applications avec plusieurs vues partageant données

**Pas nécessaire pour :**
- Applications console
- UI très simple (1-2 écrans basiques)
- Prototypes rapides
- Scripts one-shot

---

## Avantages vs Inconvénients

### Avantages
- Séparation claire des responsabilités
- Testabilité excellente
- Réutilisabilité du code
- Binding automatique = moins de bugs
- Maintenance facilitée

### Inconvénients
- Courbe d'apprentissage
- Plus de classes qu'une approche simple
- Framework dépendant (nécessite binding)
- Overhead pour petites applications

---

## Commandes Maven

```bash
# Compiler
mvn clean compile

# Lancer
mvn javafx:run

# Tester
mvn test

# Package
mvn package
```

---

## Ressources

**Dans ce projet :**
- `01-README.md` : Documentation complète
- `05-GUIDE_DEMO.md` : Guide de présentation
- `04-POINTS_CLES_DEMO.md` : Points essentiels
- `03-STRUCTURE_PROJET.md` : Architecture détaillée

**Code source :**
- `src/main/java/com/demo/mvvm/` : Toutes les classes
- `src/main/resources/com/demo/mvvm/view/` : FXML et CSS

---

## Résumé visuel

```
┌─────────────────────────────────────────┐
│              VIEW (FXML)                │
│   - Boutons, Labels, TextFields        │
│   - Pas de logique                      │
└──────────────┬──────────────────────────┘
               │ fx:controller
┌──────────────▼──────────────────────────┐
│       CONTROLLER (Java)                 │
│   - Configure bindings                  │
│   - Délègue au ViewModel                │
└──────────────┬──────────────────────────┘
               │ binding automatique
┌──────────────▼──────────────────────────┐
│       VIEW MODEL (Java)                 │
│   - Properties observables              │
│   - Commandes (increment, etc.)         │
│   - État UI (canIncrement, etc.)        │
└──────────────┬──────────────────────────┘
               │ appels de méthodes
┌──────────────▼──────────────────────────┐
│         MODEL (Java)                    │
│   - Logique métier pure                 │
│   - Données                             │
│   - Algorithmes                         │
└─────────────────────────────────────────┘
```

---

**Ce document est une référence rapide. Pour plus de détails, consultez le 01-README.md**

