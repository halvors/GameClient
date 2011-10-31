package org.halvors.Game.Client;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.halvors.Game.Client.network.NetworkManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private static Game instance;
	
	private final String name = "Game";
	private final String version = "0.0.2";
	
	private final Logger logger = Logger.getLogger("Game");
	private final KeyManager keyManager;
	
	private NetworkManager networkManager;
	private int width = 800;
	private int height = 600;
	
	public Game() throws LWJGLException {
		Game.instance = this;
		
		this.keyManager = new KeyManager(this);
	}
	
	public void main(String[] args) throws LWJGLException {
//		MainWindow mainWindow = new MainWindow(this);
		
		initialize();
	}
	
	private void initialize() throws LWJGLException {
		Display.setTitle(name + " " + version);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			
			// Initialize Keyboard and Mouse.
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		// Initialize OpenGL.
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		while (!Display.isCloseRequested()) {
			// TODO: Render OpenGL here
			loadScreen();
			
			keyManager.pollInput();
			Display.update();
			
			// FPS limit to 60.
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	private void loadScreen() throws LWJGLException {
		// Clear the screen and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5F, 0.5F, 1.0F);
		
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + 200, y);
		GL11.glVertex2f(x + 100, y + 200);
		GL11.glVertex2f(x, y + 200);
		GL11.glEnd();
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

	public void setNetworkManager(NetworkManager networkManager) {
		this.networkManager = networkManager;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
}