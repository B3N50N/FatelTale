package dom;

import java.awt.image.BufferedImage;

import adm.ADM;

public class Projector extends DynamicObject {
	
	private double rotateRadian;
	
	public Projector(){ drawable = false; }
	public Projector(int x, int y, int direction, int assetIndex, int frame){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = frame;
	}
	public Projector(int x, int y, int direction, int assetIndex){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = 0;
	}
	
	public void updateByDirection(int directionX, int directionY, int assetIndex){
		drawable = true;
		long currTime = System.currentTimeMillis();
		//int nextDirection = setDirection(directionX, directionY);
		rotateRadian = Math.atan2(directionY, directionX);
		int nextX = x+directionX;
		int nextY = y+directionY;
		if(nextX!=x || nextY!=y) // move
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
	public void updateByDirection(int directionX, int directionY){
		drawable = true;
		long currTime = System.currentTimeMillis();
		//int nextDirection = setDirection(directionX, directionY);
		rotateRadian = Math.atan2(directionY, directionX);
		int nextX = x+directionX;
		int nextY = y+directionY;
		if(nextX!=x || nextY!=y) // move
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
	
	public BufferedImage getImage(){
		return ADM.getInstance().getProjectorAsset(assetIndex, direction*DynamicObject.MAX_FRAME + frame);
	}
}
