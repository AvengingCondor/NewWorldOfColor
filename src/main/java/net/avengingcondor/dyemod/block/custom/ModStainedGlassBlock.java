package net.avengingcondor.dyemod.block.custom;

import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ModStainedGlassBlock extends TransparentBlock implements BeaconBeamBlock {
    public ModStainedGlassBlock(ModDyeColor glassColor, Properties properties) {
        super(properties);
        this.color = glassColor;
    }

    private final ModDyeColor color;

    /*
        The "BeaconBeamBlock" interface is included as a precaution, because the vanilla stained glass classes use it.
        However, the override for beacon color below seems to work on its own and this necessary "getColor" method that
        serves as the only thing the BeaconBeamBlock interface includes doesn't seem to actually do anything here, hence the placeholder return value
     */
    @Override
    public DyeColor getColor() {
        return DyeColor.WHITE;
    }
    public ModDyeColor getTrueColor() { return this.color; }

    @Override
    public @Nullable Integer getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        return this.color.getTextureDiffuseColor();
    }
}
