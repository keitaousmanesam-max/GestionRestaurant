# Script de Vérification et Installation

Ce fichier vous guide pour vérifier et installer les prérequis.

## 🔍 Vérification Rapide

### 1. Vérifier Java
```powershell
java -version
```
✅ **Résultat attendu** : Version Java affichée (vous avez la 25.0.1)

### 2. Vérifier JavaFX
```powershell
Test-Path "C:\JavaFX\javafx-sdk-21.0.9\lib"
```
❌ **Actuellement** : Retourne `False` (JavaFX non installé)

### 3. Vérifier MySQL
```powershell
mysql --version
```
✅ **Résultat** : MySQL 9.1.0 installé via WAMP

## 📥 Installation de JavaFX

### Méthode Simple (Manuelle)

1. **Télécharger JavaFX** :
   - Ouvrez votre navigateur
   - Allez sur : https://openjfx.io/
   - Cliquez sur "Download"
   - Sélectionnez "JavaFX 21.0.9" → "Windows" → "SDK"
   - Téléchargez le fichier ZIP

2. **Installer** :
   - Créez le dossier `C:\JavaFX` (s'il n'existe pas)
   - Extrayez le contenu du ZIP dans `C:\JavaFX\`
   - Vous devriez avoir : `C:\JavaFX\javafx-sdk-21.0.9\lib\` avec des fichiers `.jar`

3. **Vérifier** :
   ```powershell
   Get-ChildItem "C:\JavaFX\javafx-sdk-21.0.9\lib" | Select-Object -First 3 Name
   ```
   Vous devriez voir des fichiers comme `javafx.base.jar`, `javafx.controls.jar`, etc.

## ⚙️ Configuration IntelliJ IDEA

### Étape 1 : Ajouter JavaFX comme Bibliothèque

1. Ouvrez IntelliJ IDEA
2. `File` → `Project Structure` (ou `Ctrl+Alt+Shift+S`)
3. Dans l'onglet `Libraries` :
   - Cliquez sur le `+` en haut
   - Sélectionnez `Java`
   - Naviguez vers `C:\JavaFX\javafx-sdk-21.0.9\lib`
   - **Sélectionnez le dossier `lib`** (pas les fichiers individuels)
   - Cliquez `OK`
   - Cliquez `Apply` puis `OK`

### Étape 2 : Configurer les VM Options

1. `Run` → `Edit Configurations...`
2. Si aucune configuration n'existe, créez-en une :
   - Cliquez sur `+` → `Application`
   - Name : `Main`
   - Main class : `Main`
   - Module : `GestionRestaurant`
3. Dans `VM options`, ajoutez :
   ```
   --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
   ```
4. Cliquez `Apply` puis `OK`

## 🗄️ Vérification Base de Données

### Option 1 : Via MySQL Workbench ou phpMyAdmin

1. Ouvrez phpMyAdmin (via WAMP : http://localhost/phpmyadmin)
2. Vérifiez si la base `gestion_restaurant` existe
3. Si elle n'existe pas, créez-la :
   ```sql
   CREATE DATABASE gestion_restaurant 
   CHARACTER SET utf8mb4 
   COLLATE utf8mb4_unicode_ci;
   ```

### Option 2 : Tester la Connexion

1. Dans IntelliJ, ouvrez `src/database/TestDBConnection.java`
2. Vérifiez que le mot de passe correspond à votre MySQL
3. Exécutez `TestDBConnection.java`
4. Si la connexion réussit, vous verrez : "Connexion réussie ✅"

### Si le mot de passe est incorrect

Modifiez `src/database/DBConnection.java` :
```java
private static final String PASSWORD = "votre_vrai_mot_de_passe";
```

## ✅ Test Final

1. **Rebuild le projet** :
   - `Build` → `Rebuild Project`
   - Vérifiez qu'il n'y a pas d'erreurs

2. **Exécuter** :
   - Clic droit sur `Main.java` → `Run 'Main.main()'`
   - OU `Run` → `Run 'Main'`

3. **Résultat attendu** :
   - Une fenêtre de connexion devrait s'afficher
   - Titre : "Authentification"
   - Champs : Identifiant et Mot de passe

## 🐛 Problèmes Courants

### "Error: JavaFX runtime components are missing"
→ JavaFX n'est pas dans le classpath. Vérifiez les VM options.

### "module not found: javafx.controls"
→ Le chemin vers JavaFX est incorrect. Vérifiez que `C:\JavaFX\javafx-sdk-21.0.9\lib` existe.

### "Access denied for user 'root'"
→ Le mot de passe MySQL est incorrect. Modifiez `DBConnection.java`.

### La fenêtre ne s'affiche pas
→ Vérifiez les logs dans la console IntelliJ pour voir l'erreur exacte.

---

**Besoin d'aide ?** Consultez `doc/GUIDE_INSTALLATION.md` pour plus de détails.
