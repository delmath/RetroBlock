package fr.delmath.utils;

import org.bukkit.entity.Player;

public class print {

    public static void send_to_console(String str) {
        System.out.print(str);
    }

    public static void send_to_console_ln(String str) {
        System.out.println(str);
    }

    public static void send_to_player(Player player, String str) {
        player.sendMessage(str);
    }

}
