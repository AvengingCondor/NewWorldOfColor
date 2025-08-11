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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ModSheepEntity extends Sheep {
    private static final EntityDataAccessor<Byte> DATA_WOOL_ID = SynchedEntityData.defineId(ModSheepEntity.class, EntityDataSerializers.BYTE);
    private static final Map<ModDyeColor, ItemLike> ITEM_BY_DYE = Util.make(Maps.newEnumMap(ModDyeColor.class), (map) -> {
        for (ModDyeColor color : ModDyeColor.values()) {
            if (color.getId() < 16) //vanilla colors need to get vanilla wool instead
            {
                map.put(color, switch (color.getId()) {
                    case 1 -> Blocks.ORANGE_WOOL;
                    case 2 -> Blocks.MAGENTA_WOOL;
                    case 3 -> Blocks.LIGHT_BLUE_WOOL;
                    case 4 -> Blocks.YELLOW_WOOL;
                    case 5 -> Blocks.LIME_WOOL;
                    case 6 -> Blocks.PINK_WOOL;
                    case 7 -> Blocks.GRAY_WOOL;
                    case 8 -> Blocks.LIGHT_GRAY_WOOL;
                    case 9 -> Blocks.CYAN_WOOL;
                    case 10 -> Blocks.PURPLE_WOOL;
                    case 11 -> Blocks.BLUE_WOOL;
                    case 12 -> Blocks.BROWN_WOOL;
                    case 13 -> Blocks.GREEN_WOOL;
                    case 14 -> Blocks.RED_WOOL;
                    case 15 -> Blocks.BLACK_WOOL;
                    default -> Blocks.WHITE_WOOL;
                });
            }
            else
            {
                map.put(color, ModBlocks.DYED_BLOCKS.get("wool").get(color.getSerializedName()));
            }
        }
    });
    private static final Map<ModDyeColor, Integer> COLOR_BY_DYE = Maps.<ModDyeColor, Integer>newEnumMap(
            Arrays.stream(ModDyeColor.values()).collect(Collectors.toMap(color -> color, ModSheepEntity::createModSheepColor))
    );
    static int createModSheepColor(ModDyeColor dyeColor) {
        int i = dyeColor.getTextureDiffuseColor();
        float f = 0.75F;
        return FastColor.ARGB32.color(
                255,
                Mth.floor((float)FastColor.ARGB32.red(i) * 0.75F),
                Mth.floor((float)FastColor.ARGB32.green(i) * 0.75F),
                Mth.floor((float)FastColor.ARGB32.blue(i) * 0.75F)
        );
    }
    public static int getColor(ModDyeColor dyeColor) {
        return COLOR_BY_DYE.get(dyeColor);
    }

    public ModSheepEntity(EntityType<? extends Sheep> entityType, Level level) {
        super(entityType, level);
    }

    public ModDyeColor getDyeColor() {
        return ModDyeColor.byID(this.entityData.get(DATA_WOOL_ID) & 31);
    }


    public void setColor(ModDyeColor dyeColor) {
        byte b0 = this.entityData.get(DATA_WOOL_ID);
        this.entityData.set(DATA_WOOL_ID, (byte)(b0 & 224 | dyeColor.getId() & 31));
    }  //don't *fully* understand what this byte trickery from the base sheep code is doing, but the number 224 comes from following the same logic as vanilla, which used 240 (256, max value of one byte, minus 16. So here it's -32)

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

    //this was from an old method of having the mod sheep only handle new colors rather than just replacing all, kept just in case
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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_WOOL_ID, (byte) 0);
        //might not work, duplicate data since the sheep class super also defines "DATA_WOOL_ID"? (seemingly does work, leaving alone)
    }

    @Override
    public ResourceKey<LootTable> getDefaultLootTable() {
        if (this.isSheared()) {
            return this.getType().getDefaultLootTable();
        }
        return ModEntities.SHEEP_LOOT.get(this.getDyeColor().getSerializedName());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() instanceof DyeItem)
        {
            ModDyeColor dyeColor = ModDyeColor.byID(((DyeItem) itemstack.getItem()).getDyeColor().getId());
            if (this.isAlive() && !this.isSheared() && this.getDyeColor() != dyeColor)
            {
                this.level().playSound(player, this, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (!player.level().isClientSide) {
                    this.setColor(dyeColor);
                    itemstack.shrink(1);
                }
                return InteractionResult.sidedSuccess(player.level().isClientSide);
            }
            else
            {
                return InteractionResult.CONSUME;
            }
        }
        else if (itemstack.is(Items.SHEEP_SPAWN_EGG)) //getItem().equals
        {
            if (!this.level().isClientSide)
            {
                ModSheepEntity sheep = new ModSheepEntity(ModEntities.SHEEP.get(), this.level());
                sheep.copyPosition(this);
                sheep.setBaby(true);
                sheep.setColor(this.getDyeColor());
                this.level().addFreshEntity(sheep);
                itemstack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            else
            {
                return InteractionResult.CONSUME;
            }
        }
        else if (itemstack.is(Items.SHEARS)) {
            if (!this.level().isClientSide && this.readyForShearing()) {
                this.shear(SoundSource.PLAYERS);
                this.gameEvent(GameEvent.SHEAR, player);
                itemstack.hurtAndBreak(1, player, getSlotForHand(hand));
                return InteractionResult.SUCCESS;
            } else {
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
            ItemEntity itementity = this.spawnAtLocation(ITEM_BY_DYE.get(this.getDyeColor()), 1);
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
        this.setColor(ModDyeColor.byID(compound.getByte("DyeColor")));
    }

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
            this.entityData.set(DATA_WOOL_ID, (byte)(b0 & -33));
        }
    }

    public ModSheepEntity getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        ModSheepEntity sheep = new ModSheepEntity(ModEntities.SHEEP.get(), level);
        if (sheep != null) {
            sheep.setColor(this.getOffspringColor(this, (Sheep)otherParent));
        }

        return sheep;
    }

    private ModDyeColor getOffspringColor(Animal father, Animal mother) {
        ModDyeColor fatherColor = ((ModSheepEntity)father).getDyeColor();
        ModDyeColor motherColor = ((ModSheepEntity)mother).getDyeColor();

        return tryMixingColor(fatherColor, motherColor, father);
    }

    private static ModDyeColor tryMixingColor(ModDyeColor color1, ModDyeColor color2, Animal sheep) {
        if (color1.equals(color2))
            return color1;
        else
        {
            if (color1.equals(ModDyeColor.RED) || color2.equals(ModDyeColor.RED))
            {
                if (color1.equals(ModDyeColor.YELLOW) || color2.equals(ModDyeColor.YELLOW))
                    return ModDyeColor.ORANGE;
                else if (color1.equals(ModDyeColor.BLUE) || color2.equals(ModDyeColor.BLUE))
                    return ModDyeColor.PURPLE;
                else if (color1.equals(ModDyeColor.WHITE) || color2.equals(ModDyeColor.WHITE))
                    return ModDyeColor.PINK;
                else if (color1.equals(ModDyeColor.BLACK) || color2.equals(ModDyeColor.BLACK))
                    return ModDyeColor.CRIMSON;
                else if (color1.equals(ModDyeColor.ORANGE) || color2.equals(ModDyeColor.ORANGE))
                    return ModDyeColor.VERMILION;
            }
            else if (color1.equals(ModDyeColor.GREEN) || color2.equals(ModDyeColor.GREEN))
            {
                if (color1.equals(ModDyeColor.YELLOW) || color2.equals(ModDyeColor.YELLOW))
                    return ModDyeColor.LIME;
                else if (color1.equals(ModDyeColor.BLUE) || color2.equals(ModDyeColor.BLUE))
                    return ModDyeColor.CYAN;
                else if (color1.equals(ModDyeColor.BROWN) || color2.equals(ModDyeColor.BROWN))
                    return ModDyeColor.OLIVE;
                else if (color1.equals(ModDyeColor.BLACK) || color2.equals(ModDyeColor.BLACK))
                    return ModDyeColor.JADE;
                else if (color1.equals(ModDyeColor.WHITE) || color2.equals(ModDyeColor.WHITE))
                    return ModDyeColor.LIGHT_GREEN;
                else if (color1.equals(ModDyeColor.CYAN) || color2.equals(ModDyeColor.CYAN))
                    return ModDyeColor.TEAL;
            }
            else if (color1.equals(ModDyeColor.WHITE) || color2.equals(ModDyeColor.WHITE)) {
                if (color1.equals(ModDyeColor.BLACK) || color2.equals(ModDyeColor.BLACK))
                    return ModDyeColor.GRAY;
                else if (color1.equals(ModDyeColor.GRAY) || color2.equals(ModDyeColor.GRAY))
                    return ModDyeColor.LIGHT_GRAY;
                else if (color1.equals(ModDyeColor.BLUE) || color2.equals(ModDyeColor.BLUE))
                    return ModDyeColor.AZURE;
                else if (color1.equals(ModDyeColor.AZURE) || color2.equals(ModDyeColor.AZURE))
                    return ModDyeColor.LIGHT_BLUE;
                else if (color1.equals(ModDyeColor.BROWN) || color2.equals(ModDyeColor.BROWN))
                    return ModDyeColor.LIGHT_BROWN;
                else if (color1.equals(ModDyeColor.TEAL) || color2.equals(ModDyeColor.TEAL))
                    return ModDyeColor.TURQUOISE;
                else if (color1.equals(ModDyeColor.PURPLE) || color2.equals(ModDyeColor.PURPLE))
                    return ModDyeColor.MAUVE;
            }
            else if (color1.equals(ModDyeColor.PURPLE) || color2.equals(ModDyeColor.PURPLE)) {
                if (color1.equals(ModDyeColor.PINK) || color2.equals(ModDyeColor.PINK))
                    return ModDyeColor.MAGENTA;
                else if (color1.equals(ModDyeColor.BLUE) || color2.equals(ModDyeColor.BLUE))
                    return ModDyeColor.INDIGO;
                else if (color1.equals(ModDyeColor.CRIMSON) || color2.equals(ModDyeColor.CRIMSON))
                    return ModDyeColor.FUCHSIA;
            }
            else if (color1.equals(ModDyeColor.ORANGE) || color2.equals(ModDyeColor.ORANGE)) {
                if (color1.equals(ModDyeColor.PINK) || color2.equals(ModDyeColor.PINK))
                    return ModDyeColor.CORAL;
                else if (color1.equals(ModDyeColor.YELLOW) || color2.equals(ModDyeColor.YELLOW))
                    return ModDyeColor.AMBER;
            }
            else if ((color1.equals(ModDyeColor.YELLOW) || color2.equals(ModDyeColor.YELLOW)) && (color1.equals(ModDyeColor.LIME) || color2.equals(ModDyeColor.LIME)))
                return ModDyeColor.CHARTREUSE;
        }
        return sheep.getRandom().nextBoolean() ? color1 : color2;
    }

    @Override
    public ItemStack getPickResult() {
        SpawnEggItem spawnEggItem = SpawnEggItem.byId(EntityType.SHEEP);
        return spawnEggItem == null ? null : new ItemStack(spawnEggItem);
    }
}
