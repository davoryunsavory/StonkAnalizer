import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class Main {

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

    public static List<List<Double>> findPivots(File file, List<String> line) {
        List<List<Double>> rangeArray = findRange(file, line);
        List<List<Double>> pivotArray = new ArrayList<List<Double>>();
        List<String> updownArray = findUpDown(file, line);
        int size = rangeArray.size() - 1;

        for (int i = 0; i < size; i++) {
            if (updownArray.get(i).equals("p") && updownArray.get(i + 1).equals("v")) {
                pivotArray.add(rangeArray.get(i));
            } else if (updownArray.get(i).equals("v") && updownArray.get(i + 1).equals("p")) {
                pivotArray.add(rangeArray.get(i));
            }
        }

        return pivotArray;
    }

    public static List<Double> getDepth(File file, List<String> line, int depth) {
        List<Double> depthSlopeArray = new ArrayList<Double>();
        List<Integer> depthArray = new ArrayList<Integer>();
        List<List<Double>> rangeArray = findRange(file, line);
        List<Double> averageArray = new ArrayList<Double>();
        int size = rangeArray.size();
        int constantDepth = depth;
        int running = 0;
        int extra = 0;

        for (int i = 0; i < constantDepth; i++) {
            depth = size / depth;
            depthArray.add(depth);
            running = depthArray.get(i) + running;
            depth = constantDepth;
        }
        extra = size - running;
        for (int i = 0; i < depthArray.size(); i++) {
            if (extra == 0) {
                break;
            } else {
                depthArray.set(i, depthArray.get(i) + 1);
                extra -= 1;
            }

        }
        for (int i = 0; i < size; i++) {
            averageArray.add((rangeArray.get(i).get(0) + rangeArray.get(i).get(1)) / 2);
        }
        int runningDepth = 0;
        int runningDepthTwo = 0;

        for (int i = 0, k = 1; i < depthArray.size() && k < depthArray.size(); i++, k++) {
            runningDepth = depthArray.get(k) + runningDepth;
            runningDepthTwo = depthArray.get(i) + runningDepthTwo;
            if (runningDepth >= size || runningDepthTwo >= size) {
                break;
            }
            depthSlopeArray.add((averageArray.get(runningDepth) - averageArray.get(runningDepthTwo)) / runningDepth);
        }

        if (depth ==1) {
            depthSlopeArray.add((averageArray.get(averageArray.size() - 1) - averageArray.get(0)) / 2);
        }

        System.out.println(depthArray);
        System.out.println(averageArray);

        return depthSlopeArray;
    }

    public static List<List<Double>> findPennant (File file, List<String> line) {
        List<List<Double>> pennantArray = new ArrayList<List<Double>>();
        List<Double> depthSlopeArray = getDepth(file, line, 1);
        List<List<Double>> rangeArray = findRange(file, line);



        return pennantArray;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\david\\OneDrive\\Desktop\\stocks\\newdow.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        System.out.println(getDepth(file, line, 1));
    }
}

// 1, 6, 8, 7
// Date,Open,High,Low,Close,Adj Close,Volume
