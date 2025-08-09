#!/bin/bash
PROJECT_DIR="/home/vera/IdeaProjects/ProgrammingCalendar"

cd "$PROJECT_DIR" || {
    echo "Ошибка: не удалось перейти в $PROJECT_DIR"
    exit 1
}

mkdir -p out
javac -d out src/MainApplication.java
jar cfe out/ProgrammingCalendar.jar MainApplication -C out .
java -jar "$PROJECT_DIR/out/ProgrammingCalendar.jar" "$@"