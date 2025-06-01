package net.avengingcondor.dyemod.block;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.block.custom.ModCarpetBlock;
import net.avengingcondor.dyemod.block.custom.ModFlammableBlock;
import net.avengingcondor.dyemod.block.custom.ModStainedGlassBlock;
import net.avengingcondor.dyemod.block.custom.ModStainedGlassPaneBlock;
import net.avengingcondor.dyemod.item.ModItems;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
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


    private static void registerDyedBlocks(String colorName, ModDyeColor color) {
        String name = colorName + "_wool";
        DeferredBlock<Block> block = registerBlock(name,
                () -> new ModFlammableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("wool").put(colorName, block);

        name = colorName + "_carpet";
        block = registerBlock(name,
                () -> new ModCarpetBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("carpet").put(colorName, block);

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

        name = colorName + "_glazed_terracotta";
        block = registerBlock(name,
                () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_GLAZED_TERRACOTTA).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("glazed_terracotta").put(colorName, block);

        name = colorName + "_stained_glass";
        block = registerBlock(name,
                ()-> new ModStainedGlassBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("stained_glass").put(colorName, block);

        name += "_pane";
        block = registerBlock(name,
                ()-> new ModStainedGlassPaneBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("stained_glass_panes").put(colorName, block);

        name = colorName + "_concrete";
        block = registerBlock(name,
                ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("concrete").put(colorName, block);

        DeferredBlock<Block> matchingConcrete = block;
        name += "_powder";
        block = registerBlock(name,
                ()-> new ConcretePowderBlock(matchingConcrete.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("concrete_powder").put(colorName, block);

        name = colorName + "_candle";
        block = registerBlock(name,
                ()-> new CandleBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CANDLE).mapColor(color.getMapColor())));
        DYED_BLOCKS.get("candle").put(colorName, block);

        DeferredBlock<Block> matchingCandle = block;
        name += "_cake";
        block = registerBlock(name,
                ()-> new CandleCakeBlock(matchingCandle.value(), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CANDLE_CAKE)));
        DYED_BLOCKS.get("candle_cake").put(colorName, block);
    }

    public static void register(IEventBus eventBus) {
        DYED_BLOCKS.put("wool", new HashMap<>());
        DYED_BLOCKS.put("carpet", new HashMap<>());
        DYED_BLOCKS.put("terracotta", new HashMap<>());
        DYED_BLOCKS.put("glazed_terracotta", new HashMap<>());
        DYED_BLOCKS.put("stained_glass", new HashMap<>());
        DYED_BLOCKS.put("stained_glass_panes", new HashMap<>());
        DYED_BLOCKS.put("concrete", new HashMap<>());
        DYED_BLOCKS.put("concrete_powder", new HashMap<>());
        DYED_BLOCKS.put("candle", new HashMap<>());
        DYED_BLOCKS.put("candle_cake", new HashMap<>());

        for (ModDyeColor color : ModDyeColor.newDyeValues()) {
            registerDyedBlocks(color.getName(), color);
        }
        BLOCKS.register(eventBus);
    }
}
