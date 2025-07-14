package net.avengingcondor.dyemod.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.avengingcondor.dyemod.block.ModBlocks;
import net.avengingcondor.dyemod.block.custom.ModShulkerBoxBlock;
import net.avengingcondor.dyemod.entity.ModShulkerBoxBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ModShulkerBoxItemStackRenderer extends BlockEntityWithoutLevelRenderer {
    ModShulkerBoxBlockEntity blockEntity = null;

    public ModShulkerBoxItemStackRenderer() {
        super (Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (blockEntity == null) {
            blockEntity = new ModShulkerBoxBlockEntity(BlockPos.ZERO, ModBlocks.DYED_BLOCKS.get("shulker_box").get("crimson").get().defaultBlockState());
        }
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof ModShulkerBoxBlock shulkerBoxBlock) {
                blockEntity.setDyeColor(shulkerBoxBlock.getDyeColor());

                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, poseStack, buffer, packedLight, packedOverlay);
            }
        }
    }
}
