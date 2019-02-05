#!/bin/bash
cd src
javac -d .  Main.java
javac -d . Prescriber.java
java Main ../input/itcont.txt ../output/top_cost_drug.txt
