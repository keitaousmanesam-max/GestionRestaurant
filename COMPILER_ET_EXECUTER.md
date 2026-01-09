# Comment Compiler et Exécuter l'Application

## ❌ Erreur Actuelle

Vous avez essayé :
```powershell
java Main.java
```

**Problèmes** :
1. ❌ `java Main.java` n'est pas la bonne commande (cela fonctionne seulement avec Java 11+ pour les fichiers simples, pas pour des projets avec dépendances)
2. ❌ JavaFX n'est pas installé (d'où les erreurs "package javafx does not exist")
3. ❌ Il faut d'abord **compiler** avec `javac` puis **exécuter** avec `java`

## ✅ Solution : Compiler et Exécuter Correctement

### Prérequis
- ✅ Java installé (vous l'avez : version 25.0.1)
- ❌ **JavaFX installé** (à faire en premier !)
- ✅ MySQL démarré

### Étape 1 : Installer JavaFX (OBLIGATOIRE)

1. Télécharger depuis : https://openjfx.io/
   - Version : JavaFX 21.0.9
   - Plateforme : Windows
   - Type : SDK
2. Extraire dans : `C:\JavaFX\javafx-sdk-21.0.9\`
3. Vérifier :
   ```powershell
   Test-Path "C:\JavaFX\javafx-sdk-21.0.9\lib"
   ```
   Doit retourner `True`

### Étape 2 : Compiler le Projet

**Depuis le dossier racine du projet** (`GestionRestaurant`) :

```powershell
# Se placer dans le dossier du projet
cd "C:\Users\acer\Desktop\Projet Java G5\Code\GestionRestaurant"

# Compiler avec JavaFX
javac --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" `
      --add-modules javafx.controls,javafx.fxml `
      -cp "C:/JavaFX/javafx-sdk-21.0.9/lib/*;../../../mysql-connector-j-8.0.33/mysql-connector-j-8.0.33.jar" `
      -d out/production/GestionRestaurant `
      -sourcepath src `
      src/Main.java
```

**Explication** :
- `--module-path` : Chemin vers JavaFX
- `--add-modules` : Modules JavaFX nécessaires
- `-cp` : Classpath (JavaFX + MySQL Connector)
- `-d` : Dossier de sortie pour les fichiers compilés
- `-sourcepath` : Dossier source

### Étape 3 : Exécuter l'Application

```powershell
java --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" `
     --add-modules javafx.controls,javafx.fxml `
     -cp "out/production/GestionRestaurant;C:/JavaFX/javafx-sdk-21.0.9/lib/*;../../../mysql-connector-j-8.0.33/mysql-connector-j-8.0.33.jar" `
     Main
```

## 🎯 Méthode Recommandée : Utiliser IntelliJ IDEA

**C'est beaucoup plus simple avec IntelliJ !**

### Configuration dans IntelliJ

1. **Ajouter JavaFX comme bibliothèque** :
   - `File` → `Project Structure` (Ctrl+Alt+Shift+S)
   - `Libraries` → `+` → `Java`
   - Sélectionner `C:\JavaFX\javafx-sdk-21.0.9\lib`
   - `Apply` → `OK`

2. **Configurer les VM Options** :
   - `Run` → `Edit Configurations...`
   - Créer une configuration `Application` :
     - Name : `Main`
     - Main class : `Main`
     - VM options :
       ```
       --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
       ```
   - `Apply` → `OK`

3. **Exécuter** :
   - Clic droit sur `Main.java` → `Run 'Main.main()'`
   - OU `Run` → `Run 'Main'`

## 📝 Script PowerShell Complet

Créez un fichier `compiler-et-executer.ps1` :

```powershell
# Vérifier que JavaFX est installé
$javafxPath = "C:\JavaFX\javafx-sdk-21.0.9\lib"
if (-not (Test-Path $javafxPath)) {
    Write-Host "❌ JavaFX non installé !" -ForegroundColor Red
    Write-Host "Téléchargez depuis: https://openjfx.io/" -ForegroundColor Yellow
    exit 1
}

# Chemin du projet
$projectPath = "C:\Users\acer\Desktop\Projet Java G5\Code\GestionRestaurant"
$mysqlConnector = "$projectPath\..\..\..\mysql-connector-j-8.0.33\mysql-connector-j-8.0.33.jar"

# Compiler
Write-Host "🔨 Compilation en cours..." -ForegroundColor Cyan
javac --module-path "$javafxPath" `
      --add-modules javafx.controls,javafx.fxml `
      -cp "$javafxPath\*;$mysqlConnector" `
      -d "$projectPath\out\production\GestionRestaurant" `
      -sourcepath "$projectPath\src" `
      "$projectPath\src\Main.java"

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Compilation réussie !" -ForegroundColor Green
    
    # Exécuter
    Write-Host "🚀 Lancement de l'application..." -ForegroundColor Cyan
    java --module-path "$javafxPath" `
         --add-modules javafx.controls,javafx.fxml `
         -cp "$projectPath\out\production\GestionRestaurant;$javafxPath\*;$mysqlConnector" `
         Main
} else {
    Write-Host "❌ Erreur de compilation" -ForegroundColor Red
}
```

## 🐛 Résolution des Erreurs

### "package javafx does not exist"
→ JavaFX n'est pas installé ou le chemin est incorrect

### "module not found: javafx.controls"
→ Vérifiez le chemin vers JavaFX dans `--module-path`

### "Access denied for user 'root'"
→ Le mot de passe MySQL est incorrect dans `DBConnection.java`

### "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
→ Le MySQL Connector n'est pas dans le classpath

## 💡 Conseil

**Utilisez IntelliJ IDEA** plutôt que la ligne de commande. C'est beaucoup plus simple et gère automatiquement :
- La compilation
- Le classpath
- Les modules JavaFX
- Les dépendances

---

_Une fois JavaFX installé, utilisez IntelliJ pour exécuter l'application !_
