package org.acme;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class BeverageProcessor {

    @Incoming("nomina-ex-request")
    void process(String id) {
        System.out.println("Message received " + id);
    }

}