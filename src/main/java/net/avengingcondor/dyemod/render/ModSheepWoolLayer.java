package net.avengingcondor.dyemod.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.avengingcondor.dyemod.entity.custom.ModSheepEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;

public class ModSheepWoolLayer extends RenderLayer<ModSheepEntity, SheepModel<ModSheepEntity>> {
    protected static final ResourceLocation SHEEP_FUR_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep_fur.png");
    protected final SheepFurModel<ModSheepEntity> model;

    public ModSheepWoolLayer(RenderLayerParent<ModSheepEntity, SheepModel<ModSheepEntity>> rendererIn, EntityModelSet modelSet) {
        super(rendererIn);
        this.model = new SheepFurModel<>(modelSet.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    public void render(
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight,
            ModSheepEntity sheep,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        if (!sheep.isSheared()) {
            if (sheep.isInvisible()) {
                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = minecraft.shouldEntityAppearGlowing(sheep);
                if (flag) {
                    this.getParentModel().copyPropertiesTo(this.model);
                    this.model.prepareMobModel(sheep, limbSwing, limbSwingAmount, partialTicks);
                    this.model.setupAnim(sheep, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(SHEEP_FUR_LOCATION));
                    this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(sheep, 0.0F), -16777216);
                }
            } else {
                int i;
                if (sheep.hasCustomName() && "jeb_".equals(sheep.getName().getString())) {
                    int j = 25;
                    int k = sheep.tickCount / 25 + sheep.getId();
                    int l = DyeColor.values().length;
                    int i1 = k % l;
                    int j1 = (k + 1) % l;
                    float f = ((float)(sheep.tickCount % 25) + partialTicks) / 25.0F;
                    int k1 = Sheep.getColor(DyeColor.byId(i1));
                    int l1 = Sheep.getColor(DyeColor.byId(j1));
                    i = FastColor.ARGB32.lerp(f, k1, l1);
                }
                else {
                    i = sheep.getDyeColor().getTextureDiffuseColor();
                }

                coloredCutoutModelCopyLayerRender(
                        this.getParentModel(),
                        this.model,
                        SHEEP_FUR_LOCATION,
                        poseStack,
                        buffer,
                        packedLight,
                        sheep,
                        limbSwing,
                        limbSwingAmount,
                        ageInTicks,
                        netHeadYaw,
                        headPitch,
                        partialTicks,
                        i
                );
            }
        }
    }
}
