package fr.delmath;

import fr.delmath.cmd.BackCmd;
import fr.delmath.cmd.WandCmd;
import fr.delmath.listener.BlockListener;
import fr.delmath.listener.PlayerListener;
import fr.delmath.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import fr.delmath.utils.area;

public final class RetroBlock extends JavaPlugin {

    private static RetroBlock instance; // Instance du plugin
    private List<area> areas = new ArrayList<>(); // liste des area selected par les joueurs
    private FileManager files = new FileManager("config.yml", "BlockData.json");

    @Override
    public void onEnable() {
        instance = this;

        this.getCommand("wand").setExecutor(new WandCmd());
        this.getCommand("back").setExecutor(new BackCmd());

        getServer().getPluginManager().registerEvents(new PlayerListener(), instance);
        getServer().getPluginManager().registerEvents(new BlockListener(), instance);

        files.createConfigFile();
        files.createBlockDataFile();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RetroBlock getInstance() {
        return instance;
    }

    public List<area> getAreas() {
        return areas;
    }

    public void setAreas(List<area> areas) {
        this.areas = areas;
    }

    public FileManager getFiles() {
        return files;
    }

    public void setFiles(FileManager files) {
        this.files = files;
    }
}
