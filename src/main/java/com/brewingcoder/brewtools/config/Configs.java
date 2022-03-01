package com.brewingcoder.brewtools.config;


import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Paths;

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
