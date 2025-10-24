#!/bin/bash
# Script pour generer la javadoc dans le repertoire "doc"
# Ce script doit etre execute dans "labyrinth"

# Executer la commande maven pour generer la javadoc en utilisant le wrapper
# on ignore les erreurs de Javadoc car le code a des erreurs de documentation
mvn javadoc:javadoc -Dmaven.javadoc.failOnError=false

# Creer le repertoire doc s'il n'existe pas
mkdir -p ./doc

# Copier la javadoc generee vers le repertoire doc dans le repertoire labyrinth
# Le repertoire de sortie peut varier, essayons les deux emplacements possibles
if [ -d "target/site/apidocs" ]; then
    cp -r target/site/apidocs ./doc/
    echo "Javadoc generee dans le repertoire labyrinth/doc/"
elif [ -d "target/reports/apidocs" ]; then
    cp -r target/reports/apidocs ./doc/
    echo "Javadoc generee dans le repertoire labyrinth/doc/"
else
    echo "Aucun repertoire de javadoc trouve"
    exit 1
fi
