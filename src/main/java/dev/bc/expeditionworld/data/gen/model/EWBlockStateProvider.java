package dev.bc.expeditionworld.data.gen.model;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.MossfloraBlock;
import dev.bc.expeditionworld.registry.EWBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EWBlockStateProvider extends BlockStateProvider {
	private static final ResourceLocation SOLID = ResourceLocation.withDefaultNamespace("solid");
	private static final ResourceLocation CUTOUT = ResourceLocation.withDefaultNamespace("cutout");
	private static final ResourceLocation CUTOUT_MIPPED = ResourceLocation.withDefaultNamespace("cutout_mipped");
	private static final ResourceLocation TRANSLUCENT = ResourceLocation.withDefaultNamespace("translucent");

	public EWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, ExpeditionWorld.ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		fetteredChest(EWBlocks.FETTERED_CHEST.get());
		fetteredChest(EWBlocks.FETTERED_POT.get());
		mossflora(EWBlocks.MOSSFLORA.get());

		stoneSet(EWBlocks.FROZEN_STONE.get(), EWBlocks.FROZEN_STONE_SLAB.get(), EWBlocks.FROZEN_STONE_STAIRS.get(), EWBlocks.FROZEN_STONE_WALL.get());
		simpleBlock(EWBlocks.ICE_CRYSTAL_ORE.get());
		simpleBlock(EWBlocks.ICE_CRYSTAL_BLOCK.get());
		torch(EWBlocks.ICE_CRYSTAL_TORCH.get(), EWBlocks.ICE_CRYSTAL_WALL_TORCH.get());
		cubeBottomTop(EWBlocks.FROSTBITE_TNT.get());
		simpleBlock(EWBlocks.FROZEN_DIRT.get());
		simpleGrassBlock(EWBlocks.FROZEN_GRASS_BLOCK.get(), blockTexture(EWBlocks.FROZEN_DIRT.get()));
		simpleBlock(EWBlocks.ICE_LANTERN.get());
		pointedIce(EWBlocks.POINTED_ICE.get());
		cross(EWBlocks.FROZEN_GRASS.get());
		cross(EWBlocks.ICE_FLOWER.get());
		pottedPlant(EWBlocks.POTTED_ICE_FLOWER.get(), blockTexture(EWBlocks.ICE_FLOWER.get()));
		doublePlant(EWBlocks.FRIGID_GLADIOLUS.get());
	}

	private void stoneSet(Block stone, SlabBlock slab, StairBlock stairs, WallBlock wall) {
		simpleBlock(stone);
		slabBlock(slab, blockTexture(stone), blockTexture(stone));
		stairsBlock(stairs, blockTexture(stone));
		wallBlock(wall, blockTexture(stone));
	}

	private void torch(Block normal, Block wall) {
		ModelFile modelNormal = models().torch(name(normal), blockTexture(normal)).renderType(CUTOUT);
		ModelFile modelWall = models().torchWall(name(wall), blockTexture(normal)).renderType(CUTOUT);
		simpleBlock(normal, modelNormal);
		horizontalBlock(wall, modelWall, 90);
	}

	private void cubeBottomTop(Block block) {
		cubeBottomTop(block, blockTexture(block).withSuffix("_side"), blockTexture(block), blockTexture(block));
	}

	private void cubeBottomTop(Block block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		ModelFile modelFile = models().cubeBottomTop(name(block), side, bottom, top);
		simpleBlock(block, modelFile);
	}

	private void fetteredChest(Block block) {
		horizontalBlock(block, state -> state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER ? models().getExistingFile(modLoc(name(block))) : models().getBuilder(name(block) + "_upper").texture("particle", blockTexture(block)));
	}

	private void mossflora(Block block) {
		getVariantBuilder(block).forAllStates(state -> {
			int age = state.getValue(MossfloraBlock.AGE);
			ModelFile modelFile = models().cross(name(block) + (age > 0 ? ("_age_" + age) : ""), blockTexture(block).withSuffix(age > 0 ? ("_age_" + age) : "")).renderType(CUTOUT);
			return ConfiguredModel.builder().modelFile(modelFile).build();
		});
	}

	private void simpleGrassBlock(Block grassBlock, ResourceLocation dirt) {
		ModelFile modelFile = models().cubeBottomTop(name(grassBlock), blockTexture(grassBlock).withSuffix("_side"), dirt, blockTexture(grassBlock).withSuffix("_top"));
		getVariantBuilder(grassBlock).forAllStates(state -> ConfiguredModel.builder()
			.modelFile(modelFile).nextModel()
			.rotationY(270).modelFile(modelFile).nextModel()
			.rotationY(180).modelFile(modelFile).nextModel()
			.rotationY(90).modelFile(modelFile).build());
	}

	private void pointedIce(Block block) {
		ModelFile modelFile = models().cross(name(block), blockTexture(block)).renderType(CUTOUT);
		getVariantBuilder(block).forAllStates((state) -> ConfiguredModel.builder()
			.modelFile(modelFile).rotationX(state.getValue(BlockStateProperties.VERTICAL_DIRECTION) == Direction.DOWN ? 180 : 0).build());
	}

	private void cross(Block block) {
		cross(block, blockTexture(block), CUTOUT);
	}

	private void cross(Block block, ResourceLocation texture, ResourceLocation renderType) {
		ModelFile modelFile = models().cross(name(block), texture).renderType(renderType);
		simpleBlock(block, modelFile);
	}

	private void pottedPlant(Block potted, ResourceLocation location) {
		ModelFile modelFile = models().singleTexture(name(potted), ResourceLocation.withDefaultNamespace(ModelProvider.BLOCK_FOLDER + "/flower_pot_cross"), "plant", location).renderType(CUTOUT);
		simpleBlock(potted, modelFile);
	}

	private void doublePlant(Block block) {
		ModelFile upper = models().cross(name(block) + "_top", blockTexture(block).withSuffix("_top")).renderType(CUTOUT);
		ModelFile lower = models().cross(name(block) + "_bottom", blockTexture(block).withSuffix("_bottom")).renderType(CUTOUT);
		getVariantBuilder(block)
			.partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
			.modelForState().modelFile(upper).addModel()
			.partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
			.modelForState().modelFile(lower).addModel();
	}

	private ResourceLocation key(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block);
	}

	private String name(Block block) {
		return key(block).getPath();
	}
}
