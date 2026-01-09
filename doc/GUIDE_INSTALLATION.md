# Guide d'Installation

Ce guide vous accompagne dans l'installation et la configuration du système de gestion de restaurant.

## 📋 Prérequis

### Logiciels Requis

1. **Java Development Kit (JDK)**

   - Version recommandée : JDK 11 ou supérieur
   - Téléchargement : [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://openjdk.org/)
   - Vérification : `java -version` dans le terminal

2. **JavaFX SDK**

   - Version utilisée : JavaFX 21.0.9
   - Installation : Télécharger depuis [OpenJFX](https://openjfx.io/)
   - Emplacement : `C:/JavaFX/javafx-sdk-21.0.9/lib` (ou configurer selon votre installation)
   - **Important** : Notez le chemin d'installation pour la configuration

3. **MySQL Server**

   - Version recommandée : MySQL 8.0 ou supérieur
   - Téléchargement : [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
   - Alternative : XAMPP (inclut MySQL)

4. **MySQL Connector/J**

   - Version utilisée : mysql-connector-j-8.0.33
   - Téléchargement : [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
   - Ajouter le JAR au classpath du projet

5. **IDE (Optionnel mais recommandé)**
   - IntelliJ IDEA (recommandé)
   - Eclipse
   - NetBeans
   - VS Code avec extensions Java

## 🔧 Installation Étape par Étape

### Étape 1 : Installation de Java

1. Télécharger et installer le JDK
2. Configurer la variable d'environnement `JAVA_HOME`
3. Ajouter Java au PATH système
4. Vérifier l'installation :
   ```bash
   java -version
   javac -version
   ```

### Étape 2 : Installation de JavaFX

1. Télécharger JavaFX SDK 21.0.9 depuis [OpenJFX](https://openjfx.io/)
2. Extraire l'archive dans un dossier (ex: `C:/JavaFX/javafx-sdk-21.0.9/`)
3. Noter le chemin vers le dossier `lib` (ex: `C:/JavaFX/javafx-sdk-21.0.9/lib`)

### Étape 3 : Installation de MySQL

1. Installer MySQL Server
2. Créer un utilisateur root (ou utiliser l'utilisateur existant)
3. Noter le mot de passe MySQL
4. Vérifier que le service MySQL est démarré

### Étape 4 : Configuration de la Base de Données

1. Créer la base de données :

   ```sql
   CREATE DATABASE gestion_restaurant;
   ```

2. Importer le schéma de base de données :

   - Si un fichier `database/init.sql` existe, l'exécuter
   - Sinon, créer les tables manuellement (voir [Base de Données](BASE_DE_DONNEES.md))

3. Vérifier la connexion :
   ```sql
   USE gestion_restaurant;
   SHOW TABLES;
   ```

### Étape 5 : Configuration du Projet

#### Pour IntelliJ IDEA

1. Ouvrir le projet dans IntelliJ IDEA
2. Configurer le SDK Java :

   - File → Project Structure → Project → SDK
   - Sélectionner votre JDK

3. Ajouter JavaFX comme bibliothèque :

   - File → Project Structure → Libraries
   - Cliquer sur `+` → Java
   - Sélectionner le dossier `lib` de JavaFX (ex: `C:/JavaFX/javafx-sdk-21.0.9/lib`)
   - Appliquer les changements

4. Ajouter MySQL Connector :

   - File → Project Structure → Libraries
   - Cliquer sur `+` → Java
   - Sélectionner le fichier JAR `mysql-connector-j-8.0.33.jar`

5. Configurer les options VM pour l'exécution :
   - Run → Edit Configurations
   - Dans "VM options", ajouter :
     ```
     --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
     ```
   - **Note** : Ajuster le chemin selon votre installation JavaFX

#### Pour Eclipse

1. Ouvrir le projet dans Eclipse
2. Clic droit sur le projet → Properties
3. Java Build Path → Libraries → Add External JARs
   - Ajouter tous les JARs de JavaFX depuis `javafx-sdk-21.0.9/lib`
   - Ajouter `mysql-connector-j-8.0.33.jar`
4. Run → Run Configurations
   - Arguments → VM arguments :
     ```
     --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
     ```

#### Pour la Ligne de Commande

1. Compiler le projet :

   ```bash
   javac --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml -cp "mysql-connector-j-8.0.33.jar" -d out/production/GestionRestaurant -sourcepath src src/Main.java
   ```

2. Exécuter l'application :
   ```bash
   java --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml -cp "out/production/GestionRestaurant;mysql-connector-j-8.0.33.jar" Main
   ```

### Étape 6 : Configuration de la Connexion Base de Données

Modifier le fichier `src/database/DBConnection.java` :

```java
private static final String URL = "jdbc:mysql://127.0.0.1:3306/gestion_restaurant?serverTimezone=UTC";
private static final String USER = "root";  // Votre utilisateur MySQL
private static final String PASSWORD = "votre_mot_de_passe";  // Votre mot de passe MySQL
```

**⚠️ Sécurité** : En production, utilisez un fichier de configuration externe ou des variables d'environnement plutôt que de hardcoder les identifiants.

### Étape 7 : Création des Utilisateurs Initiaux

Créer au moins un utilisateur administrateur dans la base de données :

```sql
INSERT INTO role (nom_role, description) VALUES
('ADMIN', 'Administrateur du système'),
('SERVEUR', 'Serveur de restaurant'),
('CAISSIER', 'Caissier'),
('GESTIONNAIRE', 'Gestionnaire de stock');

INSERT INTO utilisateur (identifiant, nom, prenom, mot_de_passe, statut, id_role) VALUES
('admin', 'Admin', 'Principal', 'admin123', 'ACTIF', 1);
```

## ✅ Vérification de l'Installation

1. **Vérifier Java** :

   ```bash
   java -version
   ```

2. **Vérifier MySQL** :

   ```bash
   mysql --version
   ```

3. **Tester la connexion à la base de données** :

   - Exécuter `TestDBConnection.java` si disponible
   - Ou se connecter manuellement via MySQL Workbench

4. **Lancer l'application** :
   - Exécuter `Main.java`
   - La fenêtre de connexion devrait s'afficher

## 🐛 Résolution de Problèmes

### Erreur : "module not found: javafx.controls"

- Vérifier que le chemin vers JavaFX est correct
- Vérifier que les modules sont bien ajoutés dans les VM options

### Erreur : "package javafx does not exist"

- Vérifier que JavaFX est bien ajouté comme bibliothèque
- Rebuild le projet

### Erreur de connexion MySQL

- Vérifier que MySQL est démarré
- Vérifier les identifiants dans `DBConnection.java`
- Vérifier que la base de données `gestion_restaurant` existe

### L'application ne démarre pas

- Vérifier les logs d'erreur dans la console
- Vérifier que tous les JARs sont dans le classpath
- Vérifier la version de Java (JDK 11+ requis)

## 📝 Notes Importantes

- **Sécurité** : Ne commitez jamais les identifiants de base de données dans le contrôle de version
- **JavaFX** : JavaFX n'est plus inclus dans le JDK depuis Java 11, il doit être ajouté séparément
- **Port MySQL** : Par défaut MySQL utilise le port 3306, ajuster si nécessaire

## 🔄 Mise à Jour

Pour mettre à jour le système :

1. Sauvegarder la base de données
2. Mettre à jour le code source
3. Recompiler le projet
4. Tester les fonctionnalités

---

_Pour plus d'informations, consultez les autres guides de documentation._
