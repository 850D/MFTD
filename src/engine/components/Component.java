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
 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
 
import engine.Entity;
 
/**
 * Components are structures added on top of {@link engine.Entity entities}
 * to supply additional function, e.g. movement, collision,
 * rendering, sound, etc. or abilities like carrying a sword, eating ...
 * 
 * It implements some sort of the fluent interface 
 * design pattern (in a very simple way without any grammar).
 * 
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine.components
 * @version 0.1
 * @since 2012-10-21
 */  
public abstract class Component 
{	
	/**
	 * Component identifier
	 */
    protected String id;
    
    /**
     * Parent Entity.
     */
    protected Entity owner;
    
    /**
     * Returns the components' identifier.
     * 
     * @return String
     */
    public String getId() {
        return this.id;
    }
 
    /**
     * Sets the owner of this particular component.
     * 
     * @param owner
     * @return this
     */
    public Component setOwnerEntity(Entity owner) {
    	this.owner = owner;
    	return this;
    }
   
    public abstract void update(
    	GameContainer game_container, 
    	StateBasedGame state_based_game, 
    	int delta
    );
}