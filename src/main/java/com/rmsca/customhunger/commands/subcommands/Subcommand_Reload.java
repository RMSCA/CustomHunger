package com.rmsca.customhunger.commands.subcommands;

import com.rmsca.customhunger.commands.Subcommand;
import com.rmsca.customhunger.listeners.PlayerConsumeListener;
import static com.rmsca.customhunger.utils.CustomHungerUtils.*;
import org.bukkit.entity.Player;

public class Subcommand_Reload extends Subcommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getUsage() {
        return "Usage: /ch reload";
    }

    @Override
    public void execute(Player player, String[] args) {
        sendMessage(player, "Reloading config!");
        plugin.reloadConfig();
        PlayerConsumeListener.setFoodValueMap();
        sendMessage(player, "Reload complete!");
    }
}
