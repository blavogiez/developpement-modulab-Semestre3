#!/bin/bash
TARGET_DIR="target/classes"
rm -rf "$TARGET_DIR"
mkdir -p "$TARGET_DIR"
javac \
  --module-path "/usr/share/openjfx/lib" \
  --add-modules javafx.controls,javafx.fxml \
  -d "$TARGET_DIR" \
  $(find "src/main/java" -name "*.java")
cp -r "src/main/resources"/* "$TARGET_DIR"/ 2>/dev/null || true
jar cfm "target/labyrinthEXC.jar" "MANIFEST.MF" -C "$TARGET_DIR" .
echo "Compilation terminée "
