package org.halvors.Game.Client.entity;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.world.Location;

public class LivingEntity extends Entity implements ILivingEntity {
	private int health;
	
	public LivingEntity(Game client, Location loc) {
		super(client, loc);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health <= 10) {
			this.health = health;
		}
	}
	
	public void heal(int amount) {
        if (health > 0 && health + amount <= 10) {
            health += amount;
        }
    }
	
	public void damage(int damage) {
		if (damage <= 10 && health - damage <= 10) {
			setHealth(damage);
		}
	}
	
	public void die() {
		setHealth(0);
		remove();
	}
	
	public void move() {
		
    }
}
