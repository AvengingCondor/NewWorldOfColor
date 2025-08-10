package net.avengingcondor.dyemod.entity.custom;

import com.google.common.collect.Maps;
import net.avengingcondor.dyemod.block.ModBlocks;
import net.avengingcondor.dyemod.entity.ModEntities;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Map;

public class ModSheepEntity extends Sheep {
    private static final EntityDataAccessor<Byte> DATA_WOOL_ID = SynchedEntityData.defineId(ModSheepEntity.class, EntityDataSerializers.BYTE);
    private static final Map<ModDyeColor, ItemLike> WOOL_BY_DYE = Util.make(Maps.newEnumMap(ModDyeColor.class), (map) -> {
        for (ModDyeColor color : ModDyeColor.newDyeValues()) {
            map.put(color, ModBlocks.DYED_BLOCKS.get("wool").get(color.getSerializedName()));
        }
    });


    public ModSheepEntity(EntityType<? extends Sheep> entityType, Level level) {
        super(entityType, level);
    }

    //I'll be 100% for real, I don't follow what these byte operations are doing and I'm just blindly winging it based on the base minecraft sheep class and a different mod that adds more dye using a similar implementation
    public ModDyeColor getDyeColor() {
        return ModDyeColor.byID(this.entityData.get(DATA_WOOL_ID) & 31);
    }

    public void setColor(ModDyeColor dyeColor) {
        byte b0 = this.entityData.get(DATA_WOOL_ID);
        this.entityData.set(DATA_WOOL_ID, (byte)(b0 & 240 | dyeColor.getId() & 31));
    }

    private static void convertSheep(Sheep originalSheep, Sheep newSheep) {
        Level level = originalSheep.level();
        if (!level.isClientSide) {
            newSheep.setAge(originalSheep.getAge());
            if (originalSheep.hasCustomName()) newSheep.setCustomName(originalSheep.getCustomName());
            level.addFreshEntity(newSheep);
            originalSheep.remove(RemovalReason.DISCARDED);
            newSheep.copyPosition(originalSheep);
        }
    }

    public static void convertToVanilla(ModSheepEntity originalSheep, DyeColor color) {
        Level level = originalSheep.level();
        if (!level.isClientSide) {
            Sheep newSheep = new Sheep(EntityType.SHEEP, level);
            newSheep.setColor(color);
            convertSheep(originalSheep, newSheep);
        }
    }
    public static void convertToNewColors(Sheep originalSheep, ModDyeColor color) {
        Level level = originalSheep.level();
        if (!level.isClientSide) {
            ModSheepEntity newSheep = new ModSheepEntity(ModEntities.SHEEP.get(), level);
            newSheep.setColor(color);
            convertSheep(originalSheep, newSheep);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, Sheep.class));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_WOOL_ID, (byte) 0);
        //might not work, duplicate data since the sheep class super also define's "DATA_WOOL_ID"?
    }

    @Override
    public ResourceKey<LootTable> getDefaultLootTable() {
        if (this.isSheared()) {
            return this.getType().getDefaultLootTable();
        }
        return ModEntities.SHEEP_LOOT.get(this.getDyeColor().getSerializedName());
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() instanceof DyeItem)
        {
            //if a vanilla dye is used on the mod version of the sheep, converts it back to a normal sheep with the appropriate color
            if (!this.level().isClientSide)
            {
                ModSheepEntity.convertToVanilla(this, ((DyeItem) itemstack.getItem()).getDyeColor());
                itemstack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            else
            {
                return InteractionResult.CONSUME;
            }
        }
        else if (itemstack.getItem().equals(Items.SHEEP_SPAWN_EGG))
        {
            if (!this.level().isClientSide)
            {
                ModSheepEntity sheep = new ModSheepEntity(ModEntities.SHEEP.get(), this.level());
                sheep.copyPosition(this);
                sheep.setBaby(true);
                sheep.setColor(this.getDyeColor());
                this.level().addFreshEntity(sheep);
                if (!player.isCreative()) itemstack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            else
            {
                return InteractionResult.CONSUME;
            }
        }
        else
        {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void shear(SoundSource category) {
        this.level().playSound(null, this, SoundEvents.SHEEP_SHEAR, category, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);

        for (int j = 0; j < i; j++) {
            ItemEntity itementity = this.spawnAtLocation(WOOL_BY_DYE.get(this.getDyeColor()), 1);
            if (itementity != null) {
                itementity.setDeltaMovement(
                        itementity.getDeltaMovement()
                                .add(
                                        (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F),
                                        (double)(this.random.nextFloat() * 0.05F),
                                        (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)
                                )
                );
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("DyeColor", (byte)this.getDyeColor().getId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setColor(DyeColor.byId(compound.getByte("DyeColor")));
    }

    /*@Override
    public boolean isSheared() {
        return this.entityData.get(DATA_WOOL_ID) < 0;
    }*/
    @Override
    public boolean isSheared() {
        return (this.entityData.get(DATA_WOOL_ID) & 32) != 0;
    }

    @Override
    public void setSheared(boolean sheared) {
        byte b0 = this.entityData.get(DATA_WOOL_ID);
        if (sheared) {
            this.entityData.set(DATA_WOOL_ID, (byte)(b0 | 32));
        } else {
            this.entityData.set(DATA_WOOL_ID, (byte)(b0 & -17)); //HUH??? Where does -17 come from?
        }
    }

    //Todo: breeding

    //Todo: shearing? the base sheep class actually doesn't have anything dictating drops from sheering

    @Override
    public ItemStack getPickResult() {
        SpawnEggItem spawnEggItem = SpawnEggItem.byId(EntityType.SHEEP);
        return spawnEggItem == null ? null : new ItemStack(spawnEggItem);
    }
}
