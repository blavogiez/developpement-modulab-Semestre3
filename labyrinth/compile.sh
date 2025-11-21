#!/bin/bash
TARGET_DIR="target/classes"
if ! command -v javac &> /dev/null; then
    echo "Installez JDK : sudo apt install openjdk-17-jdk"
    exit 1
fi
if ! command -v jar &> /dev/null; then
    echo "jar introuvable"
    exit 1
fi

rm -rf "$TARGET_DIR"
mkdir -p "$TARGET_DIR"
javac -Xlint:unchecked \
  --module-path "/usr/share/openjfx/lib" \
  --add-modules javafx.controls,javafx.fxml,javafx.media\
  -d "$TARGET_DIR" \
  $(find "src/main/java" -name "*.java")
cp -r "src/main/resources"/* "$TARGET_DIR"/ 2>/dev/null || true
jar cfm "target/labyrinthEXC.jar" "manifest.mf" -C "$TARGET_DIR" .
echo "Compilation terminée "
