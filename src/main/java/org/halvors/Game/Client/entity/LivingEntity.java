package org.halvors.Game.Client.entity;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;

public class LivingEntity extends Entity {
	private int health;
	
	public LivingEntity(Game client, Location loc) {
		super(client, loc);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health <= 20) {
			this.health = health;
		}
	}
	
	public void heal(int amount) {
        if (health > 0 && health + amount <= 20) {
            health += amount;
        }
    }
	
	public void damage(int damage) {
		if (damage <= 20 && health - damage <= 20) {
			setHealth(damage);
		}
	}
	
	public void die() {
		setHealth(0);
		remove();
	}
	
	public void move() {
		// TODO: Implement this.
    }
}