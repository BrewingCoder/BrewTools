package com.brewingcoder.brewtools.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

@SuppressWarnings("unused")
public class Configs {
    public static final ForgeConfigSpec WORLD_SPEC;
    public static final WorldConfig WORLD;


    public static void register() {

        Config.registerServer(WORLD_SPEC);
    }

    static <T extends IConfig> T register(final T config) {
        return config;
    }

    static {
        final Pair<WorldConfig,ForgeConfigSpec> worldGenPair = Config.get(WorldConfig::new);
        WORLD = worldGenPair.getLeft();
        WORLD_SPEC =worldGenPair.getRight();

    }
}
