# Properties et Binding en JavaFX

## Qu'est-ce qu'une Property ?

Une **Property** est une **variable observable**. C'est-à-dire qu'elle peut **avertir automatiquement** quand sa valeur change.

### Analogie simple

**Variable normale** :
```
Comme une boîte fermée.
Vous devez vérifier régulièrement si le contenu a changé.
```

**Property** :
```
Comme une boîte avec une alarme.
Elle sonne automatiquement quand le contenu change.
```

### Pourquoi c'est utile ?

Imaginez un compteur affiché à l'écran :

**Sans Property** (approche manuelle) :
```java
int compteur = 0;
Label label = new Label("0");

// À chaque changement, vous devez manuellement mettre à jour le label
compteur++;
label.setText(String.valueOf(compteur));  // VOUS devez le faire

compteur++;
label.setText(String.valueOf(compteur));  // ENCORE et ENCORE
```

**Avec Property** (approche automatique) :
```java
IntegerProperty compteur = new SimpleIntegerProperty(0);
Label label = new Label();

// Configuration UNE SEULE FOIS
label.textProperty().bind(compteur.asString());

// Maintenant, les mises à jour sont automatiques
compteur.set(1);  // Le label affiche automatiquement "1"
compteur.set(2);  // Le label affiche automatiquement "2"
```

---

## Les types de Properties

### Properties de base

| Type Java | Type Property | Classe Simple |
|-----------|---------------|---------------|
| int | IntegerProperty | SimpleIntegerProperty |
| double | DoubleProperty | SimpleDoubleProperty |
| boolean | BooleanProperty | SimpleBooleanProperty |
| String | StringProperty | SimpleStringProperty |
| Object | ObjectProperty&lt;T&gt; | SimpleObjectProperty&lt;T&gt; |

### Créer une Property

```java
// Integer
IntegerProperty age = new SimpleIntegerProperty(25);

// String
StringProperty nom = new SimpleStringProperty("Jean");

// Boolean
BooleanProperty actif = new SimpleBooleanProperty(true);

// Double
DoubleProperty prix = new SimpleDoubleProperty(19.99);
```

---

## Utiliser une Property

### Lire la valeur

```java
IntegerProperty compteur = new SimpleIntegerProperty(10);

// Méthode 1 : get()
int valeur = compteur.get();
System.out.println(valeur);  // Affiche : 10

// Méthode 2 : getValue()
Integer valeur2 = compteur.getValue();
System.out.println(valeur2);  // Affiche : 10
```

**Différence** :
- `get()` retourne le type primitif (int, double, etc.)
- `getValue()` retourne l'objet wrapper (Integer, Double, etc.)

### Modifier la valeur

```java
IntegerProperty compteur = new SimpleIntegerProperty(10);

// Méthode 1 : set()
compteur.set(20);

// Méthode 2 : setValue()
compteur.setValue(20);
```

### Écouter les changements

```java
IntegerProperty compteur = new SimpleIntegerProperty(0);

// Ajouter un listener
compteur.addListener((observable, ancienneValeur, nouvelleValeur) -> {
    System.out.println("Changement : " + ancienneValeur + " → " + nouvelleValeur);
});

compteur.set(5);   // Affiche : "Changement : 0 → 5"
compteur.set(10);  // Affiche : "Changement : 5 → 10"
```

---

## Le Binding (liaison)

Le **binding** crée une **connexion automatique** entre deux properties ou entre une property et un élément UI.

### Binding unidirectionnel

**Une property suit une autre automatiquement.**

```java
IntegerProperty source = new SimpleIntegerProperty(10);
IntegerProperty destination = new SimpleIntegerProperty();

// La destination suit la source
destination.bind(source);

System.out.println(destination.get());  // Affiche : 10

source.set(20);
System.out.println(destination.get());  // Affiche : 20 (automatiquement mis à jour)
```

**Important** : Une property bindée est **en lecture seule**. Vous ne pouvez plus faire `destination.set()`.

### Binding bidirectionnel

**Les deux properties se synchronisent mutuellement.**

```java
StringProperty champ1 = new SimpleStringProperty("Hello");
StringProperty champ2 = new SimpleStringProperty();

// Binding bidirectionnel
champ1.bindBidirectional(champ2);

champ1.set("Bonjour");
System.out.println(champ2.get());  // Affiche : "Bonjour"

champ2.set("Salut");
System.out.println(champ1.get());  // Affiche : "Salut"
```

---

## Binding avec les éléments UI

### Exemple 1 : Label suit un compteur

```java
IntegerProperty compteur = new SimpleIntegerProperty(0);
Label label = new Label();

// Le texte du label suit le compteur
label.textProperty().bind(compteur.asString());

compteur.set(5);   // Le label affiche "5"
compteur.set(10);  // Le label affiche "10"
```

**Explication** :
- `label.textProperty()` : La property du texte du label
- `compteur.asString()` : Convertit IntegerProperty en StringProperty
- `.bind()` : Crée la connexion automatique

### Exemple 2 : TextField bidirectionnel

```java
StringProperty nomUtilisateur = new SimpleStringProperty("");
TextField textField = new TextField();

// Binding bidirectionnel
textField.textProperty().bindBidirectional(nomUtilisateur);

// Quand l'utilisateur tape dans le champ, nomUtilisateur change
// Quand nomUtilisateur change en code, le champ se met à jour
```

### Exemple 3 : Bouton activé/désactivé

```java
BooleanProperty estValide = new SimpleBooleanProperty(false);
Button submitButton = new Button("Valider");

// Le bouton est désactivé quand estValide est false
submitButton.disableProperty().bind(estValide.not());

estValide.set(false);  // Bouton désactivé
estValide.set(true);   // Bouton activé
```

**Méthode `.not()`** : Inverse la valeur booléenne.

---

## Transformations de Properties

### Conversions de type

```java
IntegerProperty nombre = new SimpleIntegerProperty(42);

// Convertir en String
StringProperty texte = nombre.asString();

// Convertir en Double
DoubleProperty decimal = nombre.asObject().asString().asDouble();
```

**Méthodes courantes** :
- `.asString()` : Convertit en StringProperty
- `.asObject()` : Convertit en ObjectProperty

### Opérations arithmétiques

```java
IntegerProperty a = new SimpleIntegerProperty(10);
IntegerProperty b = new SimpleIntegerProperty(5);

// Addition
NumberBinding somme = a.add(b);
System.out.println(somme.getValue());  // 15

// Soustraction
NumberBinding difference = a.subtract(b);
System.out.println(difference.getValue());  // 5

// Multiplication
NumberBinding produit = a.multiply(2);
System.out.println(produit.getValue());  // 20

// Division
NumberBinding quotient = a.divide(b);
System.out.println(quotient.getValue());  // 2
```

### Opérations logiques

```java
BooleanProperty condition1 = new SimpleBooleanProperty(true);
BooleanProperty condition2 = new SimpleBooleanProperty(false);

// ET logique
BooleanBinding et = condition1.and(condition2);
System.out.println(et.get());  // false

// OU logique
BooleanBinding ou = condition1.or(condition2);
System.out.println(ou.get());  // true

// NON logique
BooleanBinding non = condition1.not();
System.out.println(non.get());  // false
```

### Comparaisons

```java
IntegerProperty age = new SimpleIntegerProperty(25);

// Plus grand que
BooleanBinding majeur = age.greaterThanOrEqualTo(18);
System.out.println(majeur.get());  // true

// Égalité
BooleanBinding estVingtCinq = age.isEqualTo(25);
System.out.println(estVingtCinq.get());  // true

// Plus petit que
BooleanBinding jeune = age.lessThan(30);
System.out.println(jeune.get());  // true
```

---

## Bindings personnalisés

### Avec Bindings.createXXXBinding()

```java
IntegerProperty largeur = new SimpleIntegerProperty(100);
IntegerProperty hauteur = new SimpleIntegerProperty(50);

// Calculer l'aire automatiquement
IntegerBinding aire = Bindings.createIntegerBinding(
    () -> largeur.get() * hauteur.get(),
    largeur, hauteur
);

System.out.println(aire.get());  // 5000

largeur.set(200);
System.out.println(aire.get());  // 10000 (mis à jour automatiquement)
```

**Paramètres** :
1. Lambda qui calcule la valeur
2. Liste des properties dont dépend le calcul

### Avec when().then().otherwise()

```java
IntegerProperty age = new SimpleIntegerProperty(20);

StringBinding categorie = Bindings.when(age.lessThan(18))
    .then("Mineur")
    .otherwise("Majeur");

System.out.println(categorie.get());  // "Majeur"

age.set(15);
System.out.println(categorie.get());  // "Mineur"
```

**Structure** : `when(condition).then(valeur_si_vrai).otherwise(valeur_si_faux)`

---

## Exemple complet : Calculateur simple

```java
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculateur extends Application {
    
    @Override
    public void start(Stage stage) {
        // Properties pour les deux nombres
        IntegerProperty nombre1 = new SimpleIntegerProperty(0);
        IntegerProperty nombre2 = new SimpleIntegerProperty(0);
        
        // Binding pour la somme (automatique)
        IntegerBinding somme = nombre1.add(nombre2);
        
        // Interface
        TextField field1 = new TextField("0");
        TextField field2 = new TextField("0");
        Label resultat = new Label();
        
        // Binding bidirectionnel avec conversion String ↔ Integer
        field1.textProperty().addListener((obs, old, newVal) -> {
            try {
                nombre1.set(Integer.parseInt(newVal));
            } catch (NumberFormatException e) {
                nombre1.set(0);
            }
        });
        
        field2.textProperty().addListener((obs, old, newVal) -> {
            try {
                nombre2.set(Integer.parseInt(newVal));
            } catch (NumberFormatException e) {
                nombre2.set(0);
            }
        });
        
        // Le résultat suit automatiquement la somme
        resultat.textProperty().bind(
            Bindings.concat("Résultat : ", somme.asString())
        );
        
        VBox root = new VBox(10,
            new Label("Nombre 1:"), field1,
            new Label("Nombre 2:"), field2,
            resultat
        );
        
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Calculateur");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Properties dans un ViewModel

C'est ici que le pattern MVVM prend tout son sens.

### Structure typique

```java
public class PersonneViewModel {
    
    // Properties privées
    private final StringProperty nom = new SimpleStringProperty("");
    private final IntegerProperty age = new SimpleIntegerProperty(0);
    private final BooleanProperty majeur = new SimpleBooleanProperty(false);
    
    // Constructeur
    public PersonneViewModel() {
        // Le statut majeur se calcule automatiquement
        majeur.bind(age.greaterThanOrEqualTo(18));
    }
    
    // Getters des Properties (pour le binding)
    public StringProperty nomProperty() {
        return nom;
    }
    
    public IntegerProperty ageProperty() {
        return age;
    }
    
    public BooleanProperty majeurProperty() {
        return majeur;
    }
    
    // Getters/Setters de valeurs (optionnels)
    public String getNom() {
        return nom.get();
    }
    
    public void setNom(String value) {
        nom.set(value);
    }
    
    public int getAge() {
        return age.get();
    }
    
    public void setAge(int value) {
        age.set(value);
    }
    
    public boolean isMajeur() {
        return majeur.get();
    }
}
```

### Utilisation dans le Controller

```java
public class PersonneController {
    
    @FXML
    private TextField nomField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private Label statusLabel;
    
    private PersonneViewModel viewModel;
    
    @FXML
    public void initialize() {
        viewModel = new PersonneViewModel();
        
        // Binding bidirectionnel pour le nom
        nomField.textProperty().bindBidirectional(viewModel.nomProperty());
        
        // Binding pour l'âge (avec conversion)
        ageField.textProperty().addListener((obs, old, newVal) -> {
            try {
                viewModel.setAge(Integer.parseInt(newVal));
            } catch (NumberFormatException e) {
                viewModel.setAge(0);
            }
        });
        
        // Le status suit automatiquement le calcul de majeur
        statusLabel.textProperty().bind(
            Bindings.when(viewModel.majeurProperty())
                .then("Majeur")
                .otherwise("Mineur")
        );
    }
}
```

---

## Débinder (détacher)

### Unbind

```java
IntegerProperty source = new SimpleIntegerProperty(10);
IntegerProperty destination = new SimpleIntegerProperty();

destination.bind(source);
System.out.println(destination.get());  // 10

// Détacher le binding
destination.unbind();

// Maintenant destination ne suit plus source
source.set(20);
System.out.println(destination.get());  // Toujours 10
```

### UnbindBidirectional

```java
StringProperty prop1 = new SimpleStringProperty("A");
StringProperty prop2 = new SimpleStringProperty("B");

prop1.bindBidirectional(prop2);

// Détacher
prop1.unbindBidirectional(prop2);
```

---

## Exercice pratique : Formulaire d'inscription

### Objectif

Créer un formulaire où :
- Un champ email
- Un champ confirmation email
- Un bouton "S'inscrire"
- Le bouton est activé seulement si :
  - L'email n'est pas vide
  - Les deux emails correspondent
  - L'email contient "@"

### Solution

```java
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormulaireInscription extends Application {
    
    @Override
    public void start(Stage stage) {
        // Properties
        StringProperty email = new SimpleStringProperty("");
        StringProperty confirmEmail = new SimpleStringProperty("");
        
        // Interface
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        
        TextField confirmField = new TextField();
        confirmField.setPromptText("Confirmer email");
        
        Button submitButton = new Button("S'inscrire");
        Label statusLabel = new Label();
        
        // Bindings bidirectionnels
        emailField.textProperty().bindBidirectional(email);
        confirmField.textProperty().bindBidirectional(confirmEmail);
        
        // Conditions de validation
        BooleanBinding emailNonVide = email.isNotEmpty();
        BooleanBinding emailValide = email.greaterThan("");  // Simplifié
        BooleanBinding emailsCorrespondent = email.isEqualTo(confirmEmail);
        BooleanBinding contientArobase = email.greaterThan("");
        
        // Le bouton est activé si toutes les conditions sont vraies
        BooleanBinding formulaireValide = emailNonVide
            .and(emailsCorrespondent)
            .and(email.length().greaterThan(3));
        
        submitButton.disableProperty().bind(formulaireValide.not());
        
        // Message de status
        statusLabel.textProperty().bind(
            Bindings.when(email.isEmpty())
                .then("Entrez votre email")
            .otherwise(
                Bindings.when(confirmEmail.isEmpty())
                    .then("Confirmez votre email")
                .otherwise(
                    Bindings.when(emailsCorrespondent.not())
                        .then("Les emails ne correspondent pas")
                    .otherwise("Formulaire valide")
                )
            )
        );
        
        // Action du bouton
        submitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription");
            alert.setContentText("Inscription réussie pour : " + email.get());
            alert.showAndWait();
        });
        
        VBox root = new VBox(10,
            new Label("Inscription"),
            emailField,
            confirmField,
            statusLabel,
            submitButton
        );
        root.setPadding(new javafx.geometry.Insets(20));
        
        Scene scene = new Scene(root, 350, 250);
        stage.setScene(scene);
        stage.setTitle("Formulaire");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Points clés à retenir

1. **Property** = Variable observable qui notifie les changements
2. **Binding unidirectionnel** = A suit B automatiquement
3. **Binding bidirectionnel** = A et B se synchronisent mutuellement
4. **Transformations** = Conversions et opérations sur les properties
5. **ViewModel** = Utilise des Properties pour exposer les données
6. **Controller** = Configure les bindings entre View et ViewModel

---

## Tableau récapitulatif des méthodes

| Opération | Méthode | Exemple |
|-----------|---------|---------|
| Lire | `.get()` | `int v = prop.get();` |
| Écrire | `.set(value)` | `prop.set(10);` |
| Binding uni | `.bind(other)` | `dest.bind(source);` |
| Binding bi | `.bindBidirectional(other)` | `a.bindBidirectional(b);` |
| Détacher | `.unbind()` | `prop.unbind();` |
| Écouter | `.addListener()` | `prop.addListener(...);` |
| Convertir | `.asString()` | `StringProperty s = i.asString();` |

---

## Prochaine leçon

Dans la prochaine leçon, nous verrons comment **structurer une application JavaFX complète** avec MVVM, incluant plusieurs vues et navigation.

**Fichier suivant** : `03-ARCHITECTURE_APPLICATION.md`

