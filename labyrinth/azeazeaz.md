## 🚀 Exécution du projet

Il existe plusieurs façons de lancer le jeu Labyrinth :

### 1️⃣ Avec Maven

```bash
cd labyrinth
mvn javafx:run
```

> ⚠️ Cette méthode utilise Maven pour gérer JavaFX automatiquement.

---

### 2️⃣ Avec le script `run.sh` (Linux/Mac)

Si JavaFX n’est pas encore installé, faites :

```bash
sudo apt install openjfx
```

Ensuite, lancez le script :

```bash
./run.sh
```

> 💡 Le script configure automatiquement le chemin vers JavaFX et démarre le jeu.

---

### 3️⃣ Avec le JAR exécutable

```bash
java --module-path /usr/share/openjfx/lib \
     --add-modules javafx.controls,javafx.fxml \
     -jar target/labyrinth-1.0-SNAPSHOT-jar-with-dependencies.jar
```

> 💡 Vous pouvez créer un alias ou un script pour éviter de taper la ligne complète à chaque fois.

---

### 4️⃣ Avec le script `run.bat` (Windows)

1. Téléchargez et installez le SDK JavaFX 17 pour Windows.
2. Créez un fichier `run.bat` dans le dossier du projet :

```bat
@echo off
set "JAVAFX_PATH=C:\chemin\vers\javafx-sdk-17\lib"
java --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml -jar target\labyrinth-1.0-SNAPSHOT-jar-with-dependencies.jar
pause
```

3. Double-cliquez sur `run.bat` pour lancer le jeu.

---

## 🎮 Modes de jeu

* **Mode Libre** : explorez le labyrinthe sans contraintes.
* **Mode Progression** : résolvez des labyrinthes de difficulté croissante.
* **Mode Progression Limité** : progression avec contraintes supplémentaires.

---


## ⚡ Astuces

* Sur Linux/Mac, utilisez `./run.sh` pour un lancement rapide.
* Sur Windows, double-cliquez sur `run.bat`.
* Pour déboguer, vous pouvez toujours exécuter via Maven : `mvn javafx:run`.
* Assurez-vous que JavaFX correspond à la version de Java utilisée (ici **17**).


