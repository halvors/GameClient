package org.halvors.Game.Client.render;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.entity.Entity;

public class RenderEntity extends Render {
	public RenderEntity(Game client) {
		super(client);
	}
	
	@Override
	public void render(Entity entity) { // double d, double d1, double d2, float f, float f1
		Location loc = entity.getLocation();
		
		// TODO: Render entity here.
    }
}
