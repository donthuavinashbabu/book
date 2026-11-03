package com.apache.beam.windowing;

import com.apache.beam.Constants;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollection;
import org.joda.time.Duration;

/**
 * Assigns each row of {@code input4.csv} to a fixed window of one second.
 * The windowed collection is not written anywhere.
 */
public class WindowingExample1 {

    /**
     * Reads {@code input4.csv} and assigns each element to a one-second fixed window.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        PCollection<String> pCollectionInput = pipeline.apply(TextIO.read().from(Constants.INPUT_OUTPUT_FILES_PATH + "input4.csv"));

        pCollectionInput.apply(Window.into(FixedWindows.of(Duration.millis(1000))));
        pipeline.run();
    }
}
