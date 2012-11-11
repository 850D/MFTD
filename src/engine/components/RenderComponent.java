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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package engine.components;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine
 * @version 0.1
 * @since 2012-10-21
 */
public abstract class RenderComponent extends Component 
{
    /**
     * HashMap where we can store our animations.
     */
    private HashMap<String, Animation> animationMap;
    private String currentAnimation = "DEFAULT";
    
	/**
	 * Constructor initializing 
	 * the component id
	 * 
	 * @param id string
	 */
	public RenderComponent() {
		//this.id = id;
		this.type = CompTypes.RENDER;
		this.animationMap = new HashMap<String, Animation>(); 
	}
	
	/**
	 * Getter / Setter
	 */
	public Animation getAnimation(String id) {
	    return this.animationMap.get(id);
	}

	public Animation getCurrentAnimation() {
        return this.animationMap.get(this.currentAnimation);
    }

    public RenderComponent setCurrentAnimation(String id) {
        this.currentAnimation = id;
        return this;
    }
	
    public RenderComponent addAnimation(String id, Animation animation) {
        this.animationMap.put(id, animation);
        return this;
    }
	
	/**
	 * Abstract render method
	 * 
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);
}