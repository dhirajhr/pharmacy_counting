#!/bin/bash
javac ./src/Main.java
javac ./src/Prescriber.java
java -cp ./src Main ./input/itcont.txt ./output/top_cost_drug.txt
