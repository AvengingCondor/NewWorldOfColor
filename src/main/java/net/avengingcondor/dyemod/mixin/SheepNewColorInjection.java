package net.avengingcondor.dyemod.mixin;

import com.google.common.collect.Maps;
import net.avengingcondor.dyemod.block.ModBlocks;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.Util;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(Sheep.class)
public abstract class SheepNewColorInjection extends Animal /*implements ModSheepUtil*/ {

    @Unique
    private static final Map<ModDyeColor, ItemLike> MOD_ITEM_BY_DYE = Util.make(Maps.newEnumMap(ModDyeColor.class), (enumMap) -> {
        enumMap.put(ModDyeColor.CRIMSON, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.CRIMSON.getSerializedName()));
        enumMap.put(ModDyeColor.VERMILION, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.VERMILION.getSerializedName()));
        enumMap.put(ModDyeColor.AMBER, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.AMBER.getSerializedName()));
        enumMap.put(ModDyeColor.CHARTREUSE, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.CHARTREUSE.getSerializedName()));
        enumMap.put(ModDyeColor.OLIVE, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.OLIVE.getSerializedName()));
        enumMap.put(ModDyeColor.LIGHT_GREEN, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.LIGHT_GREEN.getSerializedName()));
        enumMap.put(ModDyeColor.JADE, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.JADE.getSerializedName()));
        enumMap.put(ModDyeColor.TEAL, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.TEAL.getSerializedName()));
        enumMap.put(ModDyeColor.TURQUOISE, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.TURQUOISE.getSerializedName()));
        enumMap.put(ModDyeColor.AZURE, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.AZURE.getSerializedName()));
        enumMap.put(ModDyeColor.INDIGO, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.INDIGO.getSerializedName()));
        enumMap.put(ModDyeColor.MAUVE, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.MAUVE.getSerializedName()));
        enumMap.put(ModDyeColor.FUCHSIA, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.FUCHSIA.getSerializedName()));
        enumMap.put(ModDyeColor.CORAL, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.CORAL.getSerializedName()));
        enumMap.put(ModDyeColor.BURGUNDY, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.BURGUNDY.getSerializedName()));
        enumMap.put(ModDyeColor.LIGHT_BROWN, ModBlocks.DYED_BLOCKS.get("wool").get(ModDyeColor.LIGHT_BROWN.getSerializedName()));
    });
    /*@Unique
    private final Map<ModDyeColor, Integer> MOD_COLOR_BY_DYE = Maps.<ModDyeColor, Integer>newEnumMap(
            Arrays.stream(ModDyeColor.newDyeValues()).collect(Collectors.toMap(color -> (ModDyeColor)color, ModSheepUtil::createModSheepColor))
    );*/
    @Unique
    private int neoForge_DyeMod$dyeID = 0;

    //this is just required so that the IDE won't yell at me, it's never used for anything
    protected SheepNewColorInjection(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    //adds a check to getColor to redirect to the needed ModDyeColor value instead if the sheep does not have a vanilla color
    /*@Inject(method = "getColor", at = @At("HEAD"), cancellable = true)
    protected void onGetColor(CallbackInfoReturnable<Integer> cir) {
        if (neoForge_DyeMod$dyeID > 15)
        {
            ModDyeColor color = switch (neoForge_DyeMod$dyeID) {
                case 16 -> ModDyeColor.CRIMSON;
                case 17 -> ModDyeColor.VERMILION;
                case 18 -> ModDyeColor.AMBER;
                case 19 -> ModDyeColor.CHARTREUSE;
                case 20 -> ModDyeColor.OLIVE;
                case 21 -> ModDyeColor.LIGHT_GREEN;
                case 22 -> ModDyeColor.JADE;
                case 23 -> ModDyeColor.TEAL;
                case 24 -> ModDyeColor.TURQUOISE;
                case 25 -> ModDyeColor.AZURE;
                case 26 -> ModDyeColor.INDIGO;
                case 27 -> ModDyeColor.MAUVE;
                case 28 -> ModDyeColor.FUCHSIA;
                case 29 -> ModDyeColor.BURGUNDY;
                case 30 -> ModDyeColor.CORAL;
                case 31 -> ModDyeColor.LIGHT_BROWN;
                default -> ModDyeColor.CRIMSON;
            };
            cir.setReturnValue(MOD_COLOR_BY_DYE.get(color));
        }
    }*/
}
