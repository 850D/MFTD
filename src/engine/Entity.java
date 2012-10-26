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
package engine;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import engine.components.Component;
import engine.components.RenderComponent;

/**
 * Entity Class
 * 
 * Everything in the game is an entity. 
 * This class resembles the core structure.
 * 
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine
 * @version 0.1
 * @since 2012-10-21
 */
public class Entity 
{
	String id;
	Vector2f position;
	float scale;
	float rotation;
	String anim;

	RenderComponent renderComponent = null;

	ArrayList<Component> components = null;

	public Entity(String id) {
		this.id = id;

		components = new ArrayList<Component>();

		position = new Vector2f(0, 0);
		scale = 1;
		rotation = 0;
		anim = "default";
	}

	/**
	 * Decorates the entity with a new component.
	 * The component must be an child of the 
	 * abstract component class. 
	 * 
	 * @param component An instace of component
	 */
	public void addComponent(Component component) {
		if (RenderComponent.class.isInstance(component))
			renderComponent = (RenderComponent) component;

		component.setOwnerEntity(this);
		components.add(component);
	}

	public Component getComponent(String id) {
		for (Component comp : components) {
			if (comp.getId().equalsIgnoreCase(id))
				return comp;
		}

		return null;
	}

	public Vector2f getPosition() {
		return position;
	}

	public float getScale() {
		return scale;
	}

	public float getRotation() {
		return rotation;
	}

	public String getId() {
		return id;
	}

	public String getAnimation() {
		return this.anim;
	}

	public void setAnimation(String name) {
		this.anim = name;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public void setRotation(float rotate) {
		rotation = rotate;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for (Component component : components) {
			component.update(gc, sb, delta);
		}
	}

	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		if (renderComponent != null)
			renderComponent.render(gc, sb, gr);
	}
}