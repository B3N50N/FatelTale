package entity;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ColliderInfo {
	
	private static ColliderInfo uniqueInstance;
	
	public static synchronized ColliderInfo getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new ColliderInfo();
		}
		return uniqueInstance;
	}
	
	public Collider getCollider(BufferedReader br) throws IOException {
		String Input = br.readLine(); // Collider
		StringTokenizer st;
		if ( Input == "Box" ) {
			int w, h;
			
			Input = br.readLine();
			st = new StringTokenizer(Input);
			
			assert st.countTokens() == 2 : "Wrong Format.";
			
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			w = Integer.parseInt(Input);
			
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			h = Integer.parseInt(Input);
			
			return new BoxCollider(new Point(0, 0), new Point(0, 0), w, h);
		}
		if ( Input == "Sphere" ) {
			double r;
			
			Input = br.readLine();
			st = new StringTokenizer(Input);
			
			assert st.countTokens() == 1 : "Wrong Format.";
			
			Input = st.nextToken();
			r = Double.parseDouble(Input);
			
			return new SphereCollider(new Point(0, 0), r);
		}
		
		assert false : "Wrong Format.";
		return null;
	}
}
