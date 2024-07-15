package dev.bc.expeditionworld.client.renderer.entity;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.client.model.entity.ChilledModel;
import dev.bc.expeditionworld.entity.living.frozencaves.Chilled;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChilledRenderer extends AbstractZombieRenderer<Chilled, ZombieModel<Chilled>> {
	private static final ResourceLocation CHILLED_LOCATION = ExpeditionWorld.id("textures/entity/chilled.png");

	public static final ModelLayerLocation MAIN_LAYER = new ModelLayerLocation(ExpeditionWorld.id("chilled"), "main");
	public static final ModelLayerLocation INNER_ARMOR_LAYER = new ModelLayerLocation(ExpeditionWorld.id("chilled"), "inner_armor");
	public static final ModelLayerLocation OUTER_ARMOR_LAYER = new ModelLayerLocation(ExpeditionWorld.id("chilled"), "outer_armor");

	public ChilledRenderer(EntityRendererProvider.Context context) {
		super(context, new ChilledModel(context.bakeLayer(MAIN_LAYER)), new ZombieModel<>(context.bakeLayer(INNER_ARMOR_LAYER)), new ZombieModel<>(context.bakeLayer(OUTER_ARMOR_LAYER)));
	}

	public ResourceLocation getTextureLocation(Zombie zombie) {
		return CHILLED_LOCATION;
	}
}