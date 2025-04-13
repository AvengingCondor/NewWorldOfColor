package net.avengingcondor.dyemod.item.custom;

import com.google.common.collect.Maps;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import java.util.Map;

//recreating vanilla DyeItem class just with expanded color enum used instead
public class ModDyeItem extends Item implements SignApplicator {
    private static final Map<ModDyeColor, ModDyeItem> ITEM_BY_COLOR = Maps.newEnumMap(ModDyeColor.class);
    private final ModDyeColor dyeColor;

    public ModDyeItem(ModDyeColor dyeColor, Item.Properties properties) {
        super(properties);
        this.dyeColor = dyeColor;
        ITEM_BY_COLOR.put(dyeColor, this);
    }

    /**
     * Try interacting with given entity. Return {@code InteractionResult.PASS} if nothing should happen.
     */
    /*@Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof Sheep sheep && sheep.isAlive() && !sheep.isSheared() && sheep.getColor() != this.dyeColor) {
            sheep.level().playSound(player, sheep, SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!player.level().isClientSide) {
                sheep.setColor(this.dyeColor);
                stack.shrink(1);
            }

            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }

        return InteractionResult.PASS;
    }*/

    public ModDyeColor getDyeColor() {
        return this.dyeColor;
    }

    public static ModDyeItem byColor(ModDyeColor color) {
        return ITEM_BY_COLOR.get(color);
    }

    @Override
    public boolean tryApplyToSign(Level level, SignBlockEntity sign, boolean isFront, Player player) {
        if (sign.updateText(text -> text.setColor(this.dyeColor.getVanillaEquivalent()), isFront)) {
            level.playSound(null, sign.getBlockPos(), SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }
}
