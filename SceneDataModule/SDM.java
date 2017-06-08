package SceneDataModule;

public class SDM {
	
	private static SDM uniqueInstance;
	
	private SDM() {
		
	}
	
	public static synchronized SDM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new SDM();
		}
		return uniqueInstance;
	}
}
