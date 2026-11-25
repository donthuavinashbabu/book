package com.java.route;

import com.java.restapi.HateoasControllerClient;
import com.java.restapi.model.Student;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HateoasControllerRoute extends RouteBuilder {

    @Autowired
    private HateoasControllerClient hateoasControllerClient;

    @Value("${app.rest-api.hateoas.route:false}")
    private Boolean hateoasRoute;

    @Override
    public void configure() throws Exception {
        if (hateoasRoute) {
            from("timer:hateoasControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("hateoasControllerRoute")
                .log("hateoasControllerRoute started")
                .bean(hateoasControllerClient, "findAllStudents()")
                .log("findAllStudents=${body}")
                .bean(hateoasControllerClient, "findStudentById('1')")
                .log("findStudentById=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(hateoasControllerClient, "saveStudent")
                .log("saveStudent=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(hateoasControllerClient, "saveStudents2")
                .log("saveStudents2=${body}")
                .log("hateoasControllerRoute completed")
            .end();
        }
    }

    private Student student() {
        return Student.builder().name("Ann").book("Java").build();
    }
}
