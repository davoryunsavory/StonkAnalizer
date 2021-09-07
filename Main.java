package com.mycompany.stockanalizer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BACKENDSTOCK {

    public static List<Double> BASE_ARRAY(File file, List<String> line) {
        String[] TEMP_ARRAY = new String[line.size()];
        List<Double> BASE_ARRAY = new ArrayList<>();
        for (String lines : line) {
            TEMP_ARRAY = lines.split(",");
            BASE_ARRAY.add(Double.parseDouble(TEMP_ARRAY[1]));
            BASE_ARRAY.add(Double.parseDouble(TEMP_ARRAY[4]));
        }
        return BASE_ARRAY;
    }
    
    public static List<Double> PIVOT_ARRAY(File file, List<String> line, int DEPTH) {
        List<Double> BASE_ARRAY = BASE_ARRAY(file, line);
        List<Double> PIVOT_ARRAY = new ArrayList<>();
        int SIZE = BASE_ARRAY.size();
        
        for (int i = 0; i < SIZE; i++) {
            
        }
        
        return PIVOT_ARRAY;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\david\\OneDrive\\Documents\\DELL.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        System.out.println(BASE_ARRAY(file, line));
    }
}
