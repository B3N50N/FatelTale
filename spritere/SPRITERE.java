package spritere;

import java.awt.Graphics;
import java.util.Vector;

import dom.DOM;
import dom.DynamicObject;

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
			ob.draw(g);
		}
	}
}
