package com.rmsca.customhunger;

import com.rmsca.customhunger.commands.CommandManager;
import com.rmsca.customhunger.listeners.PlayerConsumeListener;
import static com.rmsca.customhunger.utils.CustomHungerUtils.logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomHunger extends JavaPlugin {
    private final String VERSION = getDescription().getVersion();

    @Override
    public void onEnable() {
        // Register command
        getCommand("ch").setExecutor(new CommandManager());
        // Register event
        getServer().getPluginManager().registerEvents(new PlayerConsumeListener(), this);
        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Plugin startup logic
        logger.info("Successfully enabled CustomHunger v" + VERSION + "!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Successfully disabled CustomHunger v" + VERSION + "!");
    }
}
