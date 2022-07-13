package com.rmsca.customhunger.utils;

public enum DefaultFoodValue {
    APPLE(4),
    BAKED_POTATO(5),
    BEEF(3),
    BEETROOT(1),
    BEETROOT_SOUP(6),
    BREAD(5),
    CARROT(3),
    CHICKEN(2),
    CHORUS_FRUIT(4),
    COD(2),
    COOKED_BEEF(8),
    COOKED_CHICKEN(6),
    COOKED_COD(5),
    COOKED_MUTTON(6),
    COOKED_PORKCHOP(8),
    COOKED_RABBIT(5),
    COOKED_SALMON(6),
    COOKIE(2),
    DRIED_KELP(1),
    ENCHANTED_GOLDEN_APPLE(4),
    GLOW_BERRIES(2),
    GOLDEN_APPLE(4),
    GOLDEN_CARROT(6),
    HONEY_BOTTLE(6),
    MELON_SLICE(2),
    MUSHROOM_STEW(6),
    MUTTON(2),
    POISONOUS_POTATO(2),
    PORKCHOP(3),
    POTATO(1),
    PUFFERFISH(1),
    PUMPKIN_PIE(8),
    RABBIT(3),
    RABBIT_STEW(10),
    ROTTEN_FLESH(4),
    SALMON(2),
    SPIDER_EYE(2),
    SUSPICIOUS_STEW(6),
    SWEET_BERRIES(2),
    TROPICAL_FISH(1);
    private final int DEFAULT_FOOD_VALUE;

    DefaultFoodValue(int default_food_value) {
        DEFAULT_FOOD_VALUE = default_food_value;
    }

    public int getDefaultFoodValue() {
        return DEFAULT_FOOD_VALUE;
    }
}
