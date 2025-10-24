#!/bin/bash
mvn clean package
#java --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -jar target/labyrinth-1.0-SNAPSHOT-jar-with-dependencies.jar
mvn javafx:run