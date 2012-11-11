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

import java.io.*;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import engine.components.Component;
import engine.components.render.BlockMapRenderComponent;

/**
 * @author Mark Arendt <mark@madesign.info>
 * @category mftd.engine
 * @version 0.1
 * @since 2012-11-09
 */
public class EntityLoader
{

    private HashMap<String, Entity> entities = null;

    public EntityLoader() {
        this.entities = new HashMap<String, Entity>();
    }
    
    public void loadResources(InputStream is) throws SlickException {
        loadResources(is, false);
    }

    public EntityLoader loadResourcesDir(String dir, boolean deferred) {
        String files;

        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        try {
            for (int i = 0; i < ((listOfFiles.length)); i++) {
                if (listOfFiles[i].isFile()) {
                
                    String fileName = listOfFiles[i].getCanonicalPath();
                    
                    if (fileName.endsWith(".xml")) {
        
                        files = listOfFiles[i].getName();
                        System.out.println(dir.concat("/" + files));

                        InputStream inputStream = new FileInputStream(
                            dir.concat("/" + files)
                        );
                            
                        this.loadResources(inputStream, false);
                    }
                }   
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    
        return this;
    }
    
    public HashMap<String, Entity> getEntities() {
        return this.entities;
    }

    public void loadResources(InputStream is, boolean deferred) throws SlickException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new SlickException("Could not load resources", e);
        }

        Document doc = null;
        try {
            doc = docBuilder.parse(is);
        } catch (SAXException e) {
            throw new SlickException("Could not load resources", e);
        } catch (IOException e) {
            throw new SlickException("Could not load resources", e);
        }

        // normalize text representation
        doc.getDocumentElement().normalize();

        NodeList listResources = doc.getElementsByTagName("resource");

        int totalResources = listResources.getLength();

        if (deferred) {
            LoadingList.setDeferredLoading(true);
        }

        // Initialize Entity Class
        Entity newEntity = new Entity("");

        for (int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++) {

            Node resourceNode = listResources.item(resourceIdx);

            if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element resourceElement = (Element) resourceNode;

                String type = resourceElement.getAttribute("type");

                if (type.equals("id")) {
                    newEntity.setId(resourceElement.getTextContent());

                } else if (type.equals("class")) {
                    //System.out.println(resourceElement.getTextContent());

                } else if (type.equals("component")) {
                    newEntity.addComponent(
                            (Component)createComponent(resourceElement)
                    );
                    
                /*  } else if (type.equals("image")) {
                    addElementAsImage(resourceElement);

                } else if (type.equals("sound")) {
                    addElementAsSound(resourceElement);

                } else if (type.equals("text")) {
                    addElementAsText(resourceElement);

                } else if (type.equals("font")) {

*/              } else if (type.equals("animation")) {
                    String id = resourceElement.getAttribute("id");
                    Animation anim = this.addElementAsAnimation(resourceElement);

                    newEntity.getRenderComponent().addAnimation(id, anim);

                } else if (type.equals("position")) {
                    int x = Integer.valueOf(resourceElement.getAttribute("x"));
                    int y = Integer.valueOf(resourceElement.getAttribute("y"));
                    
                    Vector2f pos = new Vector2f(x, y);
                    newEntity.setPosition(pos);
 
                } else if (type.equals("map")) {
                    System.out.println("map");
                    // @FIXME    
                    BlockMapRenderComponent map = (BlockMapRenderComponent)newEntity.getRenderComponent();
                    map.loadMap(resourceElement.getTextContent());
                }

            }
        }
        
        this.entities.put(newEntity.getId(), newEntity);        
    }

    private Object createComponent(Element resourceElement) throws SlickException {
        Object tmp = null;
        String id = resourceElement.getTextContent();
        
        try {
            tmp = Class.forName(id).newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return tmp;
    }

    private Animation addElementAsAnimation(Element resourceElement)
            throws SlickException {
        String[] tmp = resourceElement.getAttribute("color").split(",");
       
        Color color = new Color (
            Integer.parseInt(tmp[0]), 
            Integer.parseInt(tmp[1]), 
            Integer.parseInt(tmp[2])
        );
       
        return loadAnimation(resourceElement.getAttribute("id"),
            resourceElement.getTextContent(),
            Integer.valueOf(resourceElement.getAttribute("tw")),
            Integer.valueOf(resourceElement.getAttribute("th")),
            Integer.valueOf(resourceElement.getAttribute("duration")),
            color
        );
    }

    private Animation loadAnimation(
            String id, 
            String spriteSheetPath, 
            int tw,
            int th, 
            int duration, 
            Color color
    ) throws SlickException {
        if (spriteSheetPath == null || spriteSheetPath.length() == 0)
            throw new SlickException("Image resource [" + id
                    + "] has invalid path");

        SpriteSheet sprite = new SpriteSheet(spriteSheetPath, tw, th, color);
        return new Animation(sprite, duration);
    }

    private void addElementAsText(Element resourceElement) throws SlickException {
        loadText(resourceElement.getAttribute("id"),
                resourceElement.getTextContent());
    }

    public String loadText(String id, String value) throws SlickException {
        if (value == null)
            throw new SlickException("Text resource [" + id
                    + "] has invalid value");

        // textMap.put(id, value);

        return value;
    }

    private void addElementAsSound(Element resourceElement)
            throws SlickException {
        loadSound(resourceElement.getAttribute("id"),
                resourceElement.getTextContent());
    }

    public Sound loadSound(String id, String path) throws SlickException {
        if (path == null || path.length() == 0)
            throw new SlickException("Sound resource [" + id
                    + "] has invalid path");

        Sound sound = null;

        try {
            sound = new Sound(path);
        } catch (SlickException e) {
            throw new SlickException("Could not load sound", e);
        }

        // this.soundMap.put(id, sound);

        return sound;
    }

    private final void addElementAsImage(Element resourceElement)
            throws SlickException {
        System.out.println("Image");
        loadImage(resourceElement.getAttribute("id"),
                resourceElement.getTextContent());
    }

    public Image loadImage(String id, String path) throws SlickException {
        if (path == null || path.length() == 0)
            throw new SlickException("Image resource [" + id
                    + "] has invalid path");

        Image image = null;
        try {
            image = new Image(path);
        } catch (SlickException e) {
            throw new SlickException("Could not load image", e);
        }

        // this.imageMap.put(id, image);

        return image;
    }
}
