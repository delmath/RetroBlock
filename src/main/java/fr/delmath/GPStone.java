package fr.delmath;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.delmath.utils.print;

import java.io.File;

public final class GPStone extends JavaPlugin {

    private FileConfiguration config;
    private File configFile;
    private static GPStone instance; // Instance du plugin

    @Override
    public void onEnable() {
        instance = this;
        print.send_to_console_ln("-----------------[ GPStone - Start ]-----------------");
        print.send_to_console_ln("Config check : ");
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {

            print.send_to_console("/!\\ Config Error /!\\");
            print.send_to_console_ln("try to fix");
            try {
                if (!configFile.getParentFile().exists()) {
                    configFile.getParentFile().mkdirs();
                }
                saveDefaultConfig();
                print.send_to_console_ln("config created !");
                print.send_to_console_ln("-----------------[ GPStone - OK ]-----------------");
            } catch (Exception e) {
                print.send_to_console_ln("Failed to make config!");
                print.send_to_console_ln("-----------------[ GPStone - Error ]-----------------");
                e.printStackTrace();
                PluginManager pluginManager = getServer().getPluginManager();
                pluginManager.disablePlugin(instance);
                return;
            }
        }
        if (!config_validation()) {
            print.send_to_console("/!\\ Config Error /!\\");
            print.send_to_console_ln("try to fix");
            try {
                if (!configFile.getParentFile().exists()) {
                    configFile.getParentFile().mkdirs();
                }
                saveDefaultConfig();
                print.send_to_console_ln("config created !");
                print.send_to_console_ln("-----------------[ GPStone - OK ]-----------------");
            } catch (Exception e) {
                print.send_to_console_ln("Failed to make config!");
                print.send_to_console_ln("-----------------[ GPStone - Error ]-----------------");
                e.printStackTrace();
                PluginManager pluginManager = getServer().getPluginManager();
                pluginManager.disablePlugin(instance);
                return;
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GPStone getInstance() {
        return instance;
    }

    public static boolean config_validation() {
        FileConfiguration config = GPStone.getInstance().getConfig();

        if (!config.isString("api-token")) {
            GPStone.getInstance().saveResource("config.yml", true); // Recréer le fichier de configuration
            return false;
        }

        if (!config.isInt("parametres.cooldown-temps")) {
            GPStone.getInstance().saveResource("config.yml", true);
            return false;
        }

        if (!config.isInt("parametres.limite-schemas")) {
            GPStone.getInstance().saveResource("config.yml", true);
            return false;
        }

        return true;
    }
}
