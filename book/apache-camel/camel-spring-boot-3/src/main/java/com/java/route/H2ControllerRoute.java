package com.java.route;

import com.java.restapi.H2ControllerClient;
import com.java.restapi.model.EmployeeEntity;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class H2ControllerRoute extends RouteBuilder {

    @Autowired
    private H2ControllerClient h2ControllerClient;

    @Value("${app.rest-api.h2.route:false}")
    private Boolean h2Route;

    @Override
    public void configure() throws Exception {
        if (h2Route) {
            from("timer:h2ControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("h2ControllerRoute")
                .log("h2ControllerRoute started")
                .process(exchange -> exchange.getMessage().setBody(employee()))
                .bean(h2ControllerClient, "saveEmployee")
                .log("saveEmployee=${body}")
                .process(exchange -> exchange.getMessage().setBody(List.of(employee())))
                .bean(h2ControllerClient, "saveEmployeeEntityList")
                .log("saveEmployeeEntityList=${body}")
                .bean(h2ControllerClient, "findAllEmployeeEntities()")
                .log("findAllEmployeeEntities=${body}")
                .log("h2ControllerRoute completed")
            .end();
        }
    }

    private EmployeeEntity employee() {
        return EmployeeEntity.builder().name("Ann").joiningDate("24112026").build();
    }
}
