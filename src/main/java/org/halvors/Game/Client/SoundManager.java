package org.halvors.Game.Client;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;

public class SoundManager {
	private final Game client;
	
	private final HashMap<String, File> sounds = new HashMap<String, File>();
	
	public SoundManager(Game client) {
		this.client = client;
		
		// TODO: Add sounds here.
	}
	
	public void initialize(int channels) {
		try {
			AL.create();
		} catch (LWJGLException e) {
			client.log(Level.WARNING, "Sound disabled.");
			e.printStackTrace();
		}
		
//		// allocate sources
//			scratchBuffer.limit(channels);
//			AL10.alGenSources(scratchBuffer);
//			scratchBuffer.rewind();
//			scratchBuffer.get(sources = new int[channels]);
//		
//		// could we allocate all channels?
//		if(AL10.alGetError() != AL10.AL_NO_ERROR) {
//		throw new LWJGLException("Unable to allocate " + channels + " sources");
//		}
//		
//		// we have sound
//		soundOutput = true;
//		} catch (LWJGLException le) {
//		le.printStackTrace();
//		System.out.println("Sound disabled");
	}
	
	public void playSound(String key) {
		// TODO: Play sound here.
	}
	
	public File getSound(String key) {
		return sounds.get(key);
	}
	
	public void addSound(String key, File file) {
		if (!sounds.containsKey(key)) {
			sounds.put(key, file);
		}
	}
	
	public void removeSound(String key) {
		if (sounds.containsKey(key)) {
			sounds.remove(key);
		}
	}
	
	public HashMap<String, File> getSounds() {
		return sounds;
	}
	
	public Game getClient() {
		return client;
	}
}
