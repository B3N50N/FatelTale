package SceneDataModule;

class Block {
	
	private boolean _walkable;
	private int _asset_index;

	public Block(boolean b, int index) {
		_walkable = b;
		_asset_index = index;
	}
	
	public boolean isWalkable() {
		return _walkable;
	}
	
	public int getAssetIndex() {
		return _asset_index;
	}
}
