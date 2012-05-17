package org.halvors.Game.Client.world;

public class Location {
	private World world;
	private float x;
	private float y;
	
	public Location(World world, float x, float y) {
		setWorld(world);
		setX(x);
		setY(y);
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
