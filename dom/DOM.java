package dom;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DOM {
	private static DOM uniqueInstance;
	private Map<Integer, Player> Players;
	private Map<Integer, Monster> Monsters;
	private Map<Integer, Projector> Projectors;
	private Map<Integer, Item> Items;
	private Map<Integer, PlayerInfo> PlayerInfos;
	
	public DOM() {
		Players = new HashMap<Integer, Player>();
		Monsters = new HashMap<Integer, Monster>();
		Projectors = new HashMap<Integer, Projector>();
		Items = new HashMap<Integer, Item>();
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
	
	

	/**************************************
	 *                                    * 
	 *              Player                *
	 *                                    *
	 **************************************/
	
	void addPlayer(int clientno, Player player){
		assert Players.get(clientno)!=null : "Add player failed, already have player with clientno "+clientno;
		Players.put(clientno, player);
	}
	void addPlayer(int clientno, int x, int y, int direction, int assertIndex, int frame){
		assert Players.get(clientno)!=null : "Add player failed, already have player with clientno "+clientno;
		Player player = new Player(x, y, direction, assertIndex, frame);
		Players.put(clientno, player);
	}

	void updatePlayer(int clientno, Player player){
		assert Players.get(clientno)==null : "Update player failed, no player with clientno "+clientno;
		Players.put(clientno, player);
	}
	void updatePlayer(int clientno, int x, int y, int direction, int assertIndex, int frame){
		assert Players.get(clientno)==null : "Update player failed, no player with clientno "+clientno;
		Player player = new Player(x, y, direction, assertIndex, frame);
		Players.put(clientno, player);
	}
	 
	public void removePlayer(int clientno){
		assert Players.get(clientno)==null : "Remove player failed, no player with clientno "+clientno;
		Players.remove(clientno);
	}
	
	
	

	/**************************************
	 *                                    * 
	 *              Monster               *
	 *                                    *
	 **************************************/
	
	void addMonster(int id, Monster monster){
		assert Monsters.get(id)!=null : "Add monster failed, already have monster with id "+id;
		Monsters.put(id, monster);
	}
	void addMonster(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Monsters.get(id)!=null : "Add monster failed, already have monster with id "+id;
		Monster monster = new Monster(x, y, direction, assertIndex, frame);
		Monsters.put(id, monster);
	}

	void updateMonster(int id, Monster monster){
		assert Monsters.get(id)==null : "Update monster failed, no monster with id "+id;
		Monsters.put(id, monster);
	}
	void updateMonster(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Monsters.get(id)==null : "Update monster failed, no monster with id "+id;
		Monster monster = new Monster(x, y, direction, assertIndex, frame);
		Monsters.put(id, monster);
	}

	public void removeMonster(int id){
		assert Monsters.get(id)==null : "Remove monster failed, no monster with id"+id;
		Monsters.remove(id);
	}
	
	
	

	/**************************************
	 *                                    * 
	 *             Projector              *
	 *                                    *
	 **************************************/
	
	void addProjector(int id, Projector projector){
		assert Projectors.get(id)!=null : "Add Projector failed, already have projector with id "+id;
		Projectors.put(id, projector);
	}
	void addProjector(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Projectors.get(id)!=null : "Add Projector failed, already have projector with id "+id;
		Projector projector = new Projector(x, y, direction, assertIndex, frame);
		Projectors.put(id, projector);
	}
	
	void updateProjector(int id, Projector projector){
		assert Projectors.get(id)==null : "Update projector failed, no projector with id "+id;
		Projectors.put(id, projector);
	}
	void updateProjector(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Projectors.get(id)==null : "Update projector failed, no projector with id "+id;
		Projector projector = new Projector(x, y, direction, assertIndex, frame);
		Projectors.put(id, projector);
	}

	public void removeProjector(int id){
		assert Projectors.get(id)==null : "Remove projector failed, no projector with id "+id;
		Projectors.remove(id);
	}
	
	
	

	/**************************************
	 *                                    * 
	 *               Item                 *
	 *                                    *
	 **************************************/
	
	void addItem(int id, Item item){
		assert Items.get(id)!=null : "Add Item failed, already have projector with id "+id;
		Items.put(id, item);
	}
	void addItem(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Items.get(id)!=null : "Add Item failed, already have projector with id "+id;
		Item item = new Item(x, y, direction, assertIndex, frame);
		Items.put(id, item);
	}
	
	void updateItem(int id, Item item){
		assert Items.get(id)==null : "Update item failed, no item with id "+id;
		Items.put(id, item);
	}
	void updateItem(int id, int x, int y, int direction, int assertIndex, int frame){
		assert Items.get(id)==null : "Update item failed, no item with id "+id;
		Item item = new Item(x, y, direction, assertIndex, frame);
		Items.put(id, item);
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
	
	
	

	/**************************************
	 *                                    * 
	 *            PlayerInfo              *
	 *                                    *
	 **************************************/
	
	public PlayerInfo getPlayerInfo(int clientno) {
		assert PlayerInfos.get(clientno)==null : "Get playerInfo failed, no playerInfo with clientno "+clientno;
		return PlayerInfos.get(clientno);
	}
	public int getPlayerHealth(int clientno) {
		assert PlayerInfos.get(clientno)==null : "Get player's health failed, no playerInfo with clientno "+clientno;
		return PlayerInfos.get(clientno).getHealth();
	}
	public int getPlayerMaxHealth(int clientno) {
		assert PlayerInfos.get(clientno)==null : "Get player's maxHealth failed, no playerInfo with clientno "+clientno;
		return PlayerInfos.get(clientno).getMaxHealth();
	}
	public int getPlayerScore(int clientno) {
		assert PlayerInfos.get(clientno)==null : "Get player's score failed, no playerInfo with clientno "+clientno;
		return PlayerInfos.get(clientno).getScore();
	}
	
	public PlayerInfo addPlayerInfo(int clientno, PlayerInfo info) {
		assert PlayerInfos.get(clientno)!=null : "Add playerInfo failed, already have a playerInfo with clientno "+clientno;
		return PlayerInfos.put(clientno, info);
	}
	public PlayerInfo addPlayerInfo(int clientno, int health, int maxHealth, int score) {
		assert PlayerInfos.get(clientno)!=null : "Add playerInfo failed, already have a playerInfo with clientno "+clientno;
		PlayerInfo info = new PlayerInfo(clientno, health, maxHealth, score);
		return PlayerInfos.put(clientno, info);
	}
	
	public void updatePlayerInfo(int clientno, int health, int maxHealth, int score) {
		assert PlayerInfos.get(clientno)==null : "Update playerInfo failed, no playerInfo with clientno "+clientno;
		PlayerInfo info = new PlayerInfo(clientno, health, maxHealth, score);
		PlayerInfos.put(clientno, info);
	}
	
	public void removePlayerInfo(int clientno) {
		assert Players.get(clientno)==null : "Remove playerInfo failed, no playerInfo with clientno "+clientno;
		PlayerInfos.remove(clientno);
	}
}
