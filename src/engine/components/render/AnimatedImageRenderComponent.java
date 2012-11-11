package engine.components.render;
 
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Animation;

import engine.components.RenderComponent;
 
public class AnimatedImageRenderComponent extends RenderComponent 
{
 	private SpriteSheet sprite;
 	private HashMap<String, Animation> animationMap;
 
	public AnimatedImageRenderComponent(
			String id, 
			String imageLocation, 
		//	HashMap<String, Animation> aMap,
		//	int height,
		//	int width,			
			int duration
		//	Color color
	) throws SlickException {
		super(id);
		Color color = new Color(22, 45, 9);
		
		this.sprite = new SpriteSheet(imageLocation, 32, 42, color);
		this.animationMap = new HashMap<String, Animation>();
		this.animationMap.put("default", new Animation(this.sprite, 0, 0, 0, 0, true, duration, false));
		this.animationMap.put("walk", new Animation(this.sprite, 0, 0, 4, 0, true, duration, false));
		this.animationMap.put("jump", new Animation(this.sprite, 5, 0, 5, 0, true, 1, false));
		this.animationMap.put("longJump", new Animation(this.sprite, 0, 1, 5, 1, true, duration, false));
	}
 
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		this.animationMap.get(owner.getAnimation()).draw(pos.x, pos.y);

		//if (owner.getAnimation() == "jump")
		//	this.animationMap.get(owner.getAnimation()).stop();
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		this.animationMap.get(owner.getAnimation()).update(delta);
	}
}