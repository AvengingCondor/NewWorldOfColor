package net.avengingcondor.dyemod.recipe;

import net.avengingcondor.dyemod.block.custom.ModShulkerBoxBlock;
import net.avengingcondor.dyemod.item.custom.ModDyeItem;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class ModShulkerBoxDyingRecipe extends CustomRecipe
{
    public ModShulkerBoxDyingRecipe(CraftingBookCategory category) {
        super(category);
    }


    @Override
    public boolean matches(CraftingInput input, Level level) {
        int numberOfShulkers = 0; //needs to be blocked if there's more than one slot with either of these in the crafting grid, so can't just use boolean
        int numberofDye = 0;

        //if (input.size() > 2) return false; //if there are more than two items, already know it's invalid

        for (int i = 0; i < input.size(); i++)
        {
            ItemStack itemInSlot = input.getItem(i);
            if (!itemInSlot.isEmpty())
            {
                Item item = itemInSlot.getItem();
                if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock)
                {
                    numberOfShulkers++;
                    if (numberOfShulkers > 1) return false;
                }
                else if (item instanceof ModDyeItem) //only need mod dyes because vanilla recipe actually covery dying modded boxes to vanilla colors fine
                {
                    numberofDye++;
                    if (numberofDye > 1) return false;
                }
                else return false; //reached if an item in the grid was not dye or a shulker box
            }
        }
        return numberofDye == 1 && numberOfShulkers == 1;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack shulker = ItemStack.EMPTY;
        ModDyeItem dye = null;
        //needs to be a loop because the way the craftingInput is handled means if the items aren't right next to each other on the grid there are empty "air" slots technically included in the recipe
        for (int i = 0; i < input.size(); i++)
        {
            ItemStack itemInSlot = input.getItem(i);
            if (!itemInSlot.isEmpty())
            {
                Item item = itemInSlot.getItem();
                if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock)
                    shulker = itemInSlot;
                else if (item instanceof ModDyeItem)
                    dye = (ModDyeItem) item;
            }
        }
       /* int otherSlot = 0;

        if (input.getItem(0).getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock)
        {
            shulker = input.getItem(0);
            otherSlot = 1;
        }
        else shulker = input.getItem(1); //there should only be two items. If the box isn't the first checked, it must be the other
        dye = (ModDyeItem) input.getItem(otherSlot).getItem();*/

        ItemLike output = ModShulkerBoxBlock.getDyedItemStack(dye.getDyeColor()).getItem();
        return shulker.transmuteCopy(output, 1);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height > 1; //should this just return true? If there aren't at least 2 slots it isn't a crafting grid lol
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.SHULKER_BOX.get();
    }
}
