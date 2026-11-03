package com.apache.beam;


import org.apache.beam.sdk.Pipeline;

/**
 * Builds a pipeline and prints it. The pipeline has no transforms and is not run.
 */
public class CreatePipeline {

    /**
     * Creates a pipeline and prints {@code Done} and the pipeline object.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();
        System.out.println("Done");
        System.out.println(pipeline);
    }

}
