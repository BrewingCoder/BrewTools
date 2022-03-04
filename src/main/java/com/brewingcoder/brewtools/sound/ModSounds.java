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

    public static final RegistryObject<SoundEvent> LASSO_WHOOSH = registerSoundEvent("lasso_whoosh");
    public static final RegistryObject<SoundEvent> WHIP_CRACK = registerSoundEvent("whip");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        RegistryObject<SoundEvent> registryObject =
         SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(BrewTools.MODID,name)));
        return registryObject;
    }
    public static void register(IEventBus bus){ SOUND_EVENTS.register(bus);}
}
