package net.avengingcondor.dyemod;

import net.avengingcondor.dyemod.block.ModBlocks;
import net.avengingcondor.dyemod.entity.ModBlockEntities;
import net.avengingcondor.dyemod.entity.ModEntities;
import net.avengingcondor.dyemod.item.ModCreativeModeTabs;
import net.avengingcondor.dyemod.item.ModItems;
import net.avengingcondor.dyemod.render.ModBedBlockEntityRenderer;
import net.avengingcondor.dyemod.render.ModSheepEntityRenderer;
import net.avengingcondor.dyemod.render.ModShulkerBoxBlockEntityRenderer;
import net.avengingcondor.dyemod.util.BedItemApplyRenderer;
import net.avengingcondor.dyemod.util.ShulkerBoxItemApplyRenderer;
import net.avengingcondor.dyemod.util.ModDyeColor;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DyeMod.MOD_ID)
public class DyeMod
{
    public static final String MOD_ID = "condordyemod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Map<Integer, Material> SHULKER_MATERIAL_MAP = new HashMap<>();
    public static final Map<Integer, Material> BED_MATERIAL_MAP = new HashMap<>();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public DyeMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModEntities.ENTITY_TYPE.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerBlockEntityRenderer(
                    ModBlockEntities.SHULKER_BOX.get(),
                    ModShulkerBoxBlockEntityRenderer::new
            );
            event.registerBlockEntityRenderer(
                    ModBlockEntities.BED.get(),
                    ModBedBlockEntityRenderer::new
            );
            event.registerEntityRenderer(
                    ModEntities.SHEEP.get(),
                    ModSheepEntityRenderer::new
            );
        }

        @SubscribeEvent
        public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
            event.registerItem(
                    new ShulkerBoxItemApplyRenderer(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("crimson").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("amber").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("vermilion").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("chartreuse").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("jade").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("olive").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("light_green").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("light_brown").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("teal").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("turquoise").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("azure").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("indigo").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("mauve").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("fuchsia").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("burgundy").asItem(),
                    ModBlocks.DYED_BLOCKS.get("shulker_box").get("coral").asItem()
            );
            event.registerItem(
                    new BedItemApplyRenderer(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("crimson").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("amber").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("vermilion").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("chartreuse").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("jade").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("olive").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("light_green").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("light_brown").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("teal").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("turquoise").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("azure").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("indigo").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("mauve").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("fuchsia").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("burgundy").asItem(),
                    ModBlocks.DYED_BLOCKS.get("bed").get("coral").asItem()
            );
        }

        @SubscribeEvent
        public static void onEntityAttributeCreate(EntityAttributeCreationEvent event) {
            event.put(ModEntities.SHEEP.get(), Sheep.createAttributes().build());
        }

        @SubscribeEvent
        public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
            event.register(ModEntities.SHEEP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (animal, worldIn, reason, pos, random) -> false, RegisterSpawnPlacementsEvent.Operation.OR);
        }

        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event) {
            for (ModDyeColor color : ModDyeColor.newDyeValues()) {
                SHULKER_MATERIAL_MAP.put(color.getId(), new Material(Sheets.SHULKER_SHEET, ResourceLocation.fromNamespaceAndPath(MOD_ID, "entity/shulker/shulker_" + color.getSerializedName())));
            }
            for (ModDyeColor color : ModDyeColor.newDyeValues()) {
                BED_MATERIAL_MAP.put(color.getId(), new Material(Sheets.BED_SHEET, ResourceLocation.fromNamespaceAndPath(MOD_ID, "entity/bed/bed_" + color.getSerializedName())));
            }
        }
    }
}
