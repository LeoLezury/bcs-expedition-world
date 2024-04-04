package dev.bc.expeditionworld.block;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExpeditionWorld.MOD_ID);
    public static final RegistryObject<FetteredChestBlock> FETTERED_CHEST = BLOCKS.register("fettered_chest", () -> new FetteredChestBlock(true, BlockBehaviour.Properties.copy(Blocks.CHEST)));
    public static final RegistryObject<FetteredChestBlock> FETTERED_POT = BLOCKS.register("fettered_pot", () -> new FetteredChestBlock(false, BlockBehaviour.Properties.copy(Blocks.DECORATED_POT)));
}
