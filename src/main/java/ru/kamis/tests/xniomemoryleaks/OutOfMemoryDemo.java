package ru.kamis.tests.xniomemoryleaks;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.helpers.Operations;
import org.jboss.dmr.ModelNode;

public class OutOfMemoryDemo {
	public static void main(String[] args) throws Exception {
		
		ModelNode operation = Operations.createReadAttributeOperation(new ModelNode().setEmptyList(), "server-state");
		ModelControllerClient client = ModelControllerClient.Factory.create("localhost", 9990);
		
		for(int i=0; i<1000000; i++) {
			client.execute(operation);
			
			if(i % 1000 == 0) {
				System.out.println("Processed: " + i);
			}
		}
		client.close();
	}
}
