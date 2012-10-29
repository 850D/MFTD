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
 
	public RightLeftMovement( String id )
	{
		this.id = id;
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
		
		if (owner.getAnimation() == "jump")
        	position.y += 0.9f * delta;
			
		owner.setAnimation("default");
		
        if (input.isKeyDown(Input.KEY_A)) {
    		owner.setAnimation("walk");
        	position.x -= 0.1f * delta;
        }
 
        if (input.isKeyDown(Input.KEY_D)) {
    		owner.setAnimation("walk");
        	position.x += 0.1f * delta;
        }
 
        if (input.isKeyDown(Input.KEY_W)) {
        	owner.setAnimation("jump");
        	position.y -= 0.9f * delta;
        }

        if (input.isKeyDown(Input.KEY_S)) {
            
        }
        
		owner.setPosition(position);

	}
}