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
    public static final Map<String, Map<String, DeferredBlock<Block>>> DYED_BLOCKS = new HashMap<>();

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    //ideally need to turn this into something more broadly usable when I'm less tired
    private static void registerDyedBlocks(String colorName, ModDyeColor color) {
        String name = colorName + "_wool";
        DeferredBlock<Block> block = registerBlock(name,
                () -> new ModFlammableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("wool").put(colorName, block);

        name = colorName + "_terracotta";
        MapColor terracottaColor = switch (color) {
            case CRIMSON -> MapColor.CRIMSON_HYPHAE;
            case AMBER -> MapColor.COLOR_ORANGE;
            case BURGUNDY -> MapColor.TERRACOTTA_PURPLE;
            case CORAL -> MapColor.TERRACOTTA_MAGENTA;
            case JADE -> MapColor.TERRACOTTA_GREEN;
            case LIGHT_GREEN -> MapColor.COLOR_GREEN;
            case OLIVE -> MapColor.TERRACOTTA_LIGHT_GREEN;
            case AZURE -> MapColor.COLOR_CYAN;
            case TEAL -> MapColor.GLOW_LICHEN;
            case TURQUOISE -> MapColor.COLOR_LIGHT_BLUE;
            case MAUVE -> MapColor.CLAY;
            case FUCHSIA -> MapColor.CRIMSON_STEM;
            case INDIGO -> MapColor.TERRACOTTA_BLUE;
            case LIGHT_BROWN -> MapColor.COLOR_BROWN;
            default -> color.getMapColor();
        };
        block = registerBlock(name,
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TERRACOTTA).mapColor(terracottaColor)));
        DYED_BLOCKS.get("terracotta").put(colorName, block);

        name = colorName + "_stained_glass";
        block = registerBlock(name,
                ()-> new ModStainedGlassBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("stained_glass").put(colorName, block);

        name += "_pane";
        block = registerBlock(name,
                ()-> new ModStainedGlassPaneBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("stained_glass_panes").put(colorName, block);
    }

    public static void register(IEventBus eventBus) {
        DYED_BLOCKS.put("wool", new HashMap<>());
        DYED_BLOCKS.put("terracotta", new HashMap<>());
        DYED_BLOCKS.put("stained_glass", new HashMap<>());
        DYED_BLOCKS.put("stained_glass_panes", new HashMap<>());

        for (ModDyeColor color : ModDyeColor.newDyeValues()) {
            registerDyedBlocks(color.getName(), color);
        }
        BLOCKS.register(eventBus);
    }
}
