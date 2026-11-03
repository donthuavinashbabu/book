package com.apache.beam;

import org.apache.beam.sdk.options.PipelineOptions;

/**
 * Command-line options for {@link PipeLineOptionsExample}.
 */
public interface MyPipelineOptions extends PipelineOptions {

    /**
     * Sets the path of the file to read.
     *
     * @param inputFile input file path
     */
    void setInputFile(String inputFile);

    /**
     * Returns the path of the file to read.
     *
     * @return input file path
     */
    String getInputFile();

    /**
     * Sets the path prefix of the file to write.
     *
     * @param outputFile output file path
     */
    void setOutputFile(String outputFile);

    /**
     * Returns the path prefix of the file to write.
     *
     * @return output file path
     */
    String getOutputFile();

    /**
     * Sets the suffix appended to the output file name.
     *
     * @param extension output file suffix, such as {@code .csv}
     */
    void setExtension(String extension);

    /**
     * Returns the suffix appended to the output file name.
     *
     * @return output file suffix
     */
    String getExtension();

}
