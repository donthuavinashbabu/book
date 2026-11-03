package com.apache.beam;

import com.apache.beam.model.Customer;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.Create.Values;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.ArrayList;
import java.util.List;

import static org.apache.beam.sdk.io.TextIO.*;

/**
 * Builds an in-memory {@code PCollection} of {@link Customer} values and writes their names in upper case.
 */
public class InMemory {

    /**
     * Returns the three customers used as the in-memory source.
     *
     * @return customers named jack, jill, and john
     */
    private static List<Customer> createCustomerList(){
        Customer customer1 = Customer.builder().id("1").name("jack").build();
        Customer customer2 = Customer.builder().id("1").name("jill").build();
        Customer customer3 = Customer.builder().id("1").name("john").build();

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        return customerList;
    }

    /**
     * Maps each customer name to upper case and writes the names.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        Values<Customer> customerValues = Create.of(createCustomerList());
        PCollection<Customer> pCollection1 = pipeline.apply(customerValues);
        PCollection<String> pCollection2 = pCollection1.apply(MapElements.into(TypeDescriptors.strings()).via((Customer::getName)));
        PCollection<String> pCollection3 = pCollection2.apply(MapElements.into(TypeDescriptors.strings()).via(String::toUpperCase));
        pCollection3.apply(write().to("C:\\practice-projects\\apache-beam\\hello-world\\files\\output.csv").withNumShards(1).withSuffix(".csv"));

        pipeline.run();
    }
}
