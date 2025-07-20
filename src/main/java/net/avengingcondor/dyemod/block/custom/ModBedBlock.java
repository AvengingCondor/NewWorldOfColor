package net.avengingcondor.dyemod.block.custom;

import net.avengingcondor.dyemod.entity.ModBedBlockEntity;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModBedBlock extends BedBlock {
    private final ModDyeColor color;

    public ModBedBlock(ModDyeColor color, BlockBehaviour.Properties properties) {
        super(DyeColor.WHITE, properties);
        this.color = color;
        //this.registerDefaultState(((this.stateDefinition.any()).setValue(PART, BedPart.FOOT)).setValue(OCCUPIED, false));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModBedBlockEntity(pos, state, this.color);
    }

    public ModDyeColor getDyeColor() {
        return this.color;
    }
}
