package com.rmsca.customhunger.utils;

import com.rmsca.customhunger.CustomHunger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.logging.Logger;

public final class CustomHungerUtils {
    public static final Plugin plugin = CustomHunger.getPlugin(CustomHunger.class);
    public static final Logger logger = plugin.getLogger();
    private CustomHungerUtils() {}

    /**
     * Check if a String can be converted to Integer
     *
     * @param str the String to be checked
     * @return return true if the string only contains numbers
     * @since 1.0.0
     */
    public static boolean isInteger(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Send a message to the player if possible, otherwise send the message to console
     *
     * @param player the player who will receive the message
     * @param msg the message that will be sent
     * @since 1.0.0
     */
    public static void sendMessage(@Nullable Player player, String msg) {
        if (player == null) {
            Bukkit.getConsoleSender().sendMessage(msg);
        } else {
            player.sendMessage(msg);
        }
    }
}
