package entity;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ProjectorInfo {
	
	private static ProjectorInfo uniqueInstance;
	
	private ProjectorInfo() {
		
	}
	
	public static synchronized ProjectorInfo getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new ProjectorInfo();
		}
		return uniqueInstance;
	}
	
	public Projector getProjector(String type, Collider c) {
		assert type != null : "Null Object.";
		assert c != null : "Null Object.";
		
		if ( type == "StrikeProjector" ) {
			if ( c.getType() == "Box" )
				return new StrikeProjector( ((BoxCollider)c).getDirection(), c, 0L, 0, 0);
			return new StrikeProjector(new Point(0, 0), c, 0L, 0, 0);
		}
		
		assert false : "Wrong Format.";
		return null;
	}
	
	public Projector getProjector(BufferedReader br) throws IOException {
		
		String Input = br.readLine();
		StringTokenizer st;
		
		if ( Input.equals("StrikeProjector") ) {
			Collider c = ColliderInfo.getInstance().getCollider(br);
			
			Long speed;
			int asset_index;
			Input = br.readLine();
			st = new StringTokenizer(Input);
			assert st.countTokens() == 2 : "Wrong Format.";
			
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			speed = Long.parseLong(Input);
			
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			asset_index = Integer.parseInt(Input);
			
			return new StrikeProjector(new Point(0, 0), c, speed, -1, asset_index);
		}
		
		assert false : "Wrong Format.";
		return null;
	}
}
