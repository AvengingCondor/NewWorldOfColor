package net.avengingcondor.dyemod.block;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.item.ModItems;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DyeMod.MOD_ID);

    public static final DeferredBlock<Block> CRIMSON_WOOL = registerBlock("crimson_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.CRIMSON.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> VERMILION_WOOL = registerBlock("vermilion_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.VERMILION.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> BURGUNDY_WOOL = registerBlock("burgundy_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.BURGUNDY.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CORAL_WOOL = registerBlock("coral_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.CORAL.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AMBER_WOOL = registerBlock("amber_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.AMBER.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CHARTREUSE_WOOL = registerBlock("chartreuse_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.CHARTREUSE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> OLIVE_WOOL = registerBlock("olive_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.OLIVE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> JADE_WOOL = registerBlock("jade_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.JADE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_GREEN_WOOL = registerBlock("light_green_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.LIGHT_GREEN.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> TEAL_WOOL = registerBlock("teal_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.TEAL.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> TURQUOISE_WOOL = registerBlock("turquoise_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.TURQUOISE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AZURE_WOOL = registerBlock("azure_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.AZURE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> INDIGO_WOOL = registerBlock("indigo_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.INDIGO.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MAUVE_WOOL = registerBlock("mauve_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.MAUVE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> FUCHSIA_WOOL = registerBlock("fuchsia_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.FUCHSIA.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_BROWN_WOOL = registerBlock("light_brown_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.LIGHT_BROWN.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CRIMSON_TERRACOTTA = registerBlock("crimson_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> VERMILION_TERRACOTTA = registerBlock("vermilion_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> AMBER_TERRACOTTA = registerBlock("amber_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> CHARTREUSE_TERRACOTTA = registerBlock("chartreuse_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BURGUNDY_TERRACOTTA = registerBlock("burgundy_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> CORAL_TERRACOTTA = registerBlock("coral_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> JADE_TERRACOTTA = registerBlock("jade_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LIGHT_GREEN_TERRACOTTA = registerBlock("light_green_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> OLIVE_TERRACOTTA = registerBlock("olive_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> AZURE_TERRACOTTA = registerBlock("azure_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TEAL_TERRACOTTA = registerBlock("teal_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TURQUOISE_TERRACOTTA = registerBlock("turquoise_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MAUVE_TERRACOTTA = registerBlock("mauve_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> FUCHSIA_TERRACOTTA = registerBlock("fuchsia_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> INDIGO_TERRACOTTA = registerBlock("indigo_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LIGHT_BROWN_TERRACOTTA = registerBlock("light_brown_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    /* Stainedglass has its own block type with special handling for changing beacon beam color, but references Dyecolor. maybe hold off
    on fully adding the rest until I sort out my plan of action there?
     */
    public static final DeferredBlock<Block> CRIMSON_STAINED_GLASS = registerBlock("crimson_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> VERMILION_STAINED_GLASS = registerBlock("vermilion_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> AMBER_STAINED_GLASS = registerBlock("amber_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> CHARTREUSE_STAINED_GLASS = registerBlock("chartreuse_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> JADE_STAINED_GLASS = registerBlock("jade_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> LIGHT_GREEN_STAINED_GLASS = registerBlock("light_green_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> OLIVE_STAINED_GLASS = registerBlock("olive_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> TEAL_STAINED_GLASS = registerBlock("teal_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> TURQUOISE_STAINED_GLASS = registerBlock("turquoise_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> AZURE_STAINED_GLASS = registerBlock("azure_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> INDIGO_STAINED_GLASS = registerBlock("indigo_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> MAUVE_STAINED_GLASS = registerBlock("mauve_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> BURGUNDY_STAINED_GLASS = registerBlock("burgundy_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> FUCHSIA_STAINED_GLASS = registerBlock("fuchsia_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> CORAL_STAINED_GLASS = registerBlock("coral_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));
    public static final DeferredBlock<Block> LIGHT_BROWN_STAINED_GLASS = registerBlock("light_brown_stained_glass",
            () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlocKItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlocKItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
