package com.java.route;

import com.java.restapi.I18NControllerClient;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class I18NControllerRoute extends RouteBuilder {

    @Autowired
    private I18NControllerClient i18NControllerClient;

    @Value("${app.rest-api.i18n.route:false}")
    private Boolean i18nRoute;

    @Override
    public void configure() throws Exception {
        if (i18nRoute) {
            from("timer:i18nControllerRoute?period={{app.rest-api.timer.period}}")
                .routeId("i18nControllerRoute")
                .log("i18nControllerRoute started")
                .bean(i18NControllerClient, "helloWorld()")
                .log("helloWorld=${body}")
                .bean(i18NControllerClient, "helloWorld('fr')")
                .log("helloWorldFr=${body}")
                .bean(i18NControllerClient, "helloWorldWithName('Ann')")
                .log("helloWorldWithName=${body}")
                .bean(i18NControllerClient, "helloWorldWithName('Ann','fr')")
                .log("helloWorldWithNameFr=${body}")
                .log("i18nControllerRoute completed")
            .end();
        }
    }
}
