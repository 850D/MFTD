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
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
 
import engine.Entity;
import engine.EntityManager;
import engine.components.movement.TopDownMovement;
import engine.components.movement.RightLeftMovement;
import engine.components.render.ImageRenderComponent;
import engine.components.render.AnimatedImageRenderComponent;
import engine.components.render.BlockMapRenderComponent;
import engine.components.render.CollidableAnimatedSpriteRenderComponent;

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
	 * Game test entities
	 */
	private Entity duke = null;
    private Entity bg_map = null;
 
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
    	
    	
    	bg_map = new Entity("map");
 
        bg_map.addComponent (
        	new BlockMapRenderComponent(
        		"MapRender", 
        		"/data/map.tmx"
        	)
        );
 
        duke = new Entity("duke");
        duke.addComponent(
        	new CollidableAnimatedSpriteRenderComponent(
        		"PlayerRender", 
        		"/data/duke.png",
        		180
        	) 
        );
        
        duke.addComponent(new RightLeftMovement("PlayerMovement"));
        duke.setPosition(new Vector2f(100, 350));
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
    	bg_map.update(gc, null, delta);
    	duke.update(gc, null, delta);
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
    	bg_map.render(gc, null, gr);
    	duke.render(gc, null, gr);
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