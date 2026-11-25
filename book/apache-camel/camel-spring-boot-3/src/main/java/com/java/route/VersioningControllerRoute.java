package com.java.route;

import com.java.restapi.VersioningControllerClient;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersioningControllerRoute extends RouteBuilder {

    @Autowired
    private VersioningControllerClient versioningControllerClient;

    @Value("${app.rest-api.versioning.route:false}")
    private Boolean versioningRoute;

    @Override
    public void configure() throws Exception {
        if (versioningRoute) {
            from("timer:versioningControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("versioningControllerRoute")
                .log("versioningControllerRoute started")
                .bean(versioningControllerClient, "urlVersioning1()")
                .log("urlVersioning1=${body}")
                .bean(versioningControllerClient, "urlVersioning2()")
                .log("urlVersioning2=${body}")
                .bean(versioningControllerClient, "requestParamVersioning1()")
                .log("requestParamVersioning1=${body}")
                .bean(versioningControllerClient, "requestParamVersioning2()")
                .log("requestParamVersioning2=${body}")
                .bean(versioningControllerClient, "headerVersioning1()")
                .log("headerVersioning1=${body}")
                .bean(versioningControllerClient, "headerVersioning2()")
                .log("headerVersioning2=${body}")
                .bean(versioningControllerClient, "mediaTypeVersioning1()")
                .log("mediaTypeVersioning1=${body}")
                .bean(versioningControllerClient, "mediaTypeVersioning2()")
                .log("mediaTypeVersioning2=${body}")
                .log("versioningControllerRoute completed")
            .end();
        }
    }
}
