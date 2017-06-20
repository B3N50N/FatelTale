package dom;

public class PlayerInfo {
	private int clientno;
	private int health, maxHealth;
	private int score;
	
	public PlayerInfo() {}
	public PlayerInfo(int clientno) {
		this.clientno = clientno;
		score = 0;
	}
	public PlayerInfo(int clientno, int maxHealth) {
		this.clientno = clientno;
		health = maxHealth;
		this.maxHealth = maxHealth;
		score = 0;
	}
	public PlayerInfo(int clientno, int health, int maxHealth, int score) {
		this.clientno = clientno;
		this.health = health;
		this.maxHealth = maxHealth;
		this.score = score;
	}
	
	public int getClientNo() {
		return clientno;
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
	
	public void updateClientNo(int clientno) {
		this.clientno = clientno;
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
