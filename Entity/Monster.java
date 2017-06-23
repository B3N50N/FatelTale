package entity;
public class Monster {
	
	private int _max_health;
	private int _health;
	private float _x, _y;
	private int _asset_index;
	
	public Monster(int h, float x, float y, int index) {
		_health = _max_health = h;
		_x = x;
		_y = y;
		_asset_index = index;
	}
	
	public void changeHealth(int delta) {
		_health += delta;
		if ( _health < 0 ) _health = 0;
		if ( _health > _max_health ) _health = _max_health;
	}
	
	public void changePosition(float x, float y) {
		/*
		if ( SDM.getInstance().isWalkable(x, y) ) {
			_x = x;
			_y = y;
		}
		*/
	}
	public String toString()
	{
		String str="";
		str+="Monster ";
		str+=String.valueOf(_x);
		str+=" ";
		str+=String.valueOf(_y);
		str+=" ";
		str+=String.valueOf(_asset_index);
		return str;
	}
}