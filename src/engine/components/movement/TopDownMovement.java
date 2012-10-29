package engine.components.movement;

 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import engine.components.Component;
 
 
public class TopDownMovement extends Component 
{
 
	float direction;
	float speed;
 
	public TopDownMovement( String id )
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
 
		float rotation = owner.getRotation();
		float scale = owner.getScale();
		Vector2f position = owner.getPosition();
 
		Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_A))
        {
        	rotation += -0.2f * delta;
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
        	rotation += 0.2f * delta;
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
            float hip = 0.4f * delta;
 
            position.x += hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
            position.y -= hip *java.lang.Math.cos(java.lang.Math.toRadians(rotation));
        }
 
		owner.setPosition(position);
		owner.setRotation(rotation);
		owner.setScale(scale);
	}
 
}