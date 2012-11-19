package engine.components.movement;

 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import engine.Entity;
import engine.EntityManager;
import engine.components.Component;
import engine.components.render.BlockMapRenderComponent;
import engine.components.render.CollidableAnimatedSpriteRenderComponent;
 
 
/**
 * @author Mark Arendt <mark@madesign.info>
 * @category engine.components.movement
 * @version 0.1
 * @since 2012-10-21
 */
public class RightLeftMovement extends Component 
{
 
	float direction;
	float speed;
	float walk;
	boolean jump = false;
	float jumpStart;
	
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
		
		//if (this.jump)
        EntityManager em = EntityManager.getInstance();
        Entity en = em.getEntity("map");
        
        BlockMapRenderComponent rc = (BlockMapRenderComponent)en.getRenderComponent();
        
        CollidableAnimatedSpriteRenderComponent car = 
                (CollidableAnimatedSpriteRenderComponent)owner.getRenderComponent();
                
        if (this.jump) {
            float tmp = this.jumpStart - (2f * delta);
            
            if (position.y > tmp)
                position.y -= 0.4f * delta;
            else
                this.jump = false;
            
        } else {
            
            try {
                owner.getRenderComponent().setCurrentAnimation("DEFAULT");
    
                // Gravity
                if (!car.entityCollisionWith(rc.getBlockList()))
                    position.y += 0.3f * delta;
                
                if (input.isKeyDown(Input.KEY_A)) {
                    owner.getRenderComponent().setCurrentAnimation("WALKING_LEFT");
                	position.x -= 0.1f * delta;
                	
                	if (car.entityCollisionWith(rc.getBlockList()))
                        position.x += 0.3f * delta;
                }
         
                if (input.isKeyDown(Input.KEY_D)) {
                    owner.getRenderComponent().setCurrentAnimation("WALKING_LEFT");
                	position.x += 0.1f * delta;
                
                	if (car.entityCollisionWith(rc.getBlockList()))
                        position.x -= 0.3f * delta;
                }
         
                if (!this.jump && input.isKeyDown(Input.KEY_W)) {
                    owner.getRenderComponent().setCurrentAnimation("JUMPING");
                    
                    this.jumpStart = position.y;                    
                	this.jump = true;
                }
        
                if (input.isKeyDown(Input.KEY_S)) {
                    
                }
    
            } catch (SlickException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
		owner.setPosition(position);

	}
}