package org.halvors.Game.Client.render;

import java.util.HashMap;

import org.halvors.Game.Client.Game;

public class TextureManager {
	private final Game client;
	private final HashMap<String, Integer> textures = new HashMap<String, Integer>();
	
	public TextureManager(Game client) {
		this.client = client;
	}

	public Game getClient() {
		return client;
	}
	
	public HashMap<String, Integer> getTextures() {
		return textures;
	}
	
	public int getTexture(String key) {
		return textures.get(key);
	}
	
	public void addTexture(String key, int id) {
		if (!textures.containsKey(key)) {
			textures.put(key, id);
		}
	}
	
	public void removeTexture(String key) {
		if (textures.containsKey(key)) {
			textures.remove(key);
		}
	}
}
