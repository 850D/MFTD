package engine.components.movement;

 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import engine.components.Component;
 
 
public class RightLeftMovement extends Component 
{
 
	float direction;
	float speed;
	float walk;
 
	public RightLeftMovement()
	{
		//this.id = id;
	}
 
	public float getSpeed()
	{
		return speed;
	}
 
	public float getDirection()
	{
		return direction;
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
 		Vector2f position = owner.getPosition();
 
		Input input = gc.getInput();
		
        if (input.isKeyDown(Input.KEY_A)) {
            owner.getRenderComponent().setCurrentAnimation("WALKING_LEFT");
        	position.x -= 0.1f * delta;
        }
 
        if (input.isKeyDown(Input.KEY_D)) {
            owner.getRenderComponent().setCurrentAnimation("WALKING_LEFT");
        	position.x += 0.1f * delta;
        }
 
        if (input.isKeyDown(Input.KEY_W)) {
            owner.getRenderComponent().setCurrentAnimation("JUMPING");
        	position.y -= 0.9f * delta;
        }

        if (input.isKeyDown(Input.KEY_S)) {
            
        }
        
		owner.setPosition(position);

	}
}