package dev.bc.expeditionworld.client.renderer.entity;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.projectile.FrozenArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrozenArrowRenderer extends ArrowRenderer<FrozenArrow> {
	public static final ResourceLocation FROZEN_ARROW_LOCATION = ExpeditionWorld.id("textures/entity/frozen_arrow.png");

	public FrozenArrowRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(FrozenArrow frozenArrow) {
		return FROZEN_ARROW_LOCATION;
	}
}
