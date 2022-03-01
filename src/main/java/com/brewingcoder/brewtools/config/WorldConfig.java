package com.brewingcoder.brewtools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WorldConfig {

    public final ForgeConfigSpec.BooleanValue doAbyssal;
    public final ForgeConfigSpec.IntValue AbyssalMinY;
    public final ForgeConfigSpec.IntValue AbyssalMaxY;
    public final ForgeConfigSpec.IntValue AbyssalNumPerChunk;
    public final ForgeConfigSpec.IntValue AbyssalClusterSize;


    public final ForgeConfigSpec.BooleanValue doQuarried;
    public final ForgeConfigSpec.IntValue QuarriedMinY;
    public final ForgeConfigSpec.IntValue QuarriedMaxY;
    public final ForgeConfigSpec.IntValue QuarriedNumPerChunk;
    public final ForgeConfigSpec.IntValue QuarriedClusterSize;


    public WorldConfig(ForgeConfigSpec.Builder builder){

        builder.push("World_Generation");

        builder.push("Decoration_Abyssal");
        this.doAbyssal = builder.define("abyssalGeneration",true);
        this.AbyssalMinY = builder.defineInRange("abyssalMinY",24,1,50);
        this.AbyssalMaxY = builder.defineInRange("abyssalMaxY",75,1,255);
        this.AbyssalNumPerChunk = builder.defineInRange("abyssalNumPerChunk",6,1,64);
        this.AbyssalClusterSize = builder.defineInRange("abyssalClusterSize",35,1,100);


        builder.pop();
        builder.push("Decoration_Quarried");
        this.doQuarried = builder.define("quarriedGeneration",true);
        this.QuarriedMinY = builder.defineInRange("quarriedMinY",24,1,50);
        this.QuarriedMaxY = builder.defineInRange("quarriedMaxY",75,1,255);
        this.QuarriedNumPerChunk = builder.defineInRange("quarriedNumPerChunk",6,1,64);
        this.QuarriedClusterSize =builder.defineInRange("quarriedClusterSize",35,1,100);




        builder.pop();
    }
}
