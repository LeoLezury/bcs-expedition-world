package dev.bc.expeditionworld.potion;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ExpeditionWorld.MOD_ID);
    public static final RegistryObject<CatwalkMobEffect> CATWALK = MOB_EFFECTS.register("catwalk", () -> new CatwalkMobEffect(MobEffectCategory.BENEFICIAL, 0x052a32));
}
