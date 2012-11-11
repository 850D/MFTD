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
package engine.components;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * RenderComponent extends {@link engine.components.Component Component}
 * with the ability to define a set of animations stored in an HashMap and 
 * rendered when needed. It implements some sort of the fluent interface 
 * design pattern (in a very simple way without any grammar).
 * 
 * @author Mark Arendt <mark@madesign.info>
 * @category engine.components
 * @version 0.2
 * @since 2012-10-21
 */
public abstract class RenderComponent extends Component 
{
    /**
     * HashMap where we can store our animations.
     */
    private HashMap<String, Animation> animationMap;
    
    /**
     * Store the current animations' 
     * identifier right here. 
     */
    private String currentAnimation;
    
	/**
	 * Constructor initializing the 
	 * animation map with the default animation.
	 * 
	 * @param id string
	 */
	public RenderComponent() {
	    // Init the HashMap storing our animation Data.
	    // It's a common mistake (of myself) forgetting
	    // to initialize the maps. This always results in 
	    // errors very hard to find/debug.
        this.animationMap = new HashMap<String, Animation>(); 

        // The default animation shall always be called
        // ...yes, indeed: DEFAULT. Hear ye, hear ye!
        this.setCurrentAnimation("DEFAULT");  
	}
	
	/**
	 * Returns an animation defined 
	 * by its string identifier.
	 * 
	 * @return Animation
	 */
	public Animation getAnimation(String id) {
	    return this.animationMap.get(id);
	}

	/**
	 * Gets the current animation and returns 
	 * the right value from the {@link engine.components.RenderComponent#animationMap animationMap}.
	 * 
	 * @return Animation
	 */
	public Animation getCurrentAnimation() {
        return this.animationMap.get(this.currentAnimation);
    }

    /**
     * Set the current animations' identifier.
     * 
     * @param id
     * @return this
     */
    public RenderComponent setCurrentAnimation(String id) {
        this.currentAnimation = id;
        return this;
    }
	    
    /**
     * Adds a new animation to the 
     * {@link engine.components.RenderComponent#animationMap animationMap}.
     * 
     * @param id
     * @param animation
     * @return this
     */
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