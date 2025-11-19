# Démonstration Pattern MVVM avec JavaFX

## Vue d'ensemble

Cette application est une démonstration pédagogique du **pattern architectural MVVM** (Model-View-ViewModel) appliqué à JavaFX. Elle illustre de manière simple et claire les principes fondamentaux de ce pattern.

## Qu'est-ce que MVVM ?

**MVVM** (Model-View-ViewModel) est un pattern architectural qui sépare la logique de présentation de l'interface utilisateur. Il est particulièrement adapté aux frameworks supportant le **data binding bidirectionnel** comme JavaFX.

### Les 3 couches du pattern MVVM

```
┌─────────────────────────────────────────────────────┐
│                      VIEW                           │
│  (Interface utilisateur - FXML + Controller)        │
│  - Affichage pur                                    │
│  - Pas de logique métier                            │
│  - Bindings vers le ViewModel                       │
└─────────────────┬───────────────────────────────────┘
                  │ Data Binding
                  │ Bidirectionnel
┌─────────────────▼───────────────────────────────────┐
│                   VIEW MODEL                        │
│  (Logique de présentation)                          │
│  - Properties observables                           │
│  - Commandes (actions)                              │
│  - Pas de référence à la View                       │
└─────────────────┬───────────────────────────────────┘
                  │ Appels de méthodes
                  │
┌─────────────────▼───────────────────────────────────┐
│                     MODEL                           │
│  (Logique métier pure)                              │
│  - Données                                          │
│  - Règles métier                                    │
│  - Indépendant de l'UI                              │
└─────────────────────────────────────────────────────┘
```

## Structure du projet

```
mvvm-demo/
├── src/main/java/com/demo/mvvm/
│   ├── MainApp.java                    # Point d'entrée
│   ├── model/
│   │   └── Counter.java                # MODEL - Logique métier
│   ├── viewmodel/
│   │   └── CounterViewModel.java       # VIEW MODEL - Présentation
│   └── view/
│       └── CounterViewController.java  # Controller
│
├── src/main/resources/com/demo/mvvm/view/
│   ├── CounterView.fxml                # VIEW - Interface
│   └── styles.css                      # Styles CSS
│
└── pom.xml                             # Configuration Maven
```

## Explication détaillée des composants

### 1. MODEL (`Counter.java`)

**Responsabilité :** Logique métier pure, aucune dépendance UI

```java
- Gère la valeur du compteur
- Définit les règles métier (min/max)
- Opérations : increment(), decrement(), reset()
- AUCUNE référence à JavaFX
```

**Points clés :**
- Complètement testable indépendamment
- Réutilisable dans d'autres contextes
- Pas de connaissance de l'UI

### 2. VIEW MODEL (`CounterViewModel.java`)

**Responsabilité :** Pont entre Model et View, expose des Properties observables

```java
- Contient une instance du Model
- Expose des Properties JavaFX (IntegerProperty, StringProperty, etc.)
- Fournit des commandes (méthodes publiques pour les actions)
- Met à jour l'état de l'UI (boutons actifs/inactifs)
```

**Avantages du ViewModel :**
- Testable sans lancer l'UI
- Pas de dépendance directe à la View
- Facilite le binding bidirectionnel

### 3. VIEW (`CounterView.fxml` + `CounterViewController.java`)

**Responsabilité :** Affichage pur et bindings

**FXML :**
- Définit la structure de l'interface
- Références aux éléments UI (fx:id)
- Actions liées aux méthodes du Controller

**Controller :**
- Configure les bindings dans `initialize()`
- Délègue toutes les actions au ViewModel
- Aucune logique métier ou de présentation

## Flux de données dans MVVM

### Exemple : L'utilisateur clique sur "Incrémenter"

```
1. VIEW (Button) → Action onIncrement()
                    ↓
2. CONTROLLER → Appelle viewModel.increment()
                    ↓
3. VIEW MODEL → Appelle counter.increment()
                    ↓
4. MODEL → Modifie la valeur
                    ↓
5. VIEW MODEL → Met à jour les Properties
                    ↓
6. VIEW → Mise à jour AUTOMATIQUE via binding
         (Label affiche nouvelle valeur)
         (Boutons activés/désactivés)
```

## Le Data Binding : La magie de MVVM

### Qu'est-ce que le Data Binding ?

Le **binding** crée une connexion automatique entre une Property du ViewModel et un élément de la View. Quand la Property change, la View se met à jour automatiquement.

### Exemples dans le code

```java
// Binding unidirectionnel (ViewModel → View)
counterLabel.textProperty().bind(
    viewModel.counterValueProperty().asString()
);
// Quand counterValue change, le Label s'update automatiquement

// Binding avec transformation
incrementButton.disableProperty().bind(
    viewModel.canIncrementProperty().not()
);
// Le bouton est désactivé quand canIncrement est false
```

## Avantages du pattern MVVM

1. **Séparation des responsabilités**
   - Chaque couche a un rôle clair
   - Code plus maintenable

2. **Testabilité**
   - Le ViewModel est testable sans UI
   - Tests unitaires simples

3. **Réutilisabilité**
   - Le Model peut être utilisé ailleurs
   - Plusieurs Views peuvent partager le même ViewModel

4. **Productivité**
   - Le binding réduit le code boilerplate
   - Moins d'erreurs de synchronisation

## Lancer l'application

### Prérequis
- Java 17 ou supérieur
- Maven 3.6 ou supérieur

### Commandes

```bash
# Compiler le projet
mvn clean compile

# Lancer l'application
mvn javafx:run
```

## Points pédagogiques à démontrer

### 1. Séparation des couches
Montrer comment chaque classe a une responsabilité unique et ne dépend pas des autres de manière inappropriée.

### 2. Data Binding en action
- Cliquer sur les boutons
- Observer la mise à jour automatique du compteur
- Observer l'activation/désactivation automatique des boutons aux limites

### 3. Testabilité
Expliquer comment tester le ViewModel sans lancer l'interface :

```java
@Test
public void testIncrement() {
    CounterViewModel vm = new CounterViewModel();
    vm.increment();
    assertEquals(1, vm.getCounterValue());
}
```

### 4. État de l'UI
Montrer comment le ViewModel gère l'état :
- Boutons désactivés aux limites (100 et -100)
- Messages de status dynamiques

## Extensions possibles

Pour aller plus loin avec les étudiants :

1. **Ajouter des fonctionnalités**
   - Incrément par pas personnalisable
   - Historique des opérations
   - Sauvegarde de l'état

2. **Tests unitaires**
   - Ajouter JUnit pour tester le Model et ViewModel

3. **Multiple ViewModels**
   - Créer une application avec plusieurs écrans

4. **Commands Pattern**
   - Implémenter un système de commandes réversibles (Undo/Redo)

## Comparaison avec MVC

| Critère | MVC | MVVM |
|---------|-----|------|
| Binding | Manuel | Automatique (bidirectionnel) |
| Controller | Contient logique présentation | Minimal, juste bindings |
| Testabilité | Difficile (dépendances View) | Facile (ViewModel indépendant) |
| Framework | Tous | Ceux avec data binding |

## Conclusion

Cette démo illustre les principes fondamentaux du pattern MVVM :
- **Séparation claire** des responsabilités
- **Data binding** pour synchronisation automatique
- **Testabilité** améliorée
- **Maintenabilité** du code

Le pattern MVVM est particulièrement adapté aux applications JavaFX modernes et constitue une excellente base pour des projets professionnels.

---

**Développé pour une démonstration pédagogique**

