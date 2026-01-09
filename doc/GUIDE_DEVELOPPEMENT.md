# Guide de Développement

Ce guide est destiné aux développeurs souhaitant contribuer au projet ou comprendre le code.

## 🛠️ Environnement de Développement

### Outils Recommandés

- **IDE** : IntelliJ IDEA (recommandé) ou Eclipse
- **Version Control** : Git
- **Build Tool** : Maven ou Gradle (optionnel, actuellement compilation manuelle)
- **Base de données** : MySQL Workbench pour la gestion de la BDD
- **Java Version** : JDK 11 ou supérieur

### Configuration IDE

#### IntelliJ IDEA

1. **Importer le projet** : File → Open → Sélectionner le dossier du projet
2. **Configurer le SDK** : File → Project Structure → Project → SDK
3. **Configurer JavaFX** : File → Project Structure → Libraries → Ajouter JavaFX
4. **Configurer les VM Options** : Run → Edit Configurations → VM options :
   ```
   --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
   ```

## 📝 Conventions de Code

### Nommage

- **Classes** : PascalCase (`UtilisateurDAO`, `LoginView`)
- **Méthodes** : camelCase (`findAll()`, `addUtilisateur()`)
- **Variables** : camelCase (`idUtilisateur`, `nomPlat`)
- **Constantes** : UPPER_SNAKE_CASE (`MAX_RETRY`, `DEFAULT_TIMEOUT`)
- **Packages** : lowercase (`model`, `controller`, `dao`)

### Structure des Classes

```java
package model;

public class Exemple {
    // 1. Attributs privés
    private int id;
    private String nom;

    // 2. Constructeurs
    public Exemple() {}
    public Exemple(int id, String nom) { ... }

    // 3. Getters
    public int getId() { return id; }

    // 4. Setters
    public void setId(int id) { this.id = id; }

    // 5. Méthodes utilitaires (toString, equals, hashCode)
    @Override
    public String toString() { ... }
}
```

### Commentaires

- **JavaDoc** pour les méthodes publiques :

  ```java
  /**
   * Ajoute un utilisateur à la base de données
   * @param utilisateur L'utilisateur à ajouter
   * @return true si l'ajout a réussi, false sinon
   */
  public boolean add(Utilisateur utilisateur) { ... }
  ```

- **Commentaires inline** pour la logique complexe :
  ```java
  // Vérifier que l'utilisateur est actif avant l'authentification
  if (!u.getStatut().equalsIgnoreCase("ACTIF")) {
      return null;
  }
  ```

### Formatage

- **Indentation** : 4 espaces (pas de tabs)
- **Longueur de ligne** : Maximum 120 caractères
- **Accolades** : Style K&R (accolade ouvrante sur la même ligne)

## 🏗️ Architecture et Patterns

### Pattern MVC

Respecter la séparation des responsabilités :

- **Model** : Données uniquement (pas de logique métier)
- **View** : Interface utilisateur uniquement (pas de logique métier)
- **Controller** : Logique métier et validation

### Pattern DAO

Chaque entité a son DAO :

```java
public class EntiteDAO {
    // CRUD standard
    public List<Entite> findAll() { ... }
    public boolean add(Entite e) { ... }
    public boolean update(Entite e) { ... }
    public boolean delete(int id) { ... }
}
```

### Gestion des Connexions

Toujours utiliser `try-with-resources` :

```java
try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
    // Code
} catch (SQLException e) {
    e.printStackTrace();
}
```

## 🔨 Ajout de Nouvelles Fonctionnalités

### Étape 1 : Créer le Modèle

1. Créer la classe dans `src/model/`
2. Ajouter les attributs, constructeurs, getters/setters
3. Implémenter `toString()`

Exemple :

```java
package model;

public class NouvelleEntite {
    private int id;
    private String nom;
    // ... getters, setters, constructeurs
}
```

### Étape 2 : Créer le DAO

1. Créer la classe dans `src/dao/`
2. Implémenter les méthodes CRUD
3. Utiliser `PreparedStatement` pour toutes les requêtes

Exemple :

```java
package dao;

public class NouvelleEntiteDAO {
    public List<NouvelleEntite> findAll() {
        // Implémentation
    }
    // ... autres méthodes
}
```

### Étape 3 : Créer le Contrôleur

1. Créer la classe dans `src/controller/`
2. Ajouter la logique métier et validation
3. Appeler les méthodes du DAO

Exemple :

```java
package controller;

public class NouvelleEntiteController {
    private final NouvelleEntiteDAO dao = new NouvelleEntiteDAO();

    public List<NouvelleEntite> getAll() {
        return dao.findAll();
    }
    // ... autres méthodes
}
```

### Étape 4 : Créer la Vue

1. Créer la classe dans `src/view/`
2. Hériter de `Stage`
3. Créer l'interface JavaFX
4. Appeler les méthodes du contrôleur

Exemple :

```java
package view;

import javafx.stage.Stage;
import controller.NouvelleEntiteController;

public class NouvelleEntiteView extends Stage {
    private final NouvelleEntiteController controller = new NouvelleEntiteController();

    public NouvelleEntiteView() {
        // Création de l'interface
    }
}
```

### Étape 5 : Créer la Table en Base de Données

```sql
CREATE TABLE nouvelle_entite (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    -- autres colonnes
);
```

## 🐛 Débogage

### Logs

Actuellement, utiliser `System.out.println()` ou `printStackTrace()`. Pour améliorer :

```java
import java.util.logging.Logger;
import java.util.logging.Level;

private static final Logger logger = Logger.getLogger(NomClasse.class.getName());

// Utilisation
logger.log(Level.INFO, "Message d'information");
logger.log(Level.SEVERE, "Erreur", exception);
```

### Points de Vérification

1. **Connexion BDD** : Vérifier `DBConnection.getConnection() != null`
2. **Requêtes SQL** : Vérifier la syntaxe dans MySQL Workbench
3. **Données** : Vérifier que les données existent en BDD
4. **Exceptions** : Lire les stack traces complètes

### Tests de Connexion

Utiliser `TestDBConnection.java` pour tester la connexion :

```java
Connection conn = DBConnection.getConnection();
if (conn != null) {
    System.out.println("Connexion réussie !");
} else {
    System.out.println("Échec de la connexion");
}
```

## 🔒 Sécurité

### Protection contre les Injections SQL

✅ **BON** : Utiliser `PreparedStatement`

```java
String sql = "SELECT * FROM utilisateur WHERE id = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setInt(1, id);
```

❌ **MAUVAIS** : Concaténation de chaînes

```java
String sql = "SELECT * FROM utilisateur WHERE id = " + id; // DANGEREUX !
```

### Gestion des Mots de Passe

Actuellement en clair. Pour améliorer :

```java
import java.security.MessageDigest;

public static String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        return bytesToHex(hash);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
```

### Configuration

Ne pas hardcoder les identifiants. Utiliser un fichier de configuration :

```java
// config.properties
db.url=jdbc:mysql://localhost:3306/gestion_restaurant
db.user=root
db.password=secret
```

## 📦 Gestion des Dépendances

### JavaFX

JavaFX doit être ajouté manuellement au projet :

1. Télécharger JavaFX SDK
2. Ajouter comme bibliothèque dans l'IDE
3. Configurer les VM options pour l'exécution

### MySQL Connector

Ajouter le JAR `mysql-connector-j-8.0.33.jar` au classpath.

### Future Migration vers Maven

Pour faciliter la gestion des dépendances :

```xml
<!-- pom.xml -->
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.9</version>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

## 🧪 Tests

### Tests Unitaires (À Implémenter)

Créer un package `test` :

```java
package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurDAOTest {
    @Test
    public void testFindAll() {
        UtilisateurDAO dao = new UtilisateurDAO();
        List<Utilisateur> users = dao.findAll();
        assertNotNull(users);
    }
}
```

### Tests d'Intégration

Tester les flux complets :

1. Créer un utilisateur via la vue
2. Vérifier en base de données
3. Se connecter avec cet utilisateur

## 📚 Ressources

### Documentation JavaFX

- [JavaFX Documentation](https://openjfx.io/)
- [JavaFX API](https://docs.oracle.com/javase/8/javafx/api/)

### Documentation MySQL

- [MySQL Documentation](https://dev.mysql.com/doc/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)

### Bonnes Pratiques Java

- [Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)

## 🚀 Contribution

### Workflow Git

1. **Créer une branche** :

   ```bash
   git checkout -b feature/ma-nouvelle-fonctionnalite
   ```

2. **Faire les modifications**

3. **Commit** :

   ```bash
   git add .
   git commit -m "Description des modifications"
   ```

4. **Push** :

   ```bash
   git push origin feature/ma-nouvelle-fonctionnalite
   ```

5. **Créer une Pull Request**

### Checklist avant Commit

- [ ] Code compilé sans erreurs
- [ ] Tests passés (si disponibles)
- [ ] Commentaires ajoutés pour le code complexe
- [ ] Pas de code commenté inutile
- [ ] Respect des conventions de nommage
- [ ] Pas de mots de passe ou identifiants en dur

## 🔄 Refactoring

### Améliorations Suggérées

1. **Extraction de constantes** : Remplacer les valeurs magiques
2. **Méthodes privées** : Extraire la logique répétée
3. **Gestion d'erreurs** : Messages d'erreur personnalisés
4. **Validation** : Créer une classe `Validator` pour centraliser

### Exemple de Refactoring

**Avant** :

```java
if (nom.length() < 3 || nom.length() > 50) {
    // Erreur
}
```

**Après** :

```java
public class Validator {
    public static boolean validateNom(String nom) {
        return nom != null && nom.length() >= 3 && nom.length() <= 50;
    }
}

// Utilisation
if (!Validator.validateNom(nom)) {
    // Erreur
}
```

---

_Pour plus d'informations sur l'architecture, consultez [ARCHITECTURE.md](ARCHITECTURE.md)._
