package net.avengingcondor.dyemod.render;

import net.avengingcondor.dyemod.entity.custom.ModSheepEntity;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ModSheepEntityRenderer extends MobRenderer<ModSheepEntity, SheepModel<ModSheepEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep.png");

    public ModSheepEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new ModSheepWoolLayer(this, context.getModelSet()));
    }

    public ResourceLocation getTextureLocation(ModSheepEntity sheep) {
        return SHEARED_SHEEP_TEXTURES;
    }
}
