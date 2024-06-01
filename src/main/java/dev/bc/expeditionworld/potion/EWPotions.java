package dev.bc.expeditionworld.potion;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, ExpeditionWorld.ID);
    public static final RegistryObject<Potion> CATWALK = POTIONS.register("catwalk", () -> new Potion(new MobEffectInstance(EWMobEffects.CATWALK.get(), 3600)));
    public static final RegistryObject<Potion> MEDIUM_CATWALK = POTIONS.register("medium_catwalk", () -> new Potion("catwalk", new MobEffectInstance(EWMobEffects.CATWALK.get(), 4800)));
    public static final RegistryObject<Potion> LONG_CATWALK = POTIONS.register("long_catwalk", () -> new Potion("catwalk", new MobEffectInstance(EWMobEffects.CATWALK.get(), 12000)));
    public static final RegistryObject<Potion> FETTERED = POTIONS.register("fettered", () -> new Potion(new MobEffectInstance(EWMobEffects.FETTERED.get(), 3600)));
    public static final RegistryObject<Potion> LONG_FETTERED = POTIONS.register("long_fettered", () -> new Potion("fettered", new MobEffectInstance(EWMobEffects.FETTERED.get(), 7200)));
}
