package dev.bc.expeditionworld.mixin.client;

import dev.bc.expeditionworld.client.event.ClientEvents;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(LightTexture.class)
public abstract class LightTextureMixin {
	@Inject(method = "getBrightness", at = @At("RETURN"), cancellable = true)
	private static void getBrightness(DimensionType dimensionType, int lightLevel, CallbackInfoReturnable<Float> cir) {
		if (ClientEvents.getAmbientLightAddition() > 0) {
			cir.setReturnValue(cir.getReturnValue() + Mth.clamp(ClientEvents.getAmbientLightAddition(), 0, 0.1f));
		}
	}
}
