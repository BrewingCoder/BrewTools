package com.brewingcoder.brewtools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig{
    public final ForgeConfigSpec.BooleanValue DoWorldGen;

    public CommonConfig(ForgeConfigSpec.Builder builder){
        builder.push("Common");
        this.DoWorldGen = builder.define("Perform WorldGen",true);
        builder.pop();
    }
}
