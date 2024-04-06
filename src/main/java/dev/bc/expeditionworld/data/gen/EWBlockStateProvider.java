package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import dev.bc.expeditionworld.block.MossfloraBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
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

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}
