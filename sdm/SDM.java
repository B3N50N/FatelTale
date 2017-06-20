package sdm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import adm.ADM;

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
	
	private void analyisFile(BufferedReader br) {
		
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
	
	public boolean isOutofBound(int x, int y) {
		return ( x >= 0 && x < ( _width * ADM.getInstance().getMapWidth() ) && y >= 0 && y < ( _height * ADM.getInstance().getMapHeight() ) );
	}
	
	public boolean isWalkable(int x, int y) {
		x /= ADM.getInstance().getMapWidth();
		y /= ADM.getInstance().getMapHeight();
		return ( x >= 0 && x < _width && y >= 0 && y < _height ) ? _map[y][x].isWalkable() : false;
	}
	
	public boolean isLegal(int x, int y) {
		return ( x >= 0 && x < _width && y >= 0 && y < _height );
	}
	
	public int getAssetIndex(int x, int y) {
		assert isLegal(x, y) : "Invalid Index.";
		return _map[y][x].getAssetIndex();
	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public void printMap() {
		for (int i=0;i<_height;i++) {
			for (int j=0;j<_width;j++) {
				System.out.print(_map[i][j].getAssetIndex() + " ");
			}
			System.out.println();
		}
	}
}
