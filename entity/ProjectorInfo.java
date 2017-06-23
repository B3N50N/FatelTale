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
	
	private void readFile(String path) {
		assert path != null : "Invalid path.";
		
		try {
			FileReader fr;
			fr = new FileReader(path+"Format.txt");
			BufferedReader br=new BufferedReader(fr);
			
			String Input;
			StringTokenizer st;
			Input = br.readLine();
			st = new StringTokenizer(Input);
			
			assert st.countTokens() == 1 : "Wrong Format.";
			Input = st.nextToken();
			
			assert Input.matches("\\d+") : "Wrong Format.";
			int size = Integer.parseInt(Input);
			
			_projector = new Projector[size];
			
			for (int i=0;i<size;i++) {
				_projector[i] = readProjectorFile(path, br);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			assert false : "File is not exist.";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assert false : "Unexpect File Format.";
		}
	}
	
	private Projector readProjectorFile(String path, BufferedReader br) throws IOException, FileNotFoundException{
		String Input;
		Input = br.readLine();
		
		if ( Input == "StrikeProjector" ) {
			return new StrikeProjector(new Point(0, 0), new Point(0, 0), null, 0L, 0);
		}
		
		assert false : "Wrong Format.";
		return null;
	}
	
	public Projector getProjector(String type, Collider c) {
		assert type != null : "Null Object.";
		assert c != null : "Null Object.";
		
		if ( type == "StrikeProjector" ) {
			return new StrikeProjector(new Point(0, 0), c, 0L, 0);
		}
		
		assert false : "Wrong Format.";
		return null;
	}
}
