package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.block.MossfloraBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class EWBlockStateProvider extends BlockStateProvider {
    private static final ResourceLocation SOLID = new ResourceLocation("solid");
    private static final ResourceLocation CUTOUT = new ResourceLocation("cutout");
    private static final ResourceLocation CUTOUT_MIPPED = new ResourceLocation("cutout_mipped");
    private static final ResourceLocation TRANSLUCENT = new ResourceLocation("translucent");

    public EWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExpeditionWorld.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        fetteredChest(EWBlocks.FETTERED_CHEST.get());
        fetteredChest(EWBlocks.FETTERED_POT.get());
        mossflora(EWBlocks.MOSSFLORA.get());

        simpleBlock(EWBlocks.FROZEN_STONE.get());
        simpleBlock(EWBlocks.FROZEN_DIRT.get());
        simpleGrassBlock(EWBlocks.FROZEN_GRASS_BLOCK.get(), blockTexture(EWBlocks.FROZEN_DIRT.get()));
        simpleBlock(EWBlocks.ICE_LANTERN.get());
        pointedIce(EWBlocks.POINTED_ICE.get());
        cross(EWBlocks.FROZEN_GRASS.get());
        cross(EWBlocks.ICE_FLOWER.get());
        pottedPlant(EWBlocks.POTTED_ICE_FLOWER.get(), blockTexture(EWBlocks.ICE_FLOWER.get()));
        doublePlant(EWBlocks.FRIGID_GLADIOLUS.get());
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
        ModelFile modelFile = models().singleTexture(name(potted), new ResourceLocation(ModelProvider.BLOCK_FOLDER + "/flower_pot_cross"), "plant", location).renderType(CUTOUT);
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
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}
