/**
 * MFTD 
 *
 * A simple java based tower defense game using the slick game framework.
 * 
 * Copyright (C) 2012 Florian Stötzel, Mark Arendt, Kai Burchardt, Dominik Augst
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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package engine.components.render;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import engine.components.RenderComponent;
import engine.components.render.BlockMapRenderComponent.Block;

/**
 * CollidableAnimatedSpriteRenderComponent extends
 * {@link engine.components.RenderComponent RenderComponent}
 * with a bounding box and the use of animations. 
 * <p>
 * It implements some sort of the fluent interface 
 * design pattern (in a very simple way without any grammar) like its parents.
 * </p>
 * @author Mark Arendt <mark@madesign.info>
 * @category engine.components.render
 * @version 0.2
 * @since 0.1
 */
public class CollidableAnimatedSpriteRenderComponent extends RenderComponent 
{
    /**
     * The entities bounding box, 
     * so we can do some calculations > collision
     */
	public Polygon boundingBox;
  	
    /**
     * Default Constructor
     * @throws SlickException
     */
    public CollidableAnimatedSpriteRenderComponent() throws SlickException {
        super();
              
        // Place the bounding box with the same 
        // dimensions at the same position like the entity.
		Vector2f pos = new Vector2f(0, 0);
		this.boundingBox = new Polygon(new float[] {
				pos.x, pos.y,
				pos.x + 32, pos.y,
				pos.x + 32, pos.y + 42,
				pos.x, pos.y + 42
		});
	}
 
    public boolean entityCollisionWith(ArrayList<Block> blockList) throws SlickException {
        for (int i = 0; i < blockList.size(); i++) {
            Block entity1 = (Block) blockList.get(i);
            if (this.boundingBox.intersects(entity1.poly)) {
                return true;
            }       
        }       
     
        return false;
    }       
    
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f pos = owner.getPosition();
		
		// Draw the additional bounding box
		gr.draw(this.boundingBox);
		
		// Omit redundant comments. Like this one. 
		// It's really not necessary, isn't it?
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