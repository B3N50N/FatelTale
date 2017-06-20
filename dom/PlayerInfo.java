package dom;

public class PlayerInfo {
	int clientno;
	int health, maxHealth;
	int score;
	
	private PlayerInfo() {}
	private PlayerInfo(int clientno) {
		this.clientno = clientno;
		score = 0;
	}
	private PlayerInfo(int clientno, int maxHealth) {
		this.clientno = clientno;
		health = maxHealth;
		this.maxHealth = maxHealth;
		score = 0;
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
