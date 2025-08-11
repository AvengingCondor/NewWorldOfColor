package net.avengingcondor.dyemod.entity;

import net.avengingcondor.dyemod.DyeMod;
import net.avengingcondor.dyemod.entity.custom.ModSheepEntity;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, DyeMod.MOD_ID);
    public static final DeferredHolder<EntityType<?>, EntityType<ModSheepEntity>> SHEEP = ENTITY_TYPE.register("sheep", () -> EntityType.Builder.of(ModSheepEntity::new, MobCategory.CREATURE).sized(0.9F, 1.3F).eyeHeight(1.235F).passengerAttachments(1.2375F).clientTrackingRange(10).build("sheep"));
    public static final Map<String, ResourceKey<LootTable>> SHEEP_LOOT = new HashMap<>();

    public static void register() {
        for (ModDyeColor color : ModDyeColor.values()) {
            SHEEP_LOOT.put(color.getSerializedName(), ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(DyeMod.MOD_ID, "entities/sheep/" + color.getSerializedName())));
        }
    }
}
