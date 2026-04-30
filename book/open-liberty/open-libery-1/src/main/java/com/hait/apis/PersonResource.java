package com.hait.apis;

import com.hait.dao.PersonDao;
import com.hait.model.PersonModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("persons")
public class PersonResource {

    @Inject
    private PersonDao personDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonModel> getPersons() {
        List<PersonModel> persons = new ArrayList<>();

        PersonModel person = new PersonModel();
        person.setName("a");
        person.setMail("a.mail.com");
        persons.add(person);

        PersonModel person2 = new PersonModel();
        person2.setName("b");
        person2.setMail("b.mail.com");
        persons.add(person2);

        return persons;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPerson(PersonModel person) {
        System.out.println(person.getName());
        System.out.println(person.getMail());
        PersonModel savedPersonModel = personDao.create(person);
        return Response.status(Response.Status.CREATED).entity("Saved #" + savedPersonModel.getId()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public PersonModel getPerson(@PathParam("id") Long id) {
        return personDao.findById(id);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonModel> getAllPersons() {
        return personDao.findAll();
    }

}