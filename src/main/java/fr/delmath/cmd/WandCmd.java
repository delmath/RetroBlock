package fr.delmath.cmd;

import fr.delmath.utils.Wand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WandCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        // Check if it's a player or the server
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // Add command options to the config
            if (args.length == 0) {
                // Check if the player has the required authority
                if (player.hasPermission("retroblock.permission.wand") || player.isOp()) {
                    if (Wand.removeWand(player)) player.sendMessage("§aYour wand has been successfully removed.");
                    else {
                        Wand.giveWand(player);
                    }
                } else player.sendMessage("§cYou don't have permission to execute this command!");
            } else player.sendMessage("§cCommand usage : §a/wand");
        } else System.out.print("§cYou cannot execute this command from the console.");
        return true;
    }

}
