package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
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