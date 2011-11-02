package org.halvors.Game.Client.render;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.entity.Entity;

public abstract class Render {
	private final Game client;
	
	public Render(Game client) {
		this.client = client;
	}
	
	public abstract void render(Entity entity);

	public Game getClient() {
		return client;
	}
}
