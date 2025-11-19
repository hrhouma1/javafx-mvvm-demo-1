# Démonstration Pattern MVVM avec JavaFX

Application pédagogique professionnelle démontrant le pattern architectural MVVM avec JavaFX.

## Démarrage rapide

```bash
# Compiler
mvn clean compile

# Lancer l'application
mvn javafx:run

# Ou utiliser le script Windows
run.bat
```

## Documentation complète

Toute la documentation pédagogique se trouve dans le dossier `documentation/` :

1. **[01-README.md](documentation/01-README.md)** - Vue d'ensemble complète du pattern MVVM
2. **[02-REFERENCE_RAPIDE.md](documentation/02-REFERENCE_RAPIDE.md)** - Référence rapide et concepts clés
3. **[03-STRUCTURE_PROJET.md](documentation/03-STRUCTURE_PROJET.md)** - Architecture détaillée du projet
4. **[04-POINTS_CLES_DEMO.md](documentation/04-POINTS_CLES_DEMO.md)** - Points essentiels pour la présentation
5. **[05-GUIDE_DEMO.md](documentation/05-GUIDE_DEMO.md)** - Guide complet de démonstration
6. **[06-MVVM_EXPLIQUE_DIAGRAMMES.md](documentation/06-MVVM_EXPLIQUE_DIAGRAMMES.md)** - Explication détaillée avec diagrammes Mermaid

## Architecture

```
Model (Counter.java)
  ↓
ViewModel (CounterViewModel.java) ← Properties observables
  ↓ binding automatique
View (CounterView.fxml + Controller)
```

## Fonctionnalités

- Interface professionnelle et moderne
- Data binding bidirectionnel
- Séparation claire des couches (Model/ViewModel/View)
- Testabilité optimale
- Documentation pédagogique complète

## Prérequis

- Java 17 ou supérieur
- Maven 3.6 ou supérieur

---

**Projet créé pour une démonstration pédagogique du pattern MVVM**

