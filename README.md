# jpwp-project (codename Mazedea)
Project for university subject about high level programming languages.
## Description
It is a simple maze game made as serious game in mind, with levers, gates, doors and keys. There is no indication how elements are connected which forces player to think in advance, use memory, logic and problem solving abilites to achieve exit.
## Features
- GUI in Java Swing
- terminal (CLI) interface
- dockerfile (for GUI via VNC)
- custom map loading is possible if placed in maps/map_name.map. MapLoader.returnMap class function has description how one can add more maps.
- makefile for automatic building and running of app, jar, docker image or docs
## Technical limitations
- window is fixed 1280x1024
- map is limited to 18x16 elements (no more or less, may be changed later)
- game was made with OpenJDK 17 in mind, other versions are not guaranteed to work
- provided makefile is expected to work on Linux system, others are not guaranteed to work
## Known bugs
- none known at time of writing :)
## Todo
- UI polishing
- map editor
- more maps?
