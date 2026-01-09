# Guide d'Utilisation

Ce guide explique comment utiliser le système de gestion de restaurant pour chaque type d'utilisateur.

## 🔐 Connexion au Système

### Première Connexion

1. **Lancer l'application** en exécutant `Main.java`
2. La fenêtre de connexion s'affiche automatiquement
3. **Saisir vos identifiants** :
   - Identifiant : votre nom d'utilisateur
   - Mot de passe : votre mot de passe
4. Cliquer sur **"Connexion"**

### Problèmes de Connexion

- **"Identifiant ou mot de passe incorrect"** : Vérifiez vos identifiants
- **"Compte inactif"** : Contactez un administrateur pour activer votre compte
- **"Rôle inconnu"** : Votre compte n'a pas de rôle assigné

## 👨‍💼 Guide Administrateur

L'administrateur a accès à toutes les fonctionnalités du système.

### Tableau de Bord

Le tableau de bord affiche :

- Des statistiques globales (nombre de commandes, revenus, etc.)
- Un menu d'accès rapide aux différentes sections

### Gestion des Utilisateurs

**Accès** : Menu principal → 👤 Utilisateurs

**Fonctionnalités** :

- **Ajouter un utilisateur** :

  1. Remplir le formulaire (identifiant, nom, prénom, mot de passe)
  2. Sélectionner un rôle (ADMIN, SERVEUR, CAISSIER, GESTIONNAIRE)
  3. Définir le statut (ACTIF/INACTIF)
  4. Cliquer sur "Ajouter"

- **Modifier un utilisateur** :

  1. Sélectionner un utilisateur dans le tableau
  2. Modifier les informations
  3. Cliquer sur "Modifier"

- **Supprimer un utilisateur** :
  1. Sélectionner un utilisateur
  2. Cliquer sur "Supprimer"
  3. Confirmer la suppression

### Gestion des Menus

**Accès** : Menu principal → 📋 Menus

**Fonctionnalités** :

- Créer, modifier et supprimer des menus
- Organiser les menus par catégorie
- Associer des plats aux menus

### Gestion des Plats

**Accès** : Menu principal → 🍽 Plats

**Fonctionnalités** :

- **Ajouter un plat** :

  1. Remplir le formulaire (nom, catégorie, prix)
  2. Définir la disponibilité (DISPONIBLE/INDISPONIBLE)
  3. Cliquer sur "Ajouter"

- **Modifier un plat** : Sélectionner et modifier
- **Supprimer un plat** : Sélectionner et supprimer
- **Gérer la disponibilité** : Changer le statut de disponibilité

### Gestion des Commandes

**Accès** : Menu principal → 🧾 Commandes

**Fonctionnalités** :

- Visualiser toutes les commandes
- Filtrer par statut (EN_COURS, SERVIE, etc.)
- Modifier le statut d'une commande
- Consulter les détails d'une commande

### Facturation

**Accès** : Menu principal → 💳 Facturation

**Options disponibles** :

- **Créer une facture** : Générer une facture pour une commande
- **Gérer les paiements** : Enregistrer les paiements

### Gestion des Tables

**Accès** : Menu principal → 🪑 Tables

**Fonctionnalités** :

- Visualiser l'état des tables (LIBRE, OCCUPEE, RESERVEE)
- Modifier le statut des tables
- Gérer les réservations

### Gestion des Stocks

**Accès** : Menu principal → 📦 Stocks

**Fonctionnalités** :

- Consulter les niveaux de stock
- Ajouter des produits en stock
- Modifier les quantités
- Gérer les alertes de stock faible

### Rapports et Statistiques

**Accès** : Menu principal → 📊 Rapports

**Fonctionnalités** :

- Consulter les rapports de ventes
- Analyser les statistiques
- Exporter les données

### Déconnexion

Cliquer sur le bouton **"🚪 Déconnexion"** pour quitter le système.

## 🍽️ Guide Serveur

Le serveur gère les commandes et les tables.

### Menu Principal

Le menu serveur propose :

- 🧾 Prendre commande
- 🪑 Tables
- 🚪 Déconnexion

### Prendre une Commande

1. Cliquer sur **"🧾 Prendre commande"**
2. Sélectionner une table
3. Ajouter les plats à la commande :
   - Sélectionner un plat dans la liste
   - Spécifier la quantité
   - Ajouter au panier
4. Valider la commande
5. Le statut de la table passe à "OCCUPEE"

### Gestion des Tables

1. Cliquer sur **"🪑 Tables"**
2. Visualiser l'état de toutes les tables
3. Modifier le statut :
   - **LIBRE** : Table disponible
   - **OCCUPEE** : Table avec une commande en cours
   - **RESERVEE** : Table réservée

### Suivi des Commandes

- Consulter les commandes en cours
- Modifier le statut d'une commande (EN_COURS → SERVIE)
- Voir les détails d'une commande

## 💰 Guide Caissier

Le caissier gère les factures et les paiements.

### Menu Principal

Le menu caissier propose :

- 💳 Facturation
- 🧾 Paiements
- 🚪 Déconnexion

### Créer une Facture

1. Sélectionner une commande servie
2. Vérifier les détails (plats, quantités, prix)
3. Générer la facture
4. La facture s'affiche avec le total à payer

### Enregistrer un Paiement

1. Sélectionner une facture
2. Choisir le mode de paiement :
   - Espèces
   - Carte bancaire
   - Chèque
3. Entrer le montant
4. Confirmer le paiement
5. Le statut de la facture passe à "PAYEE"

### Consultation des Transactions

- Visualiser toutes les transactions
- Filtrer par date
- Consulter l'historique des paiements

## 📦 Guide Gestionnaire

Le gestionnaire gère les stocks et consulte les rapports.

### Menu Principal

Le menu gestionnaire propose :

- 📦 Gestion du stock
- 📊 Rapports & Statistiques
- 🚪 Déconnexion

### Gestion des Stocks

1. Cliquer sur **"📦 Gestion du stock"**
2. **Consulter les stocks** :

   - Voir tous les produits en stock
   - Vérifier les quantités disponibles
   - Identifier les stocks faibles

3. **Ajouter un produit** :

   - Nom du produit
   - Quantité initiale
   - Unité de mesure
   - Seuil d'alerte

4. **Modifier les quantités** :

   - Entrées de stock (réception)
   - Sorties de stock (utilisation)
   - Ajustements

5. **Alertes** :
   - Les produits en dessous du seuil sont signalés
   - Actions recommandées pour réapprovisionner

### Rapports et Statistiques

1. Cliquer sur **"📊 Rapports & Statistiques"**
2. **Types de rapports disponibles** :

   - Rapport de ventes (par jour, semaine, mois)
   - Rapport de stocks
   - Rapport financier
   - Statistiques des plats les plus vendus

3. **Filtres** :

   - Par période (date de début, date de fin)
   - Par catégorie
   - Par produit

4. **Export** :
   - Les rapports peuvent être exportés (si fonctionnalité disponible)

## ⌨️ Raccourcis Clavier

- **Entrée** : Valider un formulaire (quand le focus est sur un bouton)
- **Échap** : Fermer une fenêtre (selon les fenêtres)
- **Tab** : Naviguer entre les champs

## 💡 Conseils d'Utilisation

### Bonnes Pratiques

1. **Sauvegardes régulières** : Les données sont sauvegardées automatiquement, mais vérifiez régulièrement
2. **Gestion des sessions** : Déconnectez-vous toujours à la fin de votre session
3. **Vérification des données** : Vérifiez toujours les informations avant de valider
4. **Gestion des erreurs** : En cas d'erreur, notez le message et contactez le support

### Workflow Recommandé

**Pour un service complet** :

1. Serveur : Prendre la commande
2. Serveur : Marquer la commande comme "SERVIE" une fois prête
3. Caissier : Créer la facture
4. Caissier : Enregistrer le paiement
5. Serveur : Libérer la table

**Pour la gestion quotidienne** :

1. Gestionnaire : Vérifier les stocks le matin
2. Administrateur : Consulter les rapports de la veille
3. Gestionnaire : Réapprovisionner si nécessaire

## 🆘 Support

En cas de problème :

1. Vérifier ce guide
2. Consulter la [documentation technique](ARCHITECTURE.md)
3. Contacter l'administrateur système
4. Signaler le bug avec les détails (écran, message d'erreur, actions effectuées)

---

_Pour plus d'informations techniques, consultez le [Guide de Développement](GUIDE_DEVELOPPEMENT.md)._
