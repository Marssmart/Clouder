package org.deer.clouder.module.events;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EventModule {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EventModule.class).application().run(args);
    }
}
