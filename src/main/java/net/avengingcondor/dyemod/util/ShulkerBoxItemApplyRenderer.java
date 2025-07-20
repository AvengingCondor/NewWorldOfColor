package net.avengingcondor.dyemod.util;


import net.avengingcondor.dyemod.render.ModShulkerBoxItemStackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ShulkerBoxItemApplyRenderer implements IClientItemExtensions {
    private final ModShulkerBoxItemStackRenderer shulkerRenderer = new ModShulkerBoxItemStackRenderer();

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return shulkerRenderer;
    }
}
