package dom;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import adm.ADM;
import ui.UI;

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
		rotateRadian = 0;
	}
	public Projector(int x, int y, int direction, int assetIndex){
		drawable = true;
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.assetIndex = assetIndex;
		this.frame = 0;
		rotateRadian = 0;
	}
	
	public void updateByDirection(int directionX, int directionY, int assetIndex){
		drawable = true;
		long currTime = System.currentTimeMillis();
		//int nextDirection = setDirection(directionX, directionY);
		rotateRadian = Math.atan2(directionY, directionX);
		int nextX = x+directionX;
		int nextY = y+directionY;
		if(currTime-lastUpdateTime >= FRAME_UPDATE_TIME)
		{
			if(nextX!=lastX || nextY!=lastY)  // move
			{
				frame += 1;
				frame %= MAX_FRAME;
				lastUpdateTime = currTime;
				lastX = nextX;
				lastY = nextY;
			}
		}
		x = nextX;
		y = nextY;
		this.assetIndex = assetIndex;
	}
	public void updateByDirection(int directionX, int directionY){
		drawable = true;
		long currTime = System.currentTimeMillis();
		rotateRadian = Math.atan2(directionY, directionX);
		int nextX = x+directionX;
		int nextY = y+directionY;
		if(currTime-lastUpdateTime >= FRAME_UPDATE_TIME)
		{
			if(nextX!=lastX || nextY!=lastY)  // move
			{
				frame += 1;
				frame %= MAX_FRAME;
				lastUpdateTime = currTime;
				lastX = nextX;
				lastY = nextY;
			}
		}
		x = nextX;
		y = nextY;
	}
	public void updateByDirection(int nextX, int nextY, int directionX, int directionY, int assetIndex){
		drawable = true;
		long currTime = System.currentTimeMillis();
		//int nextDirection = setDirection(directionX, directionY);
		rotateRadian = Math.atan2(directionY, directionX);
		if(currTime-lastUpdateTime >= FRAME_UPDATE_TIME)
		{
			if(nextX!=lastX || nextY!=lastY)  // move
			{
				frame += 1;
				frame %= MAX_FRAME;
				lastUpdateTime = currTime;
				lastX = nextX;
				lastY = nextY;
			}
		}
		x = nextX;
		y = nextY;
		this.assetIndex = assetIndex;
	}
	public void updateByDirection(int nextX, int nextY, int directionX, int directionY){
		drawable = true;
		long currTime = System.currentTimeMillis();
		rotateRadian = Math.atan2(directionY, directionX);
		if(currTime-lastUpdateTime >= FRAME_UPDATE_TIME)
		{
			if(nextX!=lastX || nextY!=lastY)  // move
			{
				frame += 1;
				frame %= MAX_FRAME;
				lastUpdateTime = currTime;
				lastX = nextX;
				lastY = nextY;
			}
		}
		x = nextX;
		y = nextY;
	}
	
	/*
	public void drawImage(Graphics g){
		if(!drawable)
			return;
		BufferedImage img = getImage();
		int playerX = DOM.getInstance().getPlayerX(), playerY = DOM.getInstance().getPlayerY();
		int canvasWidth = UI.getInstance().getCanvasWidth(), canvasHeight = UI.getInstance().getCanvasHeight();
		if( x+img.getWidth()/2+DynamicObject.DRAWING_EXTRA_RANGE < playerX - canvasWidth/2
		    || x-img.getWidth()/2-DynamicObject.DRAWING_EXTRA_RANGE > playerX + canvasWidth/2
		    || y+img.getHeight()/2+DynamicObject.DRAWING_EXTRA_RANGE < playerY - canvasHeight/2
		    || y+img.getHeight()/2-DynamicObject.DRAWING_EXTRA_RANGE > playerY + canvasHeight/2)
			return;
		
		Graphics2D g2d = (Graphics2D) g;
		
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotateRadian, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g2d.drawImage(op.filter(img, null), 
				      x - playerX - img.getWidth()/2 + canvasWidth/2 , 
				      y - playerY - img.getHeight()/2 + canvasHeight/2, null);
		//g2d.dispose();
	}
	*/
	
	public BufferedImage getImage(){
		return ADM.getInstance().getProjectorAsset(assetIndex, frame);
	}
}
