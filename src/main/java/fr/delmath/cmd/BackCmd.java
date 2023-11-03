package fr.delmath.cmd;

import fr.delmath.RetroBlock;
import fr.delmath.utils.history;
import fr.delmath.utils.ModifyBlock;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;


public class BackCmd implements Listener, CommandExecutor {

    String files = RetroBlock.getInstance().getFiles().getBlockDataFile().toString();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        // Check if it's a player or the server
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            // Add command options to the config
            if (args.length == 1) {
                // Check if the player has the required authority
                if (player.hasPermission("retroblock.permission.back") || player.isOp()) {
                    boolean isValid = true;

                    for (char c : args[0].toCharArray()) {
                        if (!(Character.isDigit(c) || c == 'm' || c == 's' || c == 'h')) {
                            isValid = false;
                            break; // Sortir de la boucle dès qu'un caractère invalide est trouvé
                        }
                    }
                    if(isValid) {
                        List<ModifyBlock> blocks = history.parseModifyBlocks(files, args[0]);
                        for(ModifyBlock blc : blocks){
                            Bukkit.broadcastMessage(blc.getCoordonnee() + blc.getBlockAvant());
                        }
                    }
                    else player.sendMessage("§cCommand usage : §a/back XhYminZs ");
                } else player.sendMessage("§cYou don't have permission to execute this command!");
            } else player.sendMessage("§cCommand usage : §a/back XhYminZs");
        } else System.out.print("§cYou cannot execute this command from the console.");
        return true;
    }
}
