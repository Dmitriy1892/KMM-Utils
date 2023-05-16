#!/bin/bash

OS=$1

if [ "$OS" == "macos-latest" ]; then
  ./gradlew publishAllPublicationsToSonatypeRepository
elif [ "$OS" == "windows-latest" ]; then
  ./gradlew publishAllPublicationsToSonatypeRepository
elif [ "$OS" == "ubuntu-latest" ]; then
  ./gradlew publishAllPublicationsToSonatypeRepository
else
  ./gradlew publishAllPublicationsToSonatypeRepository
fi