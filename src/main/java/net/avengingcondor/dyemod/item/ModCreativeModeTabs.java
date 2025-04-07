package net.avengingcondor.dyemod.item;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DyeMod.MOD_ID);

    public static final Supplier<CreativeModeTab> DYE_ITEMS_TAB = CREATIVE_MODE_TAB.register("more_dye_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BURGUNDY_WOOL))
                    .title(Component.translatable("creativetab.condordyemod.dye_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.DYE_CRIMSON);
                        output.accept(ModItems.DYE_VERMILION);
                        output.accept(ModItems.DYE_AMBER);
                        output.accept(ModItems.DYE_CHARTREUSE);
                        output.accept(ModItems.DYE_OLIVE);
                        output.accept(ModItems.DYE_JADE);
                        output.accept(ModItems.DYE_LIGHT_GREEN);
                        output.accept(ModItems.DYE_TURQUOISE);
                        output.accept(ModItems.DYE_TEAL);
                        output.accept(ModItems.DYE_AZURE);
                        output.accept(ModItems.DYE_INDIGO);
                        output.accept(ModItems.DYE_MAUVE);
                        output.accept(ModItems.DYE_FUCHSIA);
                        output.accept(ModItems.DYE_BURGUNDY);
                        output.accept(ModItems.DYE_CORAL);
                        output.accept(ModItems.DYE_LIGHT_BROWN);
                        output.accept(ModBlocks.CRIMSON_WOOL);
                        output.accept(ModBlocks.VERMILION_WOOL);
                        output.accept(ModBlocks.AMBER_WOOL);
                        output.accept(ModBlocks.CHARTREUSE_WOOL);
                        output.accept(ModBlocks.OLIVE_WOOL);
                        output.accept(ModBlocks.JADE_WOOL);
                        output.accept(ModBlocks.LIGHT_GREEN_WOOL);
                        output.accept(ModBlocks.TURQUOISE_WOOL);
                        output.accept(ModBlocks.TEAL_WOOL);
                        output.accept(ModBlocks.AZURE_WOOL);
                        output.accept(ModBlocks.INDIGO_WOOL);
                        output.accept(ModBlocks.MAUVE_WOOL);
                        output.accept(ModBlocks.FUCHSIA_WOOL);
                        output.accept(ModBlocks.BURGUNDY_WOOL);
                        output.accept(ModBlocks.CORAL_WOOL);
                        output.accept(ModBlocks.LIGHT_BROWN_WOOL);
                        output.accept(ModBlocks.CRIMSON_TERRACOTTA);
                        output.accept(ModBlocks.VERMILION_TERRACOTTA);
                        output.accept(ModBlocks.AMBER_TERRACOTTA);
                        output.accept(ModBlocks.CHARTREUSE_TERRACOTTA);
                        output.accept(ModBlocks.OLIVE_TERRACOTTA);
                        output.accept(ModBlocks.JADE_TERRACOTTA);
                        output.accept(ModBlocks.LIGHT_GREEN_TERRACOTTA);
                        output.accept(ModBlocks.TURQUOISE_TERRACOTTA);
                        output.accept(ModBlocks.TEAL_TERRACOTTA);
                        output.accept(ModBlocks.AZURE_TERRACOTTA);
                        output.accept(ModBlocks.INDIGO_TERRACOTTA);
                        output.accept(ModBlocks.MAUVE_TERRACOTTA);
                        output.accept(ModBlocks.FUCHSIA_TERRACOTTA);
                        output.accept(ModBlocks.BURGUNDY_TERRACOTTA);
                        output.accept(ModBlocks.CORAL_TERRACOTTA);
                        output.accept(ModBlocks.LIGHT_BROWN_TERRACOTTA);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
