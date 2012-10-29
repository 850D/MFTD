
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import engine.*;
import engine.components.*;
import engine.components.render.*;
import engine.components.movement.*;

@RunWith(Suite.class)
@SuiteClasses({ 
	ImageRenderComponentTest.class,
	TopDownMovementTest.class,
	RenderComponentTest.class,
	ComponentTest.class,
	EntityLoaderTest.class, 
	EntityManagerTest.class,
	EntityTest.class })
public class AllTests {
}
