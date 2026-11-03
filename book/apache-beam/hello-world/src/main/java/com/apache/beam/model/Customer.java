package com.apache.beam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * A customer with an id and a name.
 * <p>
 * Used as the element type of the in-memory {@code PCollection} in {@link com.apache.beam.InMemory}.
 * Lombok generates the constructor, builder, and accessors.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private String id;
    private String name;

}
