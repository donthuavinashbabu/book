package com.hait.dao;

import com.hait.model.PersonModel;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequestScoped
public class PersonDao {

    // @PersistenceContext defines the same reference to the persistence-unit tag in the persistence.xml
    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public PersonModel create(PersonModel personModel) {
        em.persist(personModel);
        return personModel;
    }

    public PersonModel findById(Long id) {
        return em.find(PersonModel.class, id);
    }

    public List<PersonModel> findAll() {
        Query query = em.createQuery("select p from PersonModel p");
        List<PersonModel> resultList = (List<PersonModel>) query.getResultList();
        return resultList;
    }
}