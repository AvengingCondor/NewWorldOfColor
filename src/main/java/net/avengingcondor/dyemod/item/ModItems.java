package net.avengingcondor.dyemod.item;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.item.custom.ModDyeItem;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DyeMod.MOD_ID);

    public static final DeferredItem<Item> DYE_CRIMSON = ITEMS.register("dye_crimson", () -> new ModDyeItem(ModDyeColor.CRIMSON, new Item.Properties()));
    public static final DeferredItem<Item> DYE_VERMILION = ITEMS.register("dye_vermilion", () -> new ModDyeItem(ModDyeColor.VERMILION, new Item.Properties()));
    public static final DeferredItem<Item> DYE_BURGUNDY = ITEMS.register("dye_burgundy", () ->  new ModDyeItem(ModDyeColor.BURGUNDY,new Item.Properties()));
    public static final DeferredItem<Item> DYE_CORAL = ITEMS.register("dye_coral", () ->  new ModDyeItem(ModDyeColor.CORAL,new Item.Properties()));
    public static final DeferredItem<Item> DYE_AMBER = ITEMS.register("dye_amber", () ->  new ModDyeItem(ModDyeColor.AMBER,new Item.Properties()));
    public static final DeferredItem<Item> DYE_CHARTREUSE = ITEMS.register("dye_chartreuse", () ->  new ModDyeItem(ModDyeColor.CHARTREUSE,new Item.Properties()));
    public static final DeferredItem<Item> DYE_OLIVE = ITEMS.register("dye_olive", () ->  new ModDyeItem(ModDyeColor.OLIVE,new Item.Properties()));
    public static final DeferredItem<Item> DYE_JADE = ITEMS.register("dye_jade", () ->  new ModDyeItem(ModDyeColor.JADE,new Item.Properties()));
    public static final DeferredItem<Item> DYE_LIGHT_GREEN = ITEMS.register("dye_light_green", () ->  new ModDyeItem(ModDyeColor.LIGHT_GREEN,new Item.Properties()));
    public static final DeferredItem<Item> DYE_TEAL = ITEMS.register("dye_teal", () ->  new ModDyeItem(ModDyeColor.TEAL,new Item.Properties()));
    public static final DeferredItem<Item> DYE_TURQUOISE = ITEMS.register("dye_turquoise", () ->  new ModDyeItem(ModDyeColor.TURQUOISE,new Item.Properties()));
    public static final DeferredItem<Item> DYE_AZURE = ITEMS.register("dye_azure", () ->  new ModDyeItem(ModDyeColor.AZURE,new Item.Properties()));
    public static final DeferredItem<Item> DYE_INDIGO = ITEMS.register("dye_indigo", () ->  new ModDyeItem(ModDyeColor.INDIGO,new Item.Properties()));
    public static final DeferredItem<Item> DYE_MAUVE = ITEMS.register("dye_mauve", () ->  new ModDyeItem(ModDyeColor.MAUVE,new Item.Properties()));
    public static final DeferredItem<Item> DYE_FUCHSIA = ITEMS.register("dye_fuchsia", () ->  new ModDyeItem(ModDyeColor.FUCHSIA,new Item.Properties()));
    public static final DeferredItem<Item> DYE_LIGHT_BROWN = ITEMS.register("dye_light_brown", () ->  new ModDyeItem(ModDyeColor.LIGHT_BROWN,new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
