
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.demo</groupId>
    <artifactId>mvvm-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>MVVM Pattern Demo</name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <javafx.version>21.0.1</javafx.version>
    </properties>

    <dependencies>
        <!-- JavaFX -->
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
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <configuration>
                    <mainClass>com.demo.mvvm.MainApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```


Voici une explication **ultra détaillée**, ligne par ligne, de tout ce fichier `pom.xml`.
Aucune simplification. Chaque partie est décortiquée pour comprendre exactement *pourquoi elle existe* et *comment Maven l’utilise*.

---

# 1. En-tête XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
```

Cette ligne indique :

* Le fichier est un document XML version 1.0.
* Les caractères sont encodés en UTF-8 (tous les caractères internationaux sont correctement traités).

Elle est obligatoire dans tous les fichiers XML.

---

# 2. Déclaration du projet Maven

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
```

Cette portion définit **l’espace de noms XML** utilisé par Maven.

Explications :

* `xmlns="http://maven.apache.org/POM/4.0.0"`
  Indique que le document suit le schéma officiel d’un POM Maven version 4.0.0.

* `xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"`
  Ajoute le support pour les schémas XML (XSD).

* `xsi:schemaLocation="..."`
  Indique *où* se trouve le schéma XSD utilisé pour valider le fichier POM.

En résumé :
C’est la déclaration technique qui permet à Maven (et aux IDE comme IntelliJ, VSCode, Eclipse) de vérifier la validité structurelle du POM.

---

# 3. Version du modèle POM

```xml
<modelVersion>4.0.0</modelVersion>
```

Signifie :
Le fichier suit les règles du modèle Maven **4.0.0**, la version standard actuelle.

C’est statique.
Tous les projets Maven modernes utilisent cette valeur.

---

# 4. Identification du projet

```xml
<groupId>com.demo</groupId>
<artifactId>mvvm-demo</artifactId>
<version>1.0-SNAPSHOT</version>
<name>MVVM Pattern Demo</name>
```

Ces quatre lignes identifient ton projet.

Détails :

### groupId

Identité de l’organisation ou de toi.

Exemples :

* `com.demo`
* `com.company.app`
* `io.github.hrhouma`

Fonctionne comme un namespace Java.

### artifactId

Nom du projet (le “paquet” généré).

Ici : `mvvm-demo`

Le fichier final aura le nom :

```
mvvm-demo-1.0-SNAPSHOT.jar
```

### version

Version du projet.

* `1.0` = stable
* `1.0.3` = patches
* `1.0-SNAPSHOT` = version de développement

Maven traite un *SNAPSHOT* comme un projet qui change fréquemment.

### name

Nom lisible du projet.
Utilisé par les IDE, pas par Maven.

---

# 5. Section `<properties>`

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <javafx.version>21.0.1</javafx.version>
</properties>
```

Cette section centralise les variables utilisées partout dans le POM.

Détails :

### project.build.sourceEncoding

Définit l’encodage des fichiers source.
UTF-8 est standard aujourd’hui.

### maven.compiler.source / target

Version du JDK utilisée pour compiler.

Ici : Java 17.

`source` : syntaxe autorisée
`target` : bytecode généré

### javafx.version

Version JavaFX utilisée par les dépendances.

Permet :

* de changer facilement la version JavaFX à un seul endroit
* d’éviter la duplication de la version dans plusieurs dépendances

---

# 6. Les dépendances JavaFX

```xml
<dependencies>
    <!-- JavaFX -->
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
```

Ces deux dépendances sont indispensables pour une application JavaFX moderne.

### javafx-controls

Contient :

* Button
* Label
* TextField
* TableView
* Scene
* Stage
* Layouts (VBox, HBox, GridPane)

### javafx-fxml

Contient :

* Loader FXML (`FXMLLoader`)
* Support des fichiers `.fxml`
* Injection des contrôleurs

Les deux utilisent la même version définie dans `${javafx.version}`.

---

# 7. La section `<build>`

```xml
<build>
    <plugins>
        ...
    </plugins>
</build>
```

Cette section contient les plugins nécessaires à la compilation et à l’exécution.

---

# 8. Plugin 1 : maven-compiler-plugin

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
        <source>17</source>
        <target>17</target>
    </configuration>
</plugin>
```

Ce plugin dit à Maven :

* d’utiliser le compilateur Java officiel (`javac`)
* de compiler en Java 17

Il confirme explicitement ce qui est déjà dit dans `<properties>`, ce qui garantit la compatibilité même dans d'anciens IDE.

Il sert à :

* vérification des erreurs de syntaxe
* production du bytecode `.class`
* optimisation de compilation

---

# 9. Plugin 2 : javafx-maven-plugin

```xml
<plugin>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-maven-plugin</artifactId>
    <version>0.0.8</version>
    <configuration>
        <mainClass>com.demo.mvvm.MainApp</mainClass>
    </configuration>
</plugin>
```

C’est le plugin essentiel pour exécuter une application JavaFX.

Il a trois rôles :

### 1. Charger dynamiquement les modules JavaFX

JavaFX est modulaire (modules séparés).
Sans plugin, Maven ne sait pas les charger correctement.

### 2. Gérer les options `--module-path` et `--add-modules`

Normalement pour JavaFX tu dois exécuter :

```
java --module-path /lib/javafx --add-modules javafx.controls,javafx.fxml MainApp
```

Le plugin le fait automatiquement.

### 3. Exécuter l'application JavaFX

Commandes :

```
mvn javafx:run
```

### mainClass

Chemin complet vers ta classe principale JavaFX :

```
package com.demo.mvvm;

public class MainApp extends Application { ... }
```

Sans cette information, Maven ne peut pas lancer l'application.

---

# 10. Fermeture du fichier

```xml
</project>
```

Fin du POM.

---

# Résumé global

Ce `pom.xml` :

1. identifie le projet
2. définit la version Java
3. importe JavaFX (controls + fxml)
4. configure la compilation
5. configure l'exécution JavaFX depuis Maven
6. centralise la version JavaFX dans une propriété
7. utilise Java 17 comme standard

C’est un fichier propre, complet et parfaitement adapté à une application JavaFX MVVM structurée.




