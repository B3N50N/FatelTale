package scenere;

import java.awt.Graphics;

import adm.ADM;
import dom.DOM;
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
		g.clearRect(0, 0, UI.getInstance().getCanvasWidth(), UI.getInstance().getCanvasHeight());
		//assert something..
		int x = DOM.getInstance().getPlayerX(0), y = DOM.getInstance().getPlayerY(0);	
		int ix = x - ( UI.getInstance().getCanvasWidth() / 2 ), iy = y - ( UI.getInstance().getCanvasHeight() / 2 );
		ix /= ADM.getInstance().getMapWidth();
		iy /= ADM.getInstance().getMapHeight();
		
		int nx = UI.getInstance().getCanvasWidth() / ADM.getInstance().getMapWidth(), 
			ny = UI.getInstance().getCanvasHeight() / ADM.getInstance().getMapHeight();
		
		for (int dy=0;dy<=ny;dy++) {
			for (int dx=0;dx<=nx;dx++) {
				int nextX = ix + dx, nextY = iy + dy;
				if ( SDM.getInstance().isLegal(nextX, nextY) ) {
					g.drawImage(ADM.getInstance().getMapAsset(SDM.getInstance().getAssetIndex(nextX, nextY)), 
							    nextX * ADM.getInstance().getMapWidth() - (int)x + UI.getInstance().getCanvasWidth() / 2 ,
							    nextY * ADM.getInstance().getMapHeight() - (int)y + UI.getInstance().getCanvasHeight() / 2, null);
				}
			}
		}
	}
	
	public void testRender(Graphics g) {
		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				g.drawImage(ADM.getInstance().getMapAsset(0), j*100, i*100, null);
			}
		}
		
	}
}
