package com.apache.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import lombok.extern.slf4j.Slf4j;

/**
 * Keeps rows of {@code input.csv} whose name starts with {@code a}.
 */
@Slf4j
class CustomerFilter extends DoFn<String, String> {

	private static final long serialVersionUID = 4729118751499987481L;

	/**
	 * Emits the row when the name column starts with {@code a}.
	 *
	 * @param processContext element context for one CSV row
	 */
	@ProcessElement
	public void processElement(ProcessContext processContext) {
		String line = processContext.element();
		log.info("line: {}", line);

		String[] lineArray = line.split(",");
		if (lineArray[1].startsWith("a")) {
			processContext.output(line);
		}
	}
}

/**
 * Keeps rows of {@code input.csv} whose name starts with {@code a}, using {@link ParDo}.
 */
public class ParDoExample {

	/**
	 * Filters {@code input.csv} with {@link CustomerFilter} and writes the kept rows.
	 *
	 * @param args unused command-line arguments
	 */
	public static void main(String[] args) {
		Pipeline pipeline = Pipeline.create();
		PCollection<String> pCollection1 = pipeline
				.apply(TextIO.read().from(Constants.INPUT_OUTPUT_FILES_PATH + "input.csv"));
		PCollection<String> pCollection2 = pCollection1.apply(ParDo.of(new CustomerFilter()));
		pCollection2.apply(TextIO.write().to(Constants.INPUT_OUTPUT_FILES_PATH + "output.csv")
				.withHeader("id,name").withNumShards(1).withSuffix(".csv"));

		pipeline.run();
	}
}
