package com.java.route;

import com.java.restapi.DeleteControllerClient;
import com.java.restapi.model.Student;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeleteControllerRoute extends RouteBuilder {

    @Autowired
    private DeleteControllerClient deleteControllerClient;

    @Value("${app.rest-api.delete.route:false}")
    private Boolean deleteRoute;

    @Override
    public void configure() throws Exception {
        if (deleteRoute) {
            from("timer:deleteControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("deleteControllerRoute")
                .log("deleteControllerRoute started")
                .setBody(constant("{\"name\":\"Ann\",\"book\":\"Java\"}"))
                .bean(deleteControllerClient, "studentV1")
                .log("studentV1=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(deleteControllerClient, "studentV2")
                .log("studentV2=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(deleteControllerClient, "studentV3(${body}, 'Ann', 'Java')")
                .log("studentV3=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(deleteControllerClient, "studentV4(${body}, 'Ann', 'Java')")
                .log("studentV4=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(deleteControllerClient, "studentV5(${body}, 'Ann', 'Java')")
                .log("studentV5=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(deleteControllerClient, "studentV6(${body}, '10', 'Ann', 'Java')")
                .log("studentV6=${body}")
                .log("deleteControllerRoute completed")
            .end();
        }
    }

    private Student student() {
        return Student.builder().name("Ann").book("Java").build();
    }
}
