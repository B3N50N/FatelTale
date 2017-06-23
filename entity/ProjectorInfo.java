package entity;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ProjectorInfo {
	
	private static ProjectorInfo uniqueInstance;
	
	private Projector[] _projector;
	
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
	
	public Projector getProjector(BufferedReader br) {
		
		return null;
	}
}
