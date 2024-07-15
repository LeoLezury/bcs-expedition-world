package dev.bc.expeditionworld.client.model.entity;

import com.google.common.collect.ImmutableList;
import dev.bc.expeditionworld.entity.living.frozencaves.Chilled;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChilledModel extends ZombieModel<Chilled> {
	private final ModelPart jacket;
	private final ModelPart rightSleeve;
	private final ModelPart leftSleeve;
	private final ModelPart rightPants;
	private final ModelPart leftPants;

	public ChilledModel(ModelPart root) {
		super(root);
		this.jacket = root.getChild("jacket");
		this.rightSleeve = root.getChild("right_sleeve");
		this.leftSleeve = root.getChild("left_sleeve");
		this.rightPants = root.getChild("right_pants");
		this.leftPants = root.getChild("left_pants");
	}

	public static MeshDefinition createMesh(CubeDeformation deformation) {
		MeshDefinition definition = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition root = definition.getRoot();
		root.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		root.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		root.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation.extend(0.25F)), PartPose.ZERO);
		return definition;
	}

	@Override
	public void setupAnim(Chilled entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.leftPants.copyFrom(this.leftLeg);
		this.rightPants.copyFrom(this.rightLeg);
		this.leftSleeve.copyFrom(this.leftArm);
		this.rightSleeve.copyFrom(this.rightArm);
		this.jacket.copyFrom(this.body);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.jacket, this.rightArm, this.rightSleeve, this.leftArm, this.leftSleeve, this.rightLeg, this.rightPants, this.leftLeg, this.leftPants);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.head, this.hat);
	}
}
