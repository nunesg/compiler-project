package com.github.biancacristina.lexical.analysis;

import com.github.biancacristina.lexical.analysis.service.impl.AutomatonSimulationServiceImpl;

import java.io.InputStream;
import java.util.Scanner;

public class LexicalAnalysis {

    private static String readFile(String fileName) {
        InputStream inputStream  = LexicalAnalysis.class.getResourceAsStream(fileName);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder fileStr = new StringBuilder();
        while(scanner.hasNextLine()) {
            fileStr.append(scanner.nextLine());
        }
        scanner.close();
        return fileStr.toString();
    }

    public static void main(String[] args) {
        AutomatonSimulationServiceImpl simulator = new AutomatonSimulationServiceImpl();

        String s = LexicalAnalysis.readFile(args[0]);
        simulator.simulate(s);
    }
}