#!/bin/bash

# Если нет аргументов — берём текущий месяц и год
if [ -z "$1" ] || [ -z "$2" ]; then
  MONTH=$(date +%B | tr '[:upper:]' '[:lower:]')  # Полное название месяца в нижнем регистре
  YEAR=$(date +%Y)                                # Год
else
  MONTH=$1
  YEAR=$2
fi

FILE="/home/vera/IdeaProjects/ProgrammingCalendar/out/${MONTH}_${YEAR}.txt"

if [ -f "$FILE" ]; then
  cat "$FILE"
else
  echo "Нет данных по таким параметрам: $MONTH $YEAR"
fi
