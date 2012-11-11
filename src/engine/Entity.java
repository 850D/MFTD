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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package engine;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import engine.components.Component;
import engine.components.RenderComponent;

/**
 * Everything in the game is an entity. 
 * This class resembles the core structure.
 * 
 * @author Mark Arendt <mark@madesign.info>
 * @category engine
 * @version 0.1
 * @since 2012-10-21
 */
public class Entity 
{	
	private float scale;
	private float rotation;

	private String id;
	private Vector2f position = null;

	private RenderComponent renderComponent = null;
	private ArrayList<Component> components = null;

	/**
	 * Default Constructor initializing an 
	 * unscaled, non rotated entity at 0,0

	 * @param id The Entity Identifier
	 */
	public Entity(String id) {
		this.id = id;

		components = new ArrayList<Component>();

		position = new Vector2f(0, 0);
		scale = 1;
		rotation = 0;
	}

	/*
	 * Getter
	 */
	public Vector2f getPosition() {
		return this.position;
	}

	public float getScale() {
		return this.scale;
	}

	public float getRotation() {
		return this.rotation;
	}

	public String getId() {
		return this.id;
	}

    public RenderComponent getRenderComponent() {
        return this.renderComponent;
    }
	
	/*
	 * Setter
	 */
	public Entity setPosition(Vector2f position) {
		this.position = position;
		return this;
	}

	public Entity setRotation(float rotate) {
	    this.rotation = rotate;
		return this;
	}

	public Entity setScale(float scale) {
		this.scale = scale;
		
		return this;
	}

    public Entity setId(String id) {
        this.id = id;
        return this;
    }
	
	/**
	 * Decorates the entity with a new component.
	 * The component must be a child of the 
	 * abstract {@link engine.components.Component Component} class. 
	 * 
	 * @param component A {@link engine.components.Component Component} instance
	 */
	public Entity addComponent(Component component) {
        // renderComponent to declare? 
        if (RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent) component;

        // Sets the component's owner
        component.setOwnerEntity(this);
        
        components.add(component);
	    return this;
	}

	/**
	 * Returns a specific {@link engine.components.Component Component}
	 * identified by its id.
	 * 
	 * @param  id
	 * @return {@link engine.components.Component Component}
	 */	
	public Component getComponent(String id) {
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				return comp;
		}

		return null;
	}
	
	public Entity update(GameContainer gc, StateBasedGame sb, int delta) {
	    for (Component component : components) {
			component.update(gc, sb, delta);
		}
		
		return this;
	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        try {
    	    if (renderComponent != null)
    			renderComponent.render(gc, sb, gr);
        } catch (Exception e) {
            System.out.println(e);            
        }
	}
}