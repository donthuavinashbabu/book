package com.avinashbabu.callbacks;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleFlywayCallback implements Callback {
    private static final Logger log = LoggerFactory.getLogger(ExampleFlywayCallback.class);

    @Override
    public boolean supports(Event event, Context context) {
        return event == Event.AFTER_EACH_MIGRATE;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return true;
    }

    @Override
    public void handle(Event event, Context context) {
        if(event == Event.AFTER_EACH_MIGRATE) {
            log.info("handle method");
        }
    }

    @Override
    public String getCallbackName() {
        return this.getClass().getName();
    }

}