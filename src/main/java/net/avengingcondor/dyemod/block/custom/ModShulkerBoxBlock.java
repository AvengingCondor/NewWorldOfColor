package net.avengingcondor.dyemod.block.custom;

import net.avengingcondor.dyemod.block.ModBlocks;
import net.avengingcondor.dyemod.entity.ModBlockEntities;
import net.avengingcondor.dyemod.entity.ModShulkerBoxBlockEntity;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class ModShulkerBoxBlock extends ShulkerBoxBlock {
    private final ModDyeColor color;

    public ModShulkerBoxBlock (ModDyeColor color, BlockBehaviour.Properties properties)
    {
        super(DyeColor.WHITE, properties);
        this.color = color;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModShulkerBoxBlockEntity(this.color, pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.SHULKER_BOX.get(), ShulkerBoxBlockEntity::tick);
    }

    public ModDyeColor getDyeColor() {
        return color;
    }

    public static ItemStack getDyedItemStack(ModDyeColor color) {
        return new ItemStack(ModBlocks.DYED_BLOCKS.get("shulker_box").get(color.getName()).get());
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ModShulkerBoxBlockEntity shulkerboxblockentity) {
            if (!level.isClientSide && player.isCreative() && !shulkerboxblockentity.isEmpty()) {
                ItemStack itemstack = getDyedItemStack(this.getDyeColor());
                itemstack.applyComponents(blockentity.collectComponents());
                ItemEntity itementity = new ItemEntity(level, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            } else {
                shulkerboxblockentity.unpackLootTable(player);
            }
        }

        /*
        This  last part is copied directly from the original Block class method because I couldn't call the ShulkerBoxBlock version with super or it
        caused a niche bug where breaking shulker boxes in creative dropped a white duplicate of the shulker box, since that method includes the same logic as
        above but with the basic getColor instead of modded version. So it's a rare case where I needed the *grandparent* version of a method overridden by parent,
        which there is no way to directly call in java to my knowledge
         */
        this.spawnDestroyParticles(level, player, pos, state);
        if (state.is(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinAi.angerNearbyPiglins(player, false);
        }

        level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));
        return state;
        //return super.playerWillDestroy(level, pos, state, player);
    }
}
