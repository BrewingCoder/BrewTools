package com.brewingcoder.brewtools.sound;

import com.brewingcoder.brewtools.BrewTools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BrewTools.MODID);

    public static final RegistryObject<SoundEvent> LASSO_WHOOSH = registerSoundEvent("lasso_whoosh");
    public static final RegistryObject<SoundEvent> WHIP_CRACK = registerSoundEvent("whip");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(BrewTools.MODID,name)));
    }
    public static void register(IEventBus bus){ SOUND_EVENTS.register(bus);}
}
