/**
 * MFTD 
 * 
 * Mark and Florian's Tower Defense (or Motherfucking Tower Defense)
 * A simple java based tower defense game using the slick game framework.
 * 
 * Copyright (C) 2012 Florian Stötzel, Mark Arendt
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package engine.components.render;
 
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Animation;

import engine.components.RenderComponent;

/**
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine
 * @version 0.1
 * @since 2012-10-21
 */
public class CollidableAnimatedSpriteRenderComponent extends RenderComponent 
{
	private Polygon boundingBox;
  	
    public CollidableAnimatedSpriteRenderComponent() throws SlickException {
        super();
        this.setCurrentAnimation("DEFAULT");        
   
		Vector2f pos = new Vector2f(100, 350);
		this.boundingBox = new Polygon(new float[] {
				pos.x, pos.y,
				pos.x + 32, pos.y,
				pos.x + 32, pos.y + 42,
				pos.x, pos.y + 42
		});
	}
 
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		
		gr.draw(this.boundingBox);
		this.getCurrentAnimation().draw(pos.x, pos.y);
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		
	    Vector2f pos = owner.getPosition();
		this.boundingBox.setX(pos.x);
		this.boundingBox.setY(pos.y);
		
		this.getCurrentAnimation().update(delta);

	}
}