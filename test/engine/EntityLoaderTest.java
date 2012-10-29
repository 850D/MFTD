package engine;

import static org.junit.Assert.*;
import org.junit.Test;

public class EntityLoaderTest {

	@Test
	public void testMultiply() {
	    EntityLoader tester = new EntityLoader();
	    assertEquals("Result", 50, tester.multiply(10, 5));
	}
}
