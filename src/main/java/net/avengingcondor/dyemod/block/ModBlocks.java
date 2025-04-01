package net.avengingcondor.dyemod.block;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DyeMod.MOD_ID);

    public static final DeferredBlock<Block> CRIMSON_WOOL = registerBlock("crimson_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> VERMILION_WOOL = registerBlock("vermilion_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MAROON_WOOL = registerBlock("maroon_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CORAL_WOOL = registerBlock("coral_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AMBER_WOOL = registerBlock("amber_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> CHARTREUSE_WOOL = registerBlock("chartreuse_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> OLIVE_WOOL = registerBlock("olive_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> JADE_WOOL = registerBlock("jade_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_GREEN_WOOL = registerBlock("light_green_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> TEAL_WOOL = registerBlock("teal_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> SEAFOAM_WOOL = registerBlock("seafoam_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AZURE_WOOL = registerBlock("azure_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> INDIGO_WOOL = registerBlock("indigo_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> MAUVE_WOOL = registerBlock("mauve_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> FUCHSIA_WOOL = registerBlock("fuchsia_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LIGHT_BROWN_WOOL = registerBlock("light_brown_wool",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8f).instrument(NoteBlockInstrument.GUITAR).ignitedByLava().sound(SoundType.WOOL)));


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
