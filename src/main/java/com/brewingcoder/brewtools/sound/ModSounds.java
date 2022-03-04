package com.brewingcoder.brewtools.sound;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BrewTools.MODID);

    public static final RegistryObject<SoundEvent> LASSO_WHOOSH = SOUND_EVENTS.register("lasso_whoosh",()->new SoundEvent(new ResourceLocation(BrewTools.MODID)));

    public static void register(IEventBus bus){ SOUND_EVENTS.register(bus);}
}
