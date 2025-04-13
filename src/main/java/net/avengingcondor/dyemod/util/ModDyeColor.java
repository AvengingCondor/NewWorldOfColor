package net.avengingcondor.dyemod.util;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.FastColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/* Recreating the vanilla DyeColor enum to now include the new colors, as the original is not extensible.
    some methods and fields may not actually be necessary for my uses
 */
public enum ModDyeColor implements StringRepresentable {
    //Vanilla Colors
    WHITE(0, "white", 16383998, MapColor.SNOW, 15790320, 16777215, DyeColor.WHITE),
    ORANGE(1, "orange", 16351261, MapColor.COLOR_ORANGE, 15435844, 16738335, DyeColor.ORANGE),
    MAGENTA(2, "magenta", 13061821, MapColor.COLOR_MAGENTA, 12801229, 16711935, DyeColor.MAGENTA),
    LIGHT_BLUE(3, "light_blue", 3847130, MapColor.COLOR_LIGHT_BLUE, 6719955, 10141901, DyeColor.LIGHT_BLUE),
    YELLOW(4, "yellow", 16701501, MapColor.COLOR_YELLOW, 14602026, 16776960, DyeColor.YELLOW),
    LIME(5, "lime", 8439583, MapColor.COLOR_LIGHT_GREEN, 4312372, 12582656, DyeColor.LIME),
    PINK(6, "pink", 15961002, MapColor.COLOR_PINK, 14188952, 16738740, DyeColor.PINK),
    GRAY(7, "gray", 4673362, MapColor.COLOR_GRAY, 4408131, 8421504, DyeColor.GRAY),
    LIGHT_GRAY(8, "light_gray", 10329495, MapColor.COLOR_LIGHT_GRAY, 11250603, 13882323, DyeColor.LIGHT_GRAY),
    CYAN(9, "cyan", 1481884, MapColor.COLOR_CYAN, 2651799, 65535, DyeColor.CYAN),
    PURPLE(10, "purple", 8991416, MapColor.COLOR_PURPLE, 8073150, 10494192, DyeColor.PURPLE),
    BLUE(11, "blue", 3949738, MapColor.COLOR_BLUE, 2437522, 255, DyeColor.BLUE),
    BROWN(12, "brown", 8606770, MapColor.COLOR_BROWN, 5320730, 9127187, DyeColor.BROWN),
    GREEN(13, "green", 6192150, MapColor.COLOR_GREEN, 3887386, 65280, DyeColor.GREEN),
    RED(14, "red", 11546150, MapColor.COLOR_RED, 11743532, 16711680, DyeColor.RED),
    BLACK(15, "black", 1908001, MapColor.COLOR_BLACK, 1973019, 0, DyeColor.BLACK),

    //New Colors(FireworkColor is placeholder for all, it's just white)
    CRIMSON(16, "crimson", 9448235, MapColor.NETHER, 15790321, 8388608, DyeColor.RED),
    VERMILION(17, "vermilion", 13326908, MapColor.TERRACOTTA_ORANGE, 15790322, 14889475, DyeColor.ORANGE),
    AMBER(18, "amber", 16762688, MapColor.TERRACOTTA_YELLOW, 15790323, 16766720, DyeColor.YELLOW),
    CHARTREUSE(19, "chartreuse", 13953641, MapColor.COLOR_YELLOW, 15790324, 14483247, DyeColor.YELLOW),
    OLIVE(20, "olive", 9811032, MapColor.GRASS, 15790325, 8300077, DyeColor.LIME),
    LIGHT_GREEN(21, "light_green", 7325039, MapColor.EMERALD, 15790326, 3329330, DyeColor.LIME),
    JADE(22, "jade", 4230720, MapColor.PLANT, 15790327, 25600, DyeColor.GREEN),
    TEAL(23, "teal", 5359278, MapColor.WARPED_STEM, 15790328, 2285752, DyeColor.CYAN),
    TURQUOISE(24, "turquoise", 9303250, MapColor.WARPED_WART_BLOCK, 15790329, 8388564, DyeColor.CYAN),
    AZURE(25, "azure", 5339114, MapColor.LAPIS, 15790330, 2003199, DyeColor.LIGHT_BLUE),
    INDIGO(26, "indigo", 5584333, MapColor.COLOR_PURPLE, 15790332, 4325573, DyeColor.BLUE),
    MAUVE(27, "mauve", 11630307, MapColor.ICE, 15790340, 12014058, DyeColor.PURPLE),
    FUCHSIA(28, "fuchsia", 12930456, MapColor.COLOR_MAGENTA, 15790333, 13896842, DyeColor.MAGENTA),
    BURGUNDY(29, "burgundy", 10107241, MapColor.WARPED_HYPHAE, 15790334, 9437268, DyeColor.PINK),
    CORAL(30, "coral", 15174290, MapColor.COLOR_PINK, 15790335, 14381203, DyeColor.PINK),
    LIGHT_BROWN(31, "light_brown", 12616787, MapColor.PODZOL, 15790336, 13468991, DyeColor.BROWN);


    private final int id;
    private final String name;
    private final MapColor mapColor;
    private final int textureDiffuseColor;
    private final int fireworkColor;
    private final int textColor;
    private final DyeColor fallback; //vanilla color closest to it for instances where it isn't feasible to rework for new colors, namely coloring sign text
    private final TagKey<Item> dyesTag;
    private final TagKey<Item> dyedTag;

    private ModDyeColor(int id, String name, int textureDefuseColor, MapColor mapColor, int fireworkColor, int textColor, DyeColor fallback) {
        this.id = id;
        this.name = name;
        this.textureDiffuseColor = FastColor.ARGB32.opaque(textureDefuseColor);
        this.mapColor = mapColor;
        this.fireworkColor = fireworkColor;
        this.textColor = textColor;
        this.fallback = fallback;
        this.dyesTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dyes/" + name));
        this.dyedTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dyed/" + name));
    }

    //I'm honestly not 100% sure what all of these next four do at the moment, just copying from the vanilla dye class,
    private static final IntFunction<ModDyeColor> BY_ID = ByIdMap.continuous(ModDyeColor::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    private static final Int2ObjectOpenHashMap<ModDyeColor> BY_FIREWORK_COLOR = new Int2ObjectOpenHashMap<>(
            Arrays.stream(values()).collect(Collectors.toMap(color -> color.fireworkColor, color -> (ModDyeColor)color))
    ); /*I thought the dyecolor cast was redundant when reading the code and then sure enough intelliJ marked it as redundant but just following
        how source wrote it exactly since I'm not very familiar with hash maps or entirely sure what the use case of this is */
    public static final EnumCodec<ModDyeColor> CODEC = StringRepresentable.fromEnum(ModDyeColor::values);
    public static final StreamCodec<ByteBuf, ModDyeColor> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ModDyeColor::getId);

    public int getId() {return id;}

    public String getName() {return name;}

    public MapColor getMapColor() {return mapColor;}

    public int getTextureDiffuseColor() {return textureDiffuseColor;}

    public int getFireworkColor() {return fireworkColor;}

    public int getTextColor() {return textColor;}
    public DyeColor getVanillaEquivalent() {return fallback;}
    public static ModDyeColor[] newDyeValues() {
        ModDyeColor[] colors = ModDyeColor.values();
        return Arrays.copyOfRange(colors, 16, colors.length);
    }
    public static ModDyeColor byID(int colorID) {return BY_ID.apply(colorID);}
    @Nullable
    public static ModDyeColor byFireworkColor(int fireworkColor) {
        return BY_FIREWORK_COLOR.get(fireworkColor);
    }
    public TagKey<Item> getTag() {
        return dyesTag;
    }
    public TagKey<Item> getDyedTag() {
        return dyedTag;
    }
    @Override
    public String toString() { return this.name;}
    @Override
    public String getSerializedName() {return this.name;}

    @Nullable
    public static ModDyeColor byName(String translationKey, @Nullable ModDyeColor fallback) {
        ModDyeColor dyecolor = CODEC.byName(translationKey);
        return dyecolor != null ? dyecolor : fallback;
    }

    /*@Nullable
    public static ModDyeColor getColor(ItemStack stack) {
        if (stack.getItem() instanceof ModDyeItem)
            return ((ModDyeItem)stack.getItem()).getDyeColor();

        for (int x = 0; x < LIGHT_BROWN.getId(); x++) {
            ModDyeColor color = byID(x);
            if (stack.is(color.getTag()))
                return color;
        }

        return null;
    }*/
}
