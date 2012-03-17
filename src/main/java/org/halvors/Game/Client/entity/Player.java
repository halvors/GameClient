package org.halvors.Game.Client.entity;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;

public class Player extends LivingEntity {
	private final String name;
	
	public Player(Game client, String name, Location loc) {
		super(client, loc);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}