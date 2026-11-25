package com.java.route;

import com.java.restapi.GetControllerClient;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetControllerRoute extends RouteBuilder {

    @Autowired
    private GetControllerClient getControllerClient;

    @Value("${app.rest-api.get.route:false}")
    private Boolean getRoute;

    @Override
    public void configure() throws Exception {
        if (getRoute) {
            from("timer:getControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("getControllerRoute")
                .log("getControllerRoute started")
                .bean(getControllerClient, "helloWorld()")
                .log("helloWorld=${body}")
                .bean(getControllerClient, "studentsV1()")
                .log("studentsV1=${body}")
                .bean(getControllerClient, "studentsV2()")
                .log("studentsV2=${body}")
                .bean(getControllerClient, "studentsV3()")
                .log("studentsV3=${body}")
                .bean(getControllerClient, "studentsV4('Ann','Java')")
                .log("studentsV4=${body}")
                .bean(getControllerClient, "studentsV5('Ann','Java')")
                .log("studentsV5=${body}")
                .bean(getControllerClient, "studentsV6('Ann','Java')")
                .log("studentsV6=${body}")
                .bean(getControllerClient, "studentsV7(0, 2, 'name')")
                .log("studentsV7=${body}")
                .bean(getControllerClient, "users1()")
                .log("users1=${body}")
                .log("getControllerRoute completed")
            .end();
        }
    }
}
