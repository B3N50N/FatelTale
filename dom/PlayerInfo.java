package dom;

public class PlayerInfo {
	private String name;
	private int health, maxHealth;
	private int score;
	
	public PlayerInfo() {}
	public PlayerInfo(int clientno) {
		name = "Player" + clientno;
		health = 100;
		maxHealth = 100;
		score = 0;
	}
	public PlayerInfo(int clientno, int maxHealth) {
		name = "Player" + clientno;
		health = maxHealth;
		this.maxHealth = maxHealth;
		score = 0;
	}
	public PlayerInfo(int clientno, int health, int maxHealth, int score) {
		name = "Player" + clientno;
		this.health = health;
		this.maxHealth = maxHealth;
		this.score = score;
	}
	public PlayerInfo(String name, int health, int maxHealth, int score) {
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public int getHealth() {
		return health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getScore() {
		return score;
	}
	
	public void updateName(String name) {
		this.name = name;
	}
	public void updateHealth(int health) {
		this.health = health;
	}
	public void updateMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public void updateScore(int score) {
		this.score = score;
	}
	
	public void addMaxHealth(int addHealth) {
		health += addHealth;
		maxHealth += addHealth;
	}
	public void addScore(int addScore) {
		score += addScore;
	}
}
