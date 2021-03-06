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
package engine;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import engine.Entity;

/**
 * Manages all loaded entities. There shall be only one instance of this class
 * at a time, so it implements the singleton (anti)pattern.
 * 
 * Furthermore it implements some sort of the fluent interface 
 * design pattern (in a very simple way without any grammar).
 *  
 * @author Mark Arendt <mark@madesign.info>
 * @category engine
 * @version 0.1
 * @since 2012-10-21
 */
public class EntityManager 
{
    String dir = null;
    GameContainer gc;

	/**
	 * List of entities
	 */
    private HashMap<String, Entity> entityList = null;
	
    /**
     * Singleton Instance
     */
    private static EntityManager _instance = new EntityManager();
    
    /**
     * Return the class instance.
     * 
     * @return EntityManager
     */
    public final static EntityManager getInstance(){
        return _instance;
    }    
    
	/**
	 * Default Constructor initializing the HashMap.
	 * Loads the resources in the specified directory.
	 * 
	 * Private -> Singleton
	 */
	private EntityManager() {
        EntityLoader entityLoader = new EntityLoader();
        
        String dir = "data/entities";
        this.entityList = new HashMap<String, Entity>();
        
        // Load all resources in the specified dir
        // @TODO Replace, Refactor 
        entityLoader.loadResourcesDir(dir, false);
        this.entityList = entityLoader.getEntities();
	}
	
	/**
	 * Adds a new entity to the HashMap.
	 * 
	 * @param entity
	 * @return
	 */
	public EntityManager addEntity (Entity entity) {
	    this.entityList.put (entity.getId(), entity);
	    
	    return this;
	}

    /**
     * Gets an entity through its id
     * 
     * @param id String
     * @return
     */
    public Entity getEntity (String id) {

        return this.entityList.get(id);
    }

    /**
     * Sets the resource directory
     * 
     * @param dir String
     * @return
     */
    public EntityManager setDataDir(String dir) {
        this.dir = dir;
        
        return this;
    }

    /**
     * Returns the resource directory.
     * 
     * @return String
     */
    public String getDataDir() {
        return this.dir;
    }

    /**
     * Sets the GameContainer.
     * 
     * @param gc GameContainer
     * @return EntityManager
     */
    public EntityManager setGameContainer(GameContainer gc) {
        this.gc = gc;
        
        return this;
    }
        
    /**
     * Updates every single entity.
     * 
     * @param gc 
     * @param sb 
     * @param delta
     * @return
     */
    public EntityManager update(GameContainer gc, StateBasedGame sb, int delta) {
        for (Entity entity : this.entityList.values()) 
            if (!entity.getId().isEmpty())
               entity.update(gc, sb, delta);
            
        
        return this;
    }

    /**
     * Renders every entity
     * 
     * @param gc GameContainer
     * @param sb
     * @param gr
     */
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {        
            for (Entity entity : this.entityList.values())
                if (!entity.getId().isEmpty())
                    entity.render(gc, sb, gr);        
    }
}