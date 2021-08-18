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
                slopeArray.add((pathArray.get(i+1) - first) / running);
                running = 0;
                first = pathArray.get(i+1);
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
    
    public static List<Double> getPath(File file, List<String> line) {
    	List<Double> pathArray = new ArrayList<Double>();
    	
    	
    	return pathArray;
    }

    public static void main(String[] args) throws Exception {
    	
        File file = new File("C:\\Users\\FD\\Downloads\\PRPH.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        System.out.println(lineFunction(file, line));
    }
}

// 1, 6, 8, 7
// Date,Open,High,Low,Close,Adj Close,Volume
