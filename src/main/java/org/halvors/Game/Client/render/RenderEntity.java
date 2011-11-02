package org.halvors.Game.Client.render;

import org.halvors.Game.Client.Game;
import org.halvors.Game.Client.Location;
import org.halvors.Game.Client.entity.Entity;
import org.lwjgl.opengl.GL11;

public class RenderEntity extends Render {
	public RenderEntity(Game client) {
		super(client);
	}
	
	@Override
	public void render(Entity entity) {
		Location loc = entity.getLocation();
		
		float x = Float.parseFloat(Double.toString(loc.getX()));
		float y = Float.parseFloat(Double.toString(loc.getY()));
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + 50, y);
		GL11.glVertex2f(x + 50, y + 50);
		GL11.glVertex2f(x, y + 50);
		GL11.glEnd();
		
		// TODO: Render entity here.
    }
}
