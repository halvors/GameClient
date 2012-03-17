package org.halvors.Game.Client.render;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.entity.Entity;
import org.lwjgl.opengl.GL11;

public class EntityRender extends Render {
	public EntityRender(Game client) {
		super(client);
	}
	
	@Override
	public void render(Entity entity) {
		Location loc = entity.getLocation();
		
		float x = loc.getX();
		float z = loc.getZ();
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, z);
		GL11.glVertex2f(x + 50, z);
		GL11.glVertex2f(x + 50, z + 50);
		GL11.glVertex2f(x, z + 50);
		GL11.glEnd();
    }
}
