package dev.bc.expeditionworld.client.renderer.entity;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.entity.projectile.MoaFeatherArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MoaFeatherArrowRenderer extends ArrowRenderer<MoaFeatherArrow> {
    public static final ResourceLocation MOA_FEATHER_ARROW_LOCATION = ExpeditionWorld.id("textures/entity/moa_feather_arrow.png");

    public MoaFeatherArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(MoaFeatherArrow frozenArrow) {
        return MOA_FEATHER_ARROW_LOCATION;
    }
}
