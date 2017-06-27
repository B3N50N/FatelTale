package spritere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;
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
		
		Collections.sort(objects, new Comparator<DynamicObject>() {
	        @Override
	    	public int compare(DynamicObject o1, DynamicObject o2) {
	            if(o1.getX()==o2.getX())
	            {
	            	if(o1.getY() > o2.getY())
	            		return -1;
	            	else if(o1.getY() == o2.getY())
	            		return 0;
	            	else
	            		return 1;
	            }
	            else
	            	return (o1.getX() > o2.getX()) ? -1 : 1;        
	        }
		});
		
		for(DynamicObject ob : objects)
		{
			ob.drawImage(g);
		}
	}
}
