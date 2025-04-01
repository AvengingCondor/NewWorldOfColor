package net.avengingcondor.dyemod.item;

import net.avengingcondor.dyemod.DyeMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DyeMod.MOD_ID);

    public static final DeferredItem<Item> DYE_CRIMSON = ITEMS.register("dye_crimson", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_VERMILION = ITEMS.register("dye_vermilion", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_MAROON = ITEMS.register("dye_maroon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_CORAL = ITEMS.register("dye_coral", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_AMBER = ITEMS.register("dye_amber", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_CHARTREUSE = ITEMS.register("dye_chartreuse", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_OLIVE = ITEMS.register("dye_olive", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_JADE = ITEMS.register("dye_jade", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_LIGHT_GREEN = ITEMS.register("dye_light_green", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_TEAL = ITEMS.register("dye_teal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_SEAFOAM = ITEMS.register("dye_seafoam", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_AZURE = ITEMS.register("dye_azure", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_INDIGO = ITEMS.register("dye_indigo", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_MAUVE = ITEMS.register("dye_mauve", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_FUCHSIA = ITEMS.register("dye_fuchsia", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DYE_LIGHT_BROWN = ITEMS.register("dye_light_brown", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
