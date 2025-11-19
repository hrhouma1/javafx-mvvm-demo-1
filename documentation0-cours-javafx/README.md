# Cours Complet JavaFX - De Zéro à Expert

## Introduction

Bienvenue dans ce cours complet sur JavaFX. Ce cours est conçu pour vous apprendre JavaFX **depuis les bases absolues** jusqu'aux concepts avancés et au pattern architectural MVVM.

### À qui s'adresse ce cours ?

- Développeurs Java connaissant les bases du langage
- Étudiants en informatique
- Développeurs souhaitant créer des applications desktop modernes
- Toute personne curieuse d'apprendre JavaFX

### Prérequis

- Connaissance de base de Java (classes, objets, héritage)
- Connaissance de Maven (ou Gradle)
- Un IDE (IntelliJ IDEA, Eclipse, ou VS Code)
- Java 17 ou supérieur installé

---

## Plan du cours

Ce cours est divisé en **5 leçons progressives** :

### Leçon 0 : Introduction à JavaFX
**Fichier** : `00-INTRODUCTION.md`  
**Durée estimée** : 2-3 heures  
**Concepts** :
- Qu'est-ce que JavaFX ?
- Structure d'une application JavaFX
- Stage, Scene, Node
- Layouts de base (VBox, HBox, BorderPane)
- Événements simples
- Premier programme complet

**Ce que vous saurez faire** :
- Créer une fenêtre avec des boutons et labels
- Gérer les clics de boutons
- Organiser les éléments visuellement

---

### Leçon 1 : FXML Expliqué
**Fichier** : `01-FXML_EXPLIQUE.md`  
**Durée estimée** : 3-4 heures  
**Concepts** :
- Qu'est-ce que FXML et pourquoi l'utiliser ?
- Syntaxe XML pour JavaFX
- fx:id et fx:controller
- FXMLLoader
- Événements dans FXML
- Méthode initialize()
- Layouts complexes en FXML

**Ce que vous saurez faire** :
- Séparer l'interface du code Java
- Créer des interfaces dans des fichiers FXML
- Connecter FXML et Java avec un Controller
- Charger dynamiquement des vues

---

### Leçon 2 : Properties et Binding
**Fichier** : `02-PROPERTIES_BINDING.md`  
**Durée estimée** : 4-5 heures  
**Concepts** :
- Qu'est-ce qu'une Property ?
- Types de Properties (Integer, String, Boolean, etc.)
- Binding unidirectionnel
- Binding bidirectionnel
- Transformations et opérations
- Bindings personnalisés
- Listeners

**Ce que vous saurez faire** :
- Synchroniser automatiquement les données
- Créer des interfaces réactives
- Valider des formulaires en temps réel
- Utiliser le data binding comme un expert

---

### Leçon 3 : Architecture d'Application
**Fichier** : `03-ARCHITECTURE_APPLICATION.md`  
**Durée estimée** : 5-6 heures  
**Concepts** :
- Pattern MVVM (Model-View-ViewModel)
- Structure d'un projet professionnel
- Séparation des responsabilités
- Model, ViewModel, View
- Navigation entre vues
- Tests unitaires du ViewModel
- Persistance des données

**Ce que vous saurez faire** :
- Structurer une application professionnelle
- Implémenter le pattern MVVM
- Tester votre code facilement
- Gérer plusieurs écrans
- Sauvegarder et charger des données

---

### Leçon 4 : Exercices Pratiques
**Fichier** : `04-EXERCICES_PRATIQUES.md`  
**Durée estimée** : 10-20 heures (selon votre rythme)  
**Contenu** :
- 8 exercices progressifs du niveau débutant à expert
- Solutions complètes
- Projets concrets (Todo List, Gestionnaire de contacts, Notes, etc.)

**Ce que vous saurez faire** :
- Appliquer tous les concepts appris
- Créer des applications complètes
- Résoudre des problèmes réels
- Développer votre portfolio

---

## Ordre d'apprentissage recommandé

```
┌─────────────────────────┐
│  00-INTRODUCTION.md     │  Bases fondamentales
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│  01-FXML_EXPLIQUE.md    │  Séparation View/Code
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│  02-PROPERTIES_BINDING  │  Synchronisation auto
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│  03-ARCHITECTURE_APP    │  Structure pro (MVVM)
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│  04-EXERCICES_PRATIQUES │  Mise en pratique
└─────────────────────────┘
```

**Important** : Ne sautez pas d'étapes. Chaque leçon s'appuie sur la précédente.

---

## Méthodologie d'apprentissage

### Pour chaque leçon

1. **Lecture** (30-40%)
   - Lisez attentivement les explications
   - Comprenez les concepts avant de coder

2. **Pratique** (40-50%)
   - Tapez les exemples vous-même (ne copiez-collez pas)
   - Modifiez les exemples pour expérimenter
   - Faites les exercices proposés

3. **Révision** (10-20%)
   - Relisez les points clés
   - Notez ce qui n'est pas clair
   - Revoyez les exemples

### Conseils

- Prenez des pauses régulières
- N'hésitez pas à revenir en arrière si nécessaire
- Créez vos propres mini-projets pour solidifier la compréhension
- Lisez le code d'autres personnes (GitHub)

---

## Configuration de votre environnement

### 1. Vérifier Java

```bash
java -version
# Doit afficher Java 17 ou supérieur
```

Si pas installé : https://adoptium.net/

### 2. Créer un projet Maven

Fichier `pom.xml` :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.monprojet</groupId>
    <artifactId>javafx-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <javafx.version>21.0.1</javafx.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
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
                    <mainClass>com.monprojet.MainApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3. Lancer votre application

```bash
mvn clean javafx:run
```

---

## Outils recommandés

### IDE

- **IntelliJ IDEA** (recommandé) - Support excellent de JavaFX
- **Eclipse** avec plugin e(fx)clipse
- **Visual Studio Code** avec extensions Java et JavaFX

### Scene Builder

Outil graphique pour créer des fichiers FXML visuellement.

Téléchargement : https://gluonhq.com/products/scene-builder/

---

## Structure recommandée de projet

```
mon-projet-javafx/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/monprojet/
│   │   │       ├── MainApp.java
│   │   │       ├── model/
│   │   │       ├── viewmodel/
│   │   │       └── view/
│   │   └── resources/
│   │       └── com/monprojet/
│   │           ├── view/
│   │           │   ├── *.fxml
│   │           │   └── styles.css
│   │           └── images/
│   └── test/
│       └── java/
└── target/
```

---

## Ressources supplémentaires

### Documentation officielle

- JavaFX Documentation : https://openjfx.io/
- JavaDoc API : https://openjfx.io/javadoc/21/

### Tutoriels

- Oracle JavaFX Tutorial : https://docs.oracle.com/javafx/2/
- JavaFX CSS Reference : https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/doc-files/cssref.html

### Communauté

- Stack Overflow (tag : javafx)
- Reddit : r/javaFX
- Forum OpenJFX : https://github.com/openjdk/jfx/discussions

---

## Contributions et Feedback

Ce cours est un travail en évolution. Si vous trouvez des erreurs, des imprécisions ou avez des suggestions d'amélioration, n'hésitez pas à contribuer.

---

## Licence

Ce cours est fourni à des fins éducatives. Vous êtes libre de l'utiliser, le modifier et le partager.

---

## Commencez maintenant !

Prêt à démarrer ? Ouvrez le premier fichier :

**[00-INTRODUCTION.md](00-INTRODUCTION.md)**

Bon apprentissage et amusez-vous bien avec JavaFX !

