package dom;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import logger.Logger;

public class DOM {
	private int clientno;

	private static DOM uniqueInstance;
	private ConcurrentHashMap<Integer, Player> Players;
	private ConcurrentHashMap<Integer, Monster> Monsters;
	private ConcurrentHashMap<Integer, Projector> Projectors;
	private ConcurrentHashMap<Integer, Item> Items;
	private ConcurrentHashMap<Integer, PlayerInfo> PlayerInfos;

	public DOM() {
		clientno = 0;
		Players = new ConcurrentHashMap<Integer, Player>();
		Monsters = new ConcurrentHashMap<Integer, Monster>();
		Projectors = new ConcurrentHashMap<Integer, Projector>();
		Items = new ConcurrentHashMap<Integer, Item>();
		PlayerInfos = new ConcurrentHashMap<Integer, PlayerInfo>();
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

	public synchronized Vector<DynamicObject> getAllDynamicObjects() {
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

	public int getPlayerNumber() {
		return PlayerInfos.size();
	}
	
	public PlayerInfo getPlayerInfo() {
		assert PlayerInfos.get(clientno) != null : "Get playerInfo failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno);
	}
	public PlayerInfo getPlayerInfo(int clientno) {
		assert PlayerInfos.get(clientno) != null : "Get playerInfo failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno);
	}

	public String getPlayerName() {
		assert PlayerInfos.get(clientno) != null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getName();
	}
	public String getPlayerName(int clientno) {
		assert PlayerInfos.get(clientno) != null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getName();
	}

	public int getPlayerHealth() {
		assert PlayerInfos.get(clientno) != null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getHealth();
	}
	public int getPlayerHealth(int clientno) {
		assert PlayerInfos.get(clientno) != null : "Get player's health failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getHealth();
	}

	public int getPlayerMaxHealth() {
		assert PlayerInfos.get(clientno) != null : "Get player's maxHealth failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getMaxHealth();
	}
	public int getPlayerMaxHealth(int clientno) {
		assert PlayerInfos.get(clientno) != null : "Get player's maxHealth failed, no playerInfo with clientno "
				+ clientno;
		return PlayerInfos.get(clientno).getMaxHealth();
	}

	public int getPlayerScore() {
		assert PlayerInfos.get(clientno) != null : "Get player's score failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno).getScore();
	}
	public int getPlayerScore(int clientno) {
		assert PlayerInfos.get(clientno) != null : "Get player's score failed, no playerInfo with clientno " + clientno;
		return PlayerInfos.get(clientno).getScore();
	}

	public void addPlayerInfo(int clientno, PlayerInfo info) {
		//assert PlayerInfos.get(clientno) == null : "Add playerInfo failed, already have a playerInfo with clientno " + clientno;
		if(PlayerInfos.get(clientno) != null)
		{
			Logger.log("Add playerInfo failed, already have a playerInfo with clientno "+ clientno);
			return;
		}
		PlayerInfos.put(clientno, info);
	}
	public void addPlayerInfo(int clientno) {
		//assert PlayerInfos.get(clientno) == null : "Add playerInfo failed, already have a playerInfo with clientno " + clientno;
		if(PlayerInfos.get(clientno) != null)
		{
			Logger.log("Add playerInfo failed, already have a playerInfo with clientno "+ clientno);
			return;
		}
		PlayerInfo info = new PlayerInfo(clientno);
		PlayerInfos.put(clientno, info);
	}
	public void addPlayerInfo(int clientno, int health, int maxHealth, int score) {
		//assert PlayerInfos.get(clientno) == null : "Add playerInfo failed, already have a playerInfo with clientno " + clientno;
		if(PlayerInfos.get(clientno) != null)
		{
			Logger.log("Add playerInfo failed, already have a playerInfo with clientno "+ clientno);
			return;
		}
		PlayerInfo info = new PlayerInfo(clientno, health, maxHealth, score);
		PlayerInfos.put(clientno, info);
	}
	public void addPlayerInfo(int clientno, String name, int health, int maxHealth, int score) {
		//assert PlayerInfos.get(clientno) == null : "Add playerInfo failed, already have a playerInfo with clientno " + clientno;
		if(PlayerInfos.get(clientno) != null)
		{
			Logger.log("Add playerInfo failed, already have a playerInfo with clientno "+ clientno);
			return;
		}
		PlayerInfo info = new PlayerInfo(name, health, maxHealth, score);
		PlayerInfos.put(clientno, info);
	}

	public void updatePlayerInfo(int clientno, String name, int health, int maxHealth, int score) {
		PlayerInfo info = PlayerInfos.get(clientno);
		//assert info == null : "Update playerInfo failed, no playerInfo with clientno " + clientno;
		if(info != null)
		{
			Logger.log("Update playerInfo failed, no playerInfo with clientno " + clientno);
			return;
		}
		info.updateName(name);
		info.updateHealth(health);
		info.updateMaxHealth(maxHealth);
		info.updateScore(score);
	}
	public void updatePlayerInfo(int clientno, int health, int maxHealth, int score) {
		PlayerInfo info = PlayerInfos.get(clientno);
		//assert info != null : "Update playerInfo failed, no playerInfo with clientno " + clientno;
		if(info == null)
		{
			Logger.log("Update playerInfo failed, no playerInfo with clientno " + clientno);
			return;
		}
		PlayerInfos.put(clientno, info);
		info.updateHealth(health);
		info.updateMaxHealth(maxHealth);
		info.updateScore(score);
	}

	public void removePlayerInfo(int clientno) {
		//assert Players.get(clientno) != null : "Remove playerInfo failed, no playerInfo with clientno " + clientno;
		if(Players.get(clientno) == null)
		{
			Logger.log("Remove playerInfo failed, no playerInfo with clientno " + clientno);
			return;
		}
		PlayerInfos.remove(clientno);
	}

	
	
	/**************************************
	 *               Player               *
	 **************************************/
	
	public int getPlayerX() {
		Player player = Players.get(clientno);
		assert player != null : "Get player's X failed, no player with clientno " + clientno;
		return player.getX();
	}
	public int getPlayerX(int clientno) {
		Player player = Players.get(clientno);
		assert player != null : "Get player's X failed, no player with clientno " + clientno;
		return player.getX();
	}

	public int getPlayerY() {
		Player player = Players.get(clientno);
		assert player != null : "Get player's Y failed, no player with clientno " + clientno;
		return player.getY();
	}
	public int getPlayerY(int clientno) {
		Player player = Players.get(clientno);
		assert player != null : "Get player's Y failed, no player with clientno " + clientno;
		return player.getY();
	}

	public void addPlayer(int clientno, Player player) {
		//assert Players.get(clientno) == null : "Add player failed, already have player with clientno " + clientno;
		if(Players.get(clientno) != null)
		{
			Logger.log("Add player failed, already have player with clientno " + clientno);
			return;
		}
		Players.put(clientno, player);
	}
	public void addPlayer(int clientno) {
		//assert Players.get(clientno) == null : "Add player failed, already have player with clientno " + clientno;
		if(Players.get(clientno) != null)
		{
			Logger.log("Add player failed, already have player with clientno " + clientno);
			return;
		}
		Player player = new Player();
		Players.put(clientno, player);
	}
	public void addPlayer(int clientno, int x, int y, int direction, int assetIndex, int frame) {
		//assert Players.get(clientno) == null : "Add player failed, already have player with clientno " + clientno;
		if(Players.get(clientno) != null)
		{
			Logger.log("Add player failed, already have player with clientno " + clientno);
			return;
		}
		Player player = new Player(x, y, direction, assetIndex, frame);
		Players.put(clientno, player);
	}
	public void addPlayer(int clientno, int x, int y, int direction, int assetIndex) {
		//assert Players.get(clientno) == null : "Add player failed, already have player with clientno " + clientno;
		if(Players.get(clientno) != null)
		{
			Logger.log("Add player failed, already have player with clientno " + clientno);
			return;
		}
		Player player = new Player(x, y, direction, assetIndex);
		Players.put(clientno, player);
	}

	public void updatePlayer(int clientno, Player player) {
		//assert Players.get(clientno) != null : "Update player failed, no player with clientno " + clientno;
		if(Players.get(clientno) == null)
		{
			Logger.log("Update player failed, no player with clientno " + clientno);
			return;
		}
		Players.put(clientno, player);
	}
	public void updatePlayer(int clientno, int x, int y, int direction, int assetIndex) {
		Player player = Players.get(clientno);
		//assert player != null : "Update player failed, no player with clientno " + clientno;
		if(player == null)
		{
			Logger.log("Update player failed, no player with clientno " + clientno);
			return;
		}
		player.update(x, y, direction, assetIndex);
	}
	public void updatePlayer(int clientno, int x, int y, int directionX, int directionY, int assetIndex) {
		Player player = Players.get(clientno);
		//assert player != null : "Update player failed, no player with clientno " + clientno;
		if(player == null)
		{
			Logger.log("Update player failed, no player with clientno " + clientno);
			return;
		}
		player.updateByDirection(x, y, directionX, directionY, assetIndex);
	}
	
	public void removePlayer(int clientno) {
		//assert Players.get(clientno) != null : "Remove player failed, no player with clientno " + clientno;
		if(Players.get(clientno) == null)
		{
			Logger.log("Update player failed, no player with clientno " + clientno);
			return;
		}
		Players.remove(clientno);
	}
	

	
	
	/**************************************
	 *             Monster                *
	 **************************************/

	public void addMonster(int id, Monster monster) {
		//assert Monsters.get(id) == null : "Add monster failed, already have monster with id " + id;
		if(Monsters.get(id) != null)
		{
			Logger.log("Add monster failed, already have monster with id " + id);
			return;
		}
		Monsters.put(id, monster);
	}
	public void addMonster(int id) {
		//assert Monsters.get(id) == null : "Add monster failed, already have monster with id " + id;
		if(Monsters.get(id) != null)
		{
			Logger.log("Add monster failed, already have monster with id " + id);
			return;
		}
		Monster monster = new Monster();
		Monsters.put(id, monster);
	}
	public void addMonster(int id, int x, int y, int direction, int assetIndex, int frame) {
		//assert Monsters.get(id) == null : "Add monster failed, already have monster with id " + id;
		if(Monsters.get(id) != null)
		{
			Logger.log("Add monster failed, already have monster with id " + id);
			return;
		}
		Monster monster = new Monster(x, y, direction, assetIndex, frame);
		Monsters.put(id, monster);
	}
	public void addMonster(int id, int x, int y, int direction, int assetIndex) {
		//assert Monsters.get(id) == null : "Add monster failed, already have monster with id " + id;
		if(Monsters.get(id) != null)
		{
			Logger.log("Add monster failed, already have monster with id " + id);
			return;
		}
		Monster monster = new Monster(x, y, direction, assetIndex);
		Monsters.put(id, monster);
	}

	public void updateMonster(int id, Monster monster) {
		//assert Monsters.get(id) != null : "Update monster failed, no monster with id " + id;
		if(Monsters.get(id) == null)
		{
			Logger.log("Update monster failed, no monster with id " + id);
			return;
		}
		Monsters.put(id, monster);
	}
	public void updateMonster(int id, int x, int y, int direction, int assetIndex) {
		Monster monster = Monsters.get(id);
		//assert monster != null : "Update monster failed, no monster with id " + id;
		if(monster == null)
		{
			Logger.log("Update monster failed, no monster with id " + id);
			return;
		}
		monster.update(x, y, direction, assetIndex);
	}
	public void updateMonster(int id, int x, int y, int directionX, int directionY, int assetIndex) {
		Monster monster = Monsters.get(id);
		//assert monster != null : "Update monster failed, no monster with id " + id;
		if(monster == null)
		{
			Logger.log("Update monster failed, no monster with id " + id);
			return;
		}
		monster.updateByDirection(x, y, directionX, directionY, assetIndex);
	}
	
	public void removeMonster(int id) {
		//assert Monsters.get(id) != null : "Remove monster failed, no monster with id" + id;
		if(Monsters.get(id) == null)
		{
			Logger.log("Remove monster failed, no monster with id" + id);
			return;
		}
		Monsters.remove(id);
	}

	
	
	/**************************************
	 *              Projector             *
	 **************************************/

	public void addProjector(int id, Projector projector) {
		//assert Projectors.get(id) == null : "Add Projector failed, already have projector with id " + id;
		if(Projectors.get(id) != null )
		{
			Logger.log("Add Projector failed, already have projector with id " + id);
			return;
		}
		Projectors.put(id, projector);
	}
	public void addProjector(int id) {
		//assert Projectors.get(id) == null : "Add Projector failed, already have projector with id " + id;
		if(Projectors.get(id) != null )
		{
			Logger.log("Add Projector failed, already have projector with id " + id);
			return;
		}
		Projector projector = new Projector();
		Projectors.put(id, projector);
	}
	public void addProjector(int id, int x, int y, int direction, int assetIndex, int frame) {
		//assert Projectors.get(id) == null : "Add Projector failed, already have projector with id " + id;
		if(Projectors.get(id) != null )
		{
			Logger.log("Add Projector failed, already have projector with id " + id);
			return;
		}
		Projector projector = new Projector(x, y, direction, assetIndex, frame);
		Projectors.put(id, projector);
	}
	public void addProjector(int id, int x, int y, int direction, int assetIndex) {
		//assert Projectors.get(id) == null : "Add Projector failed, already have projector with id " + id;
		if(Projectors.get(id) != null )
		{
			Logger.log("Add Projector failed, already have projector with id " + id);
			return;
		}
		Projector projector = new Projector(x, y, direction, assetIndex);
		Projectors.put(id, projector);
	}

	public void updateProjector(int id, Projector projector) {
		//assert Projectors.get(id) != null : "Update projector failed, no projector with id " + id;
		if(Projectors.get(id) == null)
		{
			Logger.log("Update projector failed, no projector with id " + id);
			return;
		}
		Projectors.put(id, projector);
	}
	public void updateProjector(int id, int x, int y, int direction, int assetIndex) {
		Projector projector = Projectors.get(id);
		//assert projector != null : "Update projector failed, no projector with id " + id;
		if(projector == null)
		{
			Logger.log("Update projector failed, no projector with id " + id);
			return;
		}
		projector.update(x, y, direction, assetIndex);
	}
	public void updateProjector(int id, int x, int y, int directionX, int directionY, int assetIndex) {
		Projector projector = Projectors.get(id);
		//assert projector != null : "Update projector failed, no projector with id " + id;
		if(projector == null)
		{
			Logger.log("Update projector failed, no projector with id " + id);
			return;
		}
		projector.updateByDirection(x, y, directionX, directionY, assetIndex);
	}

	public void removeProjector(int id) {
		//assert Projectors.get(id) != null : "Remove projector failed, no projector with id " + id;
		if(Projectors.get(id) == null )
		{
			Logger.log("Remove projector failed, no projector with id " + id);
			return;
		}
		Projectors.remove(id);
	}

	
	
	/**************************************
	 *                Item                *
	 **************************************/

	public void addItem(int id, Item item) {
		//assert Items.get(id) == null : "Add Item failed, already have projector with id " + id;
		if(Items.get(id) != null)
		{
			Logger.log("Add Item failed, already have projector with id " + id);
			return;
		}
		Items.put(id, item);
	}
	public void addItem(int id) {
		//assert Items.get(id) == null : "Add Item failed, already have projector with id " + id;
		if(Items.get(id) != null)
		{
			Logger.log("Add Item failed, already have projector with id " + id);
			return;
		}
		Item item = new Item();
		Items.put(id, item);
	}
	public void addItem(int id, int x, int y, int direction, int assetIndex, int frame) {
		assert Items.get(id) == null : "Add Item failed, already have projector with id " + id;
		Item item = new Item(x, y, direction, assetIndex, frame);
		Items.put(id, item);
	}
	public void addItem(int id, int x, int y, int direction, int assetIndex) {
		//assert Items.get(id) == null : "Add Item failed, already have projector with id " + id;
		if(Items.get(id) != null)
		{
			Logger.log("Add Item failed, already have projector with id " + id);
			return;
		}
		Item item = new Item(x, y, direction, assetIndex);
		Items.put(id, item);
	}

	public void updateItem(int id, Item item) {
		//assert Items.get(id) != null : "Update item failed, no item with id " + id;
		if(Items.get(id) == null)
		{
			Logger.log("Update item failed, no item with id " + id);
			return;
		}
		Items.put(id, item);
	}
	public void updateItem(int id, int x, int y, int direction, int assetIndex) {
		Item item = Items.get(id);
		//assert item != null : "Update item failed, no item with id " + id;
		if(item == null)
		{
			Logger.log("Update item failed, no item with id " + id);
			return;
		}
		item.update(x, y, direction, assetIndex);
	}
	public void updateItem(int id, int x, int y, int assetIndex) {
		Item item = Items.get(id);
		//assert item != null : "Update item failed, no item with id " + id;
		if(item == null)
		{
			Logger.log("Update item failed, no item with id " + id);
			return;
		}
		item.updateWithoutDirection(x, y, assetIndex);
	}

	public void removeItem(int id) {
		//assert Items.get(id) != null : "Remove item failed, mo item with id " + id;
		if(Items.get(id) == null )
		{
			Logger.log("Remove item failed, mo item with id " + id);
			return;
		}
		Items.remove(id);
	}
}
