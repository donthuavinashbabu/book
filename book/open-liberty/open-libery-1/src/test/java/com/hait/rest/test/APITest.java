package com.hait.rest.test;

import com.hait.model.PersonModel;
import com.hait.test.RestClient;
import org.junit.Test;

import javax.json.bind.JsonbBuilder;

import static org.junit.Assert.assertEquals;

public class APITest {

    @Test
    public void whenConsumeWithJsonb_thenGetPerson() {
        String url = "http://localhost:9080/api/persons/1";
        String result = RestClient.consumeWithJsonb(url);

        PersonModel person = JsonbBuilder.create().fromJson(result, PersonModel.class);
        assertEquals("1", person.getId().toString());
        assertEquals("a", person.getName());
        assertEquals("a@mail.com", person.getMail());
    }
}
