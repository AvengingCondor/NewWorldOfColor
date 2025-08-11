package net.avengingcondor.dyemod.util;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.entity.custom.ModSheepEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = DyeMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void replaceSheepOnSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity().getClass().equals(Sheep.class))
        {
            Sheep sheep = (Sheep) event.getEntity();
            ModDyeColor color = ModDyeColor.byID(sheep.getColor().getId());
            ModSheepEntity.convertToNewColors(sheep, color);
            event.setCanceled(true);
        } //intercepts sheep whenever they try to spawn and replaces them with a modded duplicate that supports the new colors
    }
}
