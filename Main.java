import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedList;

class Main {

	private static int size = 0;
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

    public static Map<Integer, List<Double>> findPivots(File file, List<String> line) {
        List<List<Double>> rangeArray = findRange(file, line);
        List<String> updownArray = findUpDown(file, line);
        Map<Integer, List<Double>> pivotMap = new TreeMap<Integer, List<Double>>();
        size = rangeArray.size() - 1;

        for (int i = 0; i < size; i++) {
            if (updownArray.get(i).equals("p") && updownArray.get(i + 1).equals("v")) {
                pivotMap.put(size + i,rangeArray.get(i));
            } else if (updownArray.get(i).equals("v") && updownArray.get(i + 1).equals("p")) {
            	pivotMap.put(i,rangeArray.get(i));
            }
        }

        return pivotMap;
    }

    public static List<Integer> getDepth(File file, List<String> line, int depth, List<List<Double>> array) {
        List<Integer> depthArray = new ArrayList<Integer>();;
        int size = array.size();
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
        
        
        

        System.out.println(depthArray);

        return depthArray;
    }
    
    public static List<List<Double>> getPeakSlope (File file, List<String> line) {
    	List<List<Double>> peakSlopeArray = new ArrayList<List<Double>>();
    	 Map<Integer, List<Double>> peakMap = findPivots(file,line);
    	 
    	 for (int i = 0; i < size*2; i++) {
    		 if (peakMap.containsKey(i) && i <= size) {
    			 peakSlopeArray.add(peakMap.get(i));
    		 }
    	 }
    	
    	return peakSlopeArray;
    }
    
    public static List<Double> getValleySlope (File file, List<String> line) {
    	List<List<Double>> valleyArray = new ArrayList<List<Double>>();
    	List<Double> valleySlopeArray = new ArrayList<Double>();
    	 Map<Integer, List<Double>> peakMap = findPivots(file,line);
    	 
    	 for (int i = 0; i < size*2; i++) {
    		 if (peakMap.containsKey(i) && i >= size) {
    			 valleyArray.add(peakMap.get(i));
    		 }
    	 }
    	 
    	 
    	
    	return valleySlopeArray;
    }
    
    public static List<Double> makeAverage (File file, List<String> line, List<List<Double>> array) {
    	
    	List<Double> madeAverage = new ArrayList<Double>();
    	
    	for (int i = 0; i < array.length; i++) {
    		madeAverage.add((array.get(i).get(0) + array.get(i).get(0)) / 2);
    	}
    	
    	
    	return madeAverage;
    }

    
    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\FD\\Downloads\\PRPH.csv");
        List<String> line = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

    }
}

// 1, 6, 8, 7
// Date,Open,High,Low,Close,Adj Close,Volume
