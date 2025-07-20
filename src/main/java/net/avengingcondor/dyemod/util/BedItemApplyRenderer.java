package net.avengingcondor.dyemod.util;


import net.avengingcondor.dyemod.render.ModBedItemStackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class BedItemApplyRenderer implements IClientItemExtensions {
    private final ModBedItemStackRenderer bedRenderer = new ModBedItemStackRenderer();

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return bedRenderer;
    }
}
