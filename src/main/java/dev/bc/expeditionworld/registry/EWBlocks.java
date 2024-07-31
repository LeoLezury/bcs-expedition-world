package dev.bc.expeditionworld.registry;

import dev.bc.expeditionworld.ExpeditionWorld;
import dev.bc.expeditionworld.block.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EWBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, ExpeditionWorld.ID);

	// mimichest
	public static final DeferredHolder<Block, FetteredChestBlock> FETTERED_CHEST = BLOCKS.register("fettered_chest", () -> new FetteredChestBlock(true, BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredHolder<Block, FetteredChestBlock> FETTERED_POT = BLOCKS.register("fettered_pot", () -> new FetteredChestBlock(false, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT)));
	public static final DeferredHolder<Block, MossfloraBlock> MOSSFLORA = BLOCKS.register("mossflora", () -> new MossfloraBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).randomTicks().mapColor(DyeColor.PINK)));

	// frozen caves
	public static final DeferredHolder<Block, Block> FROZEN_STONE = BLOCKS.register("frozen_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, SlabBlock> FROZEN_STONE_SLAB = BLOCKS.register("frozen_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, StairBlock> FROZEN_STONE_STAIRS = BLOCKS.register("frozen_stone_stairs", () -> new StairBlock(FROZEN_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, WallBlock> FROZEN_STONE_WALL = BLOCKS.register("frozen_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, Block> ICE_CRYSTAL_ORE = BLOCKS.register("ice_crystal_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, Block> ICE_CRYSTAL_BLOCK = BLOCKS.register("ice_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.GLASS).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, Block> ICE_CRYSTAL_TORCH = BLOCKS.register("ice_crystal_torch", () -> new TorchBlock(ParticleTypes.SNOWFLAKE, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)));
	public static final DeferredHolder<Block, Block> ICE_CRYSTAL_WALL_TORCH = BLOCKS.register("ice_crystal_wall_torch", () -> new WallTorchBlock(ParticleTypes.SNOWFLAKE, BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH)));
	public static final DeferredHolder<Block, FrostbiteTntBlock> FROSTBITE_TNT = BLOCKS.register("frostbite_tnt", () -> new FrostbiteTntBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT).mapColor(MapColor.ICE)));
	public static final DeferredHolder<Block, Block> FROZEN_DIRT = BLOCKS.register("frozen_dirt", () -> new GrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
	public static final DeferredHolder<Block, EWGrassBlock> FROZEN_GRASS_BLOCK = BLOCKS.register("frozen_grass_block", () -> new EWGrassBlock(FROZEN_DIRT.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
	public static final DeferredHolder<Block, Block> ICE_LANTERN = BLOCKS.register("ice_lantern", () -> new IceLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE).randomTicks().requiresCorrectToolForDrops().lightLevel(state -> 15)));
	public static final DeferredHolder<Block, PointedIceBlock> POINTED_ICE = BLOCKS.register("pointed_ice", () -> new PointedIceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.GLASS).randomTicks().strength(1.5F, 3.0F).requiresCorrectToolForDrops()));
	public static final DeferredHolder<Block, BushBlock> FROZEN_GRASS = BLOCKS.register("frozen_grass", () -> new EWBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)));
	public static final DeferredHolder<Block, FlowerBlock> ICE_FLOWER = BLOCKS.register("ice_flower", () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 60, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).mapColor(DyeColor.BLUE)));
	public static final DeferredHolder<Block, FlowerPotBlock> POTTED_ICE_FLOWER = BLOCKS.register("potted_ice_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ICE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)));
	public static final DeferredHolder<Block, DoublePlantBlock> FRIGID_GLADIOLUS = BLOCKS.register("frigid_gladiolus", () -> new DoublePlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_FERN).mapColor(DyeColor.BLUE)));
}
