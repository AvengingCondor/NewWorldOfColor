package net.avengingcondor.dyemod.entity;

import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

//feels like more should be needed here but fuck it we ball for now
public class ModShulkerBoxBlockEntity extends ShulkerBoxBlockEntity {
    private ModDyeColor color;

    public ModShulkerBoxBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(DyeColor.WHITE, pos, blockState);
    }//Needed for init for some reason I think?

    public ModShulkerBoxBlockEntity(ModDyeColor color, BlockPos pos, BlockState blockState)
    {
        super(DyeColor.WHITE, pos, blockState);
        this.color = color;
    }

    @Nonnull
    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.SHULKER_BOX.get();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ModDyeColor getDyeColor() {
        return color;
    }
    public void setDyeColor(ModDyeColor color) {this.color = color;}
}
