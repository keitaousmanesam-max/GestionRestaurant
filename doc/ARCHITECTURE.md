# Architecture du Système

Ce document décrit l'architecture technique du système de gestion de restaurant.

## 🏗️ Architecture Générale

Le système suit une **architecture MVC (Modèle-Vue-Contrôleur)** avec une couche d'accès aux données (DAO).

```
┌─────────────────────────────────────────────────────────┐
│                      VUE (View)                          │
│              Interfaces JavaFX (UI)                      │
└────────────────────┬──────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                  CONTRÔLEUR (Controller)                │
│              Logique métier et validation                │
└────────────────────┬──────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                    DAO (Data Access Object)             │
│              Accès à la base de données                  │
└────────────────────┬──────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              BASE DE DONNÉES (MySQL)                     │
│              Stockage persistant des données             │
└─────────────────────────────────────────────────────────┘
```

## 📁 Structure des Packages

### Package `model`

Contient les entités métier (POJOs - Plain Old Java Objects).

**Classes principales** :

- `Utilisateur` : Représente un utilisateur du système
- `Role` : Rôle d'un utilisateur (ADMIN, SERVEUR, CAISSIER, GESTIONNAIRE)
- `Plat` : Plat du menu
- `Menu` : Menu du restaurant
- `Commande` : Commande d'un client
- `CommandePlat` : Association entre une commande et un plat (avec quantité)
- `TableRestaurant` : Table du restaurant
- `Facture` : Facture générée
- `Paiement` : Paiement effectué
- `Stock` : Produit en stock
- `Rapport` : Données pour les rapports

**Caractéristiques** :

- Classes simples avec getters/setters
- Constructeurs multiples (complet, simplifié)
- Méthode `toString()` pour le débogage

### Package `view`

Contient les interfaces utilisateur JavaFX.

**Classes principales** :

- `LoginView` : Fenêtre de connexion
- `UtilisateurView` : Gestion des utilisateurs
- `PlatView` : Gestion des plats
- `MenuView` : Gestion des menus
- `CommandeView` : Gestion des commandes
- `CommandePlatView` : Détails d'une commande
- `TableRestaurantView` : Gestion des tables
- `FactureView` : Gestion des factures
- `PaiementView` : Gestion des paiements
- `StockView` : Gestion des stocks
- `RapportView` : Affichage des rapports

**Sous-package `view.menu`** :

- `MenuPrincipalAdministrateur` : Menu principal pour l'admin
- `MenuPrincipalServeur` : Menu principal pour le serveur
- `MenuPrincipalCaissier` : Menu principal pour le caissier
- `MenuPrincipalGestionnaire` : Menu principal pour le gestionnaire

**Caractéristiques** :

- Héritent de `Stage` (fenêtre JavaFX)
- Utilisent des animations JavaFX
- Interface moderne avec styles CSS intégrés

### Package `controller`

Contient la logique métier et la validation.

**Classes principales** :

- `AuthController` : Authentification et autorisation
- `UtilisateurController` : Logique de gestion des utilisateurs
- `PlatController` : Logique de gestion des plats
- `MenuController` : Logique de gestion des menus
- `CommandeController` : Logique de gestion des commandes
- `CommandePlatController` : Logique des détails de commande
- `TableRestaurantController` : Logique de gestion des tables
- `FactureController` : Logique de facturation
- `PaiementController` : Logique de paiement
- `StockController` : Logique de gestion des stocks
- `RapportController` : Logique de génération de rapports

**Responsabilités** :

- Validation des données
- Appels aux DAO
- Transformation des données
- Gestion des erreurs

### Package `dao`

Contient l'accès aux données (Data Access Object).

**Classes principales** :

- `UtilisateurDAO` : Accès aux données utilisateurs
- `PlatDAO` : Accès aux données plats
- `MenuDAO` : Accès aux données menus
- `CommandeDAO` : Accès aux données commandes
- `CommandePlatDAO` : Accès aux données commande-plat
- `TableRestaurantDAO` : Accès aux données tables
- `FactureDAO` : Accès aux données factures
- `PaiementDAO` : Accès aux données paiements
- `StockDAO` : Accès aux données stocks
- `RapportDAO` : Accès aux données rapports
- `RoleDAO` : Accès aux données rôles

**Pattern utilisé** : DAO Pattern

- Méthodes CRUD standard : `findAll()`, `add()`, `update()`, `delete()`
- Utilisation de `PreparedStatement` pour éviter les injections SQL
- Gestion des exceptions SQL

### Package `database`

Contient la configuration de la base de données.

**Classes principales** :

- `DBConnection` : Gestion de la connexion MySQL
  - Singleton pattern pour la connexion
  - Configuration centralisée (URL, USER, PASSWORD)
- `TestDBConnection` : Test de connexion (utile pour le débogage)

## 🔄 Flux de Données

### Exemple : Création d'une Commande

```
1. Utilisateur clique sur "Ajouter commande" dans CommandeView
   ↓
2. CommandeView appelle CommandeController.addCommande()
   ↓
3. CommandeController valide les données
   ↓
4. CommandeController appelle CommandeDAO.add()
   ↓
5. CommandeDAO exécute INSERT dans MySQL via DBConnection
   ↓
6. Résultat retourné à CommandeController
   ↓
7. CommandeController retourne le résultat à CommandeView
   ↓
8. CommandeView met à jour l'interface (tableau, message de succès)
```

### Exemple : Authentification

```
1. Utilisateur saisit identifiant/mot de passe dans LoginView
   ↓
2. LoginView appelle AuthController.login()
   ↓
3. AuthController exécute requête SQL via DBConnection
   ↓
4. Si authentification réussie, création d'un objet Utilisateur
   ↓
5. AuthController retourne l'Utilisateur à LoginView
   ↓
6. LoginView vérifie le rôle et ouvre le menu approprié
```

## 🔐 Gestion de l'Authentification

### Rôles et Permissions

| Rôle             | Permissions                                |
| ---------------- | ------------------------------------------ |
| **ADMIN**        | Accès complet à toutes les fonctionnalités |
| **SERVEUR**      | Commandes, Tables                          |
| **CAISSIER**     | Factures, Paiements                        |
| **GESTIONNAIRE** | Stocks, Rapports                           |

### Vérification des Rôles

La vérification se fait dans :

- `AuthController` : Méthodes `isAdmin()`, `isServeur()`, etc.
- `LoginView` : Redirection vers le menu approprié selon le rôle

## 🗄️ Gestion de la Base de Données

### Connexion

- **Pattern** : Singleton (une seule instance de connexion)
- **Classe** : `DBConnection`
- **Méthode** : `getConnection()` retourne une `Connection`

### Transactions

Actuellement, chaque opération est autonome. Pour des opérations complexes, envisager :

- `Connection.setAutoCommit(false)`
- `Connection.commit()` / `Connection.rollback()`

### Gestion des Erreurs

- Les exceptions SQL sont capturées dans les DAO
- `printStackTrace()` pour le débogage (à améliorer avec un logger)
- Retour de `null` ou `false` en cas d'erreur

## 🎨 Interface Utilisateur

### Framework

- **JavaFX 21.0.9** : Framework d'interface graphique
- **FXML** : Non utilisé (tout en code Java)
- **CSS** : Styles inline via `setStyle()`

### Composants Principaux

- `Stage` : Fenêtres principales
- `Scene` : Contenu des fenêtres
- `VBox`, `HBox`, `GridPane` : Layouts
- `TableView` : Tableaux de données
- `Button`, `TextField`, `Label` : Contrôles de base

### Animations

- `FadeTransition` : Apparition/disparition
- `ScaleTransition` : Zoom
- `TranslateTransition` : Déplacement (shake)

## 🔧 Points d'Amélioration

### Sécurité

1. **Mots de passe** : Actuellement en clair dans la BDD

   - **Solution** : Hacher avec BCrypt ou SHA-256

2. **Injection SQL** : Déjà protégé avec `PreparedStatement`

   - **Vérification** : Continuer à utiliser `PreparedStatement`

3. **Identifiants en dur** : Dans `DBConnection.java`
   - **Solution** : Fichier de configuration externe

### Performance

1. **Pool de connexions** : Actuellement une connexion par requête

   - **Solution** : Utiliser HikariCP ou C3P0

2. **Cache** : Pas de mise en cache
   - **Solution** : Cache pour les données fréquemment consultées

### Maintenabilité

1. **Logging** : Actuellement `printStackTrace()`

   - **Solution** : Utiliser Log4j ou SLF4J

2. **Gestion d'erreurs** : Messages génériques

   - **Solution** : Messages d'erreur personnalisés

3. **Tests** : Pas de tests unitaires
   - **Solution** : Ajouter JUnit pour les tests

## 📊 Diagrammes

### Diagramme de Classes (Simplifié)

```
┌─────────────┐
│   Main      │
└──────┬──────┘
       │
       ▼
┌─────────────┐      ┌──────────────┐
│  LoginView  │─────▶│AuthController│
└─────────────┘      └──────┬───────┘
                            │
                            ▼
                    ┌──────────────┐      ┌─────────────┐
                    │UtilisateurDAO│─────▶│DBConnection │
                    └──────────────┘      └─────────────┘
                            │
                            ▼
                    ┌──────────────┐
                    │   MySQL      │
                    └──────────────┘
```

### Diagramme de Séquence (Connexion)

```
User → LoginView → AuthController → UtilisateurDAO → DBConnection → MySQL
                                                              │
                                                              ▼
User ← LoginView ← AuthController ← UtilisateurDAO ← DBConnection ← MySQL
```

## 🚀 Évolutions Possibles

1. **API REST** : Exposer les fonctionnalités via une API
2. **Application Web** : Version web avec Spring Boot
3. **Application Mobile** : Application mobile pour les serveurs
4. **Multi-tenant** : Support de plusieurs restaurants
5. **Notifications** : Système de notifications en temps réel

---

_Pour plus de détails sur le développement, consultez le [Guide de Développement](GUIDE_DEVELOPPEMENT.md)._
