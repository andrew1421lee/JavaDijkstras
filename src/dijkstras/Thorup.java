package dijkstras;

import java.util.ArrayList;
import java.util.List;

public class Thorup {

    public static void RunThorup(Graph graph, int start){
        //Graph msb = new Graph();
        // copy edges over with new MSB weights
        Integer[] msb_weights = new Integer[graph.getEdges().length];
        int counter = 0;
        for(Edge e : graph.getEdges()){
            msb_weights[counter] = calculateMSB(e.getCost());
            counter++;
            //System.out.println(calculateMSB(e.getCost()));
        }
        //msb_weights.toArray(new Integer[msb_weights.size()]);
        msb_weights = BucketSort(msb_weights);
        //msb_weights = new ArrayList<>(Arrays.asList(sort(msb_weights.toArray(new Integer[msb_weights.size()]), 5)));
        for(int e : msb_weights){
            System.out.println(e);
        }
    }

    private static int calculateMSB(int weight){
        return (int)(Math.log((double)weight) / Math.log(2));
    }

    private static Integer[] BucketSort(Integer[] elements){
        int max = elements[0];
        int min = elements[0];
        for(Integer i : elements){
            if(i < min){
                min = i;
            }else if(i > max){
                max = i;
            }
        }
        int bucket_size = 3;

        int bucket_count = (max - min) / bucket_size + 1;

        List<List<Integer>> buckets = new ArrayList<>(bucket_count);
        for(int i = 0; i < bucket_count; i++){
            buckets.add(new ArrayList<>());
        }

        for(int i : elements){
            buckets.get((i - min) / bucket_size).add(i);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            Integer[] insidebucket = new Integer[bucket.size()];
            insidebucket = InsertionSort(bucket.toArray(insidebucket));
            for (Integer anInsidebucket : insidebucket) {
                elements[index] = anInsidebucket;
                index++;
            }
        }
        return elements;
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
