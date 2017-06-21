package dom;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import adm.ADM;

public class Projector extends DynamicObject {
	
	public Projector(){ drawable = false; }
	public Projector(int x, int y, int direction, int assetIndex, int frame){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = frame;
	}
	
	public BufferedImage getImage(){
		return ADM.getInstance().getProjectorAsset(assetIndex, direction*DynamicObject.MAX_FRAME + frame);
	}
}
