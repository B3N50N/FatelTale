package dom;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import adm.ADM;


public class DynamicObject {
	static public class DIRECTION{
		static public final int DOWN=0, LEFT=1, RIGHT=2, UP=3;
	}
	
	static public final int MAX_FRAME = 4;
	static public final int FRAME_UPDATE_TIME = 300;
	static public final int DRAWING_EXTRA_RANGE = 50;

	protected boolean drawable;
	
	protected int x,y; //position
	protected int direction; //face direction
	protected int assetIndex;
	protected int frame;
	protected long lastUpdateTime;
	
	public DynamicObject(){
		
	}
	public DynamicObject(int x, int y, int direction, int assetIndex, int frame){
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = frame;
		lastUpdateTime = System.currentTimeMillis();
	}
	
	public BufferedImage getImage() {
		BufferedImage img = new BufferedImage(0,0,0);
		return img;
	}
	
	public boolean getDrawable(){
		return drawable;
	}

	public void update(int nextX, int nextY, int nextDirection, int assetIndex){
		drawable = true;
		long currTime = System.currentTimeMillis();
		if(direction != nextDirection)  // turn
		{
			direction = nextDirection;
			frame = 0;
			lastUpdateTime = currTime;
		}
		else if(nextX!=x || nextY!=y) // move
		{
			if(currTime-lastUpdateTime >= FRAME_UPDATE_TIME)
			{
				frame += 1;
				frame %= MAX_FRAME;
				lastUpdateTime = currTime;
			}
		}
		else // stay
		{
			frame = 0;
			lastUpdateTime = currTime;
		}
		x = nextX;
		y = nextY;
		this.assetIndex = assetIndex;
	}
	public void update(int nextX, int nextY, int nextDirection){
		long currTime = System.currentTimeMillis();
		if(direction != nextDirection)  // turn
		{
			direction = nextDirection;
			frame = 0;
			lastUpdateTime = currTime;
		}
		else if(nextX!=x || nextY!=y) // move
		{
			if(currTime-lastUpdateTime >= FRAME_UPDATE_TIME)
			{
				frame += 1;
				frame %= MAX_FRAME;
				lastUpdateTime = currTime;
			}
		}
		else // stay
		{
			frame = 0;
			lastUpdateTime = currTime;
		}
		x = nextX;
		y = nextY;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public void updateXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void updateDirection(int direction){
		this.direction = direction;
	}
	public void updateAssetIndex(int assetIndex){
		this.assetIndex = assetIndex;
	}
	
	public void updateFrame(int frame){
		this.frame = frame;
	}
}
