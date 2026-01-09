# Documentation de la Base de Données

Ce document décrit la structure et le schéma de la base de données du système de gestion de restaurant.

## 🗄️ Informations Générales

- **SGBD** : MySQL 8.0+
- **Nom de la base** : `gestion_restaurant`
- **Encodage** : UTF-8
- **Timezone** : UTC (configuré dans la connexion JDBC)

## 📊 Schéma de la Base de Données

### Diagramme Entité-Relation (Simplifié)

```
┌─────────────┐
│   Role      │
│─────────────│
│ id_role (PK)│
│ nom_role    │
│ description │
└──────┬──────┘
       │
       │ 1:N
       │
┌──────▼──────────┐
│  Utilisateur   │
│────────────────│
│ id_utilisateur │
│ identifiant    │
│ nom            │
│ prenom         │
│ mot_de_passe   │
│ statut         │
│ id_role (FK)   │
└────────────────┘

┌─────────────┐
│ TableRestau │
│    rant     │
│─────────────│
│ id_table    │
│ numero      │
│ capacite    │
│ statut      │
└──────┬──────┘
       │
       │ 1:N
       │
┌──────▼──────────┐      ┌─────────────┐
│   Commande     │      │    Plat     │
│────────────────│      │─────────────│
│ id_commande    │      │ id_plat     │
│ id_table (FK)  │      │ nom_plat    │
│ id_serveur(FK) │      │ categorie   │
│ date_commande  │      │ prix        │
│ statut         │      │ disponibilite│
└──────┬─────────┘      └──────┬──────┘
       │                       │
       │ N:M                   │
       │                       │
┌──────▼───────────────────────▼──────┐
│        CommandePlat                │
│─────────────────────────────────────│
│ id_commande_plat                    │
│ id_commande (FK)                    │
│ id_plat (FK)                        │
│ quantite                            │
└─────────────────────────────────────┘

┌─────────────┐
│   Facture   │
│─────────────│
│ id_facture  │
│ id_commande │
│ date_facture│
│ montant_total│
│ statut      │
└──────┬──────┘
       │
       │ 1:N
       │
┌──────▼──────────┐
│   Paiement     │
│────────────────│
│ id_paiement    │
│ id_facture (FK)│
│ montant        │
│ mode_paiement  │
│ date_paiement  │
└────────────────┘

┌─────────────┐
│    Stock    │
│─────────────│
│ id_stock    │
│ nom_produit │
│ quantite    │
│ unite       │
│ seuil_alerte│
└─────────────┘
```

## 📋 Description des Tables

### Table `role`

Stocke les différents rôles du système.

| Colonne       | Type         | Contraintes                 | Description                        |
| ------------- | ------------ | --------------------------- | ---------------------------------- |
| `id_role`     | INT          | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique du rôle         |
| `nom_role`    | VARCHAR(50)  | NOT NULL, UNIQUE            | Nom du rôle (ADMIN, SERVEUR, etc.) |
| `description` | VARCHAR(255) | NULL                        | Description du rôle                |

**Valeurs par défaut** :

- ADMIN : Administrateur du système
- SERVEUR : Serveur de restaurant
- CAISSIER : Caissier
- GESTIONNAIRE : Gestionnaire de stock

### Table `utilisateur`

Stocke les utilisateurs du système.

| Colonne          | Type         | Contraintes                 | Description                          |
| ---------------- | ------------ | --------------------------- | ------------------------------------ |
| `id_utilisateur` | INT          | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique                   |
| `identifiant`    | VARCHAR(50)  | NOT NULL, UNIQUE            | Identifiant de connexion             |
| `nom`            | VARCHAR(100) | NOT NULL                    | Nom de l'utilisateur                 |
| `prenom`         | VARCHAR(100) | NOT NULL                    | Prénom de l'utilisateur              |
| `mot_de_passe`   | VARCHAR(255) | NOT NULL                    | Mot de passe (actuellement en clair) |
| `statut`         | VARCHAR(20)  | NOT NULL, DEFAULT 'ACTIF'   | Statut (ACTIF/INACTIF)               |
| `id_role`        | INT          | NOT NULL, FOREIGN KEY       | Référence vers `role.id_role`        |

**Index** :

- Index unique sur `identifiant`
- Index sur `id_role` (clé étrangère)

### Table `table_restaurant`

Stocke les tables du restaurant.

| Colonne    | Type        | Contraintes                 | Description                     |
| ---------- | ----------- | --------------------------- | ------------------------------- |
| `id_table` | INT         | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique              |
| `numero`   | INT         | NOT NULL, UNIQUE            | Numéro de la table              |
| `capacite` | INT         | NOT NULL                    | Nombre de places                |
| `statut`   | VARCHAR(20) | NOT NULL, DEFAULT 'LIBRE'   | Statut (LIBRE/OCCUPEE/RESERVEE) |

**Valeurs de statut** :

- LIBRE : Table disponible
- OCCUPEE : Table avec une commande en cours
- RESERVEE : Table réservée

### Table `plat`

Stocke les plats du menu.

| Colonne         | Type          | Contraintes                    | Description                             |
| --------------- | ------------- | ------------------------------ | --------------------------------------- |
| `id_plat`       | INT           | PRIMARY KEY, AUTO_INCREMENT    | Identifiant unique                      |
| `nom_plat`      | VARCHAR(100)  | NOT NULL                       | Nom du plat                             |
| `categorie`     | VARCHAR(50)   | NOT NULL                       | Catégorie (entrée, plat, dessert, etc.) |
| `prix`          | DECIMAL(10,2) | NOT NULL                       | Prix du plat                            |
| `disponibilite` | VARCHAR(20)   | NOT NULL, DEFAULT 'DISPONIBLE' | Disponibilité                           |

**Valeurs de disponibilité** :

- DISPONIBLE : Plat disponible
- INDISPONIBLE : Plat non disponible

### Table `menu`

Stocke les menus du restaurant.

| Colonne     | Type         | Contraintes                 | Description        |
| ----------- | ------------ | --------------------------- | ------------------ |
| `id_menu`   | INT          | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique |
| `nom_menu`  | VARCHAR(100) | NOT NULL                    | Nom du menu        |
| `categorie` | VARCHAR(50)  | NULL                        | Catégorie du menu  |

### Table `commande`

Stocke les commandes.

| Colonne         | Type        | Contraintes                         | Description                                 |
| --------------- | ----------- | ----------------------------------- | ------------------------------------------- |
| `id_commande`   | INT         | PRIMARY KEY, AUTO_INCREMENT         | Identifiant unique                          |
| `id_table`      | INT         | NOT NULL, FOREIGN KEY               | Référence vers `table_restaurant.id_table`  |
| `id_serveur`    | INT         | NOT NULL, FOREIGN KEY               | Référence vers `utilisateur.id_utilisateur` |
| `date_commande` | DATETIME    | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Date et heure de la commande                |
| `statut`        | VARCHAR(20) | NOT NULL, DEFAULT 'EN_COURS'        | Statut de la commande                       |

**Valeurs de statut** :

- EN_COURS : Commande en préparation
- SERVIE : Commande servie
- ANNULEE : Commande annulée

### Table `commande_plat`

Table de liaison entre commandes et plats (relation N:M).

| Colonne            | Type | Contraintes                 | Description                           |
| ------------------ | ---- | --------------------------- | ------------------------------------- |
| `id_commande_plat` | INT  | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique                    |
| `id_commande`      | INT  | NOT NULL, FOREIGN KEY       | Référence vers `commande.id_commande` |
| `id_plat`          | INT  | NOT NULL, FOREIGN KEY       | Référence vers `plat.id_plat`         |
| `quantite`         | INT  | NOT NULL, DEFAULT 1         | Quantité du plat dans la commande     |

**Contraintes** :

- Clé unique composite sur (`id_commande`, `id_plat`)

### Table `facture`

Stocke les factures générées.

| Colonne         | Type          | Contraintes                         | Description                           |
| --------------- | ------------- | ----------------------------------- | ------------------------------------- |
| `id_facture`    | INT           | PRIMARY KEY, AUTO_INCREMENT         | Identifiant unique                    |
| `id_commande`   | INT           | NOT NULL, FOREIGN KEY, UNIQUE       | Référence vers `commande.id_commande` |
| `date_facture`  | DATETIME      | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Date de génération                    |
| `montant_total` | DECIMAL(10,2) | NOT NULL                            | Montant total de la facture           |
| `statut`        | VARCHAR(20)   | NOT NULL, DEFAULT 'NON_PAYEE'       | Statut de paiement                    |

**Valeurs de statut** :

- NON_PAYEE : Facture non payée
- PAYEE : Facture payée
- PARTIELLEMENT_PAYEE : Facture partiellement payée

### Table `paiement`

Stocke les paiements effectués.

| Colonne         | Type          | Contraintes                         | Description                         |
| --------------- | ------------- | ----------------------------------- | ----------------------------------- |
| `id_paiement`   | INT           | PRIMARY KEY, AUTO_INCREMENT         | Identifiant unique                  |
| `id_facture`    | INT           | NOT NULL, FOREIGN KEY               | Référence vers `facture.id_facture` |
| `montant`       | DECIMAL(10,2) | NOT NULL                            | Montant du paiement                 |
| `mode_paiement` | VARCHAR(20)   | NOT NULL                            | Mode de paiement                    |
| `date_paiement` | DATETIME      | NOT NULL, DEFAULT CURRENT_TIMESTAMP | Date du paiement                    |

**Modes de paiement** :

- ESPECES
- CARTE
- CHEQUE

### Table `stock`

Stocke les produits en stock.

| Colonne        | Type          | Contraintes                 | Description                             |
| -------------- | ------------- | --------------------------- | --------------------------------------- |
| `id_stock`     | INT           | PRIMARY KEY, AUTO_INCREMENT | Identifiant unique                      |
| `nom_produit`  | VARCHAR(100)  | NOT NULL                    | Nom du produit                          |
| `quantite`     | DECIMAL(10,2) | NOT NULL, DEFAULT 0         | Quantité en stock                       |
| `unite`        | VARCHAR(20)   | NOT NULL                    | Unité de mesure (kg, L, unité, etc.)    |
| `seuil_alerte` | DECIMAL(10,2) | NOT NULL, DEFAULT 0         | Seuil d'alerte pour réapprovisionnement |

## 🔗 Relations et Contraintes

### Clés Étrangères

1. `utilisateur.id_role` → `role.id_role`

   - ON DELETE RESTRICT (empêche la suppression d'un rôle utilisé)

2. `commande.id_table` → `table_restaurant.id_table`

   - ON DELETE RESTRICT

3. `commande.id_serveur` → `utilisateur.id_utilisateur`

   - ON DELETE RESTRICT

4. `commande_plat.id_commande` → `commande.id_commande`

   - ON DELETE CASCADE (supprime les plats si la commande est supprimée)

5. `commande_plat.id_plat` → `plat.id_plat`

   - ON DELETE RESTRICT

6. `facture.id_commande` → `commande.id_commande`

   - ON DELETE RESTRICT

7. `paiement.id_facture` → `facture.id_facture`
   - ON DELETE CASCADE

## 📝 Scripts SQL

### Création de la Base de Données

```sql
CREATE DATABASE IF NOT EXISTS gestion_restaurant
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE gestion_restaurant;
```

### Création des Tables

Voir le fichier `database/init.sql` (s'il existe) ou créer les tables manuellement selon le schéma ci-dessus.

### Données Initiales

```sql
-- Insertion des rôles
INSERT INTO role (nom_role, description) VALUES
('ADMIN', 'Administrateur du système'),
('SERVEUR', 'Serveur de restaurant'),
('CAISSIER', 'Caissier'),
('GESTIONNAIRE', 'Gestionnaire de stock');

-- Insertion d'un utilisateur admin par défaut
INSERT INTO utilisateur (identifiant, nom, prenom, mot_de_passe, statut, id_role) VALUES
('admin', 'Admin', 'Principal', 'admin123', 'ACTIF', 1);

-- Insertion de quelques tables
INSERT INTO table_restaurant (numero, capacite, statut) VALUES
(1, 4, 'LIBRE'),
(2, 2, 'LIBRE'),
(3, 6, 'LIBRE'),
(4, 4, 'LIBRE'),
(5, 8, 'LIBRE');
```

## 🔍 Requêtes Utiles

### Statistiques

**Nombre de commandes par jour** :

```sql
SELECT DATE(date_commande) AS date, COUNT(*) AS nb_commandes
FROM commande
GROUP BY DATE(date_commande)
ORDER BY date DESC;
```

**Chiffre d'affaires par jour** :

```sql
SELECT DATE(date_facture) AS date, SUM(montant_total) AS ca
FROM facture
WHERE statut = 'PAYEE'
GROUP BY DATE(date_facture)
ORDER BY date DESC;
```

**Plats les plus vendus** :

```sql
SELECT p.nom_plat, SUM(cp.quantite) AS total_vendu
FROM commande_plat cp
JOIN plat p ON cp.id_plat = p.id_plat
GROUP BY p.id_plat, p.nom_plat
ORDER BY total_vendu DESC
LIMIT 10;
```

## 🔧 Maintenance

### Sauvegarde

```bash
mysqldump -u root -p gestion_restaurant > backup_restaurant.sql
```

### Restauration

```bash
mysql -u root -p gestion_restaurant < backup_restaurant.sql
```

### Optimisation

```sql
-- Analyser les tables
ANALYZE TABLE utilisateur, commande, plat;

-- Optimiser les tables
OPTIMIZE TABLE utilisateur, commande, plat;
```

## ⚠️ Notes Importantes

1. **Mots de passe** : Actuellement stockés en clair. À améliorer avec un hachage (BCrypt, SHA-256).

2. **Transactions** : Pour des opérations complexes (création de commande + facture), utiliser des transactions.

3. **Index** : Ajouter des index sur les colonnes fréquemment utilisées dans les WHERE et JOIN.

4. **Backup** : Effectuer des sauvegardes régulières de la base de données.

5. **Sécurité** : Ne pas exposer les identifiants de connexion dans le code source.

---

_Pour plus d'informations sur l'utilisation, consultez le [Guide d'Utilisation](GUIDE_UTILISATION.md)._
