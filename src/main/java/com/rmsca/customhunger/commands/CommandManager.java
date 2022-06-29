package com.rmsca.customhunger.commands;

import com.rmsca.customhunger.commands.subcommands.Subcommand_Help;
import com.rmsca.customhunger.commands.subcommands.Subcommand_SetFoodLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    public static List<Subcommand> subcommandList;

    public CommandManager() {
        subcommandList = new ArrayList<>();
        subcommandList.add(new Subcommand_SetFoodLevel());
        subcommandList.add(new Subcommand_Help());
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        if(args.length > 0) {
            for(Subcommand subcommand : subcommandList) {
                if(args[0].equals(subcommand.getName())) {
                    subcommand.execute(player, args);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        if(args.length == 1) {
            List<String> suggestionList = new ArrayList<>();
            for(Subcommand subcommand : subcommandList) {
                suggestionList.add(subcommand.getName());
            }
            return suggestionList;
        }
        return null;
    }
}
