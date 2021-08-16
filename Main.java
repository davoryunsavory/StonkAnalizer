import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    private static List<Double> openArray = new ArrayList<Double>();
    private static List<Double> closeArray = new ArrayList<Double>();
    private static List<Double> diffArray = new ArrayList<Double>();
    private static List<Double> slopeArray = new ArrayList<Double>(50);

    public static List<Double> openFunction(File file, List<String> line) {
        String[] array = new String[line.size()];
        for (String lines : line) {
            array = lines.split(",");
            openArray.add(Double.parseDouble(array[1]));
        }
        return openArray;
    }

    public static List<Double> closeFunction(File file, List<String> line) {

        String[] array = new String[line.size()];
        for (String lines : line) {
            array = lines.split(",");
            closeArray.add(Double.parseDouble(array[4]));
        }
        return closeArray;
    }

    public static List<Double> diffFuction(File file, List<String> line) {
        String[] array = new String[line.size()];
        List<Double> positive = new ArrayList<Double>();
        List<Double> negative = new ArrayList<Double>();
        for (String lines : line) {
            array = lines.split(",");
            diffArray.add(Double.parseDouble(array[4]) - Double.parseDouble(array[1]));
        }
        for (int i = 0; i < diffArray.size() - 1; i++) {
            if (diffArray.get(i) < 0 && diffArray.get(i + 1) >= 0) {
                negative.add(diffArray.get(i));
                negative.add(null);
            } else if (diffArray.get(i) >= 0 && diffArray.get(i + 1) < 0) {
                positive.add(diffArray.get(i));
                positive.add(null);
            } else {
                if (diffArray.get(i) < 0) {
                    negative.add(diffArray.get(i));
                } else if (diffArray.get(i) >= 0) {
                    positive.add(diffArray.get(i));
                }
            }
        }

        return positive;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\david\\OneDrive\\Desktop\\stocks\\newdow.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        System.out.println(diffFuction(file, line));
    }
}

// 1, 6, 8, 7
// Date,Open,High,Low,Close,Adj Close,Volume