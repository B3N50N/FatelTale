package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EmitterInfo {
	
	private static EmitterInfo uniqueInstance;
	
	private EmitterInfo() {
		
	}
	
	public static synchronized EmitterInfo getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new EmitterInfo();
		}
		return uniqueInstance;
	}
	
	public Emitter getEmitter(BufferedReader br) throws IOException {
		
		String Input;
		StringTokenizer st;
		Input = br.readLine();
		
		if ( Input.equals("DirectlyEmitter") ) {
			Input = br.readLine();
			st = new StringTokenizer(Input);
			
			Long attack_speed;
			assert st.countTokens() == 1 : "Wrong Format.";
			
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			attack_speed = Long.parseLong(Input);
			
			Projector p = ProjectorInfo.getInstance().getProjector(br);
			
			return new DirectlyEmitter(attack_speed, p.getDirection(), p.getPosition(), p);
		}
		
		assert false : "Invalid Format.";
		return null;
	}

}
