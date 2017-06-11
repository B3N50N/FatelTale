package sdm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SDM {
	
	private int _width, _height;
	private Block _map[][];
	
	private static SDM uniqueInstance;
	
	private SDM() {
		
	}
	
	public static synchronized SDM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new SDM();
		}
		return uniqueInstance;
	}
	
	public void readMap(String path) {
		assert path != null : "Path is null";
		
		try {
			FileReader fr;
			fr = new FileReader(path);
			BufferedReader br=new BufferedReader(fr);
			
			analyisFile(br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File is not exist.");
		}
	}
	
	public void analyisFile(BufferedReader br) {
		
		String Input;
		StringTokenizer st;
		
		try {
			Input = br.readLine();
			assert Input != null : "Wrong Format of File.";
			st = new StringTokenizer(Input);
			
			Input = st.nextToken();
			assert isLegal(Input) : "Wrong Format of File.";
			_width = Integer.parseInt(Input);
			
			Input = st.nextToken();
			assert isLegal(Input) : "Wrong Format of File.";
			_height = Integer.parseInt(Input);
			
			assert _width > 0 && _height > 0 : "Wrong Map Size.";
			_map = new Block[_height][_width];
			
			for (int y=0;y<_height;y++){
				Input = br.readLine();
				st = new StringTokenizer(Input);
				assert Input != null : "Wrong Format of File.";
				assert st.countTokens() == ( 2 * _width ) : "Wrong Format of File.";
				
				for (int x=0;x<_width;x++) {
					int value, bool;
					Input = st.nextToken();
					assert isLegal(Input) : "Wrong Format of File.";
					value = Integer.parseInt(Input);
					
					Input = st.nextToken();
					assert isLegal(Input) : "Wrong Format of File.";
					bool = Integer.parseInt(Input);
					assert bool == 1 || bool == 0 : "Wrong Format of File.";
					
					_map[y][x] = new Block((bool == 1 ? true : false), value);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean isLegal(String str) {
		if ( str == null || str.length() == 0 ) return false;
		for( int i=0;i<str.length();i++) {
			if ( ! Character.isDigit(str.charAt(i)) ) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isWalkable(float x, float y) {
		int X = (int) Math.floor(x), Y = (int) Math.floor(y);
		X /= 100;
		Y /= 100;
		return ( X >= 0 && X < _width && Y >= 0 && Y < _height ) ? _map[Y][X].isWalkable() : false;
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
}
