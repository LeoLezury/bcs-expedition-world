package dev.bc.expeditionworld.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.entity.MimichestKnifeModel;
import dev.bc.expeditionworld.entity.projectile.MimichestKnife;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MimichestKnifeRenderer extends EntityRenderer<MimichestKnife> {
	public static final ResourceLocation STONE_LOCATION = ExpeditionWorld.id("textures/entity/stone_mimichest_knife.png");
	public static final ResourceLocation BRICK_LOCATION = ExpeditionWorld.id("textures/entity/brick_mimichest_knife.png");
	private final MimichestKnifeModel<MimichestKnife> model;

	public MimichestKnifeRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new MimichestKnifeModel<>(context.bakeLayer(MimichestKnifeModel.LAYER_LOCATION));
	}

	public void render(MimichestKnife knife, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
		poseStack.pushPose();
		float yRot = -Mth.rotLerp(g, knife.yRotO, knife.getYRot());
		float xRot = -Mth.lerp(g, knife.xRotO, knife.getXRot()) + 90f;
		float bob = knife.tickCount + g;

		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yRot));
		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.translate(0.0F, -1.3F, 0.0F);

		this.model.prepareMobModel(knife, 0, 0, g);
		this.model.setupAnim(knife, 0, 0, bob, 0, xRot);
		RenderType renderType = this.model.renderType(getTextureLocation(knife));
		VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);
		this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, -1);

		poseStack.popPose();
		super.render(knife, f, g, poseStack, multiBufferSource, i);
	}

	public ResourceLocation getTextureLocation(MimichestKnife knife) {
		return knife.isBrick() ? BRICK_LOCATION : STONE_LOCATION;
	}
}