package dijkstras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Thorup {

    public static void RunThorup(Graph graph, int start){
        //Graph msb = new Graph();
        // copy edges over with new MSB weights
        ArrayList<Integer> msb_weights = new ArrayList<>();
        for(Edge e : graph.getEdges()){
            msb_weights.add(calculateMSB(e.getCost()));
            //System.out.println(calculateMSB(e.getCost()));
        }
        msb_weights.toArray(new Integer[msb_weights.size()]);
        msb_weights = new ArrayList<>(Arrays.asList(sort(msb_weights.toArray(new Integer[msb_weights.size()]), 5)));
        for(int e : msb_weights){
            System.out.println(e);
        }
    }

    private static int calculateMSB(int weight){
        return (int)(Math.log((double)weight) / Math.log(2));
    }

    private static ArrayList<Integer> BucketSort(ArrayList<Integer> elements){
        int max = Collections.max(elements);
        int min = Collections.min(elements);
        int bucketsize = 10;

        int bucketcount = (max - min) / bucketsize + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketcount);
        for(int i = 0; i < bucketcount; i++){
            buckets.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < elements.size(); i++){
            buckets.get((elements.get(i) - min) / buckets.size()).add(elements.get(i));
        }
        int index = 0;
        for(int i = 0; i < buckets.size(); i++){
            Integer[] insidebucket = new Integer[buckets.get(i).size()];
            insidebucket = buckets.get(i).toArray(insidebucket);
            insidebucket = InsertionSort(insidebucket);
            for(int j = 0; j < insidebucket.length; j++){
                elements.set(index, insidebucket[j]);
            }
        }
        return elements;
    }

    public static Integer[] sort(Integer[] array, int bucketSize) {
        if (array.length == 0) {
            return null;
        }

        // Determine minimum and maximum values
        Integer minValue = array[0];
        Integer maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // Initialise buckets
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // Distribute input array values into buckets
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
        }

        // Sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            InsertionSort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                array[currentIndex++] = bucketArray[j];
            }
        }
        return array;
    }

    private static Integer[] InsertionSort(Integer[] array){
        int n = array.length;
        for(int i = 1; i < n; i++){
            int key = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > key){
                array[j+1] = array[j];
                j = j -1;
            }
            array[j+1] = key;
        }
        return array;
    }
}
