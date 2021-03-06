package com.brewingcoder.brewtools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WorldConfig {


    public final ForgeConfigSpec.BooleanValue DoAbyssal;
    public final ForgeConfigSpec.BooleanValue DoQuarried;
    public final ForgeConfigSpec.IntValue AbyssalMinY;
    public final ForgeConfigSpec.IntValue AbyssalMaxY;
    public final ForgeConfigSpec.IntValue AbyssalNumPerChunk;
    public final ForgeConfigSpec.IntValue AbyssalClusterSize;
    public final ForgeConfigSpec.IntValue QuarriedMinY;
    public final ForgeConfigSpec.IntValue QuarriedMaxY;
    public final ForgeConfigSpec.IntValue QuarriedNumPerChunk;
    public final ForgeConfigSpec.IntValue QuarriedClusterSize;

    public WorldConfig(ForgeConfigSpec.Builder builder){
        builder.push("WorldGen");
        builder.comment("World generation settings are only used if ABYSSAL and QUARRIED stone and variants are enabled in the common configuration file.");
        builder.push("AbyssalStone");
        this.DoAbyssal = builder.define("doWorldGen",true);
        this.AbyssalMinY = builder.defineInRange("abyssalMinY",2,0,256);
        this.AbyssalMaxY = builder.defineInRange("abyssalMaxY",200,0,256);
        this.AbyssalNumPerChunk = builder.comment("number of times per chunk to attempt generation.").defineInRange("oreChances",8,0,256);
        this.AbyssalClusterSize = builder.comment("Maximum size of cluster").defineInRange("abyssalClusterSize",64,1,256);

        builder.pop();
        builder.push("QuarriedStone");
        this.DoQuarried=builder.define("doWorldGen",true);
        this.QuarriedMinY = builder.defineInRange("quarriedMinY",2,0,256);
        this.QuarriedMaxY = builder.defineInRange("quarriedMaxY",70,0,256);
        this.QuarriedNumPerChunk = builder.defineInRange("quarriedNumPerChunk",8,0,256);
        this.QuarriedClusterSize =builder.defineInRange("quarriedClusterSize",64,1,256);

        builder.pop(2);
    }
}
