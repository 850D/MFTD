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
package engine.components.render;
 
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import engine.components.RenderComponent;

/**
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine.components.render
 * @version 0.1
 * @since 2012-10-25
 */
public class BlockMapRenderComponent extends RenderComponent {
	
	private TiledMap tiledMap;
	private int width;
	private int height;
	private int square[] = { 1, 1, 15, 1, 15, 15, 1, 15 }; // square shaped tile
	private ArrayList<Block> blockList;

	public BlockMapRenderComponent() throws SlickException {
		super();

		blockList = new ArrayList<Block>();
	}

    /**
     * Loads a tiled map (format .tmx) from a file.
     * 
     * @param path
     * @return this
     * @throws SlickException
     */
    public BlockMapRenderComponent loadMap(String path) throws SlickException {
        tiledMap = new TiledMap(path, "data");

        this.width = tiledMap.getWidth() * tiledMap.getTileWidth();
        this.height = tiledMap.getHeight() * tiledMap.getTileHeight();

        for (int x = 0; x < tiledMap.getWidth(); x++) {
            for (int y = 0; y < tiledMap.getHeight(); y++) {
                int tileID = tiledMap.getTileId(x, y, 0);
                if (tileID == 1) {
                    blockList.add(new Block(x * 16, y * 16, square, "square"));
                }
            }
        }

        return this;
	}
	
	public boolean entityCollisionWith(Polygon bBox) throws SlickException {
		for (int i = 0; i < this.blockList.size(); i++) {
			Block entity1 = (Block) this.blockList.get(i);
			if (bBox.intersects(entity1.poly)) {
				return true;
			}       
		}       
		return false;
	}	
 
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		this.tiledMap.render(0, 0);
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		//this.entityCollisionWith();
	}

	/**
	 * @author Mark Arendt <mark@madesign.info>
	 * @category mftd.engine.components.render
	 * @version 0.1
	 * @since 2012-10-25
	 */
	public class Block  
	{
		public Polygon poly;
		
		public Block(int x, int y, int test[],String type) {
			poly = new Polygon(new float[] { x + test[0], y + test[1],
					x + test[2], y + test[3], x + test[4], y + test[5],
					x + test[6], y + test[7], });
		}
	 
		public void draw(Graphics g) {
			g.draw(poly);
		}
	}	
	
}