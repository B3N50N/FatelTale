package dom;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DOM {
	private int clientno;

	private static DOM uniqueInstance;
	private Map<Integer, Player> Players;
	private Map<Integer, Monster> Monsters;
	private Map<Integer, Projector> Projectors;
	private Map<Integer, Item> Items;
	private Map<Integer, PlayerInfo> PlayerInfos;

	public DOM() {
		clientno = 0;
		Players = new HashMap<Integer, Player>();
		Monsters = new HashMap<Integer, Monster>();
		Projectors = new HashMap<Integer, Projector>();
		Items = new HashMap<Integer, Item>();
		PlayerInfos = new HashMap<Integer, PlayerInfo>();
	}

	public static synchronized DOM getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DOM();
		}
		return uniqueInstance;
	}

	public void setClientno(int clientno) {
		this.clientno = clientno;
	}

	public Vector<DynamicObject> getAllDynamicObjects() {
		Vector<DynamicObject> allObjects = new Vector<DynamicObject>();

		allObjects.addAll(Players.values());
		allObjects.addAll(Monsters.values());
		allObjects.addAll(Projectors.values());
		allObjects.addAll(Items.values());

		return allObjects;
	}

	
	/**************************************
	 *              PlayerInfo            *
	 **************************************/

	public PlayerInfo getPlayerInfo() {
		assert PlayerInfos.get(clientno) == null : "Get playerInfo failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno);
	}
	public PlayerInfo getPlayerInfo(int clientno) {
		assert PlayerInfos.get(clientno) == null : "Get playerInfo failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno);
	}

	public String getPlayerName() {
		assert PlayerInfos.get(clientno) == null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getName();
	}
	public String getPlayerName(int clientno) {
		assert PlayerInfos.get(clientno) == null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getName();
	}

	public int getPlayerHealth() {
		assert PlayerInfos.get(clientno) == null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getHealth();
	}
	public int getPlayerHealth(int clientno) {
		assert PlayerInfos.get(clientno) == null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getHealth();
	}

	public int getPlayerMaxHealth() {
		assert PlayerInfos.get(clientno) == null : "Get player's maxHealth failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getMaxHealth();
	}
	public int getPlayerMaxHealth(int clientno) {
		assert PlayerInfos.get(clientno) == null : "Get player's maxHealth failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getMaxHealth();
	}

	public int getPlayerScore() {
		assert PlayerInfos.get(clientno) == null : "Get player's score failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno).getScore();
	}
	public int getPlayerScore(int clientno) {
		assert PlayerInfos.get(clientno) == null : "Get player's score failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno).getScore();
	}

	public void addPlayerInfo(int clientno, PlayerInfo info) {
		assert PlayerInfos.get(clientno) != null : "Add playerInfo failed, already have a playerInfo with clientno "
				+ clientno;
		PlayerInfos.put(clientno, info);
	}
	public void addPlayerInfo(int clientno, int health, int maxHealth, int score) {
		assert PlayerInfos.get(clientno) != null : "Add playerInfo failed, already have a playerInfo with clientno "
				+ clientno;
		PlayerInfo info = new PlayerInfo(clientno, health, maxHealth, score);
		PlayerInfos.put(clientno, info);
	}
	public void addPlayerInfo(int clientno, String name, int health, int maxHealth, int score) {
		assert PlayerInfos.get(clientno) != null : "Add playerInfo failed, already have a playerInfo with clientno "
				+ clientno;
		PlayerInfo info = new PlayerInfo(name, health, maxHealth, score);
		PlayerInfos.put(clientno, info);
	}

	public void updatePlayerInfo(int clientno, int health, int maxHealth, int score) {
		PlayerInfo info = PlayerInfos.get(clientno);
		assert info == null : "Update playerInfo failed, no playerInfo with clientno " + clientno;
		PlayerInfos.put(clientno, info);
		info.updateHealth(health);
		info.updateMaxHealth(maxHealth);
		info.updateScore(score);
	}

	public void removePlayerInfo(int clientno) {
		assert Players.get(clientno) == null : "Remove playerInfo failed, no playerInfo with clientno " + clientno;
		PlayerInfos.remove(clientno);
	}

	
	
	/**************************************
	 *               Player               *
	 **************************************/
	
	public int getPlayerX() {
		Player player = Players.get(clientno);
		assert player == null : "Get player's X failed, no player with clientno " + clientno;
		return player.getX();
	}
	public int getPlayerX(int clientno) {
		Player player = Players.get(clientno);
		assert player == null : "Get player's X failed, no player with clientno " + clientno;
		return player.getX();
	}

	public int getPlayerY() {
		Player player = Players.get(clientno);
		assert player == null : "Get player's Y failed, no player with clientno " + clientno;
		return player.getY();
	}
	public int getPlayerY(int clientno) {
		Player player = Players.get(clientno);
		assert player == null : "Get player's Y failed, no player with clientno " + clientno;
		return player.getY();
	}

	public void addPlayer(int clientno, Player player) {
		assert Players.get(clientno) != null : "Add player failed, already have player with clientno " + clientno;
		Players.put(clientno, player);
	}
	public void addPlayer(int clientno) {
		assert Players.get(clientno) != null : "Add player failed, already have player with clientno " + clientno;
		Player player = new Player();
		Players.put(clientno, player);
	}
	public void addPlayer(int clientno, int x, int y, int direction, int assetIndex, int frame) {
		assert Players.get(clientno) != null : "Add player failed, already have player with clientno " + clientno;
		Player player = new Player(x, y, direction, assetIndex, frame);
		Players.put(clientno, player);
	}
	public void addPlayer(int clientno, int x, int y, int direction, int assetIndex) {
		assert Players.get(clientno) != null : "Add player failed, already have player with clientno " + clientno;
		Player player = new Player(x, y, direction, assetIndex);
		Players.put(clientno, player);
	}

	public void updatePlayer(int clientno, Player player) {
		assert Players.get(clientno) == null : "Update player failed, no player with clientno " + clientno;
		Players.put(clientno, player);
	}
	public void updatePlayer(int clientno, int x, int y, int direction, int assetIndex) {
		Player player = Players.get(clientno);
		assert player == null : "Update player failed, no player with clientno " + clientno;
		player.update(x, y, direction, assetIndex);
	}
	
	public void removePlayer(int clientno) {
		assert Players.get(clientno) == null : "Remove player failed, no player with clientno " + clientno;
		Players.remove(clientno);
	}

	
	
	/**************************************
	 *             Monster                *
	 **************************************/

	public void addMonster(int id, Monster monster) {
		assert Monsters.get(id) != null : "Add monster failed, already have monster with id " + id;
		Monsters.put(id, monster);
	}
	public void addMonster(int id) {
		assert Monsters.get(id) != null : "Add monster failed, already have monster with id " + id;
		Monster monster = new Monster();
		Monsters.put(id, monster);
	}
	public void addMonster(int id, int x, int y, int direction, int assetIndex, int frame) {
		assert Monsters.get(id) != null : "Add monster failed, already have monster with id " + id;
		Monster monster = new Monster(x, y, direction, assetIndex, frame);
		Monsters.put(id, monster);
	}
	public void addMonster(int id, int x, int y, int direction, int assetIndex) {
		assert Monsters.get(id) != null : "Add monster failed, already have monster with id " + id;
		Monster monster = new Monster(x, y, direction, assetIndex);
		Monsters.put(id, monster);
	}

	public void updateMonster(int id, Monster monster) {
		assert Monsters.get(id) == null : "Update monster failed, no monster with id " + id;
		Monsters.put(id, monster);
	}
	public void updateMonster(int id, int x, int y, int direction, int assetIndex) {
		Monster monster = Monsters.get(id);
		assert monster == null : "Update monster failed, no monster with id " + id;
		monster.update(x, y, direction, assetIndex);
	}

	public void removeMonster(int id) {
		assert Monsters.get(id) == null : "Remove monster failed, no monster with id" + id;
		Monsters.remove(id);
	}

	
	
	/**************************************
	 *              Projector             *
	 **************************************/

	public void addProjector(int id, Projector projector) {
		assert Projectors.get(id) != null : "Add Projector failed, already have projector with id " + id;
		Projectors.put(id, projector);
	}
	public void addProjector(int id) {
		assert Projectors.get(id) != null : "Add Projector failed, already have projector with id " + id;
		Projector projector = new Projector();
		Projectors.put(id, projector);
	}
	public void addProjector(int id, int x, int y, int direction, int assetIndex, int frame) {
		assert Projectors.get(id) != null : "Add Projector failed, already have projector with id " + id;
		Projector projector = new Projector(x, y, direction, assetIndex, frame);
		Projectors.put(id, projector);
	}
	public void addProjector(int id, int x, int y, int direction, int assetIndex) {
		assert Projectors.get(id) != null : "Add Projector failed, already have projector with id " + id;
		Projector projector = new Projector(x, y, direction, assetIndex);
		Projectors.put(id, projector);
	}

	public void updateProjector(int id, Projector projector) {
		assert Projectors.get(id) == null : "Update projector failed, no projector with id " + id;
		Projectors.put(id, projector);
	}
	public void updateProjector(int id, int x, int y, int direction, int assetIndex) {
		Projector projector = Projectors.get(id);
		assert projector == null : "Update projector failed, no projector with id " + id;
		projector.update(x, y, direction, assetIndex);
	}

	public void removeProjector(int id) {
		assert Projectors.get(id) == null : "Remove projector failed, no projector with id " + id;
		Projectors.remove(id);
	}

	
	
	/**************************************
	 *                Item                *
	 **************************************/

	public void addItem(int id, Item item) {
		assert Items.get(id) != null : "Add Item failed, already have projector with id " + id;
		Items.put(id, item);
	}
	public void addItem(int id) {
		assert Items.get(id) != null : "Add Item failed, already have projector with id " + id;
		Item item = new Item();
		Items.put(id, item);
	}
	public void addItem(int id, int x, int y, int direction, int assetIndex, int frame) {
		assert Items.get(id) != null : "Add Item failed, already have projector with id " + id;
		Item item = new Item(x, y, direction, assetIndex, frame);
		Items.put(id, item);
	}
	public void addItem(int id, int x, int y, int direction, int assetIndex) {
		assert Items.get(id) != null : "Add Item failed, already have projector with id " + id;
		Item item = new Item(x, y, direction, assetIndex);
		Items.put(id, item);
	}

	public void updateItem(int id, Item item) {
		assert Items.get(id) == null : "Update item failed, no item with id " + id;
		Items.put(id, item);
	}
	public void updateItem(int id, int x, int y, int direction, int assetIndex) {
		Item item = Items.get(id);
		assert item == null : "Update item failed, no item with id " + id;
		item.update(x, y, direction, assetIndex);
	}

	public void removeItem(int id) {
		assert Items.get(id) == null : "Remove item failed, mo item with id " + id;
		Items.remove(id);
	}
}
