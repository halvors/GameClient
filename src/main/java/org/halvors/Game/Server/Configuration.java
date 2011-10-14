package main.java.org.halvors.Game.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;

public class Configuration {
	private final Server server;
    private final Properties properties = new Properties();
    private final File file;
    
    public String host;
    public int port;
    
    public Configuration(Server server, File file) {
    	this.server = server;
        this.file = file;
        
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
            } catch (Exception exception) {
                //a.log(Level.WARNING, "Failed to load " + file1, exception);
                createConfiguration();
            }
        } else {
            server.log(Level.WARNING, file + " does not exist");
            createConfiguration();
        }
        
        loadConfiguration();
    }

    public void createConfiguration() {
    	server.log(Level.INFO, "Generating new properties file");
        saveConfiguration();
    }
    
    public void loadConfiguration() {
    	host = properties.getProperty("host", "0.0.0.0");
    	port = Integer.parseInt(properties.getProperty("port", "7846"));
    }

    public void saveConfiguration() {
        try {
            properties.store(new FileOutputStream(file), "Game server properties");
        } catch (Exception e) {
            server.log(Level.WARNING, "Failed to save " + file);
            createConfiguration();
        }
    }
}