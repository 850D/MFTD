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
package engine.components.collision;

import Block;
import BlockMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import engine.components.Component;
 
/**
 * Square collision component class
 * 
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine.components.collision
 * @version 0.1
 * @since 2012-10-21
 */  
public class SquareCollision extends Component 
{
 
	public SquareCollision( String id ) {
		this.id = id;
	}
 
	public boolean entityCollisionWith() throws SlickException {
		for (int i = 0; i < BlockMap.entities.size(); i++) {
			Block entity1 = (Block) BlockMap.entities.get(i);
			if (playerPoly.intersects(entity1.poly)) {
				return true;
			}       
		}       
		return false;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
 	}
}