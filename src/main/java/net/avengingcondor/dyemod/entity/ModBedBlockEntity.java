package net.avengingcondor.dyemod.entity;

import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModBedBlockEntity extends BlockEntity {
    private ModDyeColor color;

    public ModBedBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BED.get(), pos, blockState);
    }

    public ModBedBlockEntity(BlockPos pos, BlockState blockState, ModDyeColor color) {
        this(pos, blockState);
        this.color = color;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ModDyeColor getDyeColor() {
        return this.color;
    }

    public void setDyeColor(ModDyeColor color) {
        this.color = color;
    }
}
