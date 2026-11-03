package com.apache.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.PCollection;

import static org.apache.beam.sdk.io.TextIO.*;

/**
 * Copies an input file to an output file using paths supplied on the command line.
 */
public class PipeLineOptionsExample {

    /**
     * Reads {@code --inputFile} and writes it to {@code --outputFile} with the {@code --extension} suffix.
     *
     * @param args pipeline options, including {@code --inputFile}, {@code --outputFile}, and {@code --extension}
     */
    public static void main(String[] args) {
        MyPipelineOptions myPipelineOptions = PipelineOptionsFactory.fromArgs(args).withValidation().as(MyPipelineOptions.class);

        Pipeline pipeline = Pipeline.create(myPipelineOptions);

        PCollection<String> pCollection1 = pipeline.apply(read().from(myPipelineOptions.getInputFile()));
        pCollection1.apply(write().to(myPipelineOptions.getOutputFile()).withNumShards(1).withSuffix(myPipelineOptions.getExtension()));

        pipeline.run();
    }
}
