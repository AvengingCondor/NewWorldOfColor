package net.avengingcondor.dyemod.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.entity.ModShulkerBoxBlockEntity;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModShulkerBoxBlockEntityRenderer implements BlockEntityRenderer<ModShulkerBoxBlockEntity> {
    private final ShulkerModel<?> model;

    public ModShulkerBoxBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new ShulkerModel(context.bakeLayer(ModelLayers.SHULKER));
    }

    public void render(ModShulkerBoxBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = Direction.UP;
        if (blockEntity.hasLevel()) {
            BlockState blockstate = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos());
            if (blockstate.getBlock() instanceof ShulkerBoxBlock) {
                direction = blockstate.getValue(ShulkerBoxBlock.FACING);
            }
        }

        ModDyeColor dyecolor = blockEntity.getDyeColor();
        Material material;
        if (dyecolor == null) {
            material = Sheets.DEFAULT_SHULKER_TEXTURE_LOCATION;
        } else {
            material = DyeMod.SHULKER_MATERIAL_MAP.get(dyecolor.getId());
        }

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.scale(0.9995F, 0.9995F, 0.9995F);
        poseStack.mulPose(direction.getRotation());
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.translate(0.0F, -1.0F, 0.0F);
        ModelPart modelpart = this.model.getLid();
        modelpart.setPos(0.0F, 24.0F - blockEntity.getProgress(partialTick) * 0.5F * 16.0F, 0.0F);
        modelpart.yRot = 270.0F * blockEntity.getProgress(partialTick) * ((float)Math.PI / 180F);
        VertexConsumer vertexconsumer = material.buffer(bufferSource, RenderType::entityCutoutNoCull);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay, -1);
        poseStack.popPose();
    }
}
