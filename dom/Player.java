package dom;

import java.awt.image.BufferedImage;

import adm.ADM;

public class Player extends DynamicObject{

	public Player() { drawable = false; }
	public Player(int x, int y, int direction, int assetIndex, int frame){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = frame;
	}
	public Player(int x, int y, int direction, int assetIndex){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = 0;
	}
	
	public BufferedImage getImage(){
		return ADM.getInstance().getPlayerAsset(assetIndex, direction*DynamicObject.MAX_FRAME + frame);
	}
}