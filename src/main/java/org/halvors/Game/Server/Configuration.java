//package main.java.org.halvors.Game.Server;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.Properties;
//import java.util.logging.Level;
//
///*
//import java.util.Properties;
//
//public class Configuration {
//	private final Properties config = new Properties();
//	
//	// Configuration start
//	public String host;
//	public int port;
//	// Configuration stop
//	
//	public Configuration() {
//		load();
//	}
//	
//	public void load() {
//		host = config.getProperty("host", "0.0.0.0");
//		port = Integer.getInteger(config.getProperty("port", "7846"));
//	}
//}
//
//*/
//
//public class Configuration {
//    private final Properties properties = new Properties();
//    private final File file;
//
//    public Configuration(File file) {
//        this.file = file;
//        
//        if (file.exists()) {
//            try {
//                properties.load(new FileInputStream(file));
//            } catch (Exception exception) {
//                //a.log(Level.WARNING, "Failed to load " + file1, exception);
//                createConfiguration();
//            }
//        } else {
//            //a.log(Level.WARNING, file1 + " does not exist");
//            createConfiguration();
//        }
//    }
//
//    public void createConfiguration() {
//    	//log(Level.INFO, "Generating new properties file");
//        savePropertiesFile();
//    }
//
//    public void savePropertiesFile() {
//        try {
//            this.properties.store(new FileOutputStream(this.c), "Minecraft server properties");
//        } catch (Exception exception) {
//            a.log(Level.WARNING, "Failed to save " + this.c, exception);
//            this.a();
//        }
//    }
//
//    public String getString(String s, String s1) {
//        if (!this.properties.containsKey(s)) {
//            s1 = this.getOverride(s, s1); // CraftBukkit
//            this.properties.setProperty(s, s1);
//            this.savePropertiesFile();
//        }
//
//        return this.getOverride(s, this.properties.getProperty(s, s1)); // CraftBukkit
//    }
//
//    public int getInt(String s, int i) {
//        try {
//            return this.getOverride(s, Integer.parseInt(this.getString(s, "" + i))); // CraftBukkit
//        } catch (Exception exception) {
//            i = this.getOverride(s, i); // CraftBukkit
//            this.properties.setProperty(s, "" + i);
//            return i;
//        }
//    }
//
//    public boolean getBoolean(String s, boolean flag) {
//        try {
//            return this.getOverride(s, Boolean.parseBoolean(this.getString(s, "" + flag))); // CraftBukkit
//        } catch (Exception exception) {
//            flag = this.getOverride(s, flag); // CraftBukkit
//            this.properties.setProperty(s, "" + flag);
//            return flag;
//        }
//    }
//
//    public void setBoolean(String s, boolean flag) {
//        properties.setProperty(s, "" + flag);
//        savePropertiesFile();
//    }
//}