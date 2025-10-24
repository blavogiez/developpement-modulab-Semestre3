#!/bin/bash
javac --module-path "/usr/share/openjfx/lib" \
      --add-modules javafx.controls,javafx.fxml \
      -d "out" $(find src/main/java -name "*.java")
cp -r src/main/resources/* "out"/ 2>/dev/null
jar cfm "labyrinthEXC.jar" MANIFEST.MF -C "out" .
echo "Compilation terminée"
