package com.rmsca.customhunger.listeners;

import static com.rmsca.customhunger.utils.CustomHungerUtils.*;
import com.rmsca.customhunger.utils.DefaultFoodValue;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class PlayerConsumeListener implements Listener {
    private static Map<Material, Integer> foodValueMap;

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        Material foodConsumed = event.getItem().getType();
        Integer consumedFoodValue = getFoodValue(foodConsumed);
        Integer defaultConsumedFoodValue = null;
        Inventory inventory = player.getInventory();
        if (isPotion(foodConsumed)) {
            return;
        }
        for (DefaultFoodValue food : DefaultFoodValue.values()) {
            if (foodConsumed.toString().equalsIgnoreCase(food.toString())) {
                defaultConsumedFoodValue = food.getDefaultFoodValue();
                break;
            }
        }
        if (defaultConsumedFoodValue == null) {
            logger.warning(foodConsumed + " is not found, the event is cancelled (DefaultFoodValue#getDefaultFoodValue)");
            player.sendMessage(foodConsumed + " is not found, please report this to an admin");
            event.setCancelled(true);
            if (inventory.firstEmpty() == -1) {
                player.getWorld().dropItem(player.getLocation(), event.getItem());
            } else {
                inventory.addItem(event.getItem());
            }
            return;
        }
        if (consumedFoodValue != null) {
            if (player.getFoodLevel() > (20 - consumedFoodValue)) {
                player.setFoodLevel(20);
            } else {
                player.setFoodLevel(player.getFoodLevel() + consumedFoodValue - defaultConsumedFoodValue);
            }
        } else {
            logger.warning(foodConsumed + "is not found (PlayerConsumeListener#getFoodValue)");
            player.sendMessage(foodConsumed + " is not found, please report this to an admin");
        }
    }

    private static Map<Material, Integer> getFoodValueMap() {
        Map<Material, Integer> foodValueMap = new HashMap<>();
        for (Material material : Material.values()) {
            if (material.isEdible()) {
                Integer configValue = plugin.getConfig().getInt(material.toString().toLowerCase());
                foodValueMap.put(material, configValue);
            }
        }
        return foodValueMap;
    }

    public static void setFoodValueMap() {
        foodValueMap = getFoodValueMap();
    }

    private static Integer getFoodValue(Material material) {
        Integer foodValue = null;
        if (foodValueMap == null) {
            setFoodValueMap();
        }
        if (foodValueMap.containsKey(material)) {
            foodValue = foodValueMap.get(material);
        } else {
            logger.warning(material.toString() + " is not found!");
        }
        return foodValue;
    }

    private static boolean isPotion(Material material) {
        return material.equals(Material.POTION);
    }
}
