package com.java.route;

import com.java.restapi.PostControllerClient;
import com.java.restapi.model.Employee;
import com.java.restapi.model.Student;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PostControllerRoute extends RouteBuilder {

    @Autowired
    private PostControllerClient postControllerClient;

    @Value("${app.rest-api.post.route:false}")
    private Boolean postRoute;

    @Override
    public void configure() throws Exception {
        if (postRoute) {
            from("timer:postControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("postControllerRoute")
                .log("postControllerRoute started")
                .setBody(constant("{\"name\":\"Ann\",\"book\":\"Java\"}"))
                .bean(postControllerClient, "studentV1")
                .log("studentV1=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(postControllerClient, "studentV2")
                .log("studentV2=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(postControllerClient, "studentV3(${body}, 'Ann', 'Java')")
                .log("studentV3=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(postControllerClient, "studentV4(${body}, 'Ann', 'Java')")
                .log("studentV4=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(postControllerClient, "studentV5(${body}, 'Ann', 'Java')")
                .log("studentV5=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(postControllerClient, "studentV6(${body}, '10', 'Ann', 'Java')")
                .log("studentV6=${body}")
                .process(exchange -> exchange.getMessage().setBody(new Employee(1L, "Ann", "dev")))
                .bean(postControllerClient, "employee")
                .log("employee=${body}")
                .log("postControllerRoute completed")
            .end();
        }
    }

    private Student student() {
        return Student.builder().name("Ann").book("Java").build();
    }
}
