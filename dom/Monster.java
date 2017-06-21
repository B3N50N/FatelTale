package dom;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import adm.ADM;

public class Monster extends DynamicObject{
	
	public Monster(){ drawable = false; }
	public Monster(int x, int y, int direction, int assetIndex, int frame){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = frame;
	}

	public BufferedImage getImage(){
		return ADM.getInstance().getMonsterAsset(assetIndex, direction*DynamicObject.MAX_FRAME + frame);
	}
}
