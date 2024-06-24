package dev.bc.expeditionworld.block;

import dev.bc.expeditionworld.ExpeditionWorld;
import net.minecraft.core.particles.ParticleTypes;
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
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExpeditionWorld.ID);

	// mimichest
	public static final RegistryObject<FetteredChestBlock> FETTERED_CHEST = BLOCKS.register("fettered_chest", () -> new FetteredChestBlock(true, BlockBehaviour.Properties.copy(Blocks.CHEST)));
	public static final RegistryObject<FetteredChestBlock> FETTERED_POT = BLOCKS.register("fettered_pot", () -> new FetteredChestBlock(false, BlockBehaviour.Properties.copy(Blocks.DECORATED_POT)));
	public static final RegistryObject<MossfloraBlock> MOSSFLORA = BLOCKS.register("mossflora", () -> new MossfloraBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).randomTicks().mapColor(DyeColor.PINK)));

	// frozen caves
	public static final RegistryObject<Block> FROZEN_STONE = BLOCKS.register("frozen_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.ICE)));
	public static final RegistryObject<SlabBlock> FROZEN_STONE_SLAB = BLOCKS.register("frozen_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_SLAB).mapColor(MapColor.ICE)));
	public static final RegistryObject<StairBlock> FROZEN_STONE_STAIRS = BLOCKS.register("frozen_stone_stairs", () -> new StairBlock(() -> FROZEN_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_STAIRS).mapColor(MapColor.ICE)));
	public static final RegistryObject<WallBlock> FROZEN_STONE_WALL = BLOCKS.register("frozen_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.ICE)));
	public static final RegistryObject<Block> ICE_CRYSTAL_ORE = BLOCKS.register("ice_crystal_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).mapColor(MapColor.ICE)));
	public static final RegistryObject<Block> ICE_CRYSTAL_BLOCK = BLOCKS.register("ice_crystal_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.GLASS).mapColor(MapColor.ICE)));
	public static final RegistryObject<Block> ICE_CRYSTAL_TORCH = BLOCKS.register("ice_crystal_torch", () -> new TorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH), ParticleTypes.SNOWFLAKE));
	public static final RegistryObject<Block> ICE_CRYSTAL_WALL_TORCH = BLOCKS.register("ice_crystal_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.copy(Blocks.WALL_TORCH), ParticleTypes.SNOWFLAKE));
	public static final RegistryObject<FrostbiteTntBlock> FROSTBITE_TNT = BLOCKS.register("frostbite_tnt", () -> new FrostbiteTntBlock(BlockBehaviour.Properties.copy(Blocks.TNT).mapColor(MapColor.ICE)));
	public static final RegistryObject<Block> FROZEN_DIRT = BLOCKS.register("frozen_dirt", () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
	public static final RegistryObject<EWGrassBlock> FROZEN_GRASS_BLOCK = BLOCKS.register("frozen_grass_block", () -> new EWGrassBlock(FROZEN_DIRT.get(), BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
	public static final RegistryObject<Block> ICE_LANTERN = BLOCKS.register("ice_lantern", () -> new IceLanternBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_ICE).randomTicks().requiresCorrectToolForDrops().lightLevel(state -> 15)));
	public static final RegistryObject<PointedIceBlock> POINTED_ICE = BLOCKS.register("pointed_ice", () -> new PointedIceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).noOcclusion().sound(SoundType.GLASS).randomTicks().strength(1.5F, 3.0F).requiresCorrectToolForDrops()));
	public static final RegistryObject<BushBlock> FROZEN_GRASS = BLOCKS.register("frozen_grass", () -> new BushBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));
	public static final RegistryObject<FlowerBlock> ICE_FLOWER = BLOCKS.register("ice_flower", () -> new FlowerBlock(() -> MobEffects.MOVEMENT_SLOWDOWN, 60, BlockBehaviour.Properties.copy(Blocks.POPPY).mapColor(DyeColor.BLUE)));
	public static final RegistryObject<FlowerPotBlock> POTTED_ICE_FLOWER = BLOCKS.register("potted_ice_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ICE_FLOWER, BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
	public static final RegistryObject<DoublePlantBlock> FRIGID_GLADIOLUS = BLOCKS.register("frigid_gladiolus", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.LARGE_FERN).mapColor(DyeColor.BLUE)));
}
