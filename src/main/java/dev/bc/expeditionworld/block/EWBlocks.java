package dev.bc.expeditionworld.block;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EWBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExpeditionWorld.MOD_ID);

    // mimichest
    public static final RegistryObject<FetteredChestBlock> FETTERED_CHEST = BLOCKS.register("fettered_chest", () -> new FetteredChestBlock(true, BlockBehaviour.Properties.copy(Blocks.CHEST)));
    public static final RegistryObject<FetteredChestBlock> FETTERED_POT = BLOCKS.register("fettered_pot", () -> new FetteredChestBlock(false, BlockBehaviour.Properties.copy(Blocks.DECORATED_POT)));
    public static final RegistryObject<MossfloraBlock> MOSSFLORA = BLOCKS.register("mossflora", () -> new MossfloraBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).randomTicks().mapColor(DyeColor.PINK)));

    // frozen caves
    public static final RegistryObject<Block> FROZEN_STONE = BLOCKS.register("frozen_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> FROZEN_DIRT = BLOCKS.register("frozen_dirt", () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<EWGrassBlock> FROZEN_GRASS_BLOCK = BLOCKS.register("frozen_grass_block", () -> new EWGrassBlock(FROZEN_DIRT.get(), BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> ICE_LANTERN = BLOCKS.register("ice_lantern", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE).requiresCorrectToolForDrops().lightLevel(state -> 15)));
    public static final RegistryObject<PointedIceBlock> POINTED_ICE = BLOCKS.register("pointed_ice", () -> new PointedIceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.GLASS).randomTicks().strength(1.5F, 3.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<BushBlock> FROZEN_GRASS = BLOCKS.register("frozen_grass", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
    public static final RegistryObject<FlowerBlock> ICE_FLOWER = BLOCKS.register("ice_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SLOWDOWN, 60, BlockBehaviour.Properties.copy(Blocks.POPPY).mapColor(DyeColor.BLUE)));
    public static final RegistryObject<FlowerPotBlock> POTTED_ICE_FLOWER = BLOCKS.register("potted_ice_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ICE_FLOWER, BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistryObject<DoublePlantBlock> FRIGID_GLADIOLUS = BLOCKS.register("frigid_gladiolus", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).mapColor(DyeColor.BLUE)));
}
