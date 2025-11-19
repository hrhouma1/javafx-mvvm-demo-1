# FXML Expliqué de A à Z

## Qu'est-ce que FXML ?

**FXML** est un langage pour **décrire une interface graphique en XML** au lieu de le faire en Java.

### Analogie simple

Imaginez construire une maison :

**Approche classique (Java pur)** :
```
"Je veux un mur de 3m de haut, puis une porte de 2m, puis..."
Vous construisez tout en parlant, dans l'ordre.
```

**Approche FXML** :
```
Vous dessinez d'abord un plan sur papier (le fichier FXML),
puis vous le donnez aux constructeurs (JavaFX) qui construisent la maison.
```

### Comparaison concrète

**Avec Java pur** :
```java
VBox root = new VBox(10);
Label label = new Label("Bonjour");
Button button = new Button("Cliquer");
root.getChildren().addAll(label, button);
```

**Avec FXML** :
```xml
<VBox spacing="10">
    <Label text="Bonjour"/>
    <Button text="Cliquer"/>
</VBox>
```

Le résultat est **exactement le même**, mais FXML est plus clair et lisible.

---

## Pourquoi utiliser FXML ?

### Avantages

1. **Séparation du design et du code**
   - Le designer peut modifier l'interface sans toucher au code Java
   - Le développeur peut modifier la logique sans casser l'interface

2. **Lisibilité**
   - On voit la structure hiérarchique clairement
   - Moins de code Java verbeux

3. **Maintenance**
   - Plus facile de modifier l'interface
   - Moins de risque d'erreurs

4. **Outils graphiques**
   - Scene Builder permet de créer l'interface visuellement
   - Comme un éditeur WYSIWYG (What You See Is What You Get)

### Inconvénients

1. **Fichier supplémentaire** à gérer
2. **Courbe d'apprentissage** pour la syntaxe XML
3. **Debugging** moins direct qu'avec du Java pur

---

## Structure d'un fichier FXML

### Le strict minimum

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.Label?>

<VBox xmlns="http://javafx.com/javafx/21">
    <Label text="Hello World"/>
</VBox>
```

### Décomposition ligne par ligne

#### 1. Déclaration XML
```xml
<?xml version="1.0" encoding="UTF-8"?>
```
**Signification** : "Ce fichier est du XML, encodé en UTF-8"  
**Obligatoire** : Oui, toujours en première ligne.

#### 2. Imports
```xml
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.Label?>
```
**Signification** : "J'ai besoin d'utiliser VBox et Label"  
**Comme en Java** : Équivalent à `import javafx.scene.layout.VBox;`  
**Obligatoire** : Oui, pour chaque classe utilisée.

#### 3. Élément racine
```xml
<VBox xmlns="http://javafx.com/javafx/21">
```
**Signification** : "Le conteneur principal est un VBox"  
**xmlns** : Déclaration de l'espace de noms (namespace) JavaFX  
**Obligatoire** : Un et un seul élément racine.

#### 4. Éléments enfants
```xml
<Label text="Hello World"/>
```
**Signification** : "Un Label avec le texte 'Hello World'"  
**Syntaxe courte** : `<Label/>` ferme directement si pas d'enfants.

---

## Correspondance FXML ↔ Java

### Exemple 1 : Créer un élément

**Java** :
```java
Label label = new Label("Bonjour");
```

**FXML** :
```xml
<Label text="Bonjour"/>
```

**Explication** : `text="Bonjour"` appelle `label.setText("Bonjour")`

### Exemple 2 : Élément avec plusieurs propriétés

**Java** :
```java
Button button = new Button("Cliquer");
button.setPrefWidth(200);
button.setPrefHeight(50);
```

**FXML** :
```xml
<Button text="Cliquer" prefWidth="200" prefHeight="50"/>
```

**Règle** : Chaque méthode `setXXX()` devient un attribut `xxx=""` en FXML.

### Exemple 3 : Hiérarchie (parent-enfants)

**Java** :
```java
VBox vbox = new VBox(10);
Label label = new Label("Titre");
Button button = new Button("Valider");
vbox.getChildren().addAll(label, button);
```

**FXML** :
```xml
<VBox spacing="10">
    <Label text="Titre"/>
    <Button text="Valider"/>
</VBox>
```

**Explication** : Les éléments à l'intérieur de `<VBox>...</VBox>` sont automatiquement ajoutés avec `getChildren().add()`.

---

## Les attributs fx:id et fx:controller

### fx:id : Identifier un élément

Le `fx:id` permet de **référencer un élément depuis le code Java**.

**FXML** :
```xml
<Label fx:id="messageLabel" text="Bonjour"/>
<Button fx:id="validateButton" text="Valider"/>
```

**Java (Controller)** :
```java
@FXML
private Label messageLabel;

@FXML
private Button validateButton;
```

**Important** : Le nom du `fx:id` doit être **exactement le même** que la variable Java.

### fx:controller : Lier un Controller

Le `fx:controller` indique quelle classe Java contrôle cette interface.

**FXML** :
```xml
<VBox xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.exemple.MonController">
    <Label fx:id="label" text="Hello"/>
</VBox>
```

**Java** :
```java
package com.exemple;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MonController {
    
    @FXML
    private Label label;
    
    @FXML
    public void initialize() {
        // Appelé automatiquement après le chargement du FXML
        label.setText("Initialisé !");
    }
}
```

---

## Gérer les événements

### Méthode 1 : onAction dans FXML

**FXML** :
```xml
<Button text="Cliquer" onAction="#handleButtonClick"/>
```

**Java** :
```java
@FXML
private void handleButtonClick(ActionEvent event) {
    System.out.println("Bouton cliqué !");
}
```

**Important** :
- Le `#` devant le nom de la méthode est obligatoire
- La méthode doit être annotée `@FXML`
- Le nom doit correspondre exactement

### Méthode 2 : Définir l'action dans le Controller

**FXML** :
```xml
<Button fx:id="myButton" text="Cliquer"/>
```

**Java** :
```java
@FXML
private Button myButton;

@FXML
public void initialize() {
    myButton.setOnAction(event -> {
        System.out.println("Bouton cliqué !");
    });
}
```

### Quelle méthode choisir ?

- **Méthode 1** : Actions simples, déclarées dans FXML
- **Méthode 2** : Logique complexe, plus de contrôle dans Java

---

## Charger un fichier FXML

### Étape par étape

**1. Créer le fichier FXML**

Fichier : `resources/com/exemple/MainView.fxml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.*?>

<VBox xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.exemple.MainController"
      spacing="10">
    <Label fx:id="titleLabel" text="Mon Application"/>
    <Button fx:id="actionButton" text="Action" onAction="#handleAction"/>
</VBox>
```

**2. Créer le Controller**

Fichier : `src/com/exemple/MainController.java`
```java
package com.exemple;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class MainController {
    
    @FXML
    private Label titleLabel;
    
    @FXML
    public void initialize() {
        System.out.println("Controller initialisé");
    }
    
    @FXML
    private void handleAction(ActionEvent event) {
        titleLabel.setText("Action effectuée !");
    }
}
```

**3. Charger dans l'application principale**

Fichier : `src/com/exemple/MainApp.java`
```java
package com.exemple;

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
            getClass().getResource("/com/exemple/MainView.fxml")
        );
        
        Parent root = loader.load();
        
        // Créer la scène
        Scene scene = new Scene(root, 400, 300);
        
        // Configurer et afficher la fenêtre
        primaryStage.setTitle("Mon Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Comprendre le cycle de chargement

### Ordre des opérations

```
1. Application démarre
   ↓
2. FXMLLoader.load() est appelé
   ↓
3. FXML est parsé (analysé)
   ↓
4. Les objets Java sont créés (Label, Button, etc.)
   ↓
5. Le Controller est instancié
   ↓
6. Les @FXML sont injectés (fx:id → variables Java)
   ↓
7. initialize() est appelé
   ↓
8. L'interface est prête
```

### Méthode initialize()

```java
@FXML
public void initialize() {
    // Cette méthode est appelée APRÈS que tous les @FXML
    // ont été injectés, mais AVANT que l'interface soit affichée.
    
    // C'est ici qu'on configure les bindings,
    // charge des données, etc.
}
```

**Moment d'appel** : Automatique, après injection des @FXML  
**Paramètres** : Aucun  
**Nom** : Doit être exactement `initialize`

---

## Les espaces de noms (namespace)

### xmlns (XML NameSpace)

```xml
<VBox xmlns="http://javafx.com/javafx/21"
      xmlns:fx="http://javafx.com/fxml/1">
```

**xmlns** : Espace de noms par défaut (JavaFX)  
**xmlns:fx** : Espace de noms pour les attributs spéciaux FXML

### Attributs avec fx:

- `fx:id` : Identifiant pour le Controller
- `fx:controller` : Classe Controller
- `fx:root` : Type de l'élément racine (avancé)
- `fx:factory` : Méthode factory (avancé)

---

## Layouts complexes en FXML

### BorderPane

```xml
<BorderPane>
    <top>
        <HBox>
            <Button text="Menu 1"/>
            <Button text="Menu 2"/>
        </HBox>
    </top>
    
    <center>
        <Label text="Contenu principal"/>
    </center>
    
    <bottom>
        <Label text="Barre de status"/>
    </bottom>
</BorderPane>
```

**Mots-clés spéciaux** : `<top>`, `<bottom>`, `<left>`, `<right>`, `<center>`

### GridPane (grille)

```xml
<GridPane hgap="10" vgap="10">
    <Label text="Nom:" GridPane.rowIndex="0" GridPane.columnIndex="0"/>
    <TextField GridPane.rowIndex="0" GridPane.columnIndex="1"/>
    
    <Label text="Email:" GridPane.rowIndex="1" GridPane.columnIndex="0"/>
    <TextField GridPane.rowIndex="1" GridPane.columnIndex="1"/>
    
    <Button text="Valider" GridPane.rowIndex="2" GridPane.columnIndex="1"/>
</GridPane>
```

**GridPane.rowIndex** : Ligne (commence à 0)  
**GridPane.columnIndex** : Colonne (commence à 0)

---

## Propriétés avec des valeurs complexes

### Tailles

```xml
<Button text="OK" prefWidth="200" prefHeight="50"/>
```

### Marges et padding

```xml
<VBox padding="10">
    <Label text="Avec marge">
        <VBox.margin>
            <Insets top="5" right="10" bottom="5" left="10"/>
        </VBox.margin>
    </Label>
</VBox>
```

### Alignement

```xml
<VBox alignment="CENTER">
    <Label text="Centré"/>
</VBox>
```

Valeurs possibles : `TOP_LEFT`, `TOP_CENTER`, `TOP_RIGHT`, `CENTER_LEFT`, `CENTER`, `CENTER_RIGHT`, `BOTTOM_LEFT`, `BOTTOM_CENTER`, `BOTTOM_RIGHT`

---

## Inclure un fichier FXML dans un autre

### fx:include

```xml
<!-- Fichier principal : MainView.fxml -->
<VBox>
    <fx:include source="Header.fxml"/>
    <Label text="Contenu principal"/>
    <fx:include source="Footer.fxml"/>
</VBox>
```

**Avantage** : Réutiliser des composants communs (menu, footer, etc.)

---

## Exercice pratique : Formulaire de contact

### Objectif

Créer un formulaire avec :
- Champs : Nom, Email, Message
- Bouton "Envoyer"
- Afficher un message de confirmation

### FXML

Fichier : `ContactForm.fxml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.control.*?>
<?import javafx.geometry.Insets?>

<VBox xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.exemple.ContactController"
      spacing="10" padding="20">
    
    <Label text="Formulaire de Contact" style="-fx-font-size: 18px; -fx-font-weight: bold;"/>
    
    <Label text="Nom:"/>
    <TextField fx:id="nameField" promptText="Votre nom"/>
    
    <Label text="Email:"/>
    <TextField fx:id="emailField" promptText="votre@email.com"/>
    
    <Label text="Message:"/>
    <TextArea fx:id="messageArea" prefRowCount="4" promptText="Votre message..."/>
    
    <Button text="Envoyer" onAction="#handleSubmit"/>
    
    <Label fx:id="statusLabel" text=""/>
</VBox>
```

### Controller

Fichier : `ContactController.java`
```java
package com.exemple;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ContactController {
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextArea messageArea;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    public void initialize() {
        statusLabel.setText("Remplissez le formulaire");
    }
    
    @FXML
    private void handleSubmit() {
        String nom = nameField.getText();
        String email = emailField.getText();
        String message = messageArea.getText();
        
        if (nom.isEmpty() || email.isEmpty() || message.isEmpty()) {
            statusLabel.setText("Veuillez remplir tous les champs");
            return;
        }
        
        // Simuler l'envoi
        statusLabel.setText("Message envoyé avec succès !");
        
        // Réinitialiser les champs
        nameField.clear();
        emailField.clear();
        messageArea.clear();
    }
}
```

---

## Points clés à retenir

1. **FXML** = XML pour décrire l'interface graphique
2. **fx:id** = Identifie un élément pour le Controller
3. **fx:controller** = Lie la vue au Controller Java
4. **@FXML** = Annotation pour l'injection et les méthodes d'action
5. **initialize()** = Méthode appelée après le chargement
6. **FXMLLoader** = Classe qui charge le fichier FXML

---

## Erreurs courantes et solutions

### Erreur 1 : "Location is not set"

**Cause** : Le chemin vers le fichier FXML est incorrect.

**Solution** :
```java
// Vérifiez que le fichier est dans resources/
getClass().getResource("/com/exemple/MainView.fxml")
```

### Erreur 2 : "fx:id is not injected"

**Cause** : Le nom du fx:id ne correspond pas à la variable Java.

**Solution** : Vérifiez l'orthographe exacte.

### Erreur 3 : "javafx.fxml.LoadException"

**Cause** : Erreur de syntaxe dans le FXML ou import manquant.

**Solution** : Vérifiez tous les `<?import ...?>` et la structure XML.

---

## Prochaine leçon

Dans la prochaine leçon, nous verrons les **Properties et le Binding** : comment synchroniser automatiquement les données entre les éléments.

**Fichier suivant** : `02-PROPERTIES_BINDING.md`

