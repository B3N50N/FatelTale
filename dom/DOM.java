package dom;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DOM {
	private static DOM uniqueInstance;
	private Map<Integer, DynamicObject> Players, Monsters, Projectors, Items;
	private Map<Integer, PlayerInfo> PlayerInfos;
	
	private DOM() {
		Players = new HashMap<Integer, DynamicObject>();
		Monsters = new HashMap<Integer, DynamicObject>();
		Projectors = new HashMap<Integer, DynamicObject>();
		Items = new HashMap<Integer, DynamicObject>();
		PlayerInfos = new HashMap<Integer, PlayerInfo>();
	}
	
	public static synchronized DOM getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new DOM();
		}
		return uniqueInstance;
	}
	
	public Vector<DynamicObject> getAllDynamicObjects(){
		Vector<DynamicObject> allObjects = new Vector<DynamicObject>();

		allObjects.addAll(Players.values());
		allObjects.addAll(Monsters.values());
		allObjects.addAll(Projectors.values());
		allObjects.addAll(Items.values());
		
		return allObjects;
	}
	void addPlayer(int clientno, DynamicObject player){
		assert Players.get(clientno)!=null : "Add player failed, already have player with clientno "+clientno;
		Players.put(clientno, player);
	}
	void addMonster(int id, DynamicObject monster){
		assert Monsters.get(id)!=null : "Add monster failed, already have monster with id "+id;
		Monsters.put(id, monster);
	}
	void addProjector(int id, DynamicObject projector){
		assert Projectors.get(id)!=null : "Add Projector failed, already have projector with id "+id;
		Projectors.put(id, projector);
	}
	void addItem(int id, DynamicObject item){
		assert Items.get(id)!=null : "Add Item failed, already have projector with id "+id;
		Items.put(id, item);
	}

	void updatePlayer(int clientno, DynamicObject player){
		assert Players.get(clientno)==null : "Update player failed, no player with clientno "+clientno;
		Players.put(clientno, player);
	}
	void updatePlayer(int clientno, int x, int y, int direction, int assertIndex, int frame){
		assert Players.get(clientno)==null : "Update player failed, no player with clientno "+clientno;
		DynamicObject player = new DynamicObject(x, y, direction, assertIndex, frame);
		Players.put(clientno, player);
	}
	void updateMonster(int id, DynamicObject monster){
		assert Monsters.get(id)==null : "Update monster failed, no monster with id "+id;
		Monsters.put(id, monster);
	}
	void updateMonster(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Monsters.get(id)==null : "Update monster failed, no monster with id "+id;
		DynamicObject monster = new DynamicObject(x, y, direction, assertIndex, frame);
		Monsters.put(id, monster);
	}
	void updateProjector(int id, DynamicObject projector){
		assert Projectors.get(id)==null : "Update projector failed, no projector with id "+id;
		Projectors.put(id, projector);
	}
	void updateProjector(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Projectors.get(id)==null : "Update projector failed, no projector with id "+id;
		DynamicObject projector = new DynamicObject(x, y, direction, assertIndex, frame);
		Projectors.put(id, projector);
	}
	void updateItem(int id, DynamicObject item){
		assert Items.get(id)==null : "Update item failed, no item with id "+id;
		Items.put(id, item);
	}
	void updateItem(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Items.get(id)==null : "Update item failed, no item with id "+id;
		DynamicObject item = new DynamicObject(x, y, direction, assertIndex, frame);
		Items.put(id, item);
	}
	 
	public void removePlayer(int clientno){
		assert Players.get(clientno)==null : "Remove player failed, no player with clientno "+clientno;
		Players.remove(clientno);
	}
	public void removeMonster(int id){
		assert Monsters.get(id)==null : "Remove monster failed, no monster with id"+id;
		Monsters.remove(id);
	}
	public void removeProjector(int id){
		assert Projectors.get(id)==null : "Remove projector failed, no projector with id "+id;
		Projectors.remove(id);
	}
	public void removeItem(int id){
		assert Items.get(id)==null : "Remove item failed, mo item with id "+id;
		Items.remove(id);
	}
	 
	public int getPlayerX(int clientno) {
		assert Players.get(clientno)==null : "Get player's X failed, no player with clientno "+clientno;
		return Players.get(clientno).getX();
	}
	public int getPlayerY(int clientno) {
		assert Players.get(clientno)==null : "Get player's Y failed, no player with clientno "+clientno;
		return Players.get(clientno).getY();
	}
	
	public void updatePlayerInfo(int clientno, PlayerInfo info) {

	}
}
