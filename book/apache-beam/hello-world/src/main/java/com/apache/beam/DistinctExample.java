package com.apache.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Distinct;
import org.apache.beam.sdk.values.PCollection;

/**
 * Drops duplicate rows from {@code customers.csv}.
 */
public class DistinctExample {

    /**
     * Writes the distinct rows of {@code customers.csv}.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        PCollection<String> pCollectionCustomers = pipeline.apply(TextIO.read().from(Constants.INPUT_OUTPUT_FILES_PATH + "customers.csv"));
        PCollection<String> pCollectionUniqueCustomers = pCollectionCustomers.apply(Distinct.<String>create());
        pCollectionUniqueCustomers.apply(TextIO.write().to(Constants.INPUT_OUTPUT_FILES_PATH + "output.csv").withNumShards(1).withSuffix(".csv"));

        pipeline.run();
    }
}
