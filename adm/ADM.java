package adm;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class ADM {
	
	private static ADM uniqueInstance;
	
	private BufferedImage[][] _map_assets;
	private int _map_width, _map_height;
	
	private ADM() {
		_map_assets = readFile("./resource/Map/");
	}
	
	public static synchronized ADM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new ADM();
		}
		return uniqueInstance;
	}
	
	private BufferedImage[][] readFile(String path) {
		assert path != null : "Invalid Path.";
		
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
			
			BufferedImage[][] assets = new BufferedImage[size][];
			
			for (int i=0;i<size;i++) {
				assets[i] = readSpriteSheet(path, br);
			}
			
			return assets;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			assert false : "No Such File.";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			assert false : "Wrong Format.";
		}
		return null;
	}
	
	private BufferedImage[] readSpriteSheet(String path, BufferedReader br) {
		try {
			String Input;
			StringTokenizer st;
			
			Input = br.readLine();
			BufferedImage Image = ImageIO.read(new File(path+Input));

			Input = br.readLine();
			st = new StringTokenizer(Input);
			assert st.countTokens() == 2 : "Wrong Format.";
			
			int width, height;
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			width = Integer.parseInt(Input);
			
			Input = st.nextToken();
			assert Input.matches("\\d+") : "Wrong Format.";
			height = Integer.parseInt(Input);
			
			return sliceSpriteSheet(Image, width, height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			assert false : "Wrong Format.";
		}
		return null;
	}
	
	private BufferedImage[] sliceSpriteSheet(BufferedImage Image, int width, int height) {
		int hei = Image.getHeight() / height, wid = Image.getWidth() / width;
		int size = hei * wid;
		BufferedImage[] assets = new BufferedImage[size];
		System.out.println(size);
		for (int i=0;i<hei;i++) {
			for (int j=0;j<wid;j++) {
				assets[i*wid+j] = Image.getSubimage(j*width, i*height, width, height);
			}
		}
		return assets;
	}
	
	public BufferedImage getMapAsset(int index) {
		assert _map_assets[0] != null && index >= 0 && index < _map_assets[0].length : "Invalid Index.";
		return _map_assets[0][index];
	}
	
	public int getMapWidth() {
		assert _map_assets[0][0] != null : "Map Assets is Null";
		return _map_assets[0][0].getWidth();
	}
	
	public int getMapHeight() {
		assert _map_assets[0][0] != null : "Map Assets is Null";
		return _map_assets[0][0].getHeight();
	}
}
