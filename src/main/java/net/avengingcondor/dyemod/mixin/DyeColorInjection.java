package net.avengingcondor.dyemod.mixin;

import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//UNUSED, SAVED FOR REFERENCE
@Mixin(DyeColor.class)
public class DyeColorInjection {
    @Shadow
    @Mutable
    @Final
    private static DyeColor[] $VALUES;

    @Invoker("<init>")
    public static DyeColor newColor(String name, int id, int j, String string2, int k, MapColor mapColor, int l, int m) {
        throw new AssertionError();
    }

    //Okay so after scrambling for a while to try and figure this out on my own, I just copied the way Dye Depot implemented it
    //(That mod is explicitly marked with a public domain licence CC-1.0, but still want to make sure I make a clear note I didn't come up with this myself)
    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/DyeColor;$VALUES:[Lnet/minecraft/world/item/DyeColor;", shift = At.Shift.AFTER))
    private static void condordyemod$addCustomColor(CallbackInfo ci) {
        List<DyeColor> dyeColors = new ArrayList<>(Arrays.asList($VALUES));
        DyeColor last = dyeColors.getLast();
        int i = 1;
        for (ModDyeColor color : ModDyeColor.newDyeValues()) {
            dyeColors.add(newColor(color.name(), last.ordinal() + i, color.getId(), color.getName(), color.getTextureDiffuseColor(), color.getMapColor(), color.getFireworkColor(), color.getTextColor()));
            i++;
        }
        $VALUES = dyeColors.toArray(new DyeColor[0]);
    }
    //unfortunately, this throws an error in neoforge and I'm not sure how to get around it, scrapping this concept for now
}
