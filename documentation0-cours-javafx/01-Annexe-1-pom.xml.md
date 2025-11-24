# Explication du `pom.xml`

Le fichier `pom.xml` est le fichier de configuration principal de Maven.
Il décrit le projet, les dépendances, les versions, et les plugins nécessaires pour compiler et exécuter une application.

Voici le fichier utilisé :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.demo</groupId>
    <artifactId>javafx-demo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>

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
                    <mainClass>com.demo.MonAppli</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

---

# 1. Informations de base du projet

```xml
<groupId>com.demo</groupId>
<artifactId>javafx-demo</artifactId>
<version>1.0-SNAPSHOT</version>
```

* `groupId` : l'identité du développeur ou de l'organisation.
* `artifactId` : le nom du projet.
* `version` : version du projet.
  `SNAPSHOT` signifie que le projet est encore en développement.

Ces trois valeurs permettent d'identifier ton projet dans le référentiel Maven.

---

# 2. Propriétés du projet

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <javafx.version>21.0.1</javafx.version>
</properties>
```

Explication :

* `project.build.sourceEncoding` : encodage des fichiers source.
* `maven.compiler.source` et `maven.compiler.target` : version du JDK utilisée.
  Ici : Java 21.
* `javafx.version` : variable réutilisée dans les dépendances pour JavaFX.
  Avantage : si tu veux changer la version de JavaFX, tu la modifies ici seulement.

---

# 3. Dépendances

```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>${javafx.version}</version>
</dependency>
```

Cette section indique à Maven quelles bibliothèques sont nécessaires.

Ici, une seule dépendance :

* `javafx-controls`
  C'est le module JavaFX qui contient les éléments visuels : Button, Label, TextField, Scene, Stage, etc.

La version est référencée avec `${javafx.version}` pour éviter la duplication.

---

# 4. Plugin JavaFX (obligatoire pour exécuter)

```xml
<plugin>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-maven-plugin</artifactId>
    <version>0.0.8</version>
    <configuration>
        <mainClass>com.demo.MonAppli</mainClass>
    </configuration>
</plugin>
```

Rôle du plugin :

* Permet d’exécuter une application JavaFX via Maven.
* Permet à Maven de savoir où se trouve la classe principale avec `main()`.
* Charge dynamiquement les modules JavaFX nécessaires.

`<mainClass>` doit contenir le nom complet (package + classe) de ta classe principale.

Exemple :

```
src/main/java/com/demo/MonAppli.java
```

doit correspondre à :

```xml
<mainClass>com.demo.MonAppli</mainClass>
```

---

# 5. Exécution

Une fois tout en place :

```bash
mvn javafx:run
```

Ce plugin :

1. charge JavaFX
2. exécute ta classe principale
3. démarre l'interface graphique

---

# Résumé

* Le `pom.xml` déclare le projet, les versions Java, les dépendances JavaFX et le plugin d’exécution.
* Une seule dépendance est requise : `javafx-controls`.
* Le plugin `javafx-maven-plugin` est indispensable pour lancer JavaFX avec Maven.
* La version JavaFX est centralisée dans une propriété pour simplifier la maintenance.

