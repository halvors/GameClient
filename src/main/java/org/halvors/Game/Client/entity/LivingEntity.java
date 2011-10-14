package main.java.org.halvors.Game.Client.entity;

import main.java.org.halvors.Game.Client.Location;

public class LivingEntity extends Entity {
	private int health;
	
	public LivingEntity(Location location) {
		super(location);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health <= 20) {
			this.health = health;
		}
	}
}
