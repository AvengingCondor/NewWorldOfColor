package net.avengingcondor.dyemod.recipe;

import net.avengingcondor.dyemod.DyeMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializer
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, DyeMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ModShulkerBoxDyingRecipe>> SHULKER_BOX = RECIPE_SERIALIZER.register("shulker_box_dye", () -> new SimpleCraftingRecipeSerializer<>(ModShulkerBoxDyingRecipe::new));
}
