# Introduction à JavaFX

## Qu'est-ce que JavaFX ?

JavaFX est une **plateforme moderne pour créer des applications graphiques** (avec interface utilisateur) en Java. C'est le successeur de Swing, l'ancienne bibliothèque graphique de Java.

### Analogie simple

Imaginez que vous voulez construire une maison :
- **Java** = les fondations et la structure
- **JavaFX** = les murs, les fenêtres, les portes, la décoration
- **FXML** = le plan architectural de la maison
- **CSS** = la peinture et le style

### Pourquoi JavaFX ?

JavaFX permet de créer des interfaces graphiques **modernes** avec :
- Des animations fluides
- Des effets visuels
- Une séparation claire entre le design et le code
- Un système de styling avec CSS (comme pour les sites web)

### Historique rapide

- **Avant 2008** : Swing (ancien, limité)
- **2008-2018** : JavaFX développé par Oracle
- **Depuis 2018** : JavaFX est un projet open-source indépendant

---

## Votre première application JavaFX

### Le strict minimum

Une application JavaFX a **toujours** cette structure de base :

```java
import javafx.application.Application;
import javafx.stage.Stage;

public class MonAppli extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // C'est ici que commence votre application
        primaryStage.setTitle("Ma première fenêtre");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Explication ligne par ligne

```java
public class MonAppli extends Application
```
**Traduction** : "Ma classe hérite de Application"  
**Signification** : JavaFX exige que votre classe principale hérite de `Application`. C'est comme dire "ma classe est une application JavaFX".

```java
public void start(Stage primaryStage)
```
**Traduction** : "Méthode qui démarre avec une scène principale"  
**Signification** : C'est le point d'entrée de votre interface graphique. Le `Stage` est votre fenêtre.

```java
launch(args);
```
**Traduction** : "Lance l'application"  
**Signification** : Cette méthode démarre JavaFX et appelle automatiquement `start()`.

---

## Les concepts fondamentaux

### 1. Stage (la Scène de théâtre)

Un **Stage** est une **fenêtre**.  
Comme au théâtre, c'est la scène où tout se passe.

```java
Stage stage = new Stage();
stage.setTitle("Mon titre");  // Le titre de la fenêtre
stage.setWidth(800);           // Largeur en pixels
stage.setHeight(600);          // Hauteur en pixels
```

### 2. Scene (le Décor)

Une **Scene** est le **contenu** de la fenêtre.  
C'est le décor sur la scène de théâtre.

```java
Scene scene = new Scene(contenu, largeur, hauteur);
stage.setScene(scene);  // On place le décor sur la scène
```

### 3. Node (les Acteurs)

Les **Nodes** sont les **éléments visuels** : boutons, labels, champs texte, etc.  
Ce sont les acteurs sur la scène.

```java
Button bouton = new Button("Cliquez-moi");
Label texte = new Label("Bonjour");
TextField champTexte = new TextField();
```

### Hiérarchie visuelle

```
Stage (Fenêtre)
  └── Scene (Contenu)
        └── Root Node (Conteneur principal)
              ├── Button (Bouton)
              ├── Label (Texte)
              └── TextField (Champ de saisie)
```

---

## Exemple complet commenté

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExempleComplet extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        // 1. CRÉER LES ÉLÉMENTS (Nodes)
        Label etiquette = new Label("Compteur : 0");
        Button bouton = new Button("Incrémenter");
        
        // 2. CRÉER UN CONTENEUR (Layout)
        // VBox = Vertical Box (empile les éléments verticalement)
        VBox conteneur = new VBox(10);  // 10 = espacement entre éléments
        conteneur.getChildren().addAll(etiquette, bouton);
        
        // 3. CRÉER LA SCÈNE (Scene)
        Scene scene = new Scene(conteneur, 300, 200);
        
        // 4. CONFIGURER LA FENÊTRE (Stage)
        primaryStage.setTitle("Mon compteur");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // 5. AJOUTER DU COMPORTEMENT
        final int[] compteur = {0};  // Variable pour stocker le compte
        bouton.setOnAction(event -> {
            compteur[0]++;
            etiquette.setText("Compteur : " + compteur[0]);
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Décomposition de l'exemple

**Étape 1 : Les éléments**
```java
Label etiquette = new Label("Compteur : 0");
Button bouton = new Button("Incrémenter");
```
Crée un texte et un bouton. Comme acheter des meubles pour votre maison.

**Étape 2 : Le conteneur**
```java
VBox conteneur = new VBox(10);
conteneur.getChildren().addAll(etiquette, bouton);
```
Place les éléments dans un conteneur vertical. Comme ranger vos meubles dans une pièce.

**Étape 3 : La scène**
```java
Scene scene = new Scene(conteneur, 300, 200);
```
Crée le contenu de la fenêtre avec une taille de 300x200 pixels.

**Étape 4 : La fenêtre**
```java
primaryStage.setTitle("Mon compteur");
primaryStage.setScene(scene);
primaryStage.show();
```
Configure et affiche la fenêtre.

**Étape 5 : L'interaction**
```java
bouton.setOnAction(event -> {
    compteur[0]++;
    etiquette.setText("Compteur : " + compteur[0]);
});
```
Définit ce qui se passe quand on clique sur le bouton.

---

## Les conteneurs (Layouts)

Les conteneurs organisent les éléments. Voici les principaux :

### VBox (Vertical Box)
Empile les éléments **verticalement** (un au-dessus de l'autre).

```
┌─────────────┐
│   Bouton 1  │
├─────────────┤
│   Bouton 2  │
├─────────────┤
│   Bouton 3  │
└─────────────┘
```

```java
VBox vbox = new VBox(10);  // 10 pixels d'espacement
vbox.getChildren().addAll(bouton1, bouton2, bouton3);
```

### HBox (Horizontal Box)
Place les éléments **horizontalement** (côte à côte).

```
┌─────────────────────────────┐
│ Bouton1 | Bouton2 | Bouton3 │
└─────────────────────────────┘
```

```java
HBox hbox = new HBox(10);
hbox.getChildren().addAll(bouton1, bouton2, bouton3);
```

### BorderPane
Divise l'espace en 5 zones : haut, bas, gauche, droite, centre.

```
┌─────────────────────┐
│        TOP          │
├──────┬───────┬──────┤
│      │       │      │
│ LEFT │CENTER │RIGHT │
│      │       │      │
├──────┴───────┴──────┤
│       BOTTOM        │
└─────────────────────┘
```

```java
BorderPane border = new BorderPane();
border.setTop(menuBar);
border.setCenter(contenu);
border.setBottom(statusBar);
```

---

## Configuration Maven pour JavaFX

Pour utiliser JavaFX, votre `pom.xml` doit contenir :

```xml
<properties>
    <javafx.version>21.0.1</javafx.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>${javafx.version}</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-maven-plugin</artifactId>
            <version>0.0.8</version>
            <configuration>
                <mainClass>com.exemple.MainApp</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Lancer l'application

```bash
mvn javafx:run
```

---

## Exercice pratique 1

Créez une application avec :
- Un label qui dit "Bienvenue"
- Un champ de texte pour entrer un nom
- Un bouton "Valider"
- Quand on clique, le label change pour dire "Bonjour [nom]"

### Solution

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercice1 extends Application {
    
    @Override
    public void start(Stage stage) {
        Label message = new Label("Bienvenue");
        TextField nomField = new TextField();
        nomField.setPromptText("Entrez votre nom");
        Button valider = new Button("Valider");
        
        valider.setOnAction(e -> {
            String nom = nomField.getText();
            message.setText("Bonjour " + nom);
        });
        
        VBox root = new VBox(10);
        root.getChildren().addAll(message, nomField, valider);
        
        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Exercice 1");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## Points clés à retenir

1. **Application** : Votre classe doit hériter de `Application`
2. **start()** : Le point d'entrée de votre interface
3. **Stage** : La fenêtre
4. **Scene** : Le contenu de la fenêtre
5. **Node** : Les éléments visuels (Button, Label, etc.)
6. **Layout** : Les conteneurs qui organisent les éléments (VBox, HBox, etc.)

---

## Prochaine leçon

Dans la prochaine leçon, nous verrons **FXML** : comment séparer l'interface graphique du code Java, comme HTML sépare la structure du JavaScript.

**Fichier suivant** : `01-FXML_EXPLIQUE.md`

