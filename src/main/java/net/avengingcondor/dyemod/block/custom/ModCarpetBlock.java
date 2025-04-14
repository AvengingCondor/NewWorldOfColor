package net.avengingcondor.dyemod.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModCarpetBlock extends CarpetBlock /*implements Equipable*/ {
    public static final MapCodec<ModCarpetBlock> CODEC = RecordCodecBuilder.mapCodec(
            color -> color.group(ModDyeColor.CODEC.fieldOf("color").forGetter(ModCarpetBlock::getColor), propertiesCodec())
                    .apply(color, ModCarpetBlock::new)
    ); //copied from normal game carpet block, not sure its actually needed here
    private final ModDyeColor color;
    int encouragement = 60; //spread speed, that's what it's labeled in fire class
    int flammability = 20;

    public ModCarpetBlock(ModDyeColor color, Properties properties) {
        super(properties);
        this.color = color;
    }

    @Override
    public MapCodec<ModCarpetBlock> codec() {
        return CODEC;
    }

    public ModDyeColor getColor() {return color;}

    /*
    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.BODY;
    }

    @Override
    public Holder<SoundEvent> getEquipSound() {
        return SoundEvents.LLAMA_SWAG;
    }*/

    //same methods as FlammableBlock
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability;
    }
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return encouragement;
    }
}
