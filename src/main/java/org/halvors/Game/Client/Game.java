package org.halvors.Game.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.halvors.Game.Client.network.NetworkManager;
import org.halvors.Game.Client.render.TextureManager;
import org.halvors.Game.Client.world.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private static Game instance;
	
	private final String name = "Game";
	private final String version = "0.0.3";
	
	private final Logger logger = Logger.getLogger("Game");
	private final TextureManager textureManager;
	private final NetworkManager networkManager;
	
	private final List<World> worlds = new ArrayList<World>();
	
	private int width = 800;
	private int height = 600;

	public Game() {
		Game.instance = this;
		
		this.textureManager = new TextureManager(this);
		this.networkManager = new NetworkManager(this);
	}
	
	public void init() {
		log(Level.INFO, "Initializing " + name + "...");
		
		try {
			// Initialize Display.
			Display.create();
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(name + " " + version);
			
			// Initialize Keyboard and Mouse.
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		// Initialize OpenGL.
		GL11.glMatrixMode(GL11.GL_4D_COLOR_TEXTURE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		while (!Display.isCloseRequested()) {
			// TODO: Render OpenGL here
			loadScreen();
			
			onTick();
			
			// Update display.
			Display.update();
			
			// Fps limit to 60.
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private void onTick() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				switch (Keyboard.getEventKey()) {
					case Keyboard.KEY_W:
					case Keyboard.KEY_A:
					case Keyboard.KEY_S:
					case Keyboard.KEY_D:
				}
			} else {
				switch (Keyboard.getEventKey()) {
					
				}
			}
		}
	}
	
	private void loadScreen() {
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5F, 0.5F, 1.0F);
	}

	public static Game getInstance() {
		return instance;
	}
	
	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public void log(Level level, String message) {
		logger.log(level, message);
	}
	
	public List<World> getWorlds() {
		return worlds;
	}
	
	public World getWorld(UUID id) {
		for (World world : worlds) {
			if (id.equals(world.getId())) {
				return world;
			}
		}
		
		return null;
	}
	
	public World getWorld(String name) {
		for (World world : worlds) {
			if (name.equals(world.getName())) {
				return world;
			}
		}
		
		return null;
	}
	
	public boolean hasWorld(World world) {
		return worlds.contains(world);
	}
	
	public World addWorld(World world) {
		if (world != null && !worlds.contains(world)) {
			worlds.add(world);
			
			return world;
		}
		
		return null;
	}
	
	public World addWorld(String name) {
		return addWorld(new World(name));
	}
	
	public void removeWorld(UUID id) {
		World world = getWorld(id);
		
		if (world != null) {
			worlds.remove(world);
		}
	}
	
	public void removeWorld(String name) {
		World world = getWorld(name);
		
		if (world != null) {
			worlds.remove(world);
		}
	}
	
	public TextureManager getTextureManager() {
		return textureManager;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}