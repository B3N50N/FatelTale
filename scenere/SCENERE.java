package scenere;

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
		//assert something..
		int x = 200, y = 200;
		/*
		x = (int)DOM..;
		y = (int)DOM..;
		x -= UIM.getWidth;
		y -= UIM.getHeight;
		x /= MAP.SIZE;
		y /= MAP.SIZE;
		for (int dx=0;dx<=(UIM.getWidth/MAP.SIZE);dx++) {
			for (int dy=0;dy<=(UIM.getHeight/MAP.SIZE);dy++) {
				render..
			}
		}
		*/
	}
}
