package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySource;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
public class WrapperFEServiceTest {
	@Inject
	@Any
	InMemoryConnector connector;
	
	@Test
	public void testProcessOrder() {
		InMemorySource<String> ordersIn = connector.source("nomina-ex-request");
		ordersIn.send("hey");
	}
}