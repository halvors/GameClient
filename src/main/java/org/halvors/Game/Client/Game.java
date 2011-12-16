package org.halvors.Game.Client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.halvors.Game.Client.gui.MainWindow;
import org.halvors.Game.Client.network.NetworkManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game implements Runnable {
	private static Game instance;
	
	private final String name = "Game";
	private final String version = "0.0.2";
	
	private final Logger logger = Logger.getLogger("Game");
	private final TextureManager textureManager;
	private final KeyManager keyManager;
	private final SoundManager soundManager;
	private final NetworkManager networkManager;
	
	private final List<World> worlds = new ArrayList<World>();
	
	private int width = 800;
	private int height = 600;
	private int fpsLimit = 60;
	
	public Game() {
		Game.instance = this;
		
		this.textureManager = new TextureManager(this);
		this.keyManager = new KeyManager(this);
		this.soundManager = new SoundManager(this);
		this.networkManager = new NetworkManager(this);
	}
	
	public void main(String[] args) {
		MainWindow mainWindow = new MainWindow(this);
		
//		initialize();
	}
	
	private void initialize() {
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
			//loadScreen();
			
			// Update keys.
			keyManager.onUpdate();
			
			// Update display.
			Display.update();
			
			// FPS limit to 60.
			Display.sync(fpsLimit);
		}
		
		Display.destroy();
	}
	
	@Override
	public void run() {
		
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
	
	public void takeScreenshot(File file) {
		GL11.glReadBuffer(GL11.GL_FRONT);
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();
		int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
		GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		
//		File file = new File("screenshot.png"); // The file to save to.
		String format = "png"; // Example: "PNG" or "JPG"
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		 
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int i = (x + (width * y)) * bpp;
		 		int r = buffer.get(i) & 0xFF;
		 		int g = buffer.get(i + 1) & 0xFF;
		 		int b = buffer.get(i + 2) & 0xFF;
		 		image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
		 	}
		}
		
		try {
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TextureManager getTextureManager() {
		return textureManager;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}