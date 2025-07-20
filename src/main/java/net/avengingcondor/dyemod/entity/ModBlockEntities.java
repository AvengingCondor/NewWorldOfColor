package net.avengingcondor.dyemod.entity;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, DyeMod.MOD_ID);

    public static final Supplier<BlockEntityType<ModShulkerBoxBlockEntity>> SHULKER_BOX = BLOCK_ENTITY_TYPES.register(
            "shulker_box",
            () -> BlockEntityType.Builder.of(
                    ModShulkerBoxBlockEntity::new,
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("crimson").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("vermilion").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("amber").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("chartreuse").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("olive").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("light_green").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("jade").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("teal").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("turquoise").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("azure").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("indigo").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("mauve").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("coral").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("fuchsia").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("burgundy").get(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("light_brown").get()
            ).build(null));

    public static final Supplier<BlockEntityType<ModBedBlockEntity>> BED = BLOCK_ENTITY_TYPES.register(
            "bed",
            () -> BlockEntityType.Builder.of(
                    ModBedBlockEntity::new,
                    ModBlocks.DYED_BLOCKS.get("bed").get("crimson").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("vermilion").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("amber").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("chartreuse").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("olive").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("light_green").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("jade").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("teal").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("turquoise").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("azure").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("indigo").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("mauve").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("coral").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("fuchsia").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("burgundy").get(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("light_brown").get()
            ).build(null));
}
