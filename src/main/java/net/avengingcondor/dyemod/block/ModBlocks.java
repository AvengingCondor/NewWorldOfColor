package net.avengingcondor.dyemod.block;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.block.custom.ModFlammableBlock;
import net.avengingcondor.dyemod.block.custom.ModStainedGlassBlock;
import net.avengingcondor.dyemod.block.custom.ModStainedGlassPaneBlock;
import net.avengingcondor.dyemod.item.ModItems;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DyeMod.MOD_ID);
    public static final Map<String, DeferredBlock<Block>> STAINED_GLASS_PANES = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> STAINED_GLASS = new HashMap<>();

    /*public static final DeferredBlock<Block> CRIMSON_WOOL = registerBlock("crimson_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.CRIMSON.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    */public static final DeferredBlock<Block> CRIMSON_WOOL = registerBlock("crimson_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(ModDyeColor.CRIMSON.getMapColor())));
    public static final DeferredBlock<Block> VERMILION_WOOL = registerBlock("vermilion_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.VERMILION.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> BURGUNDY_WOOL = registerBlock("burgundy_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.BURGUNDY.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CORAL_WOOL = registerBlock("coral_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.CORAL.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AMBER_WOOL = registerBlock("amber_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.AMBER.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CHARTREUSE_WOOL = registerBlock("chartreuse_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.CHARTREUSE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> OLIVE_WOOL = registerBlock("olive_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.OLIVE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> JADE_WOOL = registerBlock("jade_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.JADE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_GREEN_WOOL = registerBlock("light_green_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.LIGHT_GREEN.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> TEAL_WOOL = registerBlock("teal_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.TEAL.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> TURQUOISE_WOOL = registerBlock("turquoise_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.TURQUOISE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AZURE_WOOL = registerBlock("azure_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.AZURE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> INDIGO_WOOL = registerBlock("indigo_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.INDIGO.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MAUVE_WOOL = registerBlock("mauve_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.MAUVE.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> FUCHSIA_WOOL = registerBlock("fuchsia_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.FUCHSIA.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_BROWN_WOOL = registerBlock("light_brown_wool",
            () -> new ModFlammableBlock(BlockBehaviour.Properties.of()
                    .strength(0.8f).mapColor(ModDyeColor.LIGHT_BROWN.getMapColor()).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CRIMSON_TERRACOTTA = registerBlock("crimson_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.CRIMSON_HYPHAE)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> VERMILION_TERRACOTTA = registerBlock("vermilion_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(ModDyeColor.VERMILION.getMapColor())
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> AMBER_TERRACOTTA = registerBlock("amber_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> CHARTREUSE_TERRACOTTA = registerBlock("chartreuse_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(ModDyeColor.CHARTREUSE.getMapColor())
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BURGUNDY_TERRACOTTA = registerBlock("burgundy_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_PURPLE)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> CORAL_TERRACOTTA = registerBlock("coral_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_MAGENTA)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> JADE_TERRACOTTA = registerBlock("jade_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LIGHT_GREEN_TERRACOTTA = registerBlock("light_green_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> OLIVE_TERRACOTTA = registerBlock("olive_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> AZURE_TERRACOTTA = registerBlock("azure_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_CYAN)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TEAL_TERRACOTTA = registerBlock("teal_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.GLOW_LICHEN)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TURQUOISE_TERRACOTTA = registerBlock("turquoise_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MAUVE_TERRACOTTA = registerBlock("mauve_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.CLAY)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> FUCHSIA_TERRACOTTA = registerBlock("fuchsia_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.CRIMSON_STEM)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> INDIGO_TERRACOTTA = registerBlock("indigo_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LIGHT_BROWN_TERRACOTTA = registerBlock("light_brown_terracotta",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.25f, 4.2f).requiresCorrectToolForDrops().mapColor(MapColor.COLOR_BROWN)
                    .instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE)));
    /*public static final DeferredBlock<Block> CRIMSON_STAINED_GLASS = registerBlock("crimson_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.CRIMSON.getMapColor())));
    public static final DeferredBlock<Block> VERMILION_STAINED_GLASS = registerBlock("vermilion_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.VERMILION, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.VERMILION.getMapColor())));
    public static final DeferredBlock<Block> AMBER_STAINED_GLASS = registerBlock("amber_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.AMBER, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.AMBER.getMapColor())));
    public static final DeferredBlock<Block> CHARTREUSE_STAINED_GLASS = registerBlock("chartreuse_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.CHARTREUSE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.CHARTREUSE.getMapColor())));
    public static final DeferredBlock<Block> JADE_STAINED_GLASS = registerBlock("jade_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.JADE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.JADE.getMapColor())));
    public static final DeferredBlock<Block> LIGHT_GREEN_STAINED_GLASS = registerBlock("light_green_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.LIGHT_GREEN, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.LIGHT_GREEN.getMapColor())));
    public static final DeferredBlock<Block> OLIVE_STAINED_GLASS = registerBlock("olive_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.OLIVE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.OLIVE.getMapColor())));
    public static final DeferredBlock<Block> TEAL_STAINED_GLASS = registerBlock("teal_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.TEAL, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.TEAL.getMapColor())));
    public static final DeferredBlock<Block> TURQUOISE_STAINED_GLASS = registerBlock("turquoise_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.TURQUOISE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.TURQUOISE.getMapColor())));
    public static final DeferredBlock<Block> AZURE_STAINED_GLASS = registerBlock("azure_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.AZURE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.AZURE.getMapColor())));
    public static final DeferredBlock<Block> INDIGO_STAINED_GLASS = registerBlock("indigo_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.INDIGO, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.INDIGO.getMapColor())));
    public static final DeferredBlock<Block> MAUVE_STAINED_GLASS = registerBlock("mauve_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.MAUVE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.MAUVE.getMapColor())));
    public static final DeferredBlock<Block> BURGUNDY_STAINED_GLASS = registerBlock("burgundy_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.BURGUNDY, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.BURGUNDY.getMapColor())));
    public static final DeferredBlock<Block> FUCHSIA_STAINED_GLASS = registerBlock("fuchsia_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.FUCHSIA, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.FUCHSIA.getMapColor())));
    public static final DeferredBlock<Block> CORAL_STAINED_GLASS = registerBlock("coral_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.CORAL, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.CORAL.getMapColor())));
    public static final DeferredBlock<Block> LIGHT_BROWN_STAINED_GLASS = registerBlock("light_brown_stained_glass",
            () -> new ModStainedGlassBlock(ModDyeColor.LIGHT_BROWN, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(ModDyeColor.LIGHT_BROWN.getMapColor())));
    /*public static final DeferredBlock<Block> CRIMSON_STAINED_GLASS_PANE = registerBlock("crimson_stained_glass_pane",
            () -> new ModStainedGlassPaneBlock(ModDyeColor.CRIMSON, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(ModDyeColor.CRIMSON.getMapColor())));
*/

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    //ideally need to turn this into something more broadly usable when I'm less tired
    private static void /*DeferredBlock<Block>*/ registerStainedGlassPanes(String colorName, ModDyeColor color) {
        String name = colorName + "_stained_glass";
        DeferredBlock<Block> block = registerBlock(name,
                ()-> new ModStainedGlassBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(color.getMapColor())));
        STAINED_GLASS.put(colorName, block);

        name += "_pane";
        block = registerBlock(name,
                ()-> new ModStainedGlassPaneBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(color.getMapColor())));
        STAINED_GLASS_PANES.put(colorName, block);

        //return block;
    }

    public static void register(IEventBus eventBus) {
        for (ModDyeColor color : ModDyeColor.newDyeValues()) {
            registerStainedGlassPanes(color.getName(), color);
        }
        BLOCKS.register(eventBus);
    }
}
