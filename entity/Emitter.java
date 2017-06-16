package entity;

public abstract class Emitter {
	
	protected Long _attack_speed;
	protected int _dir_x, _dir_y;
	protected Projector _ori_project;
	
	public Emitter(Long as, int dx, int dy, Projector op) {
		_attack_speed = as;
		_dir_x = dx;
		_dir_y = dy;
		_ori_project = op;
	}
	
	
}
