package net.avengingcondor.dyemod.block.custom;

import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ModStainedGlassPaneBlock extends IronBarsBlock implements BeaconBeamBlock {

    public ModStainedGlassPaneBlock(ModDyeColor glassColor, Properties properties) {
        super(properties);
        this.color = glassColor;
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(NORTH, Boolean.FALSE)
                        .setValue(EAST, Boolean.FALSE)
                        .setValue(SOUTH, Boolean.FALSE)
                        .setValue(WEST, Boolean.FALSE)
                        .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }
    private final ModDyeColor color;

    //same as normal glass pane, the overridden getColor is just from implementing the BBB interface as a precaution
    @Override
    public DyeColor getColor() { return DyeColor.WHITE;}
    public ModDyeColor getTrueColor() { return this.color; }

    @Override
    public @Nullable Integer getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        return this.color.getTextureDiffuseColor();
    }
}
