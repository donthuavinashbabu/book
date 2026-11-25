package com.java.route;

import com.java.restapi.PutControllerClient;
import com.java.restapi.model.Student;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PutControllerRoute extends RouteBuilder {

    @Autowired
    private PutControllerClient putControllerClient;

    @Value("${app.rest-api.put.route:false}")
    private Boolean putRoute;

    @Override
    public void configure() throws Exception {
        if (putRoute) {
            from("timer:putControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("putControllerRoute")
                .log("putControllerRoute started")
                .setBody(constant("{\"name\":\"Ann\",\"book\":\"Java\"}"))
                .bean(putControllerClient, "studentV1")
                .log("studentV1=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(putControllerClient, "studentV2")
                .log("studentV2=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(putControllerClient, "studentV3(${body}, 'Ann', 'Java')")
                .log("studentV3=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(putControllerClient, "studentV4(${body}, 'Ann', 'Java')")
                .log("studentV4=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(putControllerClient, "studentV5(${body}, 'Ann', 'Java')")
                .log("studentV5=${body}")
                .process(exchange -> exchange.getMessage().setBody(student()))
                .bean(putControllerClient, "studentV6(${body}, '10', 'Ann', 'Java')")
                .log("studentV6=${body}")
                .log("putControllerRoute completed")
            .end();
        }
    }

    private Student student() {
        return Student.builder().name("Ann").book("Java").build();
    }
}
