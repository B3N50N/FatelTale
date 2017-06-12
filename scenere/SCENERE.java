package scenere;

import java.awt.Graphics;

import adm.ADM;
import sdm.SDM;
import ui.UI;

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
		g.clearRect(0, 0, UI.getinstance().getCanvasWidth(), UI.getinstance().getCanvasHeight());
		//assert something..
		int x = 200, y = 200;	
		int ix = x - ( UI.getinstance().getCanvasWidth() / 2 ), iy = y - ( UI.getinstance().getCanvasHeight() / 2 );
		ix /= ADM.getInstance().getMapWidth();
		iy /= ADM.getInstance().getMapHeight();
		
		int nx = UI.getinstance().getCanvasWidth() / ADM.getInstance().getMapWidth(), 
			ny = UI.getinstance().getCanvasHeight() / ADM.getInstance().getMapHeight();
		
		for (int dy=0;dy<=ny;dy++) {
			for (int dx=0;dx<=nx;dx++) {
				int nextX = ix + dx, nextY = iy + dy;
				if ( SDM.getInstance().isLegal(nextX, nextY) ) {
					g.drawImage(ADM.getInstance().getMapAsset(SDM.getInstance().getAssetIndex(nextX, nextY)), 
							    nextX * ADM.getInstance().getMapWidth() - (int)x + UI.getinstance().getCanvasWidth() / 2 ,
							    nextY * ADM.getInstance().getMapHeight() - (int)y + UI.getinstance().getCanvasHeight() / 2, null);
				}
			}
		}
	}
	
	public void testRender(Graphics g) {
		g.drawImage(ADM.getInstance().getMapAsset(0), 0, 0, null);
	}
}
