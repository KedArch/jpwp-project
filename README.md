# jpwp-project (codename Mazedea)
Project for university subject about high level programming languages.
## Description
It is a simple maze game made as serious game in mind, with levels, gates, doors and keys. There is no indication how elements are connected which forces player to think in advance, use memory, logic and problem solving abilites to achieve exit.
## Features
- custom map loading is possible if placed in maps/map_name.map. MapLoader.returnMap class function has description how one can add more maps.
- terminal (CLI) interface
- dockerfile (for GUI via VNC)
- makefile for automatic building of app, jar, docker image or docs
## Technical limitations
- map is limited to 18x16 elements (no more or less)
- game was made with OpenJDK 17 in mind, others may work or not
## Known bugs
- none known at time of writing :)
## Todo
- Map editor
- More maps?
