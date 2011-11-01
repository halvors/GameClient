package org.halvors.Game.Client.render;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.entity.Entity;
import org.lwjgl.opengl.GL11;

public class RenderEntity extends Render {
	private final Game client;
	
	public RenderEntity(Game client) {
		this.client = client;
	}
	
	@Override
	public void render(Entity entity) { // double d, double d1, double d2, float f, float f1
		Location loc = entity.getLocation();
		
		GL11.glPushMatrix();
//		renderOffsetAABB(0, loc.getX(), loc.getY(), loc.getZ());
		GL11.glPopMatrix();
		
//        GL11.glPushMatrix();
//        renderOffsetAABB(entity.boundingBox, d - entity.lastTickPosX, d1 - entity.lastTickPosY, d2 - entity.lastTickPosZ);
//        GL11.glPopMatrix();
    }
	
	public Game getClient() {
		return client;
	}
}
