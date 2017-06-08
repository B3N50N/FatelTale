package PhysicsEngineModule;

public class PEM {
	
	private static PEM uniqueInstance;
	
	private PEM() {
		
	}
	
	public static synchronized PEM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new PEM();
		}
		return uniqueInstance;
	}
}
