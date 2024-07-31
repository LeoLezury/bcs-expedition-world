package dev.bc.expeditionworld.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.bc.expeditionworld.entity.living.mimichest.Mimichest;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

@OnlyIn(Dist.CLIENT)
public class MimichestRenderer<T extends Mimichest> extends GeoEntityRenderer<T> {
	public MimichestRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
		super(renderManager, model);
		addRenderLayer(new AutoGlowingGeoLayer<>(this));
	}

	@Override
	public void preRender(PoseStack poseStack, T animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
		super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
		for (GeoBone bone : model.topLevelBones()) {
			hideFlowers(bone);
		}
	}

	private void hideFlowers(GeoBone bone) {
		for (GeoBone child : bone.getChildBones()) {
			hideFlowers(child);
			if (child.getName().contains("flower")) {
				child.setHidden(!animatable.hasMossflora());
			}
		}
	}
}
