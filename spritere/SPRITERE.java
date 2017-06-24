package spritere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import dom.DOM;
import dom.DynamicObject;
import ui.UI;

public class SPRITERE {
	private static SPRITERE uniqueInstance;
	
	public SPRITERE() {
		
	}
	
	public static synchronized SPRITERE getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new SPRITERE();
		}
		return uniqueInstance;
	}
	
	public void render(Graphics g) {
		DOM dom = DOM.getInstance();
		Vector<DynamicObject> objects;
		
		objects = dom.getAllDynamicObjects();
		
		for(DynamicObject ob : objects)
		{
			if(!ob.getDrawable()) continue;
			BufferedImage img = ob.getImage();
			if( ob.getX()+img.getWidth()/2+DynamicObject.DRAWING_EXTRA_RANGE < 0 
			    || ob.getX()-img.getWidth()/2-DynamicObject.DRAWING_EXTRA_RANGE > UI.getinstance().getCanvasWidth()
			    || ob.getY()+img.getHeight()/2+DynamicObject.DRAWING_EXTRA_RANGE < 0
			    || ob.getY()+img.getHeight()/2-DynamicObject.DRAWING_EXTRA_RANGE > UI.getinstance().getCanvasWidth())
				continue;
			g.drawImage(img, ob.getX()-img.getWidth()/2, ob.getY()-img.getHeight()/2, null);
		}
	}
}
