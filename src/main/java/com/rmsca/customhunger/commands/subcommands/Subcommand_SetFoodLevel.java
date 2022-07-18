package com.rmsca.customhunger.commands.subcommands;

import com.rmsca.customhunger.CustomHunger;
import com.rmsca.customhunger.commands.Subcommand;
import static com.rmsca.customhunger.utils.CustomHungerUtils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Subcommand_SetFoodLevel extends Subcommand implements TabCompleter {
    @Override
    public String getName() {
        return "setFoodLevel";
    }

    @Override
    public String getUsage() {
        return "Usage: /setFoodLevel <Player> <Food Level>";
    }

    @Override
    public void execute(Player player, String[] args) {
        switch (args.length) {
            // /ch setFoodLevel <Food Level>
            case 2:
                if (isInteger(args[1])) {
                    // Check if the command was executed by player or console
                    if (player == null) {
                        Bukkit.getConsoleSender().sendMessage(getUsage());
                    } else {
                        int foodLevel = Integer.parseInt(args[1]);
                        player.setFoodLevel(foodLevel);
                        plugin.getLogger().info(player.getDisplayName()
                                + "'s food level is set to "
                                + foodLevel);
                        sendMessage(player, "Your food level is set to " + foodLevel);
                    }
                } else {
                    sendMessage(player, getUsage());
                }
                break;
            // /ch setFoodLevel <player> <FoodLevel>
            case 3:
                if (isInteger(args[2])) {
                    try {
                        int foodLevel = Integer.parseInt(args[2]);
                        Player targetPlayer = Bukkit.getPlayerExact(args[1]);
                        targetPlayer.setFoodLevel(foodLevel);
                        plugin.getLogger().info(player.getDisplayName()
                                + "'s food level is set to "
                                + foodLevel);
                        sendMessage(targetPlayer, "Your food level is set to " + foodLevel);
                        sendMessage(player, targetPlayer.getDisplayName()
                                + "'s food level is set to "
                                + foodLevel);
                    } catch (NullPointerException e) {
                        sendMessage(player, "The player does not exist");
                        sendMessage(player, getUsage());
                    }
                } else {
                    sendMessage(player, getUsage());
                }
                break;
            default:
                sendMessage(player, getUsage());
                break;
        }
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        List<String> suggestionList = new ArrayList<>();
        Player[] players = new Player[Bukkit.getOnlinePlayers().size()];
        Bukkit.getOnlinePlayers().toArray(players);
        for (Player player : players) {
            suggestionList.add(player.getName());
        }
        return suggestionList;
    }
}
