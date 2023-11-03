package fr.delmath.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private String configFile;
    private String blockDataFile;

    public FileManager(String configFileName, String blockDataFileName) {
        this.configFile = configFileName;
        this.blockDataFile = blockDataFileName;
    }

    public void createConfigFile() {
        File configFile = new File(this.configFile);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readConfigFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(this.configFile)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeConfigFile(String content) {
        try {
            Files.write(Paths.get(this.configFile), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createBlockDataFile() {
        File blockDataFile = new File(this.blockDataFile);
        if (!blockDataFile.exists()) {
            try {
                blockDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readBlockDataFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(this.blockDataFile)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeBlockDataFile(String content) {
        try {
            Files.write(Paths.get(this.blockDataFile), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getBlockDataFile() {
        return blockDataFile;
    }

    public void setBlockDataFile(String blockDataFile) {
        this.blockDataFile = blockDataFile;
    }
}
