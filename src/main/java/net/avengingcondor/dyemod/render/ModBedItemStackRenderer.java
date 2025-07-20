package net.avengingcondor.dyemod.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.avengingcondor.dyemod.block.ModBlocks;
import net.avengingcondor.dyemod.block.custom.ModBedBlock;
import net.avengingcondor.dyemod.entity.ModBedBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ModBedItemStackRenderer extends BlockEntityWithoutLevelRenderer {
    ModBedBlockEntity blockEntity = null;

    public ModBedItemStackRenderer() {
        super (Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (blockEntity == null) {
            blockEntity = new ModBedBlockEntity(BlockPos.ZERO, ModBlocks.DYED_BLOCKS.get("bed").get("crimson").get().defaultBlockState());
        }
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof ModBedBlock modBedBlock) {
                blockEntity.setDyeColor(modBedBlock.getDyeColor());

                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, poseStack, buffer, packedLight, packedOverlay);
            }
        }
    }
}
