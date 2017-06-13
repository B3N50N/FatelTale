package Entity;
public class Player 
{
	private int health,attack,direction,defense;
	private float attack_speed,move_speed;
	private float x,y;
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
	public void changemove_speed(float dif)
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
	public void changeAttackSpeed(float dif)
	{
		if(attack_speed+dif<0)
			attack_speed=0;
		else
			attack_speed+=dif;
	}
	public void changePos(float newx,float newy)
	{
		assert newx>=0&&newy>=0:"The position is invalid";
		x=newx;
		y=newy;
	}
	public String toString()
	{
		String str="";
		str+=String.valueOf(health);
		str+=" ";
		str+=dirvaluetoString(direction);
		str+=" ";
		str+=String.valueOf(attack);
		str+=" ";
		str+=String.valueOf(attack_speed);
		str+=" ";
		str+=String.valueOf(move_speed);
		str+=" ";
		str+=String.valueOf(defense);
		str+=" ";
		str+=String.valueOf(x);
		str+=" ";
		str+=String.valueOf(y);
		str+=" ";
		return str;
	}
	public static void main(String[] args)
	{
		
	}
}
