package com.apache.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.values.PCollection;

/**
 * Prints every row of {@code customers.csv}.
 */
public class PrintElementsInPCollection {

    /**
     * Reads {@code customers.csv} and prints each element.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        PCollection<String> pCollectionCustomers = pipeline.apply(org.apache.beam.sdk.io.TextIO.read().from(com.apache.beam.Constants.INPUT_OUTPUT_FILES_PATH + "customers.csv"));

        pCollectionCustomers.apply(org.apache.beam.sdk.transforms.ParDo.of(new org.apache.beam.sdk.transforms.DoFn<String, Void>() {

            /**
             * Prints the current customer row.
             *
             * @param processContext element context for one row
             */
            @ProcessElement
            public void process(ProcessContext processContext){
                System.out.println(processContext.element());
            }

        }));

        pipeline.run();
    }
}
