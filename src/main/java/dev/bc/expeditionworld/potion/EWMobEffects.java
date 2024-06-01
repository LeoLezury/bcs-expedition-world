package dev.bc.expeditionworld.potion;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ExpeditionWorld.ID);
    public static final RegistryObject<CatwalkMobEffect> CATWALK = MOB_EFFECTS.register("catwalk", () -> new CatwalkMobEffect(MobEffectCategory.BENEFICIAL, 0x052a32));
    public static final RegistryObject<MobEffect> FETTERED = MOB_EFFECTS.register("fettered", () -> new MobEffect(MobEffectCategory.NEUTRAL, 0x525252));
    public static final RegistryObject<MobEffect> FROZEN = MOB_EFFECTS.register("frozen", () -> new FrozenMobEffect(MobEffectCategory.HARMFUL, 0x83a1d7).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL));
}
