import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;  
class Main {

    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\david\\OneDrive\\Desktop\\stocks\\newdow.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        for (String lines : line) { 
            String[] array = lines.split(","); 
            System.out.println(array[1]); 
         }

    }
}