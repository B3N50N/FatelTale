package SceneRenderEngine;

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
}
