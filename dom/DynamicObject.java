package dom;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import adm.ADM;


public class DynamicObject {
	static public class DIRECTION{
		int DOWN=0, LEFT=1, RIGHT=2, UP=3;
	}
	
	static public final int MAX_FRAME = 4;
	
	static public final int DRAWING_EXTRA_RANGE = 50;

	protected boolean drawable;
	
	protected int x,y; //position
	protected int direction; //face direction
	protected int assetIndex;
	protected int frame;
	
	public DynamicObject(){
		
	}
	public DynamicObject(int x, int y, int direction, int assetIndex, int frame){
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = frame;
	}
	
	public BufferedImage getImage() {
		BufferedImage img = new BufferedImage(0,0,0);
		return img;
	}
	
	public boolean getDrawable(){
		return drawable;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public void updateXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void updateDirection(int dir){
		direction = dir;
	}
	public void updateAssetIndex(int assetIndex){
		this.assetIndex = assetIndex;
	}
	public void updateFrame(int frame){
		this.frame = frame;
	}
}
