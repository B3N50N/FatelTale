package Entity;
public class Player 
{
	private int health,direction,attack,attack_speed;
	private int move_speed,defense;
	private int x,y;
	//private collider;
	static final int west=0,north=1,east=2,south=3;
	public String dirvaluetoString(int dir)
	{
		if(dir==west) return "west";
		else if(dir==north) return "north";
		else if(dir==south) return "east";
		else return "south";
	}
	public boolean isDead()
	{
		if(health<=0)
			return true;
		else
			return false;
	}
	public void changeHealth(int dif)
	{
		if(health+dif<0)
			health=0;
		else
			health+=dif;
	}
	public void chnageDirection(int newDirection)
	{
		assert newDirection>=west && newDirection<=south:"The new direction is invalid";
		direction=newDirection;
	}
	public void changemove_speed(int dif)
	{
		if(move_speed+dif<0)
			move_speed=0;
		else
			move_speed+=dif;
	}
	public void changeDefense(int dif)
	{
		if(defense+dif<0)
			defense=0;
		else
			defense+=dif;
	}
	public void changeAttack(int dif)
	{
		if(attack+dif<0)
			attack=0;
		else
			attack+=dif;
	}
	public void changeAttackSpeed(int dif)
	{
		if(attack_speed+dif<0)
			attack_speed=0;
		else
			attack_speed+=dif;
	}
	public void changePos(int newx,int newy)
	{
		assert newx>=0&&newy>=0:"The position is invalid";
		x=newx;
		y=newy;
	}
	public String toString()
	{
		String str="";
		str+=String.valueOf(health);
		str+="\n";
		str+=dirvaluetoString(direction);
		str+="\n";
		str+=String.valueOf(attack);
		str+="\n";
		str+=String.valueOf(attack_speed);
		str+="\n";
		str+=String.valueOf(move_speed);
		str+="\n";
		str+=String.valueOf(defense);
		str+="\n";
		str+="x: "+String.valueOf(x);
		str+="\n";
		str+="y: "+String.valueOf(y);
		str+="\n";
		return str;
	}
}
