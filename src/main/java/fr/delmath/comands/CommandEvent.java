package fr.delmath.comands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.delmath.utils.print;
import fr.delmath.utils.Items;
import org.bukkit.entity.Player;
import fr.delmath.utils.generation;

public class CommandEvent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if (command.getLabel() == "wand") {
                    Items.giveItem(Material.STICK, 1, "Selection Wand");
                }
                if (command.getLabel() == "generate") {
                    Player player = (Player) commandSender;

                    if (strings.length >= 1) {
                        String prompt = String.join(" ", strings);
                        player.sendMessage("Vous avez saisi : " + prompt);
                        generation.generate(prompt);
                        return true;
                    } else {
                        player.sendMessage("Utilisation incorrecte. Utilisation : /generate <prompt>");
                        return false;
                    }
                } else
                    print.send_to_player(Bukkit.getPlayer(commandSender.getName()), "$4You can't use this command !");

            }
            return false;
        }
        return false;
    }
}
