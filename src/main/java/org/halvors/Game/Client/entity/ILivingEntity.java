package org.halvors.Game.Client.entity;

public interface ILivingEntity extends IEntity {
	public int getHealth();

	public void setHealth(int health);
	
	public void heal(int amount);
	
	public void damage(int damage);
	
	public void die();
	
	public void move();
}
