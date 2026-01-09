# Guide d'Installation JavaFX - Étape par Étape

## 📋 Prérequis Vérifiés

- ✅ Java installé (version 25.0.1)
- ✅ Dossier `C:\JavaFX` créé
- ⏳ JavaFX SDK à télécharger

## 📥 Étape 1 : Télécharger JavaFX

### Option A : Téléchargement Manuel (Recommandé)

1. **Ouvrez votre navigateur** (Chrome, Firefox, Edge, etc.)

2. **Allez sur le site OpenJFX** :
   - URL : https://openjfx.io/
   - OU directement : https://openjfx.io/openjfx-docs/#install-javafx

3. **Cliquez sur "Download"** dans le menu

4. **Sélectionnez** :
   - **Version** : `21.0.9` (ou la dernière version 21.x)
   - **Platform** : `Windows`
   - **Type** : `SDK` (pas JRE)

5. **Téléchargez le fichier ZIP**
   - Nom du fichier : `openjfx-21.0.9_windows-x64_bin-sdk.zip`
   - Taille : environ 50-60 MB

### Option B : Lien Direct (Si disponible)

Si le lien direct fonctionne, vous pouvez utiliser :
```
https://download2.gluonhq.com/openjfx/21.0.9/openjfx-21.0.9_windows-x64_bin-sdk.zip
```

## 📦 Étape 2 : Extraire JavaFX

1. **Localisez le fichier ZIP** téléchargé
   - Généralement dans `C:\Users\acer\Downloads\`

2. **Clic droit sur le fichier ZIP** → `Extraire tout...`

3. **Destination** :
   - Naviguez vers `C:\JavaFX\`
   - **IMPORTANT** : Assurez-vous que le résultat soit `C:\JavaFX\javafx-sdk-21.0.9\`
   - Ne pas avoir `C:\JavaFX\openjfx-21.0.9\javafx-sdk-21.0.9\` (double dossier)

4. **Cliquez sur "Extraire"**

5. **Vérifiez la structure** :
   ```
   C:\JavaFX\
   └── javafx-sdk-21.0.9\
       ├── lib\
       │   ├── javafx.base.jar
       │   ├── javafx.controls.jar
       │   ├── javafx.fxml.jar
       │   └── ... (autres fichiers .jar)
       ├── legal\
       └── ... (autres dossiers)
   ```

## ✅ Étape 3 : Vérifier l'Installation

Ouvrez PowerShell et exécutez :

```powershell
# Vérifier que le dossier existe
Test-Path "C:\JavaFX\javafx-sdk-21.0.9\lib"

# Voir les fichiers JAR
Get-ChildItem "C:\JavaFX\javafx-sdk-21.0.9\lib\*.jar" | Select-Object Name
```

**Résultat attendu** :
- `True` pour le premier test
- Liste des fichiers `.jar` (javafx.base.jar, javafx.controls.jar, etc.)

## 🎯 Étape 4 : Configurer IntelliJ IDEA

Une fois JavaFX installé :

### 4.1 Ajouter JavaFX comme Bibliothèque

1. **Ouvrez IntelliJ IDEA**

2. **Ouvrez votre projet** `GestionRestaurant`

3. **File** → **Project Structure** (ou `Ctrl+Alt+Shift+S`)

4. **Onglet "Libraries"** (à gauche)

5. **Cliquez sur le `+`** en haut

6. **Sélectionnez "Java"**

7. **Naviguez vers** :
   ```
   C:\JavaFX\javafx-sdk-21.0.9\lib
   ```

8. **Sélectionnez le dossier `lib`** (pas les fichiers individuels)

9. **Cliquez "OK"**

10. **Cliquez "Apply"** puis **"OK"**

### 4.2 Configurer les VM Options

1. **Run** → **Edit Configurations...**

2. **Si aucune configuration n'existe** :
   - Cliquez sur `+` → `Application`
   - **Name** : `Main`
   - **Main class** : `Main`
   - **Module** : `GestionRestaurant`

3. **Dans "VM options"**, ajoutez :
   ```
   --module-path "C:/JavaFX/javafx-sdk-21.0.9/lib" --add-modules javafx.controls,javafx.fxml
   ```

4. **Cliquez "Apply"** puis **"OK"**

## 🚀 Étape 5 : Tester l'Installation

1. **Dans IntelliJ IDEA** :
   - `Build` → `Rebuild Project`
   - Vérifiez qu'il n'y a pas d'erreurs

2. **Exécuter** :
   - Clic droit sur `src/Main.java`
   - `Run 'Main.main()'`

3. **Résultat attendu** :
   - ✅ Une fenêtre de connexion s'affiche
   - ✅ Titre : "Authentification"
   - ✅ Champs : Identifiant et Mot de passe

## 🐛 Problèmes Courants

### "Le dossier lib n'existe pas"
→ Vérifiez que vous avez extrait dans le bon endroit. Le chemin doit être exactement `C:\JavaFX\javafx-sdk-21.0.9\lib`

### "module not found: javafx.controls"
→ Vérifiez les VM options dans IntelliJ. Le chemin doit être correct.

### "package javafx does not exist"
→ Rebuild le projet : `Build` → `Rebuild Project`

## 📝 Checklist

- [ ] JavaFX téléchargé depuis https://openjfx.io/
- [ ] Fichier ZIP extrait dans `C:\JavaFX\`
- [ ] Structure correcte : `C:\JavaFX\javafx-sdk-21.0.9\lib\` existe
- [ ] JavaFX ajouté comme bibliothèque dans IntelliJ
- [ ] VM options configurées dans Run Configuration
- [ ] Projet rebuild sans erreurs
- [ ] Application exécutée avec succès

---

**Besoin d'aide ?** Dites-moi à quelle étape vous êtes bloqué !
