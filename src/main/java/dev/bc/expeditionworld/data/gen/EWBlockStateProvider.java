package dev.bc.expeditionworld.data.gen;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.EWBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class EWBlockStateProvider extends BlockStateProvider {
    public EWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExpeditionWorld.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        fetteredChest(EWBlocks.FETTERED_CHEST.get());
        fetteredChest(EWBlocks.FETTERED_POT.get());
    }

    private void fetteredChest(Block block) {
        horizontalBlock(block, state -> state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER ? models().getExistingFile(modLoc(name(block))) : models().getBuilder(name(block) + "_upper").texture("particle", blockTexture(block)));
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}
