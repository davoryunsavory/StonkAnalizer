import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  
class Main {

    public static void main(String[] args) throws Exception {
        
        File file = new File("C:\\Users\\david\\OneDrive\\Desktop\\stocks\\newdow.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        String[] array = new String[line.size()];
        List<Double> openArray = new ArrayList<Double>();
        List<Double> closeArray = new ArrayList<Double>();
        List<Double> diffArray = new ArrayList<Double>();
        List<Double> slopeArray = new ArrayList<Double>();
        for (String lines : line) { 
            array = lines.split(",");
            openArray.add(Double.parseDouble(array[1]));
            closeArray.add(Double.parseDouble(array[4]));
            //System.out.println(array[1]); 
         }
        for (int i = 1; i < openArray.size(); i++) {
            diffArray.add(closeArray.get(i) - openArray.get(i));
        }

        for (int i = 0; i < diffArray.size(); i++) {
            List<Double> tempCollection = new ArrayList<Double>();
            while (diffArray.get(i) != null) {
                if (diffArray.get(i) < 0) {
                    break;
                } else {
                    tempCollection.add(diffArray.get(i));
                }
            }
        }

        System.out.println();
    }
}


// 1, 6, 8, 7