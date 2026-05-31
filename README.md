
# Tic Tac Toe

## Présentation

Ce projet est une implémentation du jeu **Tic Tac Toe (Morpion)** développée avec :

* **Backend :** Java 17, Spring Boot
* **Frontend :** React, Vite
* **Communication :** API REST

Avant chaque partie, le joueur choisit son symbole (**X** ou **O**).

L'ordinateur joue automatiquement en sélectionnant une case libre de manière aléatoire.

Les règles appliquées sont celles du Tic Tac Toe classique :

* Un joueur gagne lorsqu'il aligne trois symboles identiques horizontalement, verticalement ou en diagonale.
* Une partie peut se terminer par un match nul lorsque toutes les cases sont occupées sans gagnant.
* Il est possible de rejouer sans recharger l'application.

---

## Structure du projet

```text
tictactoe
├── backend
│   └── Application Spring Boot
├── frontend
│   └── Application React
└── README.md
```

---

## Architecture Backend

Le backend est organisé selon une architecture en couches :

```text
controller
├── GameController

service
├── GameService

model
├── Game
├── Symbol
└── GameStatus

dto
├── StartGameRequest
└── MoveRequest
```

### Responsabilités

#### GameController

Expose les endpoints REST utilisés par le frontend.

#### GameService

Contient toute la logique métier du jeu :

* Création d'une partie
* Validation des coups
* Jeu de l'ordinateur
* Détection des victoires
* Détection des matchs nuls
* Redémarrage d'une partie

#### Modèle

Représente l'état du jeu.

---

## Architecture Frontend

```text
components
├── Symbol
├── Board
├── Cell
└── GameStatus

services
└── gameApi.js
```

### Responsabilités

#### Symbol

Permet au joueur de choisir son symbole.

#### Board

Affiche le plateau de jeu et gère les interactions utilisateur.

#### Cell

Représente une case du plateau.

#### GameStatus

Affiche l'état courant de la partie.

#### gameApi

Centralise les appels vers l'API REST.

---

## Endpoints REST

### Démarrer une partie

```http
POST /api/game/start
```

Corps de la requête :

```json
{
  "playerSymbol": "X"
}
```

---

### Jouer un coup

```http
POST /api/game/move
```

Corps de la requête :

```json
{
  "row": 0,
  "col": 0
}
```

---

### Consulter l'état de la partie

```http
GET /api/game
```

---

### Recommencer une partie

```http
POST /api/game/restart
```

---

## Lancement du projet

### Backend

Se placer dans le dossier backend :

```bash
cd backend
```

Lancer l'application :

```bash
mvn spring-boot:run
```

Le backend sera disponible sur :

```text
http://localhost:8080
```

---

### Frontend

Se placer dans le dossier frontend :

```bash
cd frontend
```

Installer les dépendances :

```bash
npm install
```

Lancer l'application :

```bash
npm run dev
```

Le frontend sera disponible sur :

```text
http://localhost:5173
```

---

## Exécution des tests

Depuis le dossier backend :

```bash
mvn test
```

---

## Choix techniques

L'application a été conçue pour être facilement extensible.

La logique du jeu repose notamment sur les paramètres :

```java
size
winLength
```

plutôt que sur des valeurs codées en dur.

Cette approche permet de faire évoluer facilement le jeu vers :

* un plateau 4x4 ;
* un plateau 5x5 ;
* un nombre différent de symboles à aligner pour gagner ;
* plusieurs niveaux de difficulté ;
* une intelligence artificielle plus avancée ;
* un mode multijoueur ;
* une persistance en base de données.

Ces évolutions nécessiteraient peu de modifications dans l'architecture actuelle.

---

## Améliorations possibles

* Intelligence artificielle utilisant l'algorithme Minimax
* Plusieurs niveaux de difficulté
* Mode multijoueur
* Historique des parties
* Sauvegarde du score en base de données
* Interface responsive pour mobile et tablette
* Thèmes graphiques personnalisables

---

## Auteur

Projet réalisé dans le cadre d'un exercice technique pour Easi par Iman Azoioui.
