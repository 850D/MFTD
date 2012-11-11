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

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import engine.Entity;

/**
 * RenderComponentTest
 *
 * @author bsod <email>
 * @category engine.components
 * @version version
 * @since 11.11.2012
 *
 */
public class RenderComponentTest
{

    /**
     * Test method for {@link engine.components.RenderComponent#RenderComponent()}.
     */
    @Test
    public void testRenderComponent() {
        HashMap<String, Entity> map = new HashMap<String, Entity>();
                
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertFalse(map.containsKey("a"));
        assertNotNull(map.toString());
    }

    /**
     * Test method for {@link engine.components.RenderComponent#getAnimation(java.lang.String)}.
     */
    @Test
    public void testGetAnimation() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link engine.components.RenderComponent#getCurrentAnimation()}.
     */
    @Test
    public void testGetCurrentAnimation() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link engine.components.RenderComponent#setCurrentAnimation(java.lang.String)}.
     */
    @Test
    public void testSetCurrentAnimation() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link engine.components.RenderComponent#addAnimation(java.lang.String, org.newdawn.slick.Animation)}.
     */
    @Test
    public void testAddAnimation() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link engine.components.RenderComponent#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)}.
     */
    @Test
    public void testRender() {
        fail("Not yet implemented");
    }

}
