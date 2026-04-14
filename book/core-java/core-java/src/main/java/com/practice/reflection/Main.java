package com.practice.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
public class Main {

    /**
     * Create int array of size 10
     */
    @Test
    public void intArray(){
        int[] intArray = (int[]) Array.newInstance(int.class, 10);
        log.info("intArray length={}", intArray.length);
    }

    @Test
    public void className() {
        Class<Employee> employeeClass = Employee.class;
        log.info("employeeClass={}", employeeClass.getSimpleName());

        Class<Person> personClass = Person.class;
        log.info("personClass={}", personClass.getSimpleName());
    }

    /**
     * If we know the class name at compile time
     */
    @Test
    public void classObject1(){
        Class<Student> studentClass = Student.class;
        log.info("studentClass={}", studentClass);
    }

    /**
     * If we know the full packaged class name at run time
     */
    @Test
    public void classObject2() throws Exception{
        Class<? extends Object> studentClass = Class.forName("com.reflection.Student");
        log.info("studentClass={}", studentClass);
    }

    /**
     * Get fully packaged class name from class object
     */
    @Test
    public void getName() throws ClassNotFoundException{
        Class<? extends Object> klass = Class.forName("com.reflection.Student");
        log.info("klass name={}", klass.getName());
    }

    /**
     * Get only class name from class object
     * @throws ClassNotFoundException
     */
    @Test
    public void getSimpleName() throws  ClassNotFoundException{
        Class<? extends Object> klass = Class.forName("com.reflection.Student");
        log.info("klass name={}", klass.getSimpleName());
    }

    /**
     * Get public fields using classObject.getFields()
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void getPublicFields() throws ClassNotFoundException{
        Class<? extends Object> klass = Class.forName("com.reflection.Student");
        Field[] fields = klass.getFields();

        Arrays.stream(fields).forEach(field -> {
            log.info("field name={}", field.getName());
        });
    }

    /**
     * Get public and private fields using classObject.getDeclaredFields()
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void getAllFields() throws ClassNotFoundException{
        Class<? extends Object> klass = Class.forName("com.reflection.Student");
        Field[] fields = klass.getDeclaredFields();

        Arrays.stream(fields).forEach(field -> {
            log.info("field name={}", field.getName());
        });
    }

    /**
     * get one private field
     *
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    @Test
    public void getOnePrivateField() throws ClassNotFoundException, NoSuchFieldException{
        Class<? extends Object> klass = Class.forName("com.reflection.Student");
        Field field = klass.getDeclaredField("courses");
        log.info("field name={}", field.getName());
    }

    /**
     * Create object if
     * 1. We know class name at compile time
     * 2. Class has zero argument constructor
     *
     * @throws Exception
     */
    @Test
    public void createObjectUsingClassNewInstance() throws Exception {
        Class<Student> studentClass = Student.class;
        Student student = studentClass.newInstance();
        log.info("studentClass={}, student={}", studentClass, student);
    }

}