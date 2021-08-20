import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class Main {
    private static List<Double> slopeArray = new ArrayList<Double>();

    public static List<Double> lineFunction(File file, List<String> line) {
        Double first = 0.0;
        int running = 0;
        int direction = 0;
        List<Double> pathArray = getArray(file, line);
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
                slopeArray.add((pathArray.get(i + 1) - first) / running);
                running = 0;
                first = pathArray.get(i + 1);
            }
        }
        System.out.println(pathArray);
        return slopeArray;
    }

    public static List<Double> getArray(File file, List<String> line) {
        String[] array = new String[line.size()];
        List<Double> lineArray = new ArrayList<Double>();
        for (String lines : line) {
            array = lines.split(",");
            lineArray.add(Double.parseDouble(array[1]));
            lineArray.add(Double.parseDouble(array[4]));
        }
        return lineArray;
    }

    public static List<List<Double>> findRange(File file, List<String> line) {
        List<List<Double>> rangeArray = new ArrayList<List<Double>>();
        String[] array = new String[line.size()];

        for (String lines : line) {
            array = lines.split(",");
            List<Double> temp = new ArrayList<Double>();
            temp.add(Double.parseDouble(array[1]));
            temp.add(Double.parseDouble(array[4]));
            rangeArray.add(temp);
        }
        return rangeArray;
    }

    public static List<String> findUpDown(File file, List<String> line) {
        List<String> updownArray = new ArrayList<String>();
        List<List<Double>> rangeArray = findRange(file, line);
        int size = rangeArray.size();

        for (int i = 0; i < size; i++) {
            if (rangeArray.get(i).get(0) > rangeArray.get(i).get(1)) {
                updownArray.add("v");
            } else if (rangeArray.get(i).get(0) < rangeArray.get(i).get(1)) {
                updownArray.add("p");
            }
        }

        return updownArray;
    }

    public static List<List<Double>> findPivots (File file, List<String> line) {
        List<List<Double>> rangeArray = findRange(file, line);
        List<List<Double>> pivotArray = new ArrayList<List<Double>>();
        List<String> updownArray = findUpDown(file, line);
        int size = rangeArray.size() - 1;

        for (int i = 0; i < size; i++) {
            if (updownArray.get(i).equals("p") && updownArray.get(i+1).equals("v")) {
                pivotArray.add(rangeArray.get(i));
            } else if (updownArray.get(i).equals("v") && updownArray.get(i+1).equals("p")) {
                pivotArray.add(rangeArray.get(i));
            }
        }

        return pivotArray;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\david\\OneDrive\\Desktop\\stocks\\newdow.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        System.out.println(findRange(file, line));
        System.out.println(findUpDown(file, line));
        System.out.println(findPivots(file, line));
    }
}

// 1, 6, 8, 7
// Date,Open,High,Low,Close,Adj Close,Volume
