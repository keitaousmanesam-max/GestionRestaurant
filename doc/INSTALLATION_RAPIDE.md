# Guide d'Installation Rapide

Ce guide vous permet d'installer rapidement JavaFX et de vérifier la configuration pour exécuter l'application.

## ✅ État Actuel

- ✅ **Java** : Installé (version 25.0.1)
- ✅ **MySQL** : Installé (version 9.1.0 via WAMP)
- ❌ **JavaFX** : Non installé
- ⚠️ **Base de données** : À vérifier

## 📥 Étape 1 : Installation de JavaFX

### Option A : Installation Automatique (Recommandé)

1. **Télécharger JavaFX SDK 21.0.9** :
   - Allez sur : https://openjfx.io/
   - Cliquez sur "Download" → "JavaFX 21.0.9"
   - Sélectionnez "Windows" → "SDK"
   - Téléchargez le fichier ZIP

2. **Extraire JavaFX** :
   - Créez le dossier `C:\JavaFX` (s'il n'existe pas)
   - Extrayez le contenu du ZIP dans `C:\JavaFX\`
   - Vous devriez avoir : `C:\JavaFX\javafx-sdk-21.0.9\lib\`

3. **Vérifier l'installation** :
   ```powershell
   Test-Path "C:\JavaFX\javafx-sdk-21.0.9\lib"
   ```
   Doit retourner `True`

### Option B : Installation via Script PowerShell

Exécutez ce script PowerShell (en tant qu'administrateur) :

```powershell
# Créer le dossier JavaFX
New-Item -ItemType Directory -Path "C:\JavaFX" -Force

# Télécharger JavaFX (remplacez l'URL par la dernière version)
$url = "https://download2.gluonhq.com/openjfx/21.0.9/openjfx-21.0.9_windows-x64_bin-sdk.zip"
$output = "$env:TEMP\javafx-sdk.zip"
Invoke-WebRequest -Uri $url -OutFile $output

# Extraire
Expand-Archive -Path $output -DestinationPath "C:\JavaFX\" -Force

# Vérifier
if (Test-Path "C:\JavaFX\javafx-sdk-21.0.9\lib") {
    Write-Host "✅ JavaFX installé avec succès !" -ForegroundColor Green
} else {
    Write-Host "❌ Erreur lors de l'installation" -ForegroundColor Red
}
```

## ⚙️ Étape 2 : Configuration dans IntelliJ IDEA

1. **Ouvrir le projet** dans IntelliJ IDEA

2. **Ajouter JavaFX comme bibliothèque** :
   - `File` → `Project Structure` (Ctrl+Alt+Shift+S)
   - Onglet `Libraries`
   - Cliquez sur `+` → `Java`
   - Naviguez vers `C:\JavaFX\javafx-sdk-21.0.9\lib`
   - Sélectionnez le dossier `lib` et cliquez `OK`
   - Cliquez `Apply` puis `OK`

3. **Configurer les VM Options pour l'exécution** :
   - `Run` → `Edit Configurations...`
   - Sélectionnez votre configuration `Main` (ou créez-en une)
   - Dans `VM options`, ajoutez :
     ```
     --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
     ```
   - Cliquez `Apply` puis `OK`

## 🗄️ Étape 3 : Vérification de la Base de Données

### Vérifier si la base existe

```sql
-- Se connecter à MySQL
mysql -u root -p

-- Vérifier les bases de données
SHOW DATABASES;

-- Si gestion_restaurant n'existe pas, la créer
CREATE DATABASE IF NOT EXISTS gestion_restaurant 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE gestion_restaurant;
SHOW TABLES;
```

### Si les tables n'existent pas

Consultez le fichier `doc/BASE_DE_DONNEES.md` pour créer les tables manuellement, ou exécutez un script SQL d'initialisation si disponible.

## ✅ Étape 4 : Test de Compilation

Dans IntelliJ IDEA :

1. **Compiler le projet** :
   - `Build` → `Rebuild Project`
   - Vérifiez qu'il n'y a pas d'erreurs

2. **Exécuter Main.java** :
   - Clic droit sur `Main.java` → `Run 'Main.main()'`
   - La fenêtre de connexion devrait s'afficher

## 🐛 Résolution de Problèmes

### Erreur : "module not found: javafx.controls"
- Vérifiez que JavaFX est bien installé dans `C:\JavaFX\javafx-sdk-21.0.9\lib`
- Vérifiez les VM options dans la configuration d'exécution

### Erreur : "package javafx does not exist"
- Rebuild le projet : `Build` → `Rebuild Project`
- Vérifiez que JavaFX est bien ajouté comme bibliothèque

### Erreur de connexion MySQL
- Vérifiez que WAMP est démarré
- Vérifiez les identifiants dans `DBConnection.java`
- Testez la connexion avec `TestDBConnection.java`

### L'application démarre mais la fenêtre ne s'affiche pas
- Vérifiez les logs dans la console
- Vérifiez que JavaFX est bien dans le classpath

## 📝 Checklist Finale

Avant d'exécuter, vérifiez :

- [ ] JavaFX installé dans `C:\JavaFX\javafx-sdk-21.0.9\lib`
- [ ] JavaFX ajouté comme bibliothèque dans IntelliJ
- [ ] VM options configurées dans Run Configuration
- [ ] MySQL démarré (WAMP actif)
- [ ] Base de données `gestion_restaurant` créée
- [ ] Tables créées dans la base de données
- [ ] MySQL Connector dans le classpath

## 🚀 Exécution

Une fois tout configuré :

1. Démarrer WAMP (si pas déjà fait)
2. Dans IntelliJ : `Run` → `Run 'Main'`
3. La fenêtre de connexion devrait s'afficher !

---

_En cas de problème, consultez le [Guide d'Installation Complet](GUIDE_INSTALLATION.md)_
