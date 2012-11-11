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
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import engine.EntityManager;

/**
 * Main Class
 * 
 * Initializes the engine and gamestate manager
 * and starts a new game.
 * 
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd
 * @version 0.1
 * @since 2012-10-21
 */  
public class MFTD extends BasicGame 
{
	/**
	 * A Game Container displaying the 
	 * contents in a single stand alone application.
	 */
	private static AppGameContainer app = null;
 
	/**
	 * Entity manager
	 */
	private EntityManager entityManager;
	
    /**
     * Default constructor.
     * Sets the title and several options. 
     */
    public MFTD() {
        super("MFTD");
    }
 
    /**
     * Initializes the game // Test purpose
     *
     * @param gc GameContainer Container for the game context
     * 
     * @throws SlickException
     * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
    	this.entityManager = new EntityManager();
    }
    
    /**
     * Main update function
     * 
     * @param gc GameContainer Container for the game context
     * @param delta int
     * 
     * @throws SlickException
     * @see org.newdawn.slick.BasicGame#update
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
    	this.entityManager.update(gc, null, delta);
    }

    /**
     * Main render function
     * 
     * @param gc GameContainer Container for the game context
     * @param gr Graphics
     * 
     * @throws SlickException
     * @see org.newdawn.slick.BasicGame#update
     */    
    public void render(GameContainer gc, Graphics gr) throws SlickException {
        this.entityManager.render(gc, null, gr);
    }
 
    /**
     * @param args String[]
     * @throws SlickException
     */
    public static void main(String[] args) throws SlickException {
        MFTD.app = new AppGameContainer(new MFTD());
 
        MFTD.app.setDisplayMode(640, 480, false);
        MFTD.app.start();
    }
}