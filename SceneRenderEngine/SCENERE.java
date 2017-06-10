package SceneRenderEngine;

import java.awt.Graphics;

public class SCENERE {

	private static SCENERE uniqueInstance;
	
	private SCENERE() {
		
	}
	
	public static synchronized SCENERE getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new SCENERE();
		}
		return uniqueInstance;
	}
	
	public void render(Graphics g) {
		
	}
}
