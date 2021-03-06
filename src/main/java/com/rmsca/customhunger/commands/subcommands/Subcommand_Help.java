package com.rmsca.customhunger.commands.subcommands;

import com.rmsca.customhunger.commands.CommandManager;
import com.rmsca.customhunger.commands.Subcommand;
import static com.rmsca.customhunger.utils.CustomHungerUtils.sendMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Subcommand_Help extends Subcommand implements TabCompleter {
    List<Subcommand> subcommandList;

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "Usage: /ch help <command>";
    }

    @Override
    public void execute(Player player, String[] args) {
        subcommandList = CommandManager.subcommandList;
        switch (args.length) {
            // /ch help
            case 1:
                sendMessage(player, "Available commands:");
                for (Subcommand subcommand : subcommandList) {
                    sendMessage(player, subcommand.getName());
                }
                sendMessage(player, getUsage());
                break;
            // /ch help <command>
            case 2:
                for (Subcommand subcommand : subcommandList) {
                    if (args[1].equals(subcommand.getName())) {
                        sendMessage(player, subcommand.getUsage());
                        return;
                    }
                }
                sendMessage(player, "Command not found");
                sendMessage(player, getUsage());
                break;
            default:
                sendMessage(player, getUsage());
                break;
        }
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        List<String> suggestionList = new ArrayList<>();
        for (Subcommand subcommand : subcommandList) {
            suggestionList.add(subcommand.getName());
        }
        return suggestionList;
    }
}
