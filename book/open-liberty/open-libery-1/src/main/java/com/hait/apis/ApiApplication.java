package com.hait.apis;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// @ApplicationPath annotation for the URL path
@ApplicationPath("/api/v1")
public class ApiApplication extends Application {
}