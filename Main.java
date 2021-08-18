import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import javax.swing.text.Position;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;

class Main {

    private static List<Double> openArray = new ArrayList<Double>();
    private static List<Double> closeArray = new ArrayList<Double>();
    private static List<Double> diffArray = new ArrayList<Double>();
    private static List<Double> slopeArray = new ArrayList<Double>();
    private static List<Double> lineArray = new ArrayList<Double>();

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
        Double runningTotalPositive = 0.0;
        Double runningTotalNegative = 0.0;
        List<Double> positive = new ArrayList<Double>();
        List<Double> negative = new ArrayList<Double>();
        for (String lines : line) {
            array = lines.split(",");
            diffArray.add(Double.parseDouble(array[4]) - Double.parseDouble(array[1]));
        }
        for (int i = 0; i < diffArray.size() - 1; i++) {
            if (diffArray.get(i) < 0 && diffArray.get(i + 1) >= 0) {
                negative.add(diffArray.get(i));
                for (int k = 0; k < negative.size(); k++) {
                    runningTotalNegative = negative.get(k) + runningTotalNegative;
                }
                slopeArray.add(runningTotalNegative / negative.size());
                runningTotalNegative = 0.0;
                negative.clear();
            } else if (diffArray.get(i) >= 0 && diffArray.get(i + 1) < 0) {
                positive.add(diffArray.get(i));
                for (int k = 0; k < positive.size(); k++) {
                    runningTotalPositive = positive.get(k) + runningTotalPositive;
                }
                slopeArray.add(runningTotalPositive / positive.size());
                runningTotalPositive = 0.0;
                positive.clear();
            } else {
                if (diffArray.get(i) < 0) {
                    negative.add(diffArray.get(i));
                } else if (diffArray.get(i) >= 0) {
                    positive.add(diffArray.get(i));
                }
            }
        }
        return slopeArray;
    }

    public static List<Double> lineFunction(File file, List<String> line) {
        String[] array = new String[line.size()];
        List<Double> pathArray = new ArrayList<Double>();
        Double first = 0.0;
        int running = 0;
        int direction = 0;

        for (String lines : line) {
            array = lines.split(",");
            pathArray.add(Double.parseDouble(array[1]));
            pathArray.add(Double.parseDouble(array[4]));
        }
        first = pathArray.get(0);
        if (pathArray.get(1) - pathArray.get(0) > 0) {
            direction = 1; // positive
        } else {
            direction = 0; // negative
        }
        for (int i = 0; i < pathArray.size() - 1; i++) {
            if (pathArray.get(i + 1) - pathArray.get(i) > 0 && direction == 0) {
                pathArray.add(i, null);
                direction = 1;
            } else if (pathArray.get(i + 1) - pathArray.get(i) < 0 && direction == 1) {
                pathArray.add(i, null);
                direction = 0;
            }
        }

        for (int i = 0; i < pathArray.size(); i++) {
            running += 1;
            if (pathArray.get(i) == null) {
                slopeArray.add((pathArray.get(i+1) - first) / running);
                running = 0;
                first = pathArray.get(i+1);
            }
        }
        System.out.println(pathArray);
        return slopeArray;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\david\\OneDrive\\Desktop\\stocks\\newdow.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        System.out.println(lineFunction(file, line));
    }
}

// 1, 6, 8, 7
// Date,Open,High,Low,Close,Adj Close,Volume