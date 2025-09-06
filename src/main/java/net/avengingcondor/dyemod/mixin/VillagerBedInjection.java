package net.avengingcondor.dyemod.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

//UNUSED, SAVED FOR REFERENCE
@Mixin(PoiTypes.class)
public class VillagerBedInjection {
    @Shadow
    private static void registerBlockStates(Holder<PoiType> poi, Set<BlockState> states) {};

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void onPoiTypeRegister(Registry<PoiType> key, ResourceKey<PoiType> value, Set<BlockState> matchingStates, int maxTickets, int validRange, CallbackInfoReturnable<PoiType> cir) {
        if (value == PoiTypes.HOME)
        {
            Set<BlockState> beds = ImmutableList.of(
                            Blocks.RED_BED,
                            Blocks.BLACK_BED,
                            Blocks.BLUE_BED,
                            Blocks.BROWN_BED,
                            Blocks.CYAN_BED,
                            Blocks.GRAY_BED,
                            Blocks.GREEN_BED,
                            Blocks.LIGHT_BLUE_BED,
                            Blocks.LIGHT_GRAY_BED,
                            Blocks.LIME_BED,
                            Blocks.MAGENTA_BED,
                            Blocks.ORANGE_BED,
                            Blocks.PINK_BED,
                            Blocks.PURPLE_BED,
                            Blocks.WHITE_BED,
                            Blocks.YELLOW_BED/*,
                            ModBlocks.DYED_BLOCKS.get("bed").get("crimson").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("vermilion").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("amber").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("chartreuse").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("olive").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("light_green").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("jade").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("teal").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("turquoise").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("azure").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("indigo").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("mauve").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("fuchsia").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("coral").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("burgundy").value(),
                            ModBlocks.DYED_BLOCKS.get("bed").get("light_brown").value()*/
                    )
                    .stream()
                    .flatMap(p_218097_ -> p_218097_.getStateDefinition().getPossibleStates().stream())
                    .filter(p_218095_ -> p_218095_.getValue(BedBlock.PART) == BedPart.HEAD)
                    .collect(ImmutableSet.toImmutableSet());
            PoiType poitype = new PoiType(beds, maxTickets, validRange);
            Registry.register(key, value, poitype);
            registerBlockStates(key.getHolderOrThrow(value), matchingStates);

            cir.setReturnValue(poitype);
        }
    }
}
