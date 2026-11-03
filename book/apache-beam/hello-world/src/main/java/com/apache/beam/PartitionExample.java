package com.apache.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Partition;
import org.apache.beam.sdk.transforms.Partition.PartitionFn;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;

/**
 * Assigns a row to a partition from the first letter of the name.
 * Names starting with {@code j} go to partition 0, names starting with {@code a}
 * go to partition 1, and every other name goes to partition 2.
 */
class MyPartition implements PartitionFn<String>{

    /**
     * Returns the partition index for one CSV row.
     *
     * @param elem one CSV row, {@code id,name}
     * @param numPartitions number of partitions, expected to be 3
     * @return {@code 0} for names starting with {@code j}, {@code 1} for names starting with {@code a}, otherwise {@code 2}
     */
    @Override
    public int partitionFor(String elem, int numPartitions) {
        String[] array = elem.split(",");

        if(array[1].startsWith("j")){
            return 0;
        }else if(array[1].startsWith("a")){
            return 1;
        }else{
            return 2;
        }
    }

}

/**
 * Splits {@code input.csv} into three partitions by the first letter of the name.
 * The pipeline is built and not run.
 */
public class PartitionExample {

    /**
     * Builds the partitioned collections and does not run the pipeline.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();
        PCollection<String> pCollection1 = pipeline.apply(TextIO.read().from("C:\\practice-projects\\apache-beam\\hello-world\\files\\input.csv"));
        PCollectionList<String> pCollectionList = pCollection1.apply(Partition.of(3, new MyPartition()));

        PCollection<String> pCollection2 = pCollectionList.get(0);
        PCollection<String> pCollection3 = pCollectionList.get(1);
        PCollection<String> pCollection4 = pCollectionList.get(2);
    }
}
