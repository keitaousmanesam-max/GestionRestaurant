# Documentation du Système de Gestion de Restaurant

Bienvenue dans la documentation complète du système de gestion de restaurant développé en Java avec JavaFX.

## 📚 Structure de la Documentation

Cette documentation est organisée en plusieurs guides :

1. **[Guide d'Installation](GUIDE_INSTALLATION.md)** - Configuration et installation du système
2. **[Guide d'Utilisation](GUIDE_UTILISATION.md)** - Manuel utilisateur pour chaque rôle
3. **[Architecture du Système](ARCHITECTURE.md)** - Structure technique et organisation du code
4. **[Guide de Développement](GUIDE_DEVELOPPEMENT.md)** - Pour les développeurs souhaitant contribuer
5. **[Base de Données](BASE_DE_DONNEES.md)** - Schéma et structure de la base de données

## 🎯 Vue d'Ensemble

Le système de gestion de restaurant est une application JavaFX permettant de gérer :

- **Les utilisateurs** et leurs rôles (Administrateur, Serveur, Caissier, Gestionnaire)
- **Les menus et plats** du restaurant
- **Les commandes** et leur suivi
- **Les tables** du restaurant
- **La facturation et les paiements**
- **La gestion des stocks**
- **Les rapports et statistiques**

## 👥 Rôles Utilisateurs

### 🔐 Administrateur

- Accès complet à toutes les fonctionnalités
- Gestion des utilisateurs
- Gestion des menus, plats, commandes
- Accès à la facturation et aux rapports

### 🍽️ Serveur

- Prise de commandes
- Gestion des tables
- Visualisation des commandes en cours

### 💰 Caissier

- Gestion des factures
- Enregistrement des paiements
- Consultation des transactions

### 📦 Gestionnaire

- Gestion des stocks
- Consultation des rapports et statistiques
- Suivi des inventaires

## 🛠️ Technologies Utilisées

- **Java** - Langage de programmation principal
- **JavaFX 21.0.9** - Framework d'interface graphique
- **MySQL** - Base de données relationnelle
- **JDBC** - Connexion à la base de données
- **Architecture MVC** - Modèle-Vue-Contrôleur

## 📁 Structure du Projet

```
GestionRestaurant/
├── src/
│   ├── Main.java                    # Point d'entrée de l'application
│   ├── model/                       # Modèles de données (entités)
│   ├── view/                        # Interfaces utilisateur (JavaFX)
│   ├── controller/                  # Contrôleurs (logique métier)
│   ├── dao/                         # Data Access Objects (accès BDD)
│   └── database/                    # Configuration de la base de données
├── doc/                             # Documentation (ce dossier)
└── Readme.md                        # Fichier README principal
```

## 🚀 Démarrage Rapide

1. **Installer les prérequis** (voir [Guide d'Installation](GUIDE_INSTALLATION.md))
2. **Configurer la base de données** (voir [Base de Données](BASE_DE_DONNEES.md))
3. **Lancer l'application** via `Main.java`
4. **Se connecter** avec un compte utilisateur valide

## 📞 Support

Pour toute question ou problème, consultez les guides détaillés dans ce dossier ou contactez l'équipe de développement (Groupe 5).

---

_Dernière mise à jour : Janvier 2026_
